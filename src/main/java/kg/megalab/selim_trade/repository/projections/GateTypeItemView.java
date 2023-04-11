package kg.megalab.selim_trade.repository.projections;

import java.util.List;

public interface GateTypeItemView {
    int getId();

    String getBackgroundUrl();

    String getName();

    String getDescription();

    List<AdvantageInfo> getAdvantageList();

    List<GateItemView> getGateList();

    interface AdvantageInfo {
        int getId();

        String getTitle();

        String getDescription();
    }

    interface GateItemView {
        int getId();

        String getName();

        String getPhotoUrl();
    }


}
