package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotNull(message = "Username cannot be null!")
        @NotBlank(message = "Username cannot be empty!")
        @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters")
        String username,

        @NotNull(message = "Password cannot be null!")
        @NotBlank(message = "Password cannot be empty!")
        @Size(min = 5, max = 32, message = "Password must be between 5 and 32 characters")
        String password
) {
}
