package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.service.AdvantageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/advantage")
public class AdvantageController {
    private final AdvantageService advantageService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AdvantageResponse createAdvantage(
            @PathVariable("id") int gateTypeId,
            @RequestBody AdvantageRequest advantageRequest,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return advantageService.createAdvantage(gateTypeId, advantageRequest, adminDetails);
    }

    @PutMapping("/{id}")
    public AdvantageResponse updateAdvantageById(
            @PathVariable("id") int id,
            @RequestBody AdvantageRequest request,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return advantageService.updateAdvantageById(id, request, adminDetails);
    }

    @GetMapping
    public Page<AdvantageResponse> getAllAdvantages(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return advantageService.getAllAdvantages(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public AdvantageResponse getAdvantageById(@PathVariable("id") int id) {
        return advantageService.getAdvantageById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdvantageById(@PathVariable("id") int id) {
        advantageService.deleteAdvantageById(id);
    }
}
