package kg.megalab.selim_trade.security.jwt;

import kg.megalab.selim_trade.entity.RefreshToken;
import kg.megalab.selim_trade.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;


}
