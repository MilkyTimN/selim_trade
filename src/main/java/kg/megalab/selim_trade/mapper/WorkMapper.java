package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.WorkResponse;
import kg.megalab.selim_trade.entity.Work;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkMapper {
    WorkResponse toDto(Work work);
}
