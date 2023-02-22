package kg.megalab.selim_trade.repository.projections;

import kg.megalab.selim_trade.entity.News;

/**
 * Projection for {@link News} domain entity
 */

public interface NewsItemProjection {

    int getId();
    PictureProjection getPicture();
    String getTitle();
    String getDescription();

}
