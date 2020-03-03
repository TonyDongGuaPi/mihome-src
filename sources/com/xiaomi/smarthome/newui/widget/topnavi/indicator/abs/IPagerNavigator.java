package com.xiaomi.smarthome.newui.widget.topnavi.indicator.abs;

public interface IPagerNavigator {
    boolean isCompactMode();

    void notifyDataSetChanged();

    void onAttachToMagicIndicator();

    void onDetachFromMagicIndicator();

    void onPageScrollStateChanged(int i);

    void onPageScrolled(int i, float f, int i2);

    void onPageSelected(int i);

    void setCompactMode(boolean z);
}
