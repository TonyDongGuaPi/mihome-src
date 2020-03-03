package com.tmall.wireless.vaf.virtualview.view.nlayout;

import android.graphics.Canvas;
import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.layout.FrameLayout;

public class NFrameLayout extends FrameLayout implements INativeLayout {
    private static final String ah = "NFrameLayout_TMTEST";
    private NativeLayoutImpl ai;

    public void a(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
    }

    public boolean m() {
        return true;
    }

    public NFrameLayout(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.ai = new NativeLayoutImpl(vafContext.m());
        this.ai.setVirtualView(this);
    }

    public View g_() {
        return this.ai;
    }

    public void onComMeasure(int i, int i2) {
        this.ai.measure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.ai.onLayout(z, i, i2, i3, i4);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        this.h = i;
        this.i = i2;
        this.ai.layout(i, i2, i3, i4);
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
            return new NFrameLayout(vafContext, viewCache);
        }
    }
}
