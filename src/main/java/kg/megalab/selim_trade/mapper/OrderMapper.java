package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.dto.OrderResponse;
import kg.megalab.selim_trade.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order toModel(OrderRequest dto);

    OrderResponse toDto(Order model);
}
