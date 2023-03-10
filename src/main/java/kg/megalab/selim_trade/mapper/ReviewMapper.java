package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.ReviewResponse;
import kg.megalab.selim_trade.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponse toDto(Review review);
}
