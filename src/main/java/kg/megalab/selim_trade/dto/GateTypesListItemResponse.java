package kg.megalab.selim_trade.dto;

public record GateTypesListItemResponse(
        int id,
        String backgroundUrl,
        String name,
        String created_date,
        AdminInfoShort createdBy
) {
}
