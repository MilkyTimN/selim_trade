package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewsListItemResponse;
import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.entity.UpdatedBy;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.NewsMapper;
import kg.megalab.selim_trade.repository.NewsRepository;
import kg.megalab.selim_trade.repository.projections.NewsItemView;
import kg.megalab.selim_trade.repository.projections.NewsListView;
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
    public NewsListItemResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException {
        News news = new News();

        String resultUrl = imageService.saveImageToFileSystem(image);
        news.setPhotoUrl(resultUrl);

        news.setTitle(title);
        news.setDescription(description);
        news.setCreatedBy(authService.findAdminByUsername(adminDetails.getUsername()));


        return newsMapper.toShortNewsDto(newsRepository.save(news));
    }

    @Override
    public NewsListItemResponse updateNews(int id, MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException {

        News news = newsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("News not found!")
        );

        if (!(image == null || image.isEmpty())) {
            Files.deleteIfExists(Path.of(home_dir + news.getPhotoUrl()));
            String resultUrl = imageService.saveImageToFileSystem(image);
            news.setPhotoUrl(resultUrl);
        }
        news.setTitle(title);
        news.setDescription(description);

        news.getUpdatedByList().add(
                updatedByService.save(
                        new UpdatedBy((Admin) adminDetails, new Date())
                )
        );

        return newsMapper.toShortNewsDto(newsRepository.save(news));
    }

    @Override
    public void deleteNews(int id) throws IOException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News is not found!"));

        //deleting previous photo from file system
        Files.deleteIfExists(Path.of(home_dir + news.getPhotoUrl()));

        news.getPhotos().forEach(
                newsPhoto -> {
                    try {
                        Files.deleteIfExists(Path.of(home_dir + newsPhoto.getPhotoUrl()));
                    } catch (IOException e) {
                        throw new ResourceNotFoundException("No such photo");
                    }
                }
        );

        newsRepository.deleteById(id);
    }

    @Override
    public Page<NewsListView> getAllNewsForCustomer(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return newsRepository.findAllProjectedBy(pageable);
    }


    @Override
    public Page<NewsListItemResponse> getAllNewsShort(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return newsRepository.findAll(pageable).map(newsMapper::toShortNewsDto);
    }

    @Override
    public NewsItemView getNewsByIdForCustomer(int newsId) {
        return newsRepository.findProjectedById(newsId).orElseThrow(
                () -> new ResourceNotFoundException("News not found!")
        );
    }
}
