package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.NewOrderInProgressRequest;
import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.dto.UpdateOrderInProgressRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface OrderInProgressService {
    OrderInProgressResponse createOrderInProgress(NewOrderInProgressRequest orderRequest, UserDetails adminDetails);

    Page<OrderInProgressResponse> getAllOrdersInProgress(int pageNo, int pageSize, String sortBy);

    OrderInProgressResponse getOrderInProgressById(int id);

    OrderInProgressResponse updateOrderInProgressById(int id, UpdateOrderInProgressRequest updateOrderInProgressRequest, UserDetails adminDetails);
}
