package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.megalab.selim_trade.dto.NewsListItemResponse;
import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.repository.projections.NewsItemView;
import kg.megalab.selim_trade.repository.projections.NewsListView;
import kg.megalab.selim_trade.service.NewsPhotoService;
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
@CrossOrigin(origins = {"http://localhost:3000", "http://161.35.29.179"}, allowCredentials = "true")
public class NewsController {

    private final NewsService newsService;
    private final NewsPhotoService newsPhotoService;

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{newsId}")
    public NewsResponse getNewsById(@PathVariable("newsId") int newsId) {
        return newsService.getNewsById(newsId);
    }

    @SecurityRequirements
    @GetMapping("/short/{newsId}")
    public NewsItemView getNewsByIdForCustomer(@PathVariable("newsId") int newsId) {
        return newsService.getNewsByIdForCustomer(newsId);
    }

    @GetMapping("/short")
    @SecurityRequirements
    public Page<NewsListView> getAllNewsForCustomer(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return newsService.getAllNewsForCustomer(pageNo, pageSize, sortBy);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public Page<NewsListItemResponse> getAllNews(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        return newsService.getAllNewsShort(pageNo, pageSize, sortBy);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NewsListItemResponse createNews(@RequestParam(value = "image") MultipartFile image,
                                           @NotNull @NotBlank @RequestParam("title") String title,
                                           @NotNull @NotBlank @RequestParam("description") String description,
                                           @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return newsService.createNews(image, title, description, adminDetails);

    }


    @PutMapping(value = "/{newsId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public NewsListItemResponse updateNews(@PathVariable("newsId") int id,
                                           @RequestParam(value = "image", required = false) MultipartFile image,
                                           @NotNull @NotBlank @RequestParam("title") String title,
                                           @NotNull @NotBlank @RequestParam("description") String description,
                                           @AuthenticationPrincipal UserDetails adminDetails)
            throws IOException {
        return newsService.updateNews(id, image, title, description, adminDetails);
    }

    @DeleteMapping("/{newsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable("newsId") int id) throws IOException {
        newsService.deleteNews(id);
    }

}
