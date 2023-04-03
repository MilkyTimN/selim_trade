package kg.megalab.selim_trade.mapper;

import kg.megalab.selim_trade.dto.UpdatedByResponse;
import kg.megalab.selim_trade.entity.UpdatedBy;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    public static List<UpdatedByResponse> mapUpdatedByList(List<UpdatedBy> updatedByList) {
        return updatedByList.stream()
                .map(updatedBy -> new UpdatedByResponse(
                        updatedBy.getAdmin().getUsername(),
                        updatedBy.getDate().toString())).collect(Collectors.toList());
    }
}