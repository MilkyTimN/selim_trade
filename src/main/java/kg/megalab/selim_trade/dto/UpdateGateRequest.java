package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UpdateGateRequest(

        @NotNull(message = "gate name cannot be null!")
        @NotBlank(message = "gate name cannot be empty!")
        String name,
        @NotNull(message = "image cannot be null!")
        @NotBlank(message = "image cannot be empty!")
        MultipartFile image
) {
}
