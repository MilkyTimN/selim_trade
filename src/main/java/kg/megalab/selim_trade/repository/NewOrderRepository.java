package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.NewOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository methods for the {@link NewOrder} domain entity
 */

@Repository
public interface NewOrderRepository extends CrudRepository<NewOrder, Integer> {

}
