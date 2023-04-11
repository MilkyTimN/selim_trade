package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewsPhotoResponse;
import kg.megalab.selim_trade.entity.NewsPhoto;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.NewsPhotoMapper;
import kg.megalab.selim_trade.repository.NewsPhotoRepository;
import kg.megalab.selim_trade.repository.NewsRepository;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.NewsPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//TODO:return types password

@Service
@RequiredArgsConstructor
public class NewsPhotoServiceImpl implements NewsPhotoService {
    private final NewsPhotoRepository newsPhotoRepository;
    private final NewsRepository newsRepository;
    private final ImageService imageService;
    private final NewsPhotoMapper newsPhotoMapper;
    @Value("${home.dir}")
    private String home_dir;

    @Override
    public NewsPhotoResponse createNewsPhoto(int newsId, MultipartFile photo) throws IOException {
        NewsPhoto newsPhoto = new NewsPhoto();
        newsPhoto.setNews(
                newsRepository.findById(newsId).orElseThrow(
                        () -> new ResourceNotFoundException("News not found!")
                )
        );
        String resultPhotoUrl = imageService.saveImageToFileSystem(photo);
        newsPhoto.setPhotoUrl(resultPhotoUrl);
        return newsPhotoMapper.toDto(newsPhotoRepository.save(newsPhoto));
    }

    @Override
    public void deleteNewsPhoto(int newsPhotoId) throws IOException {
        NewsPhoto newsPhoto = newsPhotoRepository.findById(newsPhotoId)
                .orElseThrow(() -> new ResourceNotFoundException("No such photo!"));
        Files.deleteIfExists(Path.of(home_dir + newsPhoto.getPhotoUrl()));
        newsPhotoRepository.deleteById(newsPhotoId);
    }
}
