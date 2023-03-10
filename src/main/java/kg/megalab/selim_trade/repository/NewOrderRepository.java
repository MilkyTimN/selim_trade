package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.NewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository methods for the {@link NewOrder} domain entity
 */

@Repository
public interface NewOrderRepository extends JpaRepository<NewOrder, Integer> {

}
