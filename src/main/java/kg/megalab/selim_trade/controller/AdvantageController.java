package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.service.AdvantageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/advantage")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
    public AdvantageResponse createAdvantage(
            @PathVariable("gateTypeId") int gateTypeId,
            @RequestBody AdvantageRequest advantageRequest,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return advantageService.createAdvantage(gateTypeId, advantageRequest, adminDetails);
    }

    @PutMapping("/{advantageId}")
    public AdvantageResponse updateAdvantageById(
            @PathVariable("advantageId") int id,
            @RequestBody AdvantageRequest request,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return advantageService.updateAdvantageById(id, request, adminDetails);
    }

    @GetMapping
    @SecurityRequirements
    public Page<AdvantageResponse> getAllAdvantages(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return advantageService.getAllAdvantages(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{advantageId}")
    @SecurityRequirements
    public AdvantageResponse getAdvantageById(@PathVariable("advantageId") int id) {
        return advantageService.getAdvantageById(id);
    }

    @DeleteMapping("/{advantageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdvantageById(@PathVariable("advantageId") int id) {
        advantageService.deleteAdvantageById(id);
    }
}
