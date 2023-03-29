package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record UpdateOrderInProgressRequest(
        @NotBlank
        @NotNull
        String status,
        @NotBlank
        @NotNull
        String name,
        String phoneNumber,
        @NotNull
        int gateTypeId,
        @NotNull
        int gateId
) {
}
