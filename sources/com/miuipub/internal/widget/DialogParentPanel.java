package com.miuipub.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import miuipub.v6.R;

public class DialogParentPanel extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private TypedValue f8347a;
    private TypedValue b;
    private TypedValue c;
    private TypedValue d;
    private TypedValue e;
    private TypedValue f;
    private TypedValue g;
    private TypedValue h;

    public DialogParentPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_Window);
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedWidthMinor)) {
            this.f8347a = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedWidthMinor, this.f8347a);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedHeightMajor)) {
            this.b = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedHeightMajor, this.b);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedWidthMajor)) {
            this.c = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedWidthMajor, this.c);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedHeightMinor)) {
            this.d = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedHeightMinor, this.d);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowMaxWidthMinor)) {
            this.e = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowMaxWidthMinor, this.e);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowMaxWidthMajor)) {
            this.f = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowMaxWidthMajor, this.f);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowMaxHeightMajor)) {
            this.h = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowMaxHeightMajor, this.h);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowMaxHeightMinor)) {
            this.g = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowMaxHeightMinor, this.g);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(a(i), b(i2));
    }

    private int a(int i) {
        return a(i, true, this.f8347a, this.c, this.e, this.f);
    }

    private int b(int i) {
        return a(i, false, this.d, this.b, this.g, this.h);
    }

    private int a(int i, boolean z, TypedValue typedValue, TypedValue typedValue2, TypedValue typedValue3, TypedValue typedValue4) {
        if (View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            return i;
        }
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z2 = displayMetrics.widthPixels < displayMetrics.heightPixels;
        if (!z2) {
            typedValue = typedValue2;
        }
        int a2 = a(displayMetrics, typedValue, z);
        if (a2 > 0) {
            return View.MeasureSpec.makeMeasureSpec(a2, 1073741824);
        }
        if (!z2) {
            typedValue3 = typedValue4;
        }
        int a3 = a(displayMetrics, typedValue3, z);
        return a3 > 0 ? View.MeasureSpec.makeMeasureSpec(Math.min(a3, View.MeasureSpec.getSize(i)), Integer.MIN_VALUE) : i;
    }

    private int a(DisplayMetrics displayMetrics, TypedValue typedValue, boolean z) {
        if (typedValue != null) {
            if (typedValue.type == 5) {
                return (int) typedValue.getDimension(displayMetrics);
            }
            if (typedValue.type == 6) {
                float f2 = (float) (z ? displayMetrics.widthPixels : displayMetrics.heightPixels);
                return (int) typedValue.getFraction(f2, f2);
            }
        }
        return 0;
    }
}
