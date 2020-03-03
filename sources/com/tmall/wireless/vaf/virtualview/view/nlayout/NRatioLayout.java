package com.tmall.wireless.vaf.virtualview.view.nlayout;

import android.graphics.Canvas;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.layout.RatioLayout;

public class NRatioLayout extends RatioLayout implements INativeLayout {
    private static final String am = "NRatioLayout_TMTEST";
    private NativeLayoutImpl an;

    public void a(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
    }

    public boolean m() {
        return true;
    }

    public NRatioLayout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.an = new NativeLayoutImpl(vafContext.m());
        this.an.setVirtualView(this);
    }

    public View g_() {
        return this.an;
    }

    public void onComMeasure(int i, int i2) {
        this.an.measure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.an.onLayout(z, i, i2, i3, i4);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        this.h = i;
        this.i = i2;
        this.an.layout(i, i2, i3, i4);
    }

    public void c_(int i, int i2) {
        super.onComMeasure(i, i2);
    }

    public void a(boolean z, int i, int i2, int i3, int i4) {
        super.onComLayout(z, i, i2, i3, i4);
    }

    public void a_(Canvas canvas) {
        super.a(canvas);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new NRatioLayout(vafContext, viewCache);
        }
    }
}
