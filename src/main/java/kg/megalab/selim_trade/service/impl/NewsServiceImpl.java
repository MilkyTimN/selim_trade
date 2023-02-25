package kg.megalab.selim_trade.service.impl;

import jakarta.annotation.PostConstruct;
import kg.megalab.selim_trade.dto.NewOrUpdateNewsRequest;
import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.entity.Picture;
import kg.megalab.selim_trade.exceptions.NotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.NewsMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.repository.NewsRepository;
import kg.megalab.selim_trade.repository.PictureRepository;
import kg.megalab.selim_trade.repository.projections.NewsItemProjection;
import kg.megalab.selim_trade.repository.projections.NewsListProjection;
import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final PictureRepository pictureRepository;
    private final AdminRepository adminRepository;

    @Value("${file.upload-dir}")
    private String image_folder;
    private Picture defaultPicture;

    @PostConstruct
    void setUp() {
        String urlOfDefaultPicture = image_folder + "/" + "news.jpg";
        if (pictureRepository.existsByUrl(urlOfDefaultPicture)) {
            defaultPicture = pictureRepository.findByUrl(urlOfDefaultPicture)
                    .orElseThrow(() -> new NotFoundException("Image not found!"));
        } else {
            defaultPicture = pictureRepository.save(
                    new Picture(urlOfDefaultPicture)
            );
        }
    }

    @Override
    public NewsItemProjection getNewsById(int id) {
        return newsRepository.findNewsById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public Page<NewsListProjection> getAllNewses(Pageable pageable) {
        return newsRepository.findAllProjectedBy(pageable);
    }

    @Override
    public NewOrUpdateNewsResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException {
        News news = new News();

        if(image == null || image.isEmpty()) {
            news.setPicture(defaultPicture);
        } else {
            String resultFileName = UUID.randomUUID().toString()+"."+image.getOriginalFilename();
            String resultUrl = image_folder + "/" + resultFileName;
            image.transferTo(new File(resultUrl));
            Picture picture = new Picture();
            picture.setUrl(resultUrl);
            pictureRepository.save(picture);
            news.setPicture(picture);
        }
        news.setAdmin(adminRepository.findByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new));



        return newsMapper.toNewOrUpdatedResponseDto(newsRepository.save(news));
    }
}
