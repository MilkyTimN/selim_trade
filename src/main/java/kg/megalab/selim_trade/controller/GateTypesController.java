package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.GateTypesRequest;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.service.GateTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/gate-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class GateTypesController {
    private final GateTypesService gateTypesService;

    @PostMapping
    public GateTypesResponse createGateType(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @ModelAttribute("gateList") ArrayList<GateResponse> gateList,
            @AuthenticationPrincipal UserDetails adminDetails
    ) throws IOException {
        return gateTypesService.createGateType(
                image, name, gateList, adminDetails
        )
    }
}
