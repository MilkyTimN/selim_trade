package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.entity.OrderInProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderInProgressMapper {
    @Mapping(target = "gateType", source="gateType.name")
    @Mapping(target = "gate", source = "gate.name")
    OrderInProgressResponse toDto(OrderInProgress orderInProgress);
}
