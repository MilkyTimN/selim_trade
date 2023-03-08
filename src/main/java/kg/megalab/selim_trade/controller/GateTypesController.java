package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.service.GateTypesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    private static Logger logger = LoggerFactory.getLogger(GateTypesController.class);

    @PostMapping
    public GateTypesResponse createGateType(
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @AuthenticationPrincipal UserDetails adminDetails
    ) throws IOException {

//        logger.info(String.valueOf(gateList));

        return gateTypesService.createGateType(
                image, name, adminDetails
        );
    }

    @GetMapping
    public Page<GateTypesResponse> getAllGateTypes(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        return gateTypesService.getAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public GateTypesResponse getGateTypeById(@PathVariable("id") int id) {
        return gateTypesService.getGateTypeResponseById(id);
    }

    @PutMapping("/{id}")
    public GateTypesResponse updateGateTypeById(
            @PathVariable("id") int id,
            @RequestParam("image") MultipartFile image,
            @RequestParam("name") String name,
            @AuthenticationPrincipal UserDetails adminDetails) throws IOException {
        return gateTypesService.updateGateTypeById(id,image,name,adminDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGateTypeById(@PathVariable("id") int id) throws IOException {
        gateTypesService.deleteGateTypeById(id);
    }
}
