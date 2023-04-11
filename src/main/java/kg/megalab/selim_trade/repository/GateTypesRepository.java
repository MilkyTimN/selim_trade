package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.repository.projections.GateTypeItemView;
import kg.megalab.selim_trade.repository.projections.GateTypesListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GateTypesRepository extends JpaRepository<GateType, Integer> {
    Page<GateTypesListView> findAllProjectedBy(Pageable pageable);

    Optional<GateTypeItemView> findProjectedById(int id);

}
