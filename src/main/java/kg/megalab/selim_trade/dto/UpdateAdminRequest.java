package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.*;

public record UpdateAdminRequest(
        @NotBlank(message = "Username cannot be empty!")
        @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters")
        String username,

        String password,
        @NotNull(message = "'active' cannot be null!")
        boolean active
) {
}
