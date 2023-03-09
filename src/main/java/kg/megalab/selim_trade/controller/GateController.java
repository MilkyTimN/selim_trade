package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.service.GateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/gate")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class GateController {
    private final GateService gateService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public GateResponse createGate(
            @PathVariable("id") int gateTypeId,
            @RequestParam("name") String name,
            @RequestParam(value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return gateService.createGate(gateTypeId, name, image, adminDetails);
    }


    @PutMapping("/{id}")
    public GateResponse updateGate(
            @PathVariable("id") int id,
            @RequestParam("name") String name,
            @RequestParam(value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return gateService.updateGate(id, name, image, adminDetails);
    }



    @GetMapping
    public Page<GateResponse> getAllGates(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return gateService.getAllGates(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public GateResponse getGateById(@PathVariable("id") int id) {
        return gateService.getGateById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGateById(@PathVariable("id") int id) throws IOException {
        gateService.deleteGateById(id);
    }
}
