package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewOrderRequest(
        @NotNull
        @NotBlank
        String name,
        String phoneNumber,
        @NotNull
        @NotBlank
        String message
) {
}
