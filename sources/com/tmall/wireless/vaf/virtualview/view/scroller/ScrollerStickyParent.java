package com.tmall.wireless.vaf.virtualview.view.scroller;

import android.content.Context;
import android.widget.FrameLayout;
import com.tmall.wireless.vaf.virtualview.core.IView;

public class ScrollerStickyParent extends FrameLayout implements IView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9421a = "ScrollerSticky_TMTEST";

    public ScrollerStickyParent(Context context) {
        super(context);
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
