package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.*;
import kg.megalab.selim_trade.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshAccessToken(RefreshAccessTokenRequest refreshToken);

    Admin findAdminByUsername(String username);


    AdminInfo makeSuperAdmin(int id);


    Page<AdminInfo> getAllAdminsList(int pageNo, int pageSize, String sortBy);

    void logout(UserDetails adminDetails);

    AdminInfo updateAdmin(UpdateAdminRequest request, int id);

    AdminInfo getAdminById(int adminId);
}
