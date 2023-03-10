package kg.megalab.selim_trade.service;


import kg.megalab.selim_trade.dto.NewOrderRequest;
import kg.megalab.selim_trade.dto.NewOrderResponse;
import org.springframework.data.domain.Page;

public interface NewOrderService {

    NewOrderResponse saveOrder(NewOrderRequest newOrderRequest);

    void deleteNewOrder(int id);

    Page<NewOrderResponse> getAllNewOrders(int pageNo, int pageSize, String sortBy);

    NewOrderResponse getNewOrderById(int id);
}
