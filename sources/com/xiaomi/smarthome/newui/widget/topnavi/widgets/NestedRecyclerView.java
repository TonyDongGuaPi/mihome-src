package com.xiaomi.smarthome.newui.widget.topnavi.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.newui.widget.topnavi.layoutmanager.FlowLayoutManager;

public class NestedRecyclerView extends RecyclerView {
    public NestedRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setHasFixedSize(true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        FlowLayoutManager flowLayoutManager = (FlowLayoutManager) getLayoutManager();
        View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size2 = flowLayoutManager.a() + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(size, size2);
    }
}
