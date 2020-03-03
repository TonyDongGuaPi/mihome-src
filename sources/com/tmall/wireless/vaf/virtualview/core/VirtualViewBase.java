package com.tmall.wireless.vaf.virtualview.core;

import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;

public class VirtualViewBase extends ViewBase {

    /* renamed from: a  reason: collision with root package name */
    protected ViewBase.VirtualViewImp f9392a = new ViewBase.VirtualViewImp();

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public VirtualViewBase(VafContext vafContext, ViewCache viewCache) {
        super(vafContext, viewCache);
        this.f9392a.a((ViewBase) this);
    }

    public void onComMeasure(int i, int i2) {
        this.f9392a.onComMeasure(i, i2);
    }

    public void measureComponent(int i, int i2) {
        this.f9392a.measureComponent(i, i2);
    }
}
