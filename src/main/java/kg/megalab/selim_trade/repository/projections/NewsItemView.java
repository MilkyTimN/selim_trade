package kg.megalab.selim_trade.repository.projections;

import java.util.List;

public interface NewsItemView {
    int getId();

    String getPhotoUrl();

    String getTitle();

    String getDescription();

    List<NewsPhotoItem> getPhotos();

    interface NewsPhotoItem {
        int getId();

        String getPhotoUrl();
    }

}
