package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.entity.Review;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.ReviewMapper;
import kg.megalab.selim_trade.repository.ReviewRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final ReviewMapper reviewMapper;
    @Value("${home.dir}")
    private String home_dir;


    @Override
    public ReviewResponse createReview(MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException {
        //saving photo to file system
        String photoUrl = imageService.saveImageToFileSystem(image);

        Review review = new Review();
        review.setPhotoUrl(photoUrl);
        review.setName(name);
        review.setText(text);
        review.setGate(gate);
        review.setCreated_date(new Date());

        review.setCreatedBy(
                authService.findAdminByUsername(adminDetails.getUsername())
        );

        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    public Page<ReviewResponse> getAllReviews(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return reviewRepository.findAll(paging).map(reviewMapper::toDto);
    }

    @Override
    public ReviewResponse getReviewById(int id) {
        return reviewMapper.toDto(
                reviewRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Review not found!")));
    }

    @Override
    public ReviewResponse updateReview(int id, MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException {
        Review updatingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found!"));

        // previous photo from file system
        Files.deleteIfExists(Path.of(home_dir + updatingReview.getPhotoUrl()));

        String photoUrl = imageService.saveImageToFileSystem(image);

        updatingReview.setPhotoUrl(photoUrl);
        updatingReview.setName(name);
        updatingReview.setText(text);
        updatingReview.setGate(gate);
        updatingReview.setUpdated_date(new Date());

        updatingReview.getUpdatedBy().add(
                authService.findAdminByUsername(adminDetails.getUsername())
        );

        return reviewMapper.toDto(reviewRepository.save(updatingReview));
    }

    @Override
    public void deleteReviewById(int id) throws IOException {
        Review deletingReview = reviewRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Review not found!"));

        //deleting photo from file system
        Files.deleteIfExists(Path.of(home_dir + deletingReview.getPhotoUrl()));

        reviewRepository.delete(deletingReview);
    }
}
