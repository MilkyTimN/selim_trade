package kg.megalab.selim_trade.dto;

import java.util.List;

public record GateResponse(
        int id,
        String name,
        String photoUrl,
        String created_date,
        String updated_date,
        LoginResponse.AdminInfo createdBy,
        List<LoginResponse.AdminInfo> updatedBy

) {
}
