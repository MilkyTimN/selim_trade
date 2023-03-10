package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.NewOrderRequest;
import kg.megalab.selim_trade.dto.NewOrderResponse;
import kg.megalab.selim_trade.entity.NewOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewOrderMapper {
    NewOrder toModel(NewOrderRequest dto);

    NewOrderResponse toDto(NewOrder model);
}
