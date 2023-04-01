package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.Review;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
//    @Mapping(target = "updatedByList.username", source = "admin.username")
    ReviewResponse toDto(Review review);
    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
