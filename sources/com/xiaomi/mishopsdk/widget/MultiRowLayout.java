package com.xiaomi.mishopsdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.mishopsdk.R;
import java.util.ArrayList;

public class MultiRowLayout extends ViewGroup {
    public static LayoutParams sItemTagLayoutParams = new LayoutParams(-2, -2);
    private int mHorizontalSpacing = 0;
    private int mMaxRow = -1;
    protected ArrayList<RowInfo> mRows = new ArrayList<>();
    private int mVerticalSpacing = 0;

    public static class RowInfo {
        int childCount;
        int height;
        int width;

        @Deprecated
        public int getWidth() {
            return this.width;
        }

        @Deprecated
        public int getChildCount() {
            return this.childCount;
        }

        @Deprecated
        public int getHeight() {
            return this.height;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public MultiRowLayout(Context context) {
        super(context);
    }

    public MultiRowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public MultiRowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.mishopsdk_MultiRowLayout, i, 0);
        setVerticalSpacing(obtainStyledAttributes.getDimensionPixelSize(R.styleable.mishopsdk_MultiRowLayout_mishopsdk_tagVerticalSpacing, 0));
        setHorizontalSpacing(obtainStyledAttributes.getDimensionPixelSize(R.styleable.mishopsdk_MultiRowLayout_mishopsdk_tagHorizontalSpacing, 0));
        obtainStyledAttributes.recycle();
    }

    public int getRowCount() {
        return this.mRows.size();
    }

    public RowInfo getRowInfo(int i) {
        return this.mRows.get(i);
    }

    @Deprecated
    public void setMaxRow(int i) {
        this.mMaxRow = i;
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setVerticalSpacing(int i) {
        this.mVerticalSpacing = i;
        requestLayout();
    }

    public void setHorizontalSpacing(int i) {
        this.mHorizontalSpacing = i;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        this.mRows.clear();
        RowInfo rowInfo = new RowInfo();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            measureChildWithMargins(childAt, i, 0, i2, i3);
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            int i6 = rowInfo.width + measuredWidth;
            if (rowInfo.childCount > 0) {
                i6 += this.mHorizontalSpacing;
            }
            if (mode != 0 && i6 > (size - getPaddingLeft()) - getPaddingRight()) {
                if (mode2 != 0 && i3 >= size2) {
                    break;
                }
                if (this.mRows.size() > 0) {
                    i3 += this.mVerticalSpacing;
                }
                i3 += rowInfo.height;
                this.mRows.add(rowInfo);
                rowInfo = new RowInfo();
            }
            if (this.mMaxRow > 0 && this.mRows.size() >= this.mMaxRow) {
                break;
            }
            if (rowInfo.childCount > 0) {
                rowInfo.width += this.mHorizontalSpacing;
            }
            rowInfo.width += measuredWidth;
            rowInfo.childCount++;
            i4 = Math.max(i4, rowInfo.width);
            rowInfo.height = Math.max(rowInfo.height, measuredHeight);
        }
        if (rowInfo.childCount > 0) {
            if (this.mRows.size() > 0) {
                i3 += this.mVerticalSpacing;
            }
            i3 += rowInfo.height;
            this.mRows.add(rowInfo);
        }
        setMeasuredDimension(resolveSize(i4 + getPaddingLeft() + getPaddingRight(), i), resolveSize(i3 + getPaddingTop() + getPaddingBottom(), i2));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingTop = getPaddingTop();
        int i5 = 0;
        int i6 = 0;
        while (i5 < this.mRows.size()) {
            int paddingLeft = getPaddingLeft();
            int i7 = i6;
            for (int i8 = 0; i8 < this.mRows.get(i5).childCount; i8++) {
                View childAt = getChildAt(i7);
                int measuredWidth = childAt.getMeasuredWidth() + paddingLeft;
                childAt.layout(paddingLeft, paddingTop, measuredWidth, childAt.getMeasuredHeight() + paddingTop);
                paddingLeft = this.mHorizontalSpacing + measuredWidth;
                i7++;
            }
            paddingTop += this.mRows.get(i5).height + this.mVerticalSpacing;
            i5++;
            i6 = i7;
        }
    }
}
