package kg.megalab.selim_trade.service;


import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.dto.OrderResponse;

public interface NewOrderService {

    OrderResponse saveOrder(OrderRequest orderRequest);

    void deleteNewOrder(int id);
}
