package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.*;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.enums.ERole;
import kg.megalab.selim_trade.exceptions.BadRequestException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.security.jwt.JwtService;
import kg.megalab.selim_trade.security.jwt.RefreshTokenService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Auth Service Test")
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
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {

        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Register User with Valid Input")
    void registerWithValidInput() {
        RegisterRequest registerRequest = new RegisterRequest("testuser", "password");
        Admin admin = new Admin();
        admin.setUsername(registerRequest.username());
        admin.setPassword(registerRequest.password());
        admin.setActive(true);
        admin.setRoles(Set.of(ERole.ADMIN));

        when(adminRepository.existsByUsername(registerRequest.username())).thenReturn(false);
        when(adminMapper.toModel(registerRequest)).thenReturn(admin);
        when(passwordEncoder.encode(registerRequest.password())).thenReturn("encoded_password");
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
        when(adminMapper.toDto(admin)).thenReturn(new AdminInfo(admin.getId(), admin.getUsername(), admin.isEnabled(),
                admin.getRoles().stream().map(Enum::name).collect(Collectors.toSet())));

        RegisterResponse registerResponse = authService.register(registerRequest);
        Assertions.assertEquals(registerResponse.message(), admin.getUsername() + " is registered successfully!");
    }

    @Test
    @DisplayName("Register User with Duplicate Username")
    void registerWithDuplicateUsername() {
        RegisterRequest registerRequest = new RegisterRequest("testuser", "password");
        when(adminRepository.existsByUsername(registerRequest.username())).thenReturn(true);

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () -> authService.register(registerRequest));
        Assertions.assertEquals(exception.getMessage(), "Username is already taken!");
    }

    @Test
    @DisplayName("Login User with Valid Credentials")
    void loginWithValidCredentials() {
        LoginRequest loginRequest = new LoginRequest("testuser", "password");
        Admin admin = new Admin();
        admin.setUsername(loginRequest.username());
        admin.setPassword(loginRequest.password());
        admin.setActive(true);
        admin.setRoles(Set.of(ERole.ADMIN));

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(adminRepository.findByUsername(loginRequest.username())).thenReturn(Optional.of(admin));
        when(jwtService.generateToken(admin)).thenReturn("jwt_token");
        when(refreshTokenService.generateRefreshToken(admin)).thenReturn("refreshToken");
        LoginResponse loginResponse = authService.login(loginRequest);
        Assertions.assertEquals(loginResponse.accessToken(), "jwt_token");
        Assertions.assertEquals(loginResponse.refreshToken(), "refreshToken");
    }

    @Test
    @DisplayName("Login User with Invalid Credentials")
    void loginWithInvalidCredentials() {
        LoginRequest loginRequest = new LoginRequest("testuser", "password");
        when(authenticationManager.authenticate(any())).thenThrow(new UserNotFoundException());

        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class, () -> authService.login(loginRequest));
        Assertions.assertEquals(exception.getMessage(), "User not found!");
    }

}