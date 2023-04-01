package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GateMapper {

    GateResponse toDto(Gate gate);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
