package kg.megalab.selim_trade.dto;

public record ReviewListItemResponse(
        int id,
        String photoUrl,
        String name,
        String text,
        String gate,
        String created_date,
        AdminInfoShort createdBy
) {
}
