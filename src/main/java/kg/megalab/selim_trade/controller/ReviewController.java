package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(
            @RequestParam MultipartFile image,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String gate,
            @AuthenticationPrincipal UserDetails adminDetails
            ) throws IOException {
        return reviewService.createReview(image,name,text,gate,adminDetails);
    }

    @GetMapping
    public Page<ReviewResponse> getAllReviews(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return reviewService.getAllReviews(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public ReviewResponse getReviewById(@PathVariable("id") int id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping("/{id}")
    public ReviewResponse updateReview(
            @PathVariable("id") int id,
            @RequestParam MultipartFile image,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String gate,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return reviewService.updateReview(id, image, name, text, gate, adminDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable("id") int id) throws IOException {
        reviewService.deleteReviewById(id);
    }

}
