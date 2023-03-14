package kg.megalab.selim_trade.dto;

import java.util.Set;

public record AdminInfo(
        String username,
        boolean active,
        Set<String> roles
) {
}
