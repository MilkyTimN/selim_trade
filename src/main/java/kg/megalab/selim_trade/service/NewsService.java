package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.NewsListItemResponse;
import kg.megalab.selim_trade.dto.NewsResponse;
import kg.megalab.selim_trade.repository.projections.NewsItemView;
import kg.megalab.selim_trade.repository.projections.NewsListView;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NewsService {

    NewsResponse getNewsById(int id);


    NewsListItemResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException;

    NewsListItemResponse updateNews(int id, MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException;

    void deleteNews(int id) throws IOException;

    Page<NewsListView> getAllNewsForCustomer(int pageNo, int pageSize, String sortBy);

    Page<NewsListItemResponse> getAllNewsShort(int pageNo, int pageSize, String sortBy);

    NewsItemView getNewsByIdForCustomer(int newsId);
}
