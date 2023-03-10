package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewOrderInProgressRequest;
import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.dto.UpdateOrderInProgressRequest;
import kg.megalab.selim_trade.entity.EStatus;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.entity.OrderInProgress;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.OrderInProgressMapper;
import kg.megalab.selim_trade.repository.OrderInProgressRepository;
import kg.megalab.selim_trade.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderInProgressImpl implements OrderInProgressService {
    private final OrderInProgressRepository orderInProgressRepository;
    private final OrderInProgressMapper orderInProgressMapper;
    private final AuthService authService;
    private final GateTypesService gateTypesService;
    private final GateService gateService;
    private final NewOrderService newOrderService;

    @Override
    public OrderInProgressResponse createOrderInProgress(
            int id,
            NewOrderInProgressRequest orderRequest,
            UserDetails adminDetails) {
        OrderInProgress orderInProgress = new OrderInProgress();

        orderInProgress.setCreatedBy(
                authService.findAdminByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new)
        );

        orderInProgress.setStatus(EStatus.IN_PROGRESS);
        orderInProgress.setName(orderRequest.name());
        orderInProgress.setPhoneNumber(orderRequest.phoneNumber());


        GateType gateType = gateTypesService.getGateTypeById(orderRequest.gateTypeId());
        Gate gate = gateService.findGateById(orderRequest.gateId());

        orderInProgress.setGate(gate);
        orderInProgress.setGateType(gateType);

        //deleting order from new orders table
        newOrderService.deleteNewOrder(id);

        return orderInProgressMapper.toDto(orderInProgressRepository.save(orderInProgress));

    }

    @Override
    public Page<OrderInProgressResponse> getAllOrdersInProgress(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return orderInProgressRepository.findAll(paging).map(orderInProgressMapper::toDto);
    }

    @Override
    public OrderInProgressResponse getOrderInProgressById(int id) {
        return orderInProgressRepository.findById(id).map(orderInProgressMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
    }

    @Override
    public OrderInProgressResponse updateOrderInProgressById(int id, UpdateOrderInProgressRequest request, UserDetails adminDetails) {
        OrderInProgress updatingOrderInProgress = orderInProgressRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));

        updatingOrderInProgress.setStatus(EStatus.valueOf(request.status()));
        updatingOrderInProgress.setName(request.name());
        updatingOrderInProgress.setPhoneNumber(request.phoneNumber());

        GateType gateType = gateTypesService.getGateTypeById(request.gateTypeId());
        Gate gate = gateService.findGateById(request.gateId());

        updatingOrderInProgress.setGateType(gateType);
        updatingOrderInProgress.setGate(gate);

        updatingOrderInProgress.getUpdatedBy().add(
                authService.findAdminByUsername(adminDetails.getUsername())
                        .orElseThrow(UserNotFoundException::new)
        );

        updatingOrderInProgress.setUpdated_date(new Date());

        return orderInProgressMapper.toDto(orderInProgressRepository.save(updatingOrderInProgress));
    }

    @Override
    public void deleteById(int id) {
        orderInProgressRepository.deleteById(id);
    }
}
