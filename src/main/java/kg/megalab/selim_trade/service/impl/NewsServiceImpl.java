package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.entity.UpdatedBy;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.NewsMapper;
import kg.megalab.selim_trade.repository.NewsRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.NewsService;
import kg.megalab.selim_trade.service.UpdatedByService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final AuthService authService;
    private final ImageService imageService;
    private final UpdatedByService updatedByService;

    @Value("${home.dir}")
    private String home_dir;

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

        String resultUrl = imageService.saveImageToFileSystem(image);
        news.setPhotoUrl(resultUrl);

        news.setTitle(title);
        news.setDescription(description);
        news.setCreatedBy(authService.findAdminByUsername(adminDetails.getUsername()));


        return newsMapper.toNewOrUpdatedResponse(newsRepository.save(news));
    }

    @Override
    public NewsResponse updateNews(int id, MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException {

        News news = newsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("News not found!")
        );

        Files.deleteIfExists(Path.of(home_dir + news.getPhotoUrl()));
        String resultUrl = imageService.saveImageToFileSystem(image);

        news.setPhotoUrl(resultUrl);
        news.setTitle(title);
        news.setDescription(description);
//        news.getUpdatedBy().add(
//                authService.findAdminByUsername(adminDetails.getUsername())
//        );


        news.getUpdatedByList().add(
                updatedByService.save(
                        new UpdatedBy(adminDetails.getUsername(), new Date())
                )
        );


        return newsMapper.toNewOrUpdatedResponse(newsRepository.save(news));
    }

    @Override
    public void deleteNews(int id) throws IOException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News is not found!"));

        //deleting previous photo from file system
        Files.deleteIfExists(Path.of(home_dir + news.getPhotoUrl()));

        newsRepository.deleteById(id);
    }
}
