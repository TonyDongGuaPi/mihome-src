package com.tmall.wireless.vaf.virtualview.view.nlayout;

import android.graphics.Canvas;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.layout.VH2Layout;

public class NVH2Layout extends VH2Layout implements INativeLayout {
    private static final String ak = "NVH2Layout_TMTEST";
    private NativeLayoutImpl al;

    public void a(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
    }

    public boolean m() {
        return true;
    }

    public NVH2Layout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.al = new NativeLayoutImpl(vafContext.m());
        this.al.setVirtualView(this);
    }

    public View g_() {
        return this.al;
    }

    public void onComMeasure(int i, int i2) {
        this.al.measure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.al.onLayout(z, i, i2, i3, i4);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        this.h = i;
        this.i = i2;
        this.al.layout(i, i2, i3, i4);
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
            return new NVH2Layout(vafContext, viewCache);
        }
    }
}
