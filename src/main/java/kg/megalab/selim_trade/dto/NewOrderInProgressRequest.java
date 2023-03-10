package kg.megalab.selim_trade.dto;

public record NewOrderInProgressRequest(
//        GateInfo gate,
        String name,
        String phoneNumber,
        int gateTypeId,
        int gateId
) {
}
