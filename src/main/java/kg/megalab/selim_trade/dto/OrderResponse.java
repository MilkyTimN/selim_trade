package kg.megalab.selim_trade.dto;

public record OrderResponse(
        int id,
        String name,
        String phoneNumber,
        String message
) {
}
