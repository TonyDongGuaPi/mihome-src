package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs;

public interface IPagerTitleView {
    void onDeselected(int i, int i2);

    void onEnter(int i, int i2, float f, boolean z);

    void onLeave(int i, int i2, float f, boolean z);

    void onSelected(int i, int i2);
}
