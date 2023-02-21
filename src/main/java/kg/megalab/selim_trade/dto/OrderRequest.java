package kg.megalab.selim_trade.dto;

public record OrderRequest(
        String name,
        String phoneNumber,
        String message
) {
}
