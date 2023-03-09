package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.ReviewResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReviewService {

    ReviewResponse createReview(MultipartFile image, String name, String text, String gate, UserDetails adminDetails) throws IOException;
}
