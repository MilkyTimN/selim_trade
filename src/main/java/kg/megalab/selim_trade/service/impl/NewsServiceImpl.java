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
    public NewOrUpdateNewsResponse createNews(NewOrUpdateNewsRequest request, UserDetails adminDetails) {
        News news = newsMapper.toModel(request);

        if(request.image() == null || request.image().isEmpty()) {
            news.setPicture(defaultPicture);
        }
        news.setAdmin(adminRepository.findByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new));



        return newsMapper.toNewOrUpdatedResponseDto(newsRepository.save(news));
    }
}
