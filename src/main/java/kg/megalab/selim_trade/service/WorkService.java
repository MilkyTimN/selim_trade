package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.WorkResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WorkService {
    WorkResponse createWork(MultipartFile image) throws IOException;

    Page<WorkResponse> getAllWorks(int pageNo, int pageSize, String sortBy);

    WorkResponse getWorkById(int id);

    void deleteWorkById(int id) throws IOException;
}
