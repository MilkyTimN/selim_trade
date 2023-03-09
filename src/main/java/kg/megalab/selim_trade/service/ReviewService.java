package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReviewService {

    ReviewResponse createReview(MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException;

    Page<ReviewResponse> getAllReviews(int pageNo, int pageSize, String sortBy);

    ReviewResponse getReviewById(int id);

    ReviewResponse updateReview(int id, MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException;

    void deleteReviewById(int id) throws IOException;
}
