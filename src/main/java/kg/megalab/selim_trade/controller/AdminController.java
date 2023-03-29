package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kg.megalab.selim_trade.dto.AdminInfo;
import kg.megalab.selim_trade.dto.RegisterRequest;
import kg.megalab.selim_trade.dto.RegisterResponse;
import kg.megalab.selim_trade.dto.UpdateAdminRequest;
import kg.megalab.selim_trade.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = {"http://localhost:3000", "http://161.35.29.179"}, allowCredentials = "true")
public class AdminController {
    private final AuthService authService;


    @PostMapping("/register")
    @Operation(description = """
            Поля username и password не могут ни пустыми, ни null.
            Также длина username должна быть от 6 до 32 символов.
            Длина password должна быть от 5 до до 32 символов.
            """)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PutMapping("/{adminId}")
    @Operation(description = """
            Метод, чтобы изменить админа.
            Поля username и password не могут ни пустыми, ни null.
            Также длина username должна быть от 6 до 32 символов.
            Длина password должна быть от 5 до до 32 символов.
            Поле 'active' является булевым значением.
            """)
    public AdminInfo updateAdmin(@Valid @RequestBody UpdateAdminRequest request, @PathVariable("adminId") int id) {
        return authService.updateAdmin(request, id);
    }

    @GetMapping
    public Page<AdminInfo> getAllAdminsList(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return authService.getAllAdminsList(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{adminId}")
    public AdminInfo getAdminById(@PathVariable("adminId") int adminId) {
        return authService.getAdminById(adminId);
    }


    @GetMapping("/make-super-admin/{adminId}")
    public AdminInfo makeSuperAdmin(@PathVariable("adminId") int id) {
        return authService.makeSuperAdmin(id);
    }

//    @GetMapping("/disable/{adminId}")
//    public AdminInfo disableAdmin(@PathVariable("adminId") int id) {
//        return authService.disableAdmin(id);
//    }
//
//    @GetMapping("/enable/{adminId}")
//    public AdminInfo enableAdmin(@PathVariable("adminId") int id) {
//        return authService.enableAdmin(id);
//    }
}
