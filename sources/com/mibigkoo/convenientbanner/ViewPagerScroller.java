package com.mibigkoo.convenientbanner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ViewPagerScroller extends Scroller {

    /* renamed from: a  reason: collision with root package name */
    private int f7617a = 800;
    private boolean b;

    public ViewPagerScroller(Context context) {
        super(context);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator, boolean z) {
        super(context, interpolator, z);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.b ? 0 : this.f7617a);
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.b ? 0 : this.f7617a);
    }

    public int a() {
        return this.f7617a;
    }

    public void a(int i) {
        this.f7617a = i;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
