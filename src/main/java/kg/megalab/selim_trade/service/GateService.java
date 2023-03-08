package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.entity.Gate;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface GateService {
    GateResponse createGate(int gateTypeId, String name, MultipartFile image, UserDetails adminDetails) throws IOException;

    GateResponse updateGate(int id, String name, MultipartFile image, UserDetails adminDetails) throws IOException;

    Page<GateResponse> getAllGates(int pageNo, int pageSize, String sortBy);

    GateResponse getGateById(int id);

    void deleteGateById(int id) throws IOException;

    Gate findGateById(int id);
}
