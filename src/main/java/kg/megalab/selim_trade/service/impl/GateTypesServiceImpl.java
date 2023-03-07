package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.GateMapper;
import kg.megalab.selim_trade.mapper.GateTypesMapper;
import kg.megalab.selim_trade.repository.GateTypesRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.GateService;
import kg.megalab.selim_trade.service.GateTypesService;
import kg.megalab.selim_trade.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GateTypesServiceImpl implements GateTypesService {
    private final GateTypesRepository gateTypesRepository;
    private final ImageService imageService;
    private final GateService gateService;
    private final AuthService authService;
    private final GateTypesMapper gateTypesMapper;

    @Override
    public GateTypesResponse createGateType(
            MultipartFile image, String name,
            ArrayList<GateResponse> gateSet,
            UserDetails adminDetails) throws IOException {
        //creating new gateType object
        GateType gateType = new GateType();

        //saving image to the file system
        String resultUrl = imageService.saveImageToFileSystem(image);

        //creating set of gates
        List<Gate> gates = gateSet.stream()
                .map(gate -> gateService.findGateById(gate.id()))
                .collect(Collectors.toList());

        //setting attributes
        gateType.setBackgroundUrl(resultUrl);
        gateType.setName(name);
        gateType.setGateList(gates);
        gateType.setCreatedBy(authService.findAdminByUsername(
                adminDetails.getUsername()
        ).orElseThrow(UserNotFoundException::new));

        return gateTypesMapper.toDto(gateTypesRepository.save(gateType));
    }
}
