package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.repository.projections.ProductItemProjection;
import kg.megalab.selim_trade.repository.projections.ProductListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductItemProjection getProductById(Integer id);

    Page<ProductListProjection> getAll(Pageable pageable);
}
