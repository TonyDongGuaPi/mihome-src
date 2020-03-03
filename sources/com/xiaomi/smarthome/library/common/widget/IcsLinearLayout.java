package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

class IcsLinearLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f18873a = {16843049, 16843561, 16843562};
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private Drawable e;
    private int f;
    private int g;
    private int h;
    private int i;

    public IcsLinearLayout(Context context, int i2) {
        super(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, f18873a, i2, 0);
        this.i = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.h = obtainStyledAttributes.getInteger(1, 0);
        obtainStyledAttributes.recycle();
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.e) {
            this.e = drawable;
            boolean z = false;
            if (drawable != null) {
                this.f = drawable.getIntrinsicWidth();
                this.g = drawable.getIntrinsicHeight();
            } else {
                this.f = 0;
                this.g = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.g;
            } else {
                layoutParams.leftMargin = this.f;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.g;
            } else {
                layoutParams.rightMargin = this.f;
            }
        }
        super.measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.e != null) {
            if (getOrientation() == 1) {
                a(canvas);
            } else {
                b(canvas);
            }
        }
        super.onDraw(canvas);
    }

    private void a(Canvas canvas) {
        int i2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (!(childAt == null || childAt.getVisibility() == 8 || !a(i3))) {
                a(canvas, childAt.getTop() - ((LinearLayout.LayoutParams) childAt.getLayoutParams()).topMargin);
            }
        }
        if (a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                i2 = (getHeight() - getPaddingBottom()) - this.g;
            } else {
                i2 = childAt2.getBottom();
            }
            a(canvas, i2);
        }
    }

    private void b(Canvas canvas) {
        int i2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (!(childAt == null || childAt.getVisibility() == 8 || !a(i3))) {
                b(canvas, childAt.getLeft() - ((LinearLayout.LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
        }
        if (a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                i2 = (getWidth() - getPaddingRight()) - this.f;
            } else {
                i2 = childAt2.getRight();
            }
            b(canvas, i2);
        }
    }

    private void a(Canvas canvas, int i2) {
        this.e.setBounds(getPaddingLeft() + this.i, i2, (getWidth() - getPaddingRight()) - this.i, this.g + i2);
        this.e.draw(canvas);
    }

    private void b(Canvas canvas, int i2) {
        this.e.setBounds(i2, getPaddingTop() + this.i, this.f + i2, (getHeight() - getPaddingBottom()) - this.i);
        this.e.draw(canvas);
    }

    private boolean a(int i2) {
        if (i2 == 0 || i2 == getChildCount() || (this.h & 2) == 0) {
            return false;
        }
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (getChildAt(i3).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }
}
