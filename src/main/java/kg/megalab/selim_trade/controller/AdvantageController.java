package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.service.AdvantageService;
import lombok.RequiredArgsConstructor;
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
}
