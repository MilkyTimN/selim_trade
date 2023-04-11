package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.AdvantageCreateResponse;
import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdvantageService {
    AdvantageCreateResponse createAdvantage(int gateTypeId, AdvantageRequest advantageRequest, UserDetails adminDetails);

    AdvantageResponse updateAdvantageById(int id, AdvantageRequest request, UserDetails adminDetails);


    AdvantageResponse getAdvantageById(int id);

    void deleteAdvantageById(int id);
}
