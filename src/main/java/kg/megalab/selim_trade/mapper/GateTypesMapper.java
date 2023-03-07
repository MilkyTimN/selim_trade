package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.GateType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GateTypesMapper {
    GateTypesResponse toDto(GateType gateType);
}
