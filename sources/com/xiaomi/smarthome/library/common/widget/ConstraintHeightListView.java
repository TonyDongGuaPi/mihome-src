package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class ConstraintHeightListView extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private float f18790a;

    public ConstraintHeightListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ConstraintHeightListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ConstraintHeightListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f18790a = (float) DisplayUtils.a(100.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintHeightListView, 0, i);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 0) {
                this.f18790a = obtainStyledAttributes.getDimension(index, -1.0f);
            }
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.f18790a <= ((float) View.MeasureSpec.getSize(i2)) && this.f18790a > -1.0f) {
            i2 = View.MeasureSpec.makeMeasureSpec(Float.valueOf(this.f18790a).intValue(), Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
