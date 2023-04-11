package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.NewsListItemResponse;
import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.News;
import kg.megalab.selim_trade.entity.UpdatedBy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsResponse toNewOrUpdatedResponse(News news);

    NewsListItemResponse toShortNewsDto(News news);

    default List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return MapperUtils.mapUpdatedByList(updatedByList);
    }
}
