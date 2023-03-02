package kg.megalab.selim_trade.dto;

import org.springframework.web.multipart.MultipartFile;

public record UpdateGateRequest(
        String name,
        MultipartFile image
) {
}
