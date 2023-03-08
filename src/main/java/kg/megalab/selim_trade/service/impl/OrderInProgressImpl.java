package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.NewOrderInProgressRequest;
import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.entity.EStatus;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.entity.OrderInProgress;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.OrderInProgressMapper;
import kg.megalab.selim_trade.repository.OrderInProgressRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.GateService;
import kg.megalab.selim_trade.service.GateTypesService;
import kg.megalab.selim_trade.service.OrderInProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInProgressImpl implements OrderInProgressService {
    private final OrderInProgressRepository orderInProgressRepository;
    private final OrderInProgressMapper orderInProgressMapper;
    private final AuthService authService;
    private final GateTypesService gateTypesService;
    private final GateService gateService;

    @Override
    public OrderInProgressResponse createOrderInProgress(
            NewOrderInProgressRequest orderRequest,
            UserDetails adminDetails) {
        OrderInProgress orderInProgress = new OrderInProgress();

        orderInProgress.setCreatedBy(
                authService.findAdminByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new)
        );

        orderInProgress.setStatus(EStatus.IN_PROGRESS);
        orderInProgress.setName(orderInProgress.getName());
        orderInProgress.setPhoneNumber(orderInProgress.getPhoneNumber());


        GateType gateType = gateTypesService.getGateTypeById(orderRequest.gateTypeId());
        Gate gate = gateService.findGateById(orderRequest.gateId());

        orderInProgress.setGate(gate);
        orderInProgress.setGateType(gateType);

        return orderInProgressMapper.toDto(orderInProgressRepository.save(orderInProgress));

    }

    @Override
    public Page<OrderInProgressResponse> getAllOrdersInProgress(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return orderInProgressRepository.findAll(paging).map(orderInProgressMapper::toDto);
    }
}
