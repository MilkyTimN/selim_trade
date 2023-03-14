package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.AdminInfo;
import kg.megalab.selim_trade.dto.LoginRequest;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.dto.RegisterResponse;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PutMapping("/{id}")
    public AdminInfo updateAdminsUsernameAndPassword(@RequestBody LoginRequest usernameAndPassword, @PathVariable("id") int id) {
        return authService.updateAdminsUsernameAndPassword(usernameAndPassword, id);
    }

    @GetMapping("/make-super-admin/{id}")
    public AdminInfo makeSuperAdmin(@PathVariable("id") int id) {
        return authService.makeSuperAdmin(id);
    }

    @GetMapping("/disable/{id}")
    public AdminInfo disableAdmin(@PathVariable("id") int id) {
        return authService.disableAdmin(id);
    }

    @GetMapping("/enable/{id}")
    public AdminInfo enableAdmin(@PathVariable("id") int id) {
        return authService.enableAdmin(id);
    }
}
