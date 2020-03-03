package com.mi.global.shop.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class CustomDurationScroller extends Scroller {

    /* renamed from: a  reason: collision with root package name */
    private double f7154a = 2.0d;

    public CustomDurationScroller(Context context) {
        super(context);
    }

    public CustomDurationScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public void a(double d) {
        this.f7154a = d;
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        double d = (double) i5;
        double d2 = this.f7154a;
        Double.isNaN(d);
        super.startScroll(i, i2, i3, i4, (int) (d * d2));
    }
}
