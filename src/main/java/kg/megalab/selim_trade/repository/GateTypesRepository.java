package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.GateTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateTypesRepository extends JpaRepository<GateTypes, Integer> {
}
