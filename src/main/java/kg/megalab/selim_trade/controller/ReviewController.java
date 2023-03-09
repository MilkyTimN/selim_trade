package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewResponse createReview(
            @RequestParam MultipartFile image,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String gate,
            @AuthenticationPrincipal UserDetails adminDetails
            ) {
        return reviewService.createReview(image,name,text,gate,adminDetails);
    }

}
