package kg.megalab.selim_trade.dto;

import java.util.List;

public record GateTypesResponse(
        int id,
        String backgroundUrl,
        String name,
        List<AdvantageResponse> advantageList,
        List<GateResponse> gateList,
        String created_date,
        String updated_date,
        AdminInfo createdBy,
        List<AdminInfo> updatedBy

) {
}
