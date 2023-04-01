package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RefreshAccessTokenRequest(
        @NotBlank(message = "refresh token cannot be empty!")
        String refreshToken
) {
}
