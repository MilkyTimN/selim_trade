package kg.megalab.selim_trade.security.jwt;

import kg.megalab.selim_trade.dto.AdminInfo;
import kg.megalab.selim_trade.dto.LoginResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.RefreshToken;
import kg.megalab.selim_trade.entity.enums.ERole;
import kg.megalab.selim_trade.exceptions.ForbiddenException;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.RefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RefreshTokenServiceTest {

    @InjectMocks
    private RefreshTokenService refreshTokenService;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AdminMapper adminMapper;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        admin = new Admin();
        admin.setId(1);
        admin.setUsername("test");
        admin.setPassword("password");
        admin.setActive(true);
        admin.setRoles(Collections.singleton(ERole.ADMIN));


    }

        @Test
        @DisplayName("Should generate refresh token for user")
        public void shouldGenerateRefreshTokenForUser() {
            String refreshToken = UUID.randomUUID().toString();

            when(refreshTokenRepository.existsByAdmin(admin)).thenReturn(false);

            RefreshToken newRefreshToken = new RefreshToken();
            newRefreshToken.setId(1);
            newRefreshToken.setToken(refreshToken);
            newRefreshToken.setAdmin(admin);
            newRefreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

            when(refreshTokenRepository.save(any(RefreshToken.class))).thenReturn(newRefreshToken);

            String generatedToken = refreshTokenService.generateRefreshToken(admin);

            assertThat(generatedToken).isEqualTo(refreshToken);
        }

    @Test
    @DisplayName("Should replace existing refresh token with new one for user")
    public void shouldReplaceExistingRefreshTokenWithNewOneForUser() {
        String refreshToken = UUID.randomUUID().toString();

        RefreshToken existingRefreshToken = new RefreshToken();
        existingRefreshToken.setId(1);
        existingRefreshToken.setToken(refreshToken);
        existingRefreshToken.setAdmin(admin);
        existingRefreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        when(refreshTokenRepository.existsByAdmin(admin)).thenReturn(true);
        when(refreshTokenRepository.findByAdmin(admin)).thenReturn(Optional.of(existingRefreshToken));

        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setId(2);
        newRefreshToken.setToken(UUID.randomUUID().toString());
        newRefreshToken.setAdmin(admin);
        newRefreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        when(refreshTokenRepository.save(any(RefreshToken.class))).thenReturn(newRefreshToken);

        String generatedToken = refreshTokenService.generateRefreshToken(admin);

        assertThat(generatedToken).isEqualTo(newRefreshToken.getToken());
    }

    @Test
    @DisplayName("Should throw exception if refresh token is expired")
    public void shouldThrowExceptionIfRefreshTokenIsExpired() {
        RefreshToken expiredRefreshToken = new RefreshToken();
        expiredRefreshToken.setId(1);
        expiredRefreshToken.setToken(UUID.randomUUID().toString());
        expiredRefreshToken.setAdmin(admin);
        expiredRefreshToken.setExpiryDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
        when(refreshTokenRepository.findByToken(expiredRefreshToken.getToken())).thenReturn(Optional.of(expiredRefreshToken));

        assertThrows(ForbiddenException.class, () -> refreshTokenService.generateAccessToken(expiredRefreshToken.getToken()));
    }

    @Test
    @DisplayName("Should throw exception if refresh token is not found")
    public void shouldThrowExceptionIfRefreshTokenIsNotFound() {
        String nonExistingToken = UUID.randomUUID().toString();

        when(refreshTokenRepository.findByToken(nonExistingToken)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> refreshTokenService.generateAccessToken(nonExistingToken));
    }

    @Test
    @DisplayName("Should refresh access token using refresh token")
    public void shouldRefreshAccessTokenUsingRefreshToken() {
        String refreshTokenString = UUID.randomUUID().toString();

        AdminInfo adminInfo = new AdminInfo(
                admin.getId(),
                admin.getUsername(),
                admin.isActive(),
                admin.getRoles().stream().map(Enum::name).collect(Collectors.toSet())
        );

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setId(1);
        refreshToken.setToken(refreshTokenString);
        refreshToken.setAdmin(admin);
        refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        when(refreshTokenRepository.findByToken(refreshTokenString)).thenReturn(Optional.of(refreshToken));

        LoginResponse loginResponse = new LoginResponse(
                "access_token",
                refreshTokenString,
                adminInfo
        );

        when(jwtService.generateToken(admin)).thenReturn(loginResponse.accessToken());



        when(adminMapper.toDto(admin)).thenReturn(adminInfo);
        when(refreshTokenRepository.save(any(RefreshToken.class))).thenReturn(refreshToken);
        LoginResponse refreshedToken = refreshTokenService.generateAccessToken(refreshTokenString);

        assertThat(refreshedToken.accessToken()).isEqualTo(loginResponse.accessToken());
        assertThat(refreshedToken.refreshToken()).isEqualTo(refreshTokenString);
    }

}