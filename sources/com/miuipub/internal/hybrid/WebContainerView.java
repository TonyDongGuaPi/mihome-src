package com.miuipub.internal.hybrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import miuipub.hybrid.R;

public class WebContainerView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f8268a;
    private boolean b = false;
    private View c;
    private int d;
    private float e;
    private float f;

    public WebContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HybridViewStyle, 0, 0);
        this.f8268a = obtainStyledAttributes.getBoolean(R.styleable.HybridViewStyle_hybridPullable, true);
        obtainStyledAttributes.recycle();
        this.d = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public void setWebView(View view) {
        if (view != null && this.c != view) {
            if (this.c != null) {
                removeView(this.c);
            }
            this.c = view;
            addView(view, 0, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.c == null || !this.f8268a) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        if (actionMasked == 3 || actionMasked == 1) {
            this.b = false;
            return false;
        }
        if (actionMasked == 0) {
            this.e = rawX;
            this.f = rawY;
        } else if (actionMasked == 2) {
            if (this.b) {
                return true;
            }
            float f2 = this.f - rawY;
            float abs = Math.abs(this.e - rawX);
            float abs2 = Math.abs(f2);
            this.e = rawX;
            this.f = rawY;
            if (this.c.getScrollY() == 0 && f2 < 0.0f && abs2 > abs && abs2 > ((float) this.d)) {
                this.b = true;
                return true;
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f8268a || !this.b) {
            return false;
        }
        float rawY = motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 0:
                this.f = rawY;
                break;
            case 1:
            case 3:
                this.b = false;
                a();
                break;
            case 2:
                setTranslationY(getTranslationY() + ((rawY - this.f) * 0.5f));
                this.f = rawY;
                break;
        }
        return false;
    }

    private void a() {
        if (getTranslationY() != 0.0f) {
            ViewPropertyAnimator animate = animate();
            animate.translationY(0.0f);
            animate.setDuration((long) getResources().getInteger(17694721));
            animate.start();
        }
    }
}
