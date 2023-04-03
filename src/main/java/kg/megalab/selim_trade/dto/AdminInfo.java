package kg.megalab.selim_trade.dto;

import java.util.Set;

public record AdminInfo(
        int id,
        String username,
        boolean active,
        Set<String> roles
) {
}
