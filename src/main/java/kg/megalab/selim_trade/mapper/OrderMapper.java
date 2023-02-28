package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.dto.OrderResponse;
import kg.megalab.selim_trade.entity.NewOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    NewOrder toModel(OrderRequest dto);

    OrderResponse toDto(NewOrder model);
}
