package kg.megalab.selim_trade.repository;

import kg.megalab.selim_trade.entity.Product;
import kg.megalab.selim_trade.repository.projections.ProductItemProjection;
import kg.megalab.selim_trade.repository.projections.ProductListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<ProductItemProjection> findProductById(Integer id);

    Page<ProductListProjection> findAllProjectedBy(Pageable pageable);
}
