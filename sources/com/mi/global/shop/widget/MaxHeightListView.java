package com.mi.global.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.mi.util.Device;

public class MaxHeightListView extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private int f7160a = (Device.b / 2);

    public int getMaxHeight() {
        return this.f7160a;
    }

    public void setMaxHeight(int i) {
        this.f7160a = i;
    }

    public MaxHeightListView(Context context) {
        super(context);
    }

    public MaxHeightListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaxHeightListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE && size > this.f7160a) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.f7160a, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
