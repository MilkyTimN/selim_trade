package kg.megalab.selim_trade.dto;

public record NewOrUpdateOrderInProgressRequest(
        String status,
//        GateInfo gate,
        String name,
        String phoneNumber
) {
}
