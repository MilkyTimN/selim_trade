package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Advantage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvantageRepository extends JpaRepository<Advantage, Integer> {
}
