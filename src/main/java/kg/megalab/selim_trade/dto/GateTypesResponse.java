package kg.megalab.selim_trade.dto;

import java.util.List;

public record GateTypesResponse(
        int id,
        String backgroundUrl,
        String name,
        List<GateResponse> gateList,
        String created_date,
        String updated_date,
        LoginResponse.AdminInfo createdBy,
        List<LoginResponse.AdminInfo> updatedBy

) {
}
