package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.GateResponse;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.Gate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface GateTypesService {

    GateTypesResponse createGateType(MultipartFile image, String name, ArrayList<GateResponse> gateSet, UserDetails adminDetails) throws IOException;
}
