package kg.megalab.selim_trade.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        AdminInfo admin
) {

}
