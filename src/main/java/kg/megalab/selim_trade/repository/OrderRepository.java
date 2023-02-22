package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository methods for the {@link Order} domain entity
 */

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {


}
