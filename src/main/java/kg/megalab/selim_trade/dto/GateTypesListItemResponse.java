package kg.megalab.selim_trade.dto;

public record GateTypesListResponse(
        int id,
        String backgroundUrl,
        String name,
        String created_date,
        AdminInfo createdBy
) {
}
