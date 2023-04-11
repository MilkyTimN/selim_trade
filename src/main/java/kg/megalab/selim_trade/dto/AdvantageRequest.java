package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;

public record AdvantageRequest(
        @NotBlank(message = "'title' cannot be empty!")
        String title,
        @NotBlank(message = "'description' cannot be empty!")
        String description
) {
}
