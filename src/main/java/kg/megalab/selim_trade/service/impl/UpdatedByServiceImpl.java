package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.entity.UpdatedBy;
import kg.megalab.selim_trade.repository.UpdatedByRepository;
import kg.megalab.selim_trade.service.UpdatedByService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatedByServiceImpl implements UpdatedByService {
    private final UpdatedByRepository repository;

    @Override
    public UpdatedBy save(UpdatedBy updatedBy) {
        return repository.save(updatedBy);
    }
}
