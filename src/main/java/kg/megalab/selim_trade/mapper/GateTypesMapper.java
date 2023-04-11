package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.GateTypesCreateResponse;
import kg.megalab.selim_trade.dto.GateTypesListItemResponse;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.Advantage;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GateTypesMapper {
    GateTypesResponse toDto(GateType gateType);

    GateTypesResponse.GateResponseShort toShortGateForAdmin(Gate gate);

    GateTypesResponse.AdvantageResponseShort toShortAdvantageForAdmin(Advantage advantage);

    GateTypesListItemResponse toShortDto(GateType gateType);

    GateTypesCreateResponse toShortCreateDto(GateType gateType);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
