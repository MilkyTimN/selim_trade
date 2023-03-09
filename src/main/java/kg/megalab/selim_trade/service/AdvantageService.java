package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdvantageService {
    AdvantageResponse createAdvantage(int gateTypeId, AdvantageRequest advantageRequest, UserDetails adminDetails);

    AdvantageResponse updateAdvantageById(int id, AdvantageRequest request, UserDetails adminDetails);

    Page<AdvantageResponse> getAllAdvantages(int pageNo, int pageSize, String sortBy);

    AdvantageResponse getAdvantageById(int id);
}
