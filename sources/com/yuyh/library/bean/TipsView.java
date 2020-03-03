package com.yuyh.library.bean;

import android.view.View;
import android.widget.RelativeLayout;

public class TipsView {

    /* renamed from: a  reason: collision with root package name */
    public View f2565a;
    public int b = -1;
    public int c = Integer.MAX_VALUE;
    public int d = Integer.MAX_VALUE;
    public RelativeLayout.LayoutParams e;

    public TipsView(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public TipsView(View view, int i, int i2) {
        this.f2565a = view;
        this.c = i;
        this.d = i2;
    }

    public TipsView(View view, int i, int i2, RelativeLayout.LayoutParams layoutParams) {
        this.f2565a = view;
        this.c = i;
        this.d = i2;
        this.e = layoutParams;
    }
}
