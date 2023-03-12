package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.*;
import kg.megalab.selim_trade.entity.Admin;

import java.util.Optional;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshAccessToken(RefreshAccessTokenRequest refreshToken);

    Admin findAdminByUsername(String username);
}
