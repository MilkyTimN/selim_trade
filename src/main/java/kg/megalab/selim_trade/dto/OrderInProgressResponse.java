package kg.megalab.selim_trade.dto;

import java.util.List;

public record OrderInProgressResponse(
        int id,
        String status,
        String gateType,
        String gate,
        String name,
        String phoneNumber,
        String created_date,
        String updated_date,
        AdminInfo createdBy,
        List<AdminInfo> updatedBy
) {
}
