package kg.megalab.selim_trade.dto;

import java.util.List;

public record ReviewResponse(
        int id,
        String photoUrl,
        String name,
        String text,
        String gate,
        String created_date,
        AdminInfoShort createdBy,
        List<UpdatedByResponse> updatedByList
) {
}
