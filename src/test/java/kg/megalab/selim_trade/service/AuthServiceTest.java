package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.dto.RegisterResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.repository.RefreshTokenRepository;
import kg.megalab.selim_trade.service.impl.AuthServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class AuthServiceTest {
    @Mock
    AdminRepository adminRepository;

    @Mock
    RefreshTokenRepository refreshTokenRepository;

    @Spy
    AdminMapper adminMapper = Mappers.getMapper(AdminMapper.class);

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;


   @InjectMocks
    AuthServiceImpl authService;

    @Test
    void shouldRegisterUser() {
        var registerUserRequest = getRegisterUserRequest();
        when(adminRepository.existsByUsername(registerUserRequest.username())).thenReturn(false);
        when(adminRepository.save(any(Admin.class))).then(i -> i.getArgument(0));

        var registerUserResponse = authService.register(registerUserRequest);

        RegisterResponse successRegisterResponse = new RegisterResponse(registerUserRequest.username() + " is registered successfully!");

        assertEquals(registerUserResponse,successRegisterResponse);
    }



    private RegisterRequest getRegisterUserRequest() {
        return new RegisterRequest(
                "user_in_test",
                "password"
        );
    }

}
