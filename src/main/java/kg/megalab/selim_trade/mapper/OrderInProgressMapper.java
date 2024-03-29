package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.OrderInProgress;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderInProgressMapper {
    OrderInProgressResponse toDto(OrderInProgress orderInProgress);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
