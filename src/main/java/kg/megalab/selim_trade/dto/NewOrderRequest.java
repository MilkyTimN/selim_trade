package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;

public record NewOrderRequest(

        @NotBlank(message = "'name' cannot be empty!")
        String name,
        String phoneNumber,
        //TODO:Boolean to boolean;
        @NotBlank(message = "'message' cannot be empty!")
        String message
) {
}
