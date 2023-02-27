package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.dto.OrderResponse;
import kg.megalab.selim_trade.mapper.OrderMapper;
import kg.megalab.selim_trade.repository.OrderRepository;
import kg.megalab.selim_trade.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;


    @Override
    @Transactional
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        return mapper.toDto(repository.save(mapper.toModel(orderRequest)));
    }
}
