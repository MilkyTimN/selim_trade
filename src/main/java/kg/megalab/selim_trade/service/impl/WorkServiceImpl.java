package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.WorkResponse;
import kg.megalab.selim_trade.entity.Work;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.WorkMapper;
import kg.megalab.selim_trade.repository.WorkRepository;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final ImageService imageService;
    private final WorkMapper workMapper;
    private final WorkRepository workRepository;
    @Value("${home.dir}")
    private String home_dir;

    @Override
    public WorkResponse createWork(MultipartFile image) throws IOException {
        //saving the photo to the file system
        String photoUrl = imageService.saveImageToFileSystem(image);

        Work work = new Work();
        work.setPhotoUrl(photoUrl);
        work.setCreated_date(new Date());

        return workMapper.toDto(workRepository.save(work));
    }

    @Override
    public Page<WorkResponse> getAllWorks(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return workRepository.findAll(paging).map(workMapper::toDto);
    }

    @Override
    public WorkResponse getWorkById(int id) {
        return workMapper.toDto(
                workRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Work not found!"))
        );
    }

    @Override
    public void deleteWorkById(int id) throws IOException {
        Work deletingWork = workRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work not found!"));

        //deleting previous photo from file system
        Files.deleteIfExists(Path.of(home_dir + deletingWork.getPhotoUrl()));

        workRepository.delete(deletingWork);
    }
}
