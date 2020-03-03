package com.mi.global.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class BaseListView extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private OnLayoutListener f7151a;

    public interface OnLayoutListener {
        void a();
    }

    public BaseListView(Context context) {
        super(context);
        setOverScrollMode(2);
    }

    public BaseListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOverScrollMode(2);
    }

    public BaseListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOverScrollMode(2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f7151a != null) {
            this.f7151a.a();
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setOnLayoutListener(OnLayoutListener onLayoutListener) {
        this.f7151a = onLayoutListener;
    }
}
