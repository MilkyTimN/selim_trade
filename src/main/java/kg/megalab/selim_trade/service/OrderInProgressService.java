package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.NewOrderInProgressRequest;
import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface OrderInProgressService {
    OrderInProgressResponse createOrderInProgress(NewOrderInProgressRequest orderRequest, UserDetails adminDetails);
}
