package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.entity.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {


    NewsResponse toNewOrUpdatedResponse(News news);

}
