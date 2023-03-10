package kg.megalab.selim_trade.dto;

public record NewOrderRequest(
        String name,
        String phoneNumber,
        String message
) {
}
