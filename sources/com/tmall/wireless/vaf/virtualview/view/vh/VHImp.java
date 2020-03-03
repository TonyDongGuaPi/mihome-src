package com.tmall.wireless.vaf.virtualview.view.vh;

import android.content.Context;
import android.view.View;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;

public class VHImp extends VHView implements IContainer, IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9429a = "VHImp_TMTEST";
    protected ViewBase mVirtualView;

    public void attachViews() {
    }

    public void destroy() {
    }

    public View getHolderView() {
        return null;
    }

    public int getType() {
        return -1;
    }

    public VHImp(Context context) {
        super(context);
    }

    public void setVirtualView(ViewBase viewBase) {
        this.mVirtualView = viewBase;
    }

    public ViewBase getVirtualView() {
        return this.mVirtualView;
    }

    public void measureComponent(int i, int i2) {
        measure(i, i2);
    }

    public void comLayout(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    public void onComMeasure(int i, int i2) {
        onMeasure(i, i2);
    }

    public void onComLayout(boolean z, int i, int i2, int i3, int i4) {
        onLayout(z, i, i2, i3, i4);
    }

    public int getComMeasuredWidth() {
        return getMeasuredWidth();
    }

    public int getComMeasuredHeight() {
        return getMeasuredHeight();
    }
}
