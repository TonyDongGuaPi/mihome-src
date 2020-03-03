package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.xiaomi.smarthome.R;

public class FixHeightRecyclerView extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f20864a = 7;
    private static final int b = 1;
    private int c;

    public FixHeightRecyclerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FixHeightRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FixHeightRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 7;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FixHeightRecyclerView, 0, i);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 0) {
                this.c = obtainStyledAttributes.getInteger(index, -1);
            }
        }
        if (this.c <= 0) {
            this.c = 7;
        }
        obtainStyledAttributes.recycle();
        setHasFixedSize(true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        r4 = (com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy) r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r9, int r10) {
        /*
            r8 = this;
            super.onMeasure(r9, r10)
            r9 = 0
            r10 = 0
            r0 = 0
            r1 = 0
        L_0x0007:
            int r2 = r8.getChildCount()
            r3 = 1
            if (r10 >= r2) goto L_0x0056
            android.view.View r2 = r8.getChildAt(r10)
            boolean r4 = r2 instanceof com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy
            if (r4 == 0) goto L_0x0053
            r4 = r2
            com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy r4 = (com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy) r4
            int r5 = r4.getLines()
            if (r5 != 0) goto L_0x0020
            goto L_0x0053
        L_0x0020:
            int r6 = r0 + r5
            int r7 = r8.c
            if (r6 > r7) goto L_0x0043
            int r0 = r2.getMeasuredHeight()
            int r1 = r1 + r0
            int r0 = r8.getChildCount()
            int r0 = r0 - r3
            if (r10 != r0) goto L_0x0041
            int r0 = r6 + r5
            if (r0 >= r3) goto L_0x0041
            int r9 = 1 - r6
            int r9 = r9 - r5
            int r10 = r2.getMeasuredHeight()
            int r10 = r10 * r9
            int r1 = r1 + r10
            goto L_0x0057
        L_0x0041:
            r0 = r6
            goto L_0x0053
        L_0x0043:
            int r9 = r8.c
            int r9 = r9 - r0
            int r10 = r2.getMeasuredHeight()
            int r0 = r4.getLines()
            int r10 = r10 / r0
            int r9 = r9 * r10
            int r1 = r1 + r9
            goto L_0x0057
        L_0x0053:
            int r10 = r10 + 1
            goto L_0x0007
        L_0x0056:
            r3 = 0
        L_0x0057:
            if (r3 == 0) goto L_0x0060
            int r9 = r8.getMeasuredWidth()
            r8.setMeasuredDimension(r9, r1)
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.FixHeightRecyclerView.onMeasure(int, int):void");
    }

    public void setMaxLines(int i) {
        this.c = i;
    }
}
