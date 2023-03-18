package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.AdminInfo;
import kg.megalab.selim_trade.dto.LoginRequest;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.dto.RegisterResponse;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdminController {
    private final AuthService authService;


    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PutMapping("/{adminId}")
    public AdminInfo updateAdminsUsernameAndPassword(@RequestBody LoginRequest usernameAndPassword, @PathVariable("adminId") int id) {
        return authService.updateAdminsUsernameAndPassword(usernameAndPassword, id);
    }

    @GetMapping
    public Page<AdminInfo> getAllAdminsList(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return authService.getAllAdminsList(pageNo, pageSize, sortBy);
    }

    @GetMapping("/make-super-admin/{adminId}")
    public AdminInfo makeSuperAdmin(@PathVariable("adminId") int id) {
        return authService.makeSuperAdmin(id);
    }

    @GetMapping("/disable/{adminId}")
    public AdminInfo disableAdmin(@PathVariable("adminId") int id) {
        return authService.disableAdmin(id);
    }

    @GetMapping("/enable/{adminId}")
    public AdminInfo enableAdmin(@PathVariable("adminId") int id) {
        return authService.enableAdmin(id);
    }
}
