package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.repository.projections.ReviewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Page<ReviewProjection> getReviews(Pageable pageable);
}
