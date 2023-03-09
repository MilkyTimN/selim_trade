package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.Advantage;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.exceptions.UserNotFoundException;
import kg.megalab.selim_trade.mapper.AdvantageMapper;
import kg.megalab.selim_trade.repository.AdvantageRepository;
import kg.megalab.selim_trade.service.AdvantageService;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.GateTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AdvantageServiceImpl implements AdvantageService {
    private final AdvantageRepository advantageRepository;
    private final GateTypesService gateTypesService;
    private final AuthService authService;
    private final AdvantageMapper advantageMapper;

    @Override
    public AdvantageResponse createAdvantage(int gateTypeId, AdvantageRequest request, UserDetails adminDetails) {
        GateType gateType = gateTypesService.getGateTypeById(gateTypeId);

        Advantage advantage = new Advantage();

        advantage.setGateType(gateType);
        advantage.setTitle(request.title());
        advantage.setDescription(request.description());

        Admin admin = authService.findAdminByUsername(adminDetails.getUsername())
                .orElseThrow(UserNotFoundException::new);

        advantage.setCreatedBy(admin);

        //updating gatetype updatedBy list and updated date
        gateType.getUpdatedBy().add(admin);
        gateType.setUpdated_date(new Date());
        gateTypesService.save(gateType);

        return advantageMapper.toDto(advantageRepository.save(advantage));

    }
}
