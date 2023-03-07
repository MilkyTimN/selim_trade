package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.GateType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GateMapper {

    GateResponse toDto(Gate gate);

}
