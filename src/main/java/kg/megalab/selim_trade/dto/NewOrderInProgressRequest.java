package kg.megalab.selim_trade.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewOrderInProgressRequest(

        @NotEmpty(message = "'name' cannot empty!")
        String name,

        String phoneNumber,
        @NotNull(message = "'gateTypeId' cannot be empty!")
        int gateTypeId,
        @NotNull(message = "'gateId' cannot be empty!")
        int gateId
) {
}
