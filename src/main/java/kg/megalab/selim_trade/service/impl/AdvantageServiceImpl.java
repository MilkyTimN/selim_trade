package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.dto.AdvantageRequest;
import kg.megalab.selim_trade.dto.AdvantageResponse;
import kg.megalab.selim_trade.entity.Admin;
import kg.megalab.selim_trade.entity.Advantage;
import kg.megalab.selim_trade.entity.GateType;
import kg.megalab.selim_trade.entity.UpdatedBy;
import kg.megalab.selim_trade.exceptions.ResourceNotFoundException;
import kg.megalab.selim_trade.mapper.AdvantageMapper;
import kg.megalab.selim_trade.repository.AdvantageRepository;
import kg.megalab.selim_trade.service.AdvantageService;
import kg.megalab.selim_trade.service.AuthService;
import kg.megalab.selim_trade.service.GateTypesService;
import kg.megalab.selim_trade.service.UpdatedByService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final UpdatedByService updatedByService;

    @Override
    public AdvantageResponse createAdvantage(int gateTypeId, AdvantageRequest request, UserDetails adminDetails) {
        GateType gateType = gateTypesService.getGateTypeById(gateTypeId);

        Advantage advantage = new Advantage();

        advantage.setGateType(gateType);
        advantage.setTitle(request.title());
        advantage.setDescription(request.description());

        Admin admin = authService.findAdminByUsername(adminDetails.getUsername());

        advantage.setCreatedBy(admin);

        gateType.getUpdatedByList().add(updatedByService.save(
                new UpdatedBy(admin, new Date())
        ));

        gateTypesService.save(gateType);

        return advantageMapper.toDto(advantageRepository.save(advantage));

    }

    @Override
    public AdvantageResponse updateAdvantageById(int id, AdvantageRequest request, UserDetails adminDetails) {

        Advantage updatedAdvantage = advantageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Advantage not found!"));

        updatedAdvantage.setTitle(request.title());
        updatedAdvantage.setDescription(request.description());


        updatedAdvantage.getUpdatedByList().add(updatedByService.save(
                new UpdatedBy((Admin) adminDetails, new Date())
        ));

        return advantageMapper.toDto(advantageRepository.save(updatedAdvantage));
    }

    @Override
    public Page<AdvantageResponse> getAllAdvantages(int pageNo, int pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return advantageRepository.findAll(paging).map(advantageMapper::toDto);
    }

    @Override
    public AdvantageResponse getAdvantageById(int id) {
        return advantageRepository.findById(id).map(advantageMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Advantage not found!"));
    }

    @Override
    public void deleteAdvantageById(int id) {
        advantageRepository.deleteById(id);
    }
}
