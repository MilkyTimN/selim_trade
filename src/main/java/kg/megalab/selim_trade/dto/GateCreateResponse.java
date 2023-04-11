package kg.megalab.selim_trade.dto;

public record GateCreateResponse(
        int id,
        String name,
        String photoUrl,
        String created_date
) {
}
