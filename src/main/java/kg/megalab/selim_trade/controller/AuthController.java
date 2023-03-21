package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import kg.megalab.selim_trade.dto.LoginRequest;
import kg.megalab.selim_trade.dto.LoginResponse;
import kg.megalab.selim_trade.dto.RefreshAccessTokenRequest;
import kg.megalab.selim_trade.exceptions.BadRequestException;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@SecurityRequirements
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest ) {

        return authService.login(loginRequest);
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshAccessToken(@Valid @RequestBody RefreshAccessTokenRequest refreshToken) {
        return authService.refreshAccessToken(refreshToken);
    }
}
