package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateOrderInProgressRequest(
        @NotBlank
        @NotNull
        String status,
        @NotBlank
        @NotNull
        String name,
        @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Invalid phone number format")
        String phoneNumber,
        @NotNull
        @NotBlank
        int gateTypeId,
        @NotNull
        @NotBlank
        int gateId
) {
}
