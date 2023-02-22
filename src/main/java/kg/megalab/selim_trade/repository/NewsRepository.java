package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.repository.projections.NewsItemProjection;
import kg.megalab.selim_trade.repository.projections.NewsListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {

    Optional<NewsItemProjection> findNewsById(Integer id);

    Page<NewsListProjection> findAllProjectedBy(Pageable pageable);
}
