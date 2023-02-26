package kg.megalab.selim_trade.service;

import kg.megalab.selim_trade.dto.NewOrUpdateNewsResponse;
import kg.megalab.selim_trade.dto.NewsResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NewsService {

    NewsResponse getNewsById(int id);

    String getFileOriginalName(String url);

    Page<NewsResponse> getAllNews(int pageNo, int pageSize, String sortBy);

    NewOrUpdateNewsResponse createNews(MultipartFile image, String title, String description, UserDetails adminDetails) throws IOException;

    NewOrUpdateNewsResponse updateNews(int id, MultipartFile image, String title, String description, UserDetails adminDetails);

    void deleteNews(int id) throws IOException;
}
