package kg.megalab.selim_trade.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record NewsResponse(
        int id,
        String photoUrl,
        String title,
        String description,
        LocalDate createdDate,
        LocalDate updatedDate,
        LoginResponse.AdminInfo createdBy,
        List<LoginResponse.AdminInfo> updatedBy
) {

}
