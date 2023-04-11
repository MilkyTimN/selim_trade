package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.NewsPhotoResponse;
import kg.megalab.selim_trade.service.NewsPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class NewsContentPhotoController {
    private final NewsPhotoService newsPhotoService;

    @PostMapping(value = "/{newsId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NewsPhotoResponse createNewsPhoto(@PathVariable("newsId") int newsId,
                                             @RequestParam("photo") MultipartFile photo) throws IOException {
        return newsPhotoService.createNewsPhoto(newsId, photo);
    }

    @DeleteMapping("/{newsPhotoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhotoOfNews(@PathVariable("newsPhotoId") int newsPhotoId) throws IOException {
        newsPhotoService.deleteNewsPhoto(newsPhotoId);
    }
}
