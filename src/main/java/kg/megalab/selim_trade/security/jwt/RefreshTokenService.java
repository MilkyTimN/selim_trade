package kg.megalab.selim_trade.security.jwt;

import kg.megalab.selim_trade.dto.LoginResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.RefreshToken;
import kg.megalab.selim_trade.exceptions.BadRequestException;
import kg.megalab.selim_trade.exceptions.NotFoundException;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Ref;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final AdminMapper adminMapper;
    @Value("${jwt_refresh_token_expiration_in_hours}")
    private long jwt_refresh_token_expiration_in_hours;

    public String generateRefreshToken(UserDetails userDetails) {
        Admin admin = (Admin) userDetails;
        RefreshToken refreshToken = null;
        if (refreshTokenRepository.existsByAdmin(admin)) {
            refreshToken = refreshTokenRepository.findByAdmin(admin).get();
            refreshTokenRepository.delete(refreshToken);
            if (isRefreshTokenExpired(refreshToken)) {
                throw new BadRequestException("Refresh token is expired. Please sign in again.");
            }
            return refreshToken.getToken();
        }
        refreshToken = generateCompleteNewRefreshToken(admin);
        return refreshToken.getToken();
    }

    private RefreshToken generateCompleteNewRefreshToken(Admin admin) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setAdmin(admin);
        refreshToken.setExpiryDate(new Date(System.currentTimeMillis()
                + 1000 * 60 * 60 * jwt_refresh_token_expiration_in_hours));
        return refreshTokenRepository.save(refreshToken);
    }

    public LoginResponse generateAccessToken(String refreshTokenInRequest) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenInRequest)
                .orElseThrow(() -> new NotFoundException("Refresh token not found!"));
        Admin admin = refreshToken.getAdmin();
        if(isRefreshTokenExpired(refreshToken)) {
            throw new BadRequestException("Refresh token is expired. Please sign in again.");
        }
        refreshTokenRepository.delete(refreshToken);
        return new LoginResponse(
                jwtService.generateToken(admin),
                generateCompleteNewRefreshToken(admin).getToken(),
                adminMapper.toLoginResponseAdminDto(admin));
    }

    public boolean isRefreshTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }

}
