package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshAccessTokenRequest(
        @NotBlank(message = "refresh token cannot be empty!")
        String refreshToken
) {
}
