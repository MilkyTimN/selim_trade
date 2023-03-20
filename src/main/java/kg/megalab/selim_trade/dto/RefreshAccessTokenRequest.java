package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RefreshAccessTokenRequest(
        @NotNull(message = "refresh token cannot be null!")
        @NotBlank(message = "refresh token cannot be empty!")
        String refreshToken
) {
}
