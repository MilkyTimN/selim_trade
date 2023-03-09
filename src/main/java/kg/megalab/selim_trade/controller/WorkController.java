package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.WorkResponse;
import kg.megalab.selim_trade.service.WorkService;
import lombok.RequiredArgsConstructor;
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
}
