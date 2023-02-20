package kg.megalab.selim_trade.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwt_access_token_expiration_in_minutes}")
    private long access_token_expiration;

    @Value("${jwt_refresh_token_expiration_in_hours}")
    private long refresh_token_expiration;


}
