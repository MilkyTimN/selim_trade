package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.NewsPhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NewsPhotoService {
    NewsPhotoResponse createNewsPhoto(int newsId, MultipartFile photo) throws IOException;

    void deleteNewsPhoto(int newsPhotoId) throws IOException;
}
