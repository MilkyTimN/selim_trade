package kg.megalab.selim_trade.security.jwt;

import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.RefreshToken;
import kg.megalab.selim_trade.exceptions.BadRequestException;
import kg.megalab.selim_trade.exceptions.NotFoundException;
import kg.megalab.selim_trade.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${jwt_refresh_token_expiration_in_hours}")
    private long jwt_refresh_token_expiration_in_hours;

    public String generateRefreshToken(UserDetails userDetails) {
        Admin admin = (Admin) userDetails;
        RefreshToken refreshToken = null;
            if(refreshTokenRepository.existsByAdmin(admin)) {
               refreshToken = refreshTokenRepository.findByAdmin(admin).get();
               if(isRefreshTokenExpired(refreshToken)) {
                   refreshTokenRepository.delete(refreshToken);
                   throw new BadRequestException("Refresh token is expired. Please sign in again.");
               }
               return refreshToken.getToken();
            }
            refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setAdmin(admin);
            refreshToken.setExpiryDate(new Date(System.currentTimeMillis()
            + 1000 * 60 * 60 * jwt_refresh_token_expiration_in_hours));
            return refreshTokenRepository.saveAndFlush(refreshToken).getToken();
    }

    public boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }

}
