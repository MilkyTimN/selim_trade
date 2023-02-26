package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.NewOrUpdateNewsRequest;
import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/get")
    ResponseEntity<?> getNewsById(@RequestParam int id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @GetMapping("/get/all")
    ResponseEntity<?> getAllNewses(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(newsService.getAllNewses(pageable));
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public NewOrUpdateNewsResponse createNews(@RequestParam("image") MultipartFile image,
                                              @RequestParam("title") String title,
                                              @RequestParam("description") String description,
                                              @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return newsService.createNews(image, title, description, adminDetails);

    }
}
