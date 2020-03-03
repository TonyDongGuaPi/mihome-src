package com.tmall.wireless.vaf.virtualview.view.slider;

import android.view.View;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.virtualview.core.ArrayAdapter;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.core.IView;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;

public class SliderCompactImp extends SliderView implements IContainer, IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9422a = "SliderImp_TMTEST";
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

    public SliderCompactImp(VafContext vafContext) {
        super(vafContext.j());
        this.mAdapter = new ArrayAdapter(vafContext);
    }

    public void reset() {
        scrollTo(0, 0);
    }

    public void setData(Object obj) {
        this.mDataChanged = true;
        this.mAdapter.a(obj);
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

    public void setVirtualView(ViewBase viewBase) {
        this.mVirtualView = viewBase;
    }

    public ViewBase getVirtualView() {
        return this.mVirtualView;
    }
}
