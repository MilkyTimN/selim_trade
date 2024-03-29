package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kg.megalab.selim_trade.dto.AdvantageCreateResponse;
import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.service.AdvantageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/advantage")
@CrossOrigin(origins = {"http://localhost:3000", "http://161.35.29.179"}, allowCredentials = "true")
public class AdvantageController {
    private final AdvantageService advantageService;

    @Operation(
            description = """
                    Надо задать id типа ворот(gate type) в url, т.к. преимущество должно
                    принадлежать типу ворот. По этому id находиться тип ворот в бд и
                    добавляться преимущество в массив преимуществ.
                    """
    )
    @PostMapping("/{gateTypeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AdvantageCreateResponse createAdvantage(
            @PathVariable("gateTypeId") int gateTypeId,
            @Valid @RequestBody AdvantageRequest advantageRequest,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return advantageService.createAdvantage(gateTypeId, advantageRequest, adminDetails);
    }

    @PutMapping("/{advantageId}")
    public AdvantageResponse updateAdvantageById(
            @PathVariable("advantageId") int id,
            @Valid @RequestBody AdvantageRequest request,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return advantageService.updateAdvantageById(id, request, adminDetails);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','SUPER_ADMIN')")
    @GetMapping("/{advantageId}")
    public AdvantageResponse getAdvantageById(@PathVariable("advantageId") int id) {
        return advantageService.getAdvantageById(id);
    }

    @DeleteMapping("/{advantageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdvantageById(@PathVariable("advantageId") int id) {
        advantageService.deleteAdvantageById(id);
    }
}
