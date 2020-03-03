package com.tmall.wireless.vaf.virtualview.view.line;

import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;

public class NativeLine extends LineBase {
    private static final String al = "NativeLine_TMTEST";
    private NativeLineImp am;

    public NativeLine(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.am = new NativeLineImp(vafContext.m(), this);
    }

    public void d() {
        super.d();
        this.am.destroy();
        this.am = null;
    }

    public View g_() {
        return this.am;
    }

    public void f() {
        super.f();
        this.am.setPaintParam(this.ah, this.ai, this.aj);
    }

    public void onComMeasure(int i, int i2) {
        this.am.onComMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        this.am.onComLayout(z, i, i2, i3, i4);
    }

    public void measureComponent(int i, int i2) {
        this.am.measureComponent(i, i2);
    }

    public int getComMeasuredWidth() {
        return this.am.getComMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return this.am.getComMeasuredHeight();
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        super.comLayout(i, i2, i3, i4);
        this.am.comLayout(i, i2, i3, i4);
    }

    public static class Builder implements ViewBase.IBuilder {
        public ViewBase a(VafContext vafContext, ViewCache viewCache) {
            return new NativeLine(vafContext, viewCache);
        }
    }
}
