package kg.megalab.selim_trade.dto;

import java.util.Set;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        AdminInfo admin
) {

}
