package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.AuthenticateResponse;
import kg.megalab.selim_trade.dto.RegisterRequest;

public interface AuthService {
    AuthenticateResponse register(RegisterRequest registerRequest);
}
