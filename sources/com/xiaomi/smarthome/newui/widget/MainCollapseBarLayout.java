package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class MainCollapseBarLayout extends CollapsingToolbarLayout {
    public MainCollapseBarLayout(Context context) {
        super(context);
    }

    public MainCollapseBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MainCollapseBarLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        setMinimumHeight(DisplayUtils.a(5.0f));
    }
}
