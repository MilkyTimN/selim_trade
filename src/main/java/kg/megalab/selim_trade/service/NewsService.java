package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.NewOrUpdateNewsRequest;
import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.repository.projections.NewsItemProjection;
import kg.megalab.selim_trade.repository.projections.NewsListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NewsService {

    NewsItemProjection getNewsById(int id);

    Page<NewsListProjection> getAllNewses(Pageable pageable);

    NewOrUpdateNewsResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException;
}
