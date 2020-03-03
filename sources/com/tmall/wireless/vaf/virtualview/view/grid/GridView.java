package com.tmall.wireless.vaf.virtualview.view.grid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.tmall.wireless.vaf.virtualview.Helper.RtlHelper;

public class GridView extends ViewGroup {
    protected static final int DEFAULT_COLUMN_COUNT = 2;
    protected static final int DEFAULT_ITEM_HEIGHT = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final String f9404a = "GridView_TMTEST";
    protected int mAutoDimDirection = 0;
    protected float mAutoDimX = 1.0f;
    protected float mAutoDimY = 1.0f;
    protected int mCalItemHeight;
    protected int mColumnCount = 2;
    protected int mItemHeight = 0;
    protected int mItemHorizontalMargin = 0;
    protected int mItemVerticalMargin = 0;
    protected int mItemWidth;
    protected int mRowCount;

    public GridView(Context context) {
        super(context);
    }

    public void setAutoDimDirection(int i) {
        this.mAutoDimDirection = i;
    }

    public void setAutoDimX(float f) {
        this.mAutoDimX = f;
    }

    public void setAutoDimY(float f) {
        this.mAutoDimY = f;
    }

    public void setColumnCount(int i) {
        this.mColumnCount = i;
    }

    public void setItemHorizontalMargin(int i) {
        this.mItemHorizontalMargin = i;
    }

    public void setItemVerticalMargin(int i) {
        this.mItemVerticalMargin = i;
    }

    public void setItemHeight(int i) {
        this.mItemHeight = i;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a4 A[LOOP:0: B:18:0x00a2->B:19:0x00a4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r9, int r10) {
        /*
            r8 = this;
            int r0 = r8.mAutoDimDirection
            r1 = 1073741824(0x40000000, float:2.0)
            if (r0 <= 0) goto L_0x003b
            int r0 = r8.mAutoDimDirection
            switch(r0) {
                case 1: goto L_0x0024;
                case 2: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x003b
        L_0x000c:
            int r0 = android.view.View.MeasureSpec.getMode(r10)
            if (r1 != r0) goto L_0x003b
            int r9 = android.view.View.MeasureSpec.getSize(r10)
            float r9 = (float) r9
            float r0 = r8.mAutoDimX
            float r9 = r9 * r0
            float r0 = r8.mAutoDimY
            float r9 = r9 / r0
            int r9 = (int) r9
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r1)
            goto L_0x003b
        L_0x0024:
            int r0 = android.view.View.MeasureSpec.getMode(r9)
            if (r1 != r0) goto L_0x003b
            int r10 = android.view.View.MeasureSpec.getSize(r9)
            float r10 = (float) r10
            float r0 = r8.mAutoDimY
            float r10 = r10 * r0
            float r0 = r8.mAutoDimX
            float r10 = r10 / r0
            int r10 = (int) r10
            int r10 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r1)
        L_0x003b:
            int r0 = android.view.View.MeasureSpec.getSize(r9)
            int r2 = android.view.View.MeasureSpec.getSize(r10)
            android.view.View.MeasureSpec.getMode(r9)
            int r9 = android.view.View.MeasureSpec.getMode(r10)
            int r3 = r8.getPaddingLeft()
            int r4 = r8.getPaddingRight()
            int r3 = r3 + r4
            int r4 = r8.mItemHorizontalMargin
            int r5 = r8.mColumnCount
            r6 = 1
            int r5 = r5 - r6
            int r4 = r4 * r5
            int r3 = r3 + r4
            int r4 = r8.getChildCount()
            int r3 = r0 - r3
            int r5 = r8.mColumnCount
            int r3 = r3 / r5
            r8.mItemWidth = r3
            int r3 = r8.mItemHeight
            r5 = 0
            if (r3 > 0) goto L_0x0091
            if (r4 <= 0) goto L_0x0083
            android.view.View r3 = r8.getChildAt(r5)
            int r7 = r8.mItemWidth
            int r7 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r1)
            r3.measure(r7, r10)
            int r10 = r3.getMeasuredHeight()
            r8.mCalItemHeight = r10
            r10 = 1
            goto L_0x0096
        L_0x0083:
            int r10 = r8.getPaddingTop()
            int r10 = r2 - r10
            int r3 = r8.getPaddingBottom()
            int r10 = r10 - r3
            r8.mCalItemHeight = r10
            goto L_0x0095
        L_0x0091:
            int r10 = r8.mItemHeight
            r8.mCalItemHeight = r10
        L_0x0095:
            r10 = 0
        L_0x0096:
            int r3 = r8.mItemWidth
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r1)
            int r7 = r8.mCalItemHeight
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r1)
        L_0x00a2:
            if (r10 >= r4) goto L_0x00ae
            android.view.View r7 = r8.getChildAt(r10)
            r7.measure(r3, r1)
            int r10 = r10 + 1
            goto L_0x00a2
        L_0x00ae:
            int r10 = r8.mColumnCount
            int r10 = r4 / r10
            int r1 = r8.mColumnCount
            int r4 = r4 % r1
            if (r4 <= 0) goto L_0x00b8
            r5 = 1
        L_0x00b8:
            int r10 = r10 + r5
            r8.mRowCount = r10
            int r10 = r8.mRowCount
            int r1 = r8.mCalItemHeight
            int r10 = r10 * r1
            int r1 = r8.mRowCount
            int r1 = r1 - r6
            int r3 = r8.mItemVerticalMargin
            int r1 = r1 * r3
            int r10 = r10 + r1
            int r1 = r8.getPaddingTop()
            int r10 = r10 + r1
            int r1 = r8.getPaddingBottom()
            int r10 = r10 + r1
            if (r9 != 0) goto L_0x00d6
            goto L_0x00da
        L_0x00d6:
            int r10 = java.lang.Math.min(r2, r10)
        L_0x00da:
            r8.setMeasuredDimension(r0, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tmall.wireless.vaf.virtualview.view.grid.GridView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int i5 = paddingTop;
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.mRowCount) {
            int i8 = paddingLeft;
            int i9 = i7;
            for (int i10 = 0; i10 < this.mColumnCount && i9 < childCount; i10++) {
                View childAt = getChildAt(i9);
                int a2 = RtlHelper.a(RtlHelper.a(), 0, i3 - i, i8, this.mItemWidth);
                childAt.layout(a2, i5, this.mItemWidth + a2, this.mCalItemHeight + i5);
                i9++;
                i8 += this.mItemWidth + this.mItemHorizontalMargin;
            }
            i5 += this.mCalItemHeight + this.mItemVerticalMargin;
            i6++;
            i7 = i9;
        }
    }
}
