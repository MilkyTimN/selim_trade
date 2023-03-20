package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.service.GateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Operation(
            description = """
                    Надо задать id типа ворот(gate type) в url, т.к. каждые ворота принадлежат только одному
                    типу ворот. По этому id ворота будут добавляться в массив типа ворот.
                    """
    )
    @PostMapping(value = "/{gateTypeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public GateResponse createGate(
            @PathVariable("gateTypeId") int gateTypeId,
            @NotBlank @NotNull @RequestParam("name") String name,
            @NotNull @RequestParam(value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return gateService.createGate(gateTypeId, name, image, adminDetails);
    }


    @PutMapping(value = "/{gateId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GateResponse updateGate(
            @PathVariable("gateId") int id,
            @NotBlank @NotNull @RequestParam("name") String name,
            @NotNull @RequestParam(value = "image") MultipartFile image,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return gateService.updateGate(id, name, image, adminDetails);
    }


    @SecurityRequirements
    @GetMapping
    public Page<GateResponse> getAllGates(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return gateService.getAllGates(pageNo, pageSize, sortBy);
    }

    @SecurityRequirements
    @GetMapping("/{gateId}")
    public GateResponse getGateById(@PathVariable("gateId") int id) {
        return gateService.getGateById(id);
    }

    @DeleteMapping("/{gateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGateById(@PathVariable("gateId") int id) throws IOException {
        gateService.deleteGateById(id);
    }
}
