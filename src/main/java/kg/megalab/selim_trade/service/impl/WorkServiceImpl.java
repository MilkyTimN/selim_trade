package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.WorkResponse;
import kg.megalab.selim_trade.entity.Work;
import kg.megalab.selim_trade.mapper.WorkMapper;
import kg.megalab.selim_trade.repository.WorkRepository;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkService workService;
    private final ImageService imageService;
    private final WorkMapper workMapper;
    private final WorkRepository workRepository;
    @Override
    public WorkResponse createWork(MultipartFile image) throws IOException {
        //saving the photo to the file system
        String photoUrl = imageService.saveImageToFileSystem(image);

        Work work = new Work();
        work.setPhotoUrl(photoUrl);
        work.setCreated_date(new Date());

        return workMapper.toDto(workRepository.save(work));
    }
}
