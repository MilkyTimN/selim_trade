package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.GateTypesCreateResponse;
import kg.megalab.selim_trade.dto.GateTypesListItemResponse;
import kg.megalab.selim_trade.dto.GateTypesResponse;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.repository.projections.GateTypeItemView;
import kg.megalab.selim_trade.repository.projections.GateTypesListView;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GateTypesService {

    GateTypesCreateResponse createGateType(MultipartFile image, String name, String description, UserDetails adminDetails) throws IOException;


    GateTypesResponse getGateTypeResponseById(int id);

    GateType getGateTypeById(int id);


    GateType save(GateType gateType);

    GateTypesResponse updateGateTypeById(int id, MultipartFile image, String name, UserDetails adminDetails) throws IOException;

    void deleteGateTypeById(int id) throws IOException;

    Page<GateTypesListView> getListView(int pageNo, int pageSize, String sortBy);

    GateTypeItemView getGateTypeByIdForCustomer(int gateTypeId);

    Page<GateTypesListItemResponse> getAllShort(int pageNo, int pageSize, String sortBy);
}
