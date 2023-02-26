package kg.megalab.selim_trade.dto;

import java.util.Date;

public record NewsResponse(
        int id,
        String photoUrl,
        String title,
        String description,
        String created_date,
        String updated_date,
        AdminInfo admin
) {
    public record AdminInfo(
            String username
    ){}
}
