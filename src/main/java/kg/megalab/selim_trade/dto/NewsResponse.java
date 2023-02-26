package kg.megalab.selim_trade.dto;

public record NewsResponse(
        int id,
        String photoUrl,
        String title,
        String description,
        String created_date,
        String updated_date,
        LoginResponse.AdminInfo admin
) {

}
