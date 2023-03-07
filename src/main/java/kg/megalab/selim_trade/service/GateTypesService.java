package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface GateTypesService {

    GateTypesResponse createGateType(MultipartFile image, String name, ArrayList<GateResponse> gateSet, UserDetails adminDetails) throws IOException;

    Page<GateTypesResponse> getAll(int pageNo, int pageSize, String sortBy);
}
