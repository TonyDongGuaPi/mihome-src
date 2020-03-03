package com.xiaomi.infrared.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class ImiGridLayoutManager extends GridLayoutManager {
    public ImiGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public ImiGridLayoutManager(Context context, int i) {
        super(context, i);
    }

    public ImiGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        super.onMeasure(recycler, state, i, i2);
    }

    public void setMeasuredDimension(int i, int i2) {
        super.setMeasuredDimension(i, i2);
    }
}
