package kg.megalab.selim_trade.repository.projections;


import kg.megalab.selim_trade.entity.Review;

/**
 * Projection for {@link Review} domain entity
 */

public interface ReviewProjection {
    int getId();
    String getName();
    String getText();
    Picture getPicture();
    Product getProduct();

    interface Picture {
        String getUrl();
    }

    interface Product{
        String getName();
    }
}
