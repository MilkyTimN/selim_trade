package kg.megalab.selim_trade.repository.projections;

import java.util.Set;

public interface ProductItemProjection {
    int getId();

    String getName();

    String getDescription();

    Set<GateTypes> getTypes();

    Set<Advantage> getAdvantages();

    interface GateTypes {
        int getId();

        //        PictureProjection getPicture();
        String getName();
    }

    interface Advantage {
        int getId();

        String getTitle();

        String getDescription();
    }
}
