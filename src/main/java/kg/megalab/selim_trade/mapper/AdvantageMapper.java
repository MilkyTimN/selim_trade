package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.entity.Advantage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvantageMapper {
    AdvantageResponse toDto(Advantage advantage);

}
