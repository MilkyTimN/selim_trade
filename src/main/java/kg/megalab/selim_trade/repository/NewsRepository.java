package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
//    @Modifying
//    @Query(value = "DELETE FROM news WHERE id = ?1", nativeQuery = true)
//    void deleteNewsById(int id);
}

