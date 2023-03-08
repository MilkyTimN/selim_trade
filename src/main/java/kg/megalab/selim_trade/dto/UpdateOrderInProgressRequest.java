package kg.megalab.selim_trade.dto;

public record UpdateOrderInProgressRequest(
        String status,
        String name,
        String phoneNumber,
        int gateTypeId,
        int gateId
) {
}
