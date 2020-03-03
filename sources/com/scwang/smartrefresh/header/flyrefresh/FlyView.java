package com.scwang.smartrefresh.header.flyrefresh;

import android.content.Context;
import android.util.AttributeSet;
import com.scwang.smartrefresh.header.internal.pathview.PathsView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

public class FlyView extends PathsView {
    public FlyView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FlyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.parserColors(-1);
        if (!this.mPathsDrawable.a("M2.01,21L23,12 2.01,3 2,10l15,2 -15,2z")) {
            this.mPathsDrawable.a(2, 3, 20, 18);
        }
        int a2 = DensityUtil.a(25.0f);
        this.mPathsDrawable.setBounds(0, 0, a2, a2);
    }
}
