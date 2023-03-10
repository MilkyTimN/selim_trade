package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository methods for the {@link Review} domain entity
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
