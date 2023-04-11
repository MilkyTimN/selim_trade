package kg.megalab.selim_trade.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record NewsResponse(
        int id,
        String photoUrl,
        String title,
        String description,
        String created_date,
        AdminInfoShort createdBy,
        List<PhotoItem> photos,
        List<UpdatedByResponse> updatedByList
) {
    public record PhotoItem(
           int id,
           String photoUrl
    ) {

    }
}
