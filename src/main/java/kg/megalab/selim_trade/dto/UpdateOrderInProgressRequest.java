package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record UpdateOrderInProgressRequest(
        @NotBlank(message = "the 'status' field cannot be empty!")
        String status,
        @NotBlank(message = "name cannot be empty!")
        String name,
        String phoneNumber,
        @NotNull(message = "'gateTypeId' cannot be null!")
        int gateTypeId,
        @NotNull(message = "'gateId' cannot be null!")
        int gateId
) {
}
