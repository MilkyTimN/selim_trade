package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.NewsMapper;
import kg.megalab.selim_trade.repository.NewsRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final AuthService authService;

    @Value("${file.upload-dir}")
    private String image_folder;
    @Value("${default_photo_name}")
    private String defaultPhoto;


    @Override
    public NewsResponse getNewsById(int id) {
        return newsRepository.findById(id).map(newsMapper::toNewOrUpdatedResponse)
                .orElseThrow(() -> new ResourceNotFoundException("The news not found!"));
    }

    @Override
    public String getFileOriginalName(String url) {
        return url != null ? url.substring(url.indexOf(".") + 1) : null;
    }

    @Override
    public Page<NewsResponse> getAllNews(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return newsRepository.findAll(paging).map(newsMapper::toNewOrUpdatedResponse);
    }


    @Override
    public NewsResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException {
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
        news.setAdmin(authService.findAdminByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new));


        return newsMapper.toNewOrUpdatedResponse(newsRepository.save(news));
    }

    @Override
    public NewsResponse updateNews(int id, MultipartFile image, String title, String description, UserDetails adminDetails) {
        return newsRepository.findById(id).map(news -> {
            if (!news.getPhotoUrl().equals(getFileOriginalName(image.getOriginalFilename()))) {
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
            news.setAdmin(authService.findAdminByUsername(adminDetails.getUsername())
                    .orElseThrow(UserNotFoundException::new));


            return newsMapper.toNewOrUpdatedResponse(newsRepository.save(news));
        }).orElseThrow(() -> new ResourceNotFoundException("News not found!"));
    }

    @Override
    public void deleteNews(int id) throws IOException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News is not found!"));

        //deleting previous photo from file system
        Files.delete(Path.of(news.getPhotoUrl()));

        newsRepository.deleteById(id);
    }
}
