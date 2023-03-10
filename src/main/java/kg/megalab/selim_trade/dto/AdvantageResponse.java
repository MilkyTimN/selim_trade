package kg.megalab.selim_trade.dto;


import java.util.List;

public record AdvantageResponse(
        int id,
        String title,
        String description,
        String created_date,
        String updated_date,
        LoginResponse.AdminInfo createdBy,
        List<LoginResponse.AdminInfo> updatedBy
) {
}
