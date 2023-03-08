package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.GateTypesMapper;
import kg.megalab.selim_trade.repository.GateTypesRepository;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.GateService;
import kg.megalab.selim_trade.service.GateTypesService;
import kg.megalab.selim_trade.service.ImageService;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class GateTypesServiceImpl implements GateTypesService {
    private final GateTypesRepository gateTypesRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final GateTypesMapper gateTypesMapper;

    @Override
    public GateTypesResponse createGateType(
            MultipartFile image, String name,
            UserDetails adminDetails) throws IOException {
        //creating new gateType object
        GateType gateType = new GateType();

        //saving image to the file system
        String resultUrl = imageService.saveImageToFileSystem(image);


        //setting attributes
        gateType.setBackgroundUrl(resultUrl);
        gateType.setName(name);
        gateType.setCreatedBy(authService.findAdminByUsername(
                adminDetails.getUsername()
        ).orElseThrow(UserNotFoundException::new));

        return gateTypesMapper.toDto(gateTypesRepository.save(gateType));
    }

    @Override
    public Page<GateTypesResponse> getAll(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return gateTypesRepository.findAll(paging).map(gateTypesMapper::toDto);
    }

    @Override
    public GateTypesResponse getGateTypeResponseById(int id) {
        return gateTypesRepository.findById(id).map(gateTypesMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Gate type not found!"));
    }

    @Override
    public GateType getGateTypeById(int id) {
        return gateTypesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate type not found!"));
    }

    @Override
    public GateType saveAndFlush(GateType gateType) {
        return gateTypesRepository.saveAndFlush(gateType);
    }

    @Override
    public GateType save(GateType gateType) {
        return gateTypesRepository.save(gateType);
    }

    @Override
    public GateTypesResponse updateGateTypeById(int id, MultipartFile image, String name, UserDetails adminDetails) throws IOException {
        GateType updatingGateType = gateTypesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate type not found!"));

        // deleting previous photo from filesystem
        Files.deleteIfExists(Path.of(updatingGateType.getBackgroundUrl()));

        //adding admin to updatedby list
        updatingGateType.getUpdatedBy().add(
                authService.findAdminByUsername(adminDetails.getUsername())
                        .orElseThrow(UserNotFoundException::new)
        );

        //saving new photo to the filesystem
        String resultUrl = imageService.saveImageToFileSystem(image);

        updatingGateType.setBackgroundUrl(resultUrl);
        updatingGateType.setName(name);

        return gateTypesMapper.toDto(gateTypesRepository.save(updatingGateType));
    }

    @Override
    public void deleteGateTypeById(int id) throws IOException {
        //finding gateType
        GateType gateType = gateTypesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gate type not found!"));

        //delete previous photo from the filesystem
        Files.deleteIfExists(Path.of(gateType.getBackgroundUrl()));

        gateTypesRepository.deleteById(id);
    }
}
