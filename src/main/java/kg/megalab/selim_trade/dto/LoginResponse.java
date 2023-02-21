package kg.megalab.selim_trade.dto;

import kg.megalab.selim_trade.entity.Admin;

import java.util.Set;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        AdminInfo adminInfo
) {
    public record AdminInfo(
            int id,
            String username,
            Set<String> roles
    ) {}
}
