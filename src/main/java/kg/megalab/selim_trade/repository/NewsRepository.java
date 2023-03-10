package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
//    @Modifying
//    @Query(value = "DELETE FROM news WHERE id = ?1", nativeQuery = true)
//    void deleteNewsById(int id);
}

