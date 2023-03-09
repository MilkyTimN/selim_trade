package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.entity.Review;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.ReviewMapper;
import kg.megalab.selim_trade.repository.ReviewRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.ImageService;
import kg.megalab.selim_trade.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final ReviewMapper reviewMapper;


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
                        .orElseThrow(UserNotFoundException::new)
        );

        return reviewMapper.toDto(reviewRepository.save(review));
    }
}
