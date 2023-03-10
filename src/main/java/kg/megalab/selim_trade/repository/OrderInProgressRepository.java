package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.OrderInProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInProgressRepository extends JpaRepository<OrderInProgress, Integer> {
}
