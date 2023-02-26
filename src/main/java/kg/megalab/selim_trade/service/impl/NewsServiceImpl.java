package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.exceptions.NotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.NewsMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.repository.NewsRepository;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final AdminRepository adminRepository;

    @Value("${file.upload-dir}")
    private String image_folder;
    @Value("${default_photo_name}")
    private String defaultPhoto;



    @Override
    public News getNewsById(int id) {
        return newsRepository.findNewsById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public String getFileOriginalName(String url) {
        return url != null ? url.substring(url.indexOf(".") + 1) : null;
    }

    @Override
    public Page<News> getAllNewses(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }



    @Override
    public NewOrUpdateNewsResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException {
        News news = new News();

        if (image == null || image.isEmpty()) {
            news.setPhotoUrl(image_folder + "/" + defaultPhoto);
        } else {
            String resultFileName = UUID.randomUUID() + "." + image.getOriginalFilename();
            String resultUrl = image_folder + "/" + resultFileName;
            image.transferTo(new File(resultUrl));
            news.setPhotoUrl(resultUrl);
        }
        news.setTitle(title);
        news.setDescription(description);
        news.setAdmin(adminRepository.findByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new));


        return newsMapper.toNewOrUpdatedResponseDto(newsRepository.save(news));
    }

    @Override
    public NewOrUpdateNewsResponse updateNews(int id, MultipartFile image, String title, String description, UserDetails adminDetails) {
        return newsRepository.findNewsById(id).map(news -> {
            if(!news.getPhotoUrl().equals(getFileOriginalName(image.getOriginalFilename()))) {
                try {
                    Files.deleteIfExists(Path.of(news.getPhotoUrl()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String resultFileName = UUID.randomUUID() + "." + image.getOriginalFilename();
            String resultUrl = image_folder + "/" + resultFileName;
            try {
                image.transferTo(new File(resultUrl));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            news.setPhotoUrl(resultUrl);
            news.setTitle(title);
            news.setDescription(description);
            news.setAdmin(adminRepository.findByUsername(adminDetails.getUsername())
                    .orElseThrow(UserNotFoundException::new));


            return newsMapper.toNewOrUpdatedResponseDto(newsRepository.save(news));
        }).orElseThrow(() -> new NotFoundException("News not found!"));
    }
}
