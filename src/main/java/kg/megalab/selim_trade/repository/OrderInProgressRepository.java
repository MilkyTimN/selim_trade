package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.OrderInProgress;
import kg.megalab.selim_trade.entity.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInProgressRepository extends JpaRepository<OrderInProgress, Integer> {
    Page<OrderInProgress> findAllByStatus(EStatus status);
}
