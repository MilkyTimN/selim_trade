package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import kg.megalab.selim_trade.dto.LoginRequest;
import kg.megalab.selim_trade.dto.LoginResponse;
import kg.megalab.selim_trade.dto.RefreshAccessTokenRequest;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@SecurityRequirements
@CrossOrigin(origins = {"http://localhost:3000", "http://161.35.29.179"}, allowCredentials = "true")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(description = """
            При логине выдается access и refresh токены.
            С помощью access токена пользователь может авторизоваться.
            Но он действителен только в течение 30 минут.
            Потом чтобы не логиниться снова нужно отправить refresh токен на 
            ендпоинт '/refresh-token'
            все refresh токены хранятся в бд.
            Refresh токен валиден в течение суток.
            Если refresh токен есть в базе данных и срок его 
            дейтсвия еще не истек, то выдается новый refresh и 
            access токен. Старый refresh токен стирается с бд, 
            чтобы если вдруг кто-то украдет его он не смог им воспользоваться больше 1 раза.
            Если refresh токен тоже истек то он также стирается с бд и 
            пользователю придется уже логиниться.
            """)
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {

        return authService.login(loginRequest);
    }

    @PostMapping("/refresh-token")
    @Operation(description = """
            При логине выдается access и refresh токены.
            С помощью access токена пользователь может авторизоваться.
            Но он действителен только в течение 30 минут.
            Потом чтобы не логиниться снова нужно отправить refresh токен на 
            ендпоинт '/refresh-token'
            все refresh токены хранятся в бд.
            Refresh токен валиден в течение суток.
            Если refresh токен есть в базе данных и срок его 
            дейтсвия еще не истек, то выдается новый refresh и 
            access токен. Старый refresh токен стирается с бд, 
            чтобы если вдруг кто-то украдет его он не смог им воспользоваться больше 1 раза.
            Если refresh токен тоже истек то он также стирается с бд и 
            пользователю придется уже логиниться.
            """)
    public LoginResponse refreshAccessToken(@Valid @RequestBody RefreshAccessTokenRequest refreshToken) {
        return authService.refreshAccessToken(refreshToken);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/logout")
    @SecurityRequirement(name = "Bearer_Token_Authorization")
    @Operation(description = """
            Нужен access токен, чтобы удалить с бд рефреш токен при выходе
            """)
    public void logout(@AuthenticationPrincipal UserDetails adminDetails) {
        authService.logout(adminDetails);
    }

}
