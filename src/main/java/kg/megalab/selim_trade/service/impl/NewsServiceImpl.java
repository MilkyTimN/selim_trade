package kg.megalab.selim_trade.service.impl;

import kg.megalab.selim_trade.exceptions.NotFoundException;
import kg.megalab.selim_trade.repository.NewsRepository;
import kg.megalab.selim_trade.repository.projections.NewsItemProjection;
import kg.megalab.selim_trade.repository.projections.NewsListProjection;
import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;

    @Override
    public NewsItemProjection getNewsById(int id) {
        return repository.findNewsById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public Page<NewsListProjection> getAllNewses(Pageable pageable) {
        return repository.findAllProjectedBy(pageable);
    }
}
