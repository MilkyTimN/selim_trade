package kg.megalab.selim_trade.dto;

import java.util.List;

public record GateTypesResponse(
        int id,
        String backgroundUrl,
        String name,
        String description,
        List<AdvantageResponseShort> advantageList,
        List<GateResponseShort> gateList,
        String created_date,
        AdminInfoShort createdBy,
        List<UpdatedByResponse> updatedByList

) {
    public record GateResponseShort(
            int id,
            String name,
            String photoUrl,
            String created_date,
            AdminInfoShort createdBy
    ) {

    }

    public record AdvantageResponseShort(
            int id,
            String title,
            String created_date,
            AdminInfoShort createdBy
    ) {

    }
}
