package kg.megalab.selim_trade.dto;

import java.util.Set;

public record AdminInfo(
        String username,
        String isEnabled,
        Set<String> roles
) {
}
