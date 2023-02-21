package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.LoginRequest;
import kg.megalab.selim_trade.dto.LoginResponse;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);
}
