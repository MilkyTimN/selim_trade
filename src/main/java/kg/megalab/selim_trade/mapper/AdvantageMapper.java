package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.Advantage;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvantageMapper {


    AdvantageResponse toDto(Advantage advantage);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }


}
