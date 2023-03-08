package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.Gate;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.GateMapper;
import kg.megalab.selim_trade.repository.GateRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.GateService;
import kg.megalab.selim_trade.service.GateTypesService;
import kg.megalab.selim_trade.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GateServiceImpl implements GateService {
    private final GateRepository gateRepository;
    private final AuthService authService;
    private final GateMapper gateMapper;
    private final ImageService imageService;

    private final GateTypesService gateTypesService;


    @Override
    public GateResponse createGate(int gateTypeId, String name, MultipartFile image, UserDetails adminDetails) throws IOException {
        Gate gate = new Gate();

        String resultUrl = imageService.saveImageToFileSystem(image);

        gate.setPhotoUrl(resultUrl);
        gate.setCreatedBy(authService.findAdminByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new));
        gate.setName(name);
        gate.setGateType(
                gateTypesService.getGateTypeById(gateTypeId)
        );

        //gatetype entity
//        GateType gateType = gateTypesService.getGateTypeById(gateTypeId);
//        gateType.getGateList().add(gate);
//        gateTypesService.save(gateType);

        return gateMapper.toDto(gateRepository.save(gate));
    }


    @Override
    public GateResponse updateGate(
            int id, String name, MultipartFile image, UserDetails adminDetails) throws IOException {
        Gate updatingGate = gateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found"));

        //deleting previous photo from file system
        Files.deleteIfExists(Path.of(updatingGate.getPhotoUrl()));

        //adding admin to the updatedby list
        List<Admin> adminList = updatingGate.getUpdatedBy();
        adminList.add(authService.findAdminByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new));

        //saving new photo to file system
        String resultUrl = imageService.saveImageToFileSystem(image);

        updatingGate.setPhotoUrl(resultUrl);
        updatingGate.setName(name);
        return gateMapper.toDto(gateRepository.save(updatingGate));
    }

    @Override
    public Page<GateResponse> getAllGates(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return gateRepository.findAll(paging).map(gateMapper::toDto);
    }

    @Override
    public GateResponse getGateById(int id) {
        return gateRepository.findById(id).map(gateMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found!"));
    }

    @Override
    public void deleteGateById(int id) throws IOException {
        Gate gate = gateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate not found!"));

        //deleting previous photo from file system
        Files.deleteIfExists(Path.of(gate.getPhotoUrl()));

        gateRepository.deleteById(id);
    }

    @Override
    public Gate findGateById(int id) {
        return gateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gate not found!"));
    }
}
