package com.xiaomi.smarthome.newui.widget.topnavi.indicator;

import android.support.v4.view.ViewPager;

public class ViewPagerHelper {
    public static void a(final MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
                magicIndicator.onPageScrolled(i, f, i2);
            }

            public void onPageSelected(int i) {
                magicIndicator.onPageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
                magicIndicator.onPageScrollStateChanged(i);
            }
        });
    }
}
