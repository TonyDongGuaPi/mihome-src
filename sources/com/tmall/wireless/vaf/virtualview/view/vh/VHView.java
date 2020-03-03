package com.tmall.wireless.vaf.virtualview.view.vh;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class VHView extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9430a = "VHView_TMTEST";
    protected int mItemCount = 0;
    protected int mItemHeight = 0;
    protected int mItemMargin = 0;
    protected int mItemWidth = 0;
    protected int mOrientation = 1;

    public VHView(Context context) {
        super(context);
    }

    public void setOrientation(int i) {
        this.mOrientation = i;
    }

    public void setItemMargin(int i) {
        this.mItemMargin = i;
    }

    public void setItemWidth(int i) {
        this.mItemWidth = i;
    }

    public void setItemHeight(int i) {
        this.mItemHeight = i;
    }

    private void a(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (this.mItemWidth == 0) {
            this.mItemWidth = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        }
        if (this.mItemHeight == 0) {
            int paddingTop = getPaddingTop() + getPaddingBottom() + (this.mItemMargin * (this.mItemCount - 1));
            if (this.mItemCount > 1) {
                this.mItemHeight = (size - paddingTop) / this.mItemCount;
            } else {
                this.mItemHeight = size - paddingTop;
            }
        } else if (this.mItemCount > 0) {
            size = this.mItemHeight + getPaddingTop() + getPaddingBottom() + ((this.mItemMargin + this.mItemHeight) * (this.mItemCount - 1));
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mItemWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(makeMeasureSpec, makeMeasureSpec2);
        }
        setMeasuredDimension(this.mItemWidth + getPaddingLeft() + getPaddingRight(), size);
    }

    private void b(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        if (this.mItemHeight == 0) {
            this.mItemHeight = (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        }
        if (this.mItemWidth == 0) {
            int paddingLeft = getPaddingLeft() + getPaddingRight() + (this.mItemMargin * (this.mItemCount - 1));
            if (this.mItemCount > 1) {
                this.mItemWidth = (size - paddingLeft) / this.mItemCount;
            } else {
                this.mItemWidth = size - paddingLeft;
            }
        } else if (this.mItemCount > 0) {
            size = getPaddingLeft() + getPaddingRight() + ((this.mItemMargin + this.mItemWidth) * (this.mItemCount - 1)) + this.mItemWidth;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mItemWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.mItemHeight, 1073741824);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(makeMeasureSpec, makeMeasureSpec2);
        }
        setMeasuredDimension(size, this.mItemHeight + getPaddingTop() + getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.mItemCount = getChildCount();
        switch (this.mOrientation) {
            case 0:
                a(i, i2);
                return;
            case 1:
                b(i, i2);
                return;
            default:
                Log.e(f9430a, "onMeasure invalidate orientation:" + this.mOrientation);
                return;
        }
    }

    private void a(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int i5 = this.mItemWidth + paddingLeft;
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < this.mItemCount; i6++) {
            getChildAt(i6).layout(paddingLeft, paddingTop, i5, this.mItemHeight + paddingTop);
            paddingTop += this.mItemHeight + this.mItemMargin;
        }
    }

    private void b(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i5 = this.mItemHeight + paddingTop;
        for (int i6 = 0; i6 < this.mItemCount; i6++) {
            getChildAt(i6).layout(paddingLeft, paddingTop, this.mItemWidth + paddingLeft, i5);
            paddingLeft += this.mItemWidth + this.mItemMargin;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        switch (this.mOrientation) {
            case 0:
                a(z, i, i2, i3, i4);
                return;
            case 1:
                b(z, i, i2, i3, i4);
                return;
            default:
                Log.e(f9430a, "onLayout invalidate orientation:" + this.mOrientation);
                return;
        }
    }
}
