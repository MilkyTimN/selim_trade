package kg.megalab.selim_trade.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewOrderInProgressRequest(

        @NotEmpty
        String name,

        String phoneNumber,
        @NotNull
        int gateTypeId,
        @NotNull
        int gateId
) {
}
