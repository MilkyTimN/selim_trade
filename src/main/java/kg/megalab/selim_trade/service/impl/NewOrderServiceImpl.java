package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewOrderRequest;
import kg.megalab.selim_trade.dto.NewOrderResponse;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.NewOrderMapper;
import kg.megalab.selim_trade.repository.NewOrderRepository;
import kg.megalab.selim_trade.service.NewOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewOrderServiceImpl implements NewOrderService {

    private final NewOrderRepository newOrderRepository;
    private final NewOrderMapper newOrderMapper;


    @Override
    @Transactional
    public NewOrderResponse saveOrder(NewOrderRequest newOrderRequest) {

        return newOrderMapper.toDto(newOrderRepository.save(newOrderMapper.toModel(newOrderRequest)));
    }

    @Override
    public void deleteNewOrder(int id) {
        newOrderRepository.deleteById(id);
    }

    @Override
    public Page<NewOrderResponse> getAllNewOrders(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return newOrderRepository.findAll(paging).map(newOrderMapper::toDto);
    }

    @Override
    public NewOrderResponse getNewOrderById(int id) {
        return newOrderRepository.findById(id).map(newOrderMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("New Order item not found!"));
    }
}
