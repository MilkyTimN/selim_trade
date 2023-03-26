package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.service.GateTypesService;
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
@RequestMapping("/api/v1/gate-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class GateTypesController {
    private final GateTypesService gateTypesService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GateTypesResponse createGateType(
            @NotNull @RequestParam("image") MultipartFile image,
            @NotBlank @NotNull @RequestParam("name") String name,
            @AuthenticationPrincipal UserDetails adminDetails
    ) throws IOException {

//        logger.info(String.valueOf(gateList));

        return gateTypesService.createGateType(
                image, name, adminDetails
        );
    }

    @SecurityRequirements
    @GetMapping
    public Page<GateTypesResponse> getAllGateTypes(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return gateTypesService.getAll(pageNo, pageSize, sortBy);
    }

    @SecurityRequirements
    @GetMapping("/{gateTypeId}")
    public GateTypesResponse getGateTypeById(@PathVariable("gateTypeId") int id) {
        return gateTypesService.getGateTypeResponseById(id);
    }

    @PutMapping(value = "/{gateTypeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GateTypesResponse updateGateTypeById(
            @PathVariable("gateTypeId") int id,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @NotBlank @NotNull @RequestParam("name") String name,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return gateTypesService.updateGateTypeById(id, image, name, adminDetails);
    }

    @DeleteMapping("/{gateTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGateTypeById(@PathVariable("gateTypeId") int id) throws IOException {
        gateTypesService.deleteGateTypeById(id);
    }
}
