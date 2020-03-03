package com.xiaomi.smarthome.framework.page.verify.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class PinInputView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17085a = 4;
    private static final long[] b = {100, 500};
    private int c;
    /* access modifiers changed from: private */
    public int d;
    private int e;
    /* access modifiers changed from: private */
    public int f;
    /* access modifiers changed from: private */
    public int g;
    private String h;
    private PinInputItem[] i;
    /* access modifiers changed from: private */
    public PinInputItem j;
    /* access modifiers changed from: private */
    public boolean k;
    /* access modifiers changed from: private */
    public Handler l;
    /* access modifiers changed from: private */
    public Runnable m;

    public PinInputView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PinInputView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PinInputView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = 4;
        this.d = R.drawable.pin_input_item;
        this.e = 47;
        this.g = 0;
        this.h = "";
        this.k = false;
        this.m = new Runnable() {
            public void run() {
                if (PinInputView.this.k) {
                    if (PinInputView.this.j != null) {
                        if (PinInputView.this.j.isEnabled()) {
                            PinInputView.this.j.setEnabled(false);
                        } else {
                            PinInputView.this.j.setEnabled(true);
                        }
                    }
                    PinInputView.this.l.postDelayed(PinInputView.this.m, 600);
                }
            }
        };
        this.l = new Handler();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PincodeView, i2, 0);
            this.d = obtainStyledAttributes.getResourceId(1, R.drawable.pin_input_item);
            this.e = obtainStyledAttributes.getInt(2, 47);
            setSpacing(obtainStyledAttributes.getDimensionPixelSize(4, 0));
            setPincodeNumber(obtainStyledAttributes.getInt(3, 4));
            obtainStyledAttributes.recycle();
        }
        this.l.postDelayed(this.m, 1000);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = getMeasuredWidth();
        this.f = (measuredWidth - (this.g * (this.c - 1))) / this.c;
        setMeasuredDimension(measuredWidth, this.f + getPaddingTop() + getPaddingBottom());
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            getChildAt(i4).measure(0, 0);
        }
    }

    public void setSpacing(int i2) {
        this.g = i2;
    }

    public void setPincodeNumber(int i2) {
        this.c = i2;
        removeAllViews();
        init();
    }

    public void init() {
        this.i = new PinInputItem[this.c];
        setOrientation(0);
        for (int i2 = 0; i2 < this.c; i2++) {
            a(i2);
            if (this.g > 0 && i2 != this.c - 1) {
                addView(new SpacingView(getContext()));
            }
        }
        this.i[0].showHighlight();
        this.j = this.i[0];
    }

    public int getPincodeNumber() {
        return this.c;
    }

    private void a(int i2) {
        this.i[i2] = new PinInputItem(getContext());
        addView(this.i[i2]);
    }

    public String add(int i2) {
        if (this.h.length() < this.c) {
            this.h += i2;
        }
        this.i[this.h.length() - 1].showPin();
        this.i[this.h.length() - 1].clearHighlight();
        if (this.h.length() < this.c) {
            this.i[this.h.length()].showHighlight();
            this.j = this.i[this.h.length()];
        } else {
            this.j = null;
        }
        return this.h;
    }

    public String delete() {
        if (TextUtils.isEmpty(this.h)) {
            return "";
        }
        this.j = this.i[this.h.length() - 1];
        this.i[this.h.length() - 1].clearPin();
        this.i[this.h.length() - 1].showHighlight();
        if (this.h.length() < this.c) {
            this.i[this.h.length()].clearPin();
            this.i[this.h.length()].clearHighlight();
        }
        if (this.h.length() == 1) {
            this.h = "";
        } else {
            this.h = this.h.substring(0, this.h.length() - 1);
        }
        return this.h;
    }

    public void reset() {
        for (PinInputItem pinInputItem : this.i) {
            pinInputItem.clearPin();
            pinInputItem.clearHighlight();
        }
        this.i[0].showHighlight();
        this.j = this.i[0];
        this.h = "";
    }

    public void showError() {
        reset();
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
        ((Vibrator) getContext().getSystemService("vibrator")).vibrate(b, -1);
    }

    public String getPinCode() {
        return this.h;
    }

    private class SpacingView extends View {
        public SpacingView(Context context) {
            super(context);
            a();
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            setMeasuredDimension(PinInputView.this.g, 1);
        }

        private void a() {
            setLayoutParams(new LinearLayout.LayoutParams(DisplayUtils.a(getContext(), (float) PinInputView.this.g), -2));
        }
    }

    private class PinInputItem extends ImageView {
        public PinInputItem(Context context) {
            super(context);
            init();
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            setMeasuredDimension(PinInputView.this.f, PinInputView.this.f);
        }

        private void init() {
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            setImageResource(PinInputView.this.d);
            setEnabled(false);
            setSelected(false);
        }

        public void showPin() {
            setEnabled(true);
        }

        public void clearPin() {
            setEnabled(false);
        }

        public void showHighlight() {
            setSelected(true);
        }

        public void clearHighlight() {
            setSelected(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.k = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.k = false;
    }
}
