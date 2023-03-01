package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/get/{id}")
    public NewOrUpdateNewsResponse getNewsById(@PathVariable("id") int id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/get/all")
    public Page<NewOrUpdateNewsResponse> getAllNewses(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return newsService.getAllNews(pageNo, pageSize, sortBy);
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public NewOrUpdateNewsResponse createNews(@RequestParam(value = "image", required = false) MultipartFile image,
                                              @RequestParam("title") String title,
                                              @RequestParam("description") String description,
                                              @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return newsService.createNews(image, title, description, adminDetails);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NewOrUpdateNewsResponse updateNews(@PathVariable("id") int id,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("title") String title,
                                              @RequestParam("description") String description,
                                              @AuthenticationPrincipal UserDetails adminDetails)
            throws IOException {
        return newsService.updateNews(id, image, title, description, adminDetails);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable("id") int id) throws IOException {
        newsService.deleteNews(id);
    }
}
