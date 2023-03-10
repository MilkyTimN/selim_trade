package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateRepository extends JpaRepository<Gate, Integer> {
}
