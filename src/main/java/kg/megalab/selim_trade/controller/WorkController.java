package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.WorkResponse;
import kg.megalab.selim_trade.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/work")
public class WorkController {
    private final WorkService workService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkResponse createWork(@RequestBody MultipartFile image) throws IOException {
        return workService.createWork(image);
    }

    @GetMapping
    public Page<WorkResponse> getAllWorks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return workService.getAllWorks(pageNo,pageSize,sortBy);
    }

    @GetMapping("/{id}")
    public WorkResponse getWorkById(@PathVariable("id") int id) {
        return workService.getWorkById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkById(@PathVariable("id") int id) throws IOException {
        workService.deleteWorkById(id);
    }
}
