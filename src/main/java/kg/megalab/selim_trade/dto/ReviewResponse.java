package kg.megalab.selim_trade.dto;

import java.util.List;

public record ReviewResponse(
        int id,
        String photoUrl,
        String name,
        String text,
        String gate,
        String created_date,
        String updated_date,
        LoginResponse.AdminInfo createdBy,
        List<LoginResponse.AdminInfo> updatedBy
) {
}
