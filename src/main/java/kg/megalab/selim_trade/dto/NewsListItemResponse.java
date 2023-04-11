package kg.megalab.selim_trade.dto;

public record NewsListItemResponse(
        int id,
        String title,
        String photoUrl,
        String created_date,
        AdminInfoShort createdBy
) {
}
