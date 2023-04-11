package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.megalab.selim_trade.dto.ReviewListItemResponse;
import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.repository.projections.ReviewView;
import kg.megalab.selim_trade.service.ReviewService;
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
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://161.35.29.179"}, allowCredentials = "true")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create new review")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewListItemResponse createReview(
            @RequestParam MultipartFile image,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String gate,
            @AuthenticationPrincipal UserDetails adminDetails
    ) throws IOException {
        return reviewService.createReview(image, name, text, gate, adminDetails);
    }

    @SecurityRequirements
    @GetMapping("/short")
    public Page<ReviewView> getAllReviewsForCustomer(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return reviewService.getAllReviewsForCustomer(pageNo, pageSize, sortBy);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping
    public Page<ReviewListItemResponse> getAllReviewsForAdmin(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return reviewService.getAllReviewsForAdmin(pageNo, pageSize, sortBy);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{reviewId}")
    public ReviewResponse getReviewById(@PathVariable("reviewId") int id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping(value = "/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ReviewResponse updateReview(
            @PathVariable("reviewId") int id,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam String name,
            @RequestParam String text,
            @RequestParam String gate,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return reviewService.updateReview(id, image, name, text, gate, adminDetails);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewById(@PathVariable("reviewId") int id) throws IOException {
        reviewService.deleteReviewById(id);
    }

}
