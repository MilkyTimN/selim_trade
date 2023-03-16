package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.*;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.ERole;
import kg.megalab.selim_trade.exceptions.BadRequestException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.security.jwt.JwtService;
import kg.megalab.selim_trade.security.jwt.RefreshTokenService;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (adminRepository.existsByUsername(registerRequest.username())) {
            throw new BadRequestException("Username is already taken!");
        }

        Admin newAdmin = adminMapper.toModel(registerRequest);
        newAdmin.setPassword(passwordEncoder.encode(registerRequest.password()));
        newAdmin.setRoles(Set.of(ERole.ADMIN));
        newAdmin.setActive(true);
        adminRepository.save(newAdmin);
        return new RegisterResponse(newAdmin.getUsername() + " is registered successfully!");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );
        Admin admin = findAdminByUsername(loginRequest.username());
        String jwt = jwtService.generateToken(admin);
        String refreshToken = refreshTokenService.generateRefreshToken(admin);
        return new LoginResponse(jwt, refreshToken, adminMapper.toDto(admin));
    }

    @Override
    public LoginResponse refreshAccessToken(RefreshAccessTokenRequest request) {
        return refreshTokenService.generateAccessToken(request.refreshToken());
    }

    @Override
    public Admin findAdminByUsername(String username) {

        return adminRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public AdminInfo updateAdminsUsernameAndPassword(LoginRequest usernameAndPassword, int id) {
        Admin updatedAdmin = adminRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        updatedAdmin.setUsername(usernameAndPassword.username());
        updatedAdmin.setPassword(usernameAndPassword.password());

        return adminMapper.toDto(adminRepository.save(updatedAdmin));
    }

    @Override
    public AdminInfo makeSuperAdmin(int id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        admin.getRoles().add(ERole.SUPER_ADMIN);
        return adminMapper.toDto(adminRepository.save(admin));
    }

    @Override
    public AdminInfo disableAdmin(int id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        admin.setActive(false);
        return adminMapper.toDto(adminRepository.save(admin));
    }

    @Override
    public AdminInfo enableAdmin(int id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        admin.setActive(true);
        return adminMapper.toDto(adminRepository.save(admin));
    }

    @Override
    public Page<AdminInfo> getAllAdminsList(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return adminRepository.findAll(paging).map(adminMapper::toDto);
    }


}
