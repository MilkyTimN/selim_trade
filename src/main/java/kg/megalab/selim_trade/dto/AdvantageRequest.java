package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdvantageRequest(
        @NotNull
        @NotBlank
        String title,
        @NotBlank
        @NotNull
        String description
) {
}
