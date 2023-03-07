package kg.megalab.selim_trade.dto;

import java.io.File;

public record GateRequest(
        String name,
        File image
) {
}
