package kg.megalab.selim_trade.dto;

import java.util.List;

public record OrderInProgressResponse(
        int id,
        String status,
        ShortGateAndGateTypeInfo gateType,
        ShortGateAndGateTypeInfo gate,
        String name,
        String phoneNumber,
        String created_date,
        AdminInfoShort createdBy,
        List<UpdatedByResponse> updatedByList
) {
}
