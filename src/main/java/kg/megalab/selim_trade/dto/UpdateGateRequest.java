package kg.megalab.selim_trade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UpdateGateRequest(

        @NotBlank(message = "'name' cannot be empty!")
        String name,

        @NotBlank(message = "'image' cannot be empty!")
        MultipartFile image
) {
}
