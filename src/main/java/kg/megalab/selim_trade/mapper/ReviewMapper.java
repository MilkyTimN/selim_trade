package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.ReviewListItemResponse;
import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.Review;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponse toDto(Review review);

    ReviewListItemResponse toShortDto(Review review);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
