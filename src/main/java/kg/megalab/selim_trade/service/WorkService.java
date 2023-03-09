package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.WorkResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WorkService {
    WorkResponse createWork(MultipartFile image) throws IOException;
}
