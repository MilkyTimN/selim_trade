package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.NewsPhotoResponse;
import kg.megalab.selim_trade.entity.NewsPhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsPhotoMapper {
    NewsPhotoResponse toDto(NewsPhoto newsPhoto);
}
