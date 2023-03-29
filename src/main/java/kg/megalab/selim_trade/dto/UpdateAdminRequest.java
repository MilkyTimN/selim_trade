package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAdminRequest(
        @NotNull(message = "Username cannot be null!")
        @NotBlank(message = "Username cannot be empty!")
        @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters")
        String username,

        String password,
        boolean active
) {
}
