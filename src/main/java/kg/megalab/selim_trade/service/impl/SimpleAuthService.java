package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.AuthenticateResponse;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.ERole;
import kg.megalab.selim_trade.exceptions.BadRequestException;
import kg.megalab.selim_trade.mapper.AdminMapper;
import kg.megalab.selim_trade.repository.AdminRepository;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SimpleAuthService implements AuthService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticateResponse register(RegisterRequest registerRequest) {
        if(adminRepository.existsByUsername(registerRequest.password())) {
            throw new BadRequestException("Username is already taken!");
        }

        Admin newAdmin = adminMapper.toModel(registerRequest);
        newAdmin.setPassword(passwordEncoder.encode(registerRequest.password()));
        newAdmin.setRoles(Set.of(ERole.ADMIN));

        return


    }
}
