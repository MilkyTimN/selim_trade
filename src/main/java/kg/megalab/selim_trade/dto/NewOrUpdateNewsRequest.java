package kg.megalab.selim_trade.dto;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;


public record NewOrUpdateNewsRequest(
        MultipartFile image,
        String title,
        String description
) {
}
