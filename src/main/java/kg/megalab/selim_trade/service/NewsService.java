package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.repository.projections.NewsItemProjection;
import kg.megalab.selim_trade.repository.projections.NewsListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {

    NewsItemProjection getNewsById(int id);

    Page<NewsListProjection> getAllNewses(Pageable pageable);
}
