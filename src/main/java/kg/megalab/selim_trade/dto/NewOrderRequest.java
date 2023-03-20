package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NewOrderRequest(
        @NotNull
        @NotBlank
        String name,
        @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Invalid phone number format")
        String phoneNumber,
        @NotNull
        @NotBlank
        String message
) {
}
