package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.repository.projections.NewsItemView;
import kg.megalab.selim_trade.repository.projections.NewsListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Page<NewsListView> findAllProjectedBy(Pageable pageable);

    Optional<NewsItemView> findProjectedById(int id);
}

