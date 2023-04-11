package kg.megalab.selim_trade.dto;


import java.util.List;

public record GateResponse(
        int id,
        String name,
        String photoUrl,
        String created_date,
        AdminInfoShort createdBy,
        List<UpdatedByResponse> updatedByList

) {
}
