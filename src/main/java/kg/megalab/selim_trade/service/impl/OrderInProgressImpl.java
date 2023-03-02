package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.repository.OrderInProgressRepository;
import kg.megalab.selim_trade.service.OrderInProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInProgressImpl implements OrderInProgressService {
    private final OrderInProgressRepository orderInProgressRepository;

}
