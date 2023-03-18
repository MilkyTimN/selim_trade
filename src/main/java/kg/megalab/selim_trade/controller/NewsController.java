package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class NewsController {

    private final NewsService newsService;

    @SecurityRequirements
    @GetMapping("/{newsId}")
    public NewsResponse getNewsById(@PathVariable("newsId") int id) {
        return newsService.getNewsById(id);
    }

    @SecurityRequirements
    @GetMapping
    public Page<NewsResponse> getAllNewses(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return newsService.getAllNews(pageNo, pageSize, sortBy);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponse createNews(@RequestParam(value = "image") MultipartFile image,
                                   @RequestParam("title") String title,
                                   @RequestParam("description") String description,
                                   @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return newsService.createNews(image, title, description, adminDetails);

    }

    @PutMapping(value = "/{newsId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NewsResponse updateNews(@PathVariable("newsId") int id,
                                   @RequestParam("image") MultipartFile image,
                                   @RequestParam("title") String title,
                                   @RequestParam("description") String description,
                                   @AuthenticationPrincipal UserDetails adminDetails)
            throws IOException {
        return newsService.updateNews(id, image, title, description, adminDetails);
    }

    @DeleteMapping("/{newsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable("id") int id) throws IOException {
        newsService.deleteNews(id);
    }
}
