package kg.megalab.selim_trade.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record NewOrUpdateNewsResponse(
        int id,
        String url,
        String title,
        String description,
        LocalDate created_date,
        LocalDate updated_date,
        AdminInfo admin
) {
    public record AdminInfo(
            String username,
            Set<String> roles
    ) {
    }
}
