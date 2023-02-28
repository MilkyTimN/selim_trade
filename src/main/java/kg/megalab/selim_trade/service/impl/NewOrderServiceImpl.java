package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.dto.OrderResponse;
import kg.megalab.selim_trade.mapper.OrderMapper;
import kg.megalab.selim_trade.repository.NewOrderRepository;
import kg.megalab.selim_trade.service.NewOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewOrderServiceImpl implements NewOrderService {

    private final NewOrderRepository newOrderRepository;
    private final OrderMapper mapper;


    @Override
    @Transactional
    public OrderResponse saveOrder(OrderRequest orderRequest) {

        return mapper.toDto(newOrderRepository.save(mapper.toModel(orderRequest)));
    }

    @Override
    public void deleteNewOrder(int id) {
        newOrderRepository.deleteById(id);
    }
}
