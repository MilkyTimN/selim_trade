package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.*;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshAccessToken(RefreshAccessTokenRequest refreshToken);
}
