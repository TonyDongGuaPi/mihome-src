package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class BaseListView extends ListView {
    private OnLayoutListener mOnLayoutListener;

    public interface OnLayoutListener {
        void beforeOnLyaout();
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
        if (this.mOnLayoutListener != null) {
            this.mOnLayoutListener.beforeOnLyaout();
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setOnLayoutListener(OnLayoutListener onLayoutListener) {
        this.mOnLayoutListener = onLayoutListener;
    }
}
