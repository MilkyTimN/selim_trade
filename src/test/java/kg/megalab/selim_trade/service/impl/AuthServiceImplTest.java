package kg.megalab.selim_trade.service.impl;


import kg.megalab.selim_trade.dto.LoginRequest;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.dto.RegisterResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.enums.ERole;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.security.jwt.JwtService;
import kg.megalab.selim_trade.security.jwt.RefreshTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RefreshTokenService refreshTokenService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    private Admin admin;
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        admin.setId(1);
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setRoles(Set.of(ERole.ADMIN));
        admin.setActive(true);

        registerRequest = new RegisterRequest(
                "admin",
                "password"
        );

        loginRequest = new LoginRequest(
                "admin",
                "password"
        );

    }

    @Test
    void registering_shouldRegisterNewAdmin() {
        when(adminRepository.existsByUsername(anyString())).thenReturn(false);
        when(adminMapper.toModel(any())).thenReturn(admin);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(adminRepository.save(any())).thenReturn(admin);

        RegisterResponse response = authService.register(registerRequest);

        assertThat(response.message()).isEqualTo("admin is registered successfully!");
        verify(adminRepository).save(admin);
    }
   /* @Test
    void login_shouldReturnJwtAndRefreshToken_whenCredentialsAreValid() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(adminMapper.toDto(any())).thenReturn(new AdminInfo());
        when(jwtService.generateToken(any())).thenReturn("jwt");
        when(refreshTokenService.generateRefreshToken(any())).thenReturn("refreshToken");

        LoginResponse response = authService.login(loginRequest);

        assertThat(response.getJwt()).isEqualTo("jwt");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken");
        verify(refreshTokenService*/


}