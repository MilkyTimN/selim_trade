package kg.megalab.selim_trade.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public record GateTypesRequest(
        MultipartFile image,
        String name,
        List<GateResponse> gateList
) {
}
