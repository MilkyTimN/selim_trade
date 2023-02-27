package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.repository.ProductRepository;
import kg.megalab.selim_trade.repository.projections.ProductItemProjection;
import kg.megalab.selim_trade.repository.projections.ProductListProjection;
import kg.megalab.selim_trade.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public ProductItemProjection getProductById(Integer id) {
        return repository.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public Page<ProductListProjection> getAll(Pageable pageable) {
        return repository.findAllProjectedBy(pageable);
    }
}
