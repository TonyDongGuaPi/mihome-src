package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.titles;

import android.content.Context;
import com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.ArgbEvaluatorHolder;

public class ColorTransitionPagerTitleView extends SimplePagerTitleView {
    public void onDeselected(int i, int i2) {
    }

    public void onSelected(int i, int i2) {
    }

    public ColorTransitionPagerTitleView(Context context) {
        super(context);
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        setTextColor(ArgbEvaluatorHolder.a(f, this.mSelectedColor, this.mNormalColor));
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        setTextColor(ArgbEvaluatorHolder.a(f, this.mNormalColor, this.mSelectedColor));
    }
}
