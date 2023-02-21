package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.repository.ReviewRepository;
import kg.megalab.selim_trade.repository.projections.ReviewProjection;
import kg.megalab.selim_trade.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;


    @Override
    public Page<ReviewProjection> getReviews(Pageable pageable) {
        return repository.findAllProjectedBy(pageable);
    }
}
