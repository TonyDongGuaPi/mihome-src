package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs;

import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.model.PositionData;
import java.util.List;

public interface IPagerIndicator {
    void onPageScrollStateChanged(int i);

    void onPageScrolled(int i, float f, int i2);

    void onPageSelected(int i);

    void onPositionDataProvide(List<PositionData> list);
}
