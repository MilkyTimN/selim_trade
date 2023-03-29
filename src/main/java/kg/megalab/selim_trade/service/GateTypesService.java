package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.GateType;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GateTypesService {

    GateTypesResponse createGateType(MultipartFile image, String name, UserDetails adminDetails) throws IOException;

    Page<GateTypesResponse> getAll(int pageNo, int pageSize, String sortBy);

    GateTypesResponse getGateTypeResponseById(int id);

    GateType getGateTypeById(int id);

    GateType saveAndFlush(GateType gateType);

    GateType save(GateType gateType);

    GateTypesResponse updateGateTypeById(int id, MultipartFile image, String name, UserDetails adminDetails) throws IOException;

    void deleteGateTypeById(int id) throws IOException;
}
