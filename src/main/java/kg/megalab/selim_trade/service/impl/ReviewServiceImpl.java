package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.ReviewListItemResponse;
import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.Review;
import kg.megalab.selim_trade.entity.UpdatedBy;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.ReviewMapper;
import kg.megalab.selim_trade.repository.ReviewRepository;
import kg.megalab.selim_trade.repository.projections.ReviewView;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.ReviewService;
import kg.megalab.selim_trade.service.UpdatedByService;
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
    private final UpdatedByService updatedByService;
    @Value("${home.dir}")
    private String home_dir;


    @Override
    public ReviewListItemResponse createReview(MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException {
        //saving photo to file system
        String photoUrl = imageService.saveImageToFileSystem(image);

        Review review = new Review();
        review.setPhotoUrl(photoUrl);
        review.setName(name);
        review.setText(text);
        review.setGate(gate);

        review.setCreatedBy(
                authService.findAdminByUsername(adminDetails.getUsername())
        );

        return reviewMapper.toShortDto(reviewRepository.save(review));
    }


    @Override
    public ReviewResponse getReviewById(int id) {
        return reviewMapper.toDto(
                reviewRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Review not found!")));
    }

    //TODO: return types of post methods
    //TODO: Check if two sql for customers

    @Override
    public ReviewResponse updateReview(int id, MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException {
        Review updatingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found!"));

        // previous photo from file system
        if (!(image == null || image.isEmpty())) {
            Files.deleteIfExists(Path.of(home_dir + updatingReview.getPhotoUrl()));
            String photoUrl = imageService.saveImageToFileSystem(image);

            updatingReview.setPhotoUrl(photoUrl);
        }

        updatingReview.setName(name);
        updatingReview.setText(text);
        updatingReview.setGate(gate);
        updatingReview.getUpdatedByList().add(
                updatedByService.save(
                        new UpdatedBy((Admin) adminDetails, new Date())
                )
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

    @Override
    public Page<ReviewView> getAllReviewsForCustomer(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return reviewRepository.findAllProjectedBy(pageable);
    }

    @Override
    public Page<ReviewListItemResponse> getAllReviewsForAdmin(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return reviewRepository.findAll(pageable).map(reviewMapper::toShortDto);
    }
}
