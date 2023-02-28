package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.NewOrUpdateNewsRequest;
import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    News toModel(NewOrUpdateNewsRequest dto);

    NewOrUpdateNewsResponse toNewOrUpdatedResponse(News news);

}
