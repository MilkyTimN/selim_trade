package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.megalab.selim_trade.dto.WorkResponse;
import kg.megalab.selim_trade.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/work")
@CrossOrigin(origins = {"http://localhost:3000", "http://161.35.29.179"}, allowCredentials = "true")
public class WorkController {
    private final WorkService workService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkResponse createWork(@RequestParam("image") MultipartFile image) throws IOException {
        return workService.createWork(image);
    }

    @SecurityRequirements
    @GetMapping
    public Page<WorkResponse> getAllWorks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return workService.getAllWorks(pageNo, pageSize, sortBy);
    }

    @SecurityRequirements
    @GetMapping("/{workId}")
    public WorkResponse getWorkById(@PathVariable("workId") int id) {
        return workService.getWorkById(id);
    }

    @DeleteMapping("/{workId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkById(@PathVariable("workId") int id) throws IOException {
        workService.deleteWorkById(id);
    }
}
