package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.UpdatedBy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdatedByRepository extends JpaRepository<UpdatedBy, Long> {
}
