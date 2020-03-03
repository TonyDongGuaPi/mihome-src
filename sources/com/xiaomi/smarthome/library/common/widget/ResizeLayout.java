package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ResizeLayout extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private ResizeListener f18921a;

    public interface ResizeListener {
        void a(int i, int i2);
    }

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setResizeListener(ResizeListener resizeListener) {
        this.f18921a = resizeListener;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.f18921a != null) {
            this.f18921a.a(i2, i4);
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }
}
