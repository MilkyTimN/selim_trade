package kg.megalab.selim_trade.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveImageToFileSystem(MultipartFile image) throws IOException;
}
