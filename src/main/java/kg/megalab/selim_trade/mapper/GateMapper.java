package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.GateCreateResponse;
import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GateMapper {
    GateCreateResponse toShortDto(Gate gate);

    GateResponse toDto(Gate gate);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
