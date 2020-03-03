package com.xiaomi.smarthome.newui.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayoutSpringBehavior;
import android.util.AttributeSet;
import android.view.View;
import in.srain.cube.views.ptr.header.MaterialProgressDrawable;

public class SimplePushToRefreshHeader extends View implements AppBarLayoutSpringBehavior.PullToRefreshCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20886a = "SimplePushToRefreshHeader";
    /* access modifiers changed from: private */
    public MaterialProgressDrawable b;
    /* access modifiers changed from: private */
    public float c = 0.0f;
    private OnRefreshListener d;

    public interface OnRefreshListener {
        void a();
    }

    public SimplePushToRefreshHeader(Context context) {
        super(context);
        a();
    }

    public SimplePushToRefreshHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SimplePushToRefreshHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.b = new MaterialProgressDrawable(getContext(), this);
        this.b.setBackgroundColor(-1);
        this.b.setCallback(this);
        this.b.setColorSchemeColors(-14370630, -14370630, -14370630, -14370630);
    }

    public void setColorSchemeColors(int[] iArr) {
        this.b.setColorSchemeColors(iArr);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(this.b.getIntrinsicHeight() + getPaddingTop() + getPaddingBottom(), 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int intrinsicHeight = this.b.getIntrinsicHeight();
        this.b.setBounds(0, 0, intrinsicHeight, intrinsicHeight);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.b) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = this.b.getBounds();
        canvas.translate((float) (getPaddingLeft() + ((getMeasuredWidth() - this.b.getIntrinsicWidth()) / 2)), (float) getPaddingTop());
        canvas.scale(this.c, this.c, bounds.exactCenterX(), bounds.exactCenterY());
        this.b.draw(canvas);
        canvas.restoreToCount(save);
    }

    public void onDrag(int i, boolean z) {
        float min = Math.min(((float) i) / 150.0f, 1.0f);
        this.c = min;
        this.b.setAlpha((int) (255.0f * min));
        if (!z) {
            this.b.showArrow(true);
        } else {
            this.b.showArrow(false);
        }
        this.b.setStartEndTrim(0.0f, Math.min(0.8f, min * 0.8f));
        this.b.setArrowScale(Math.min(1.0f, min));
        this.b.setProgressRotation((((0.4f * min) - 16.0f) + (min * 2.0f)) * 0.5f);
        invalidate();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.c = 0.0f;
        this.b.setAlpha(0);
        invalidate();
    }

    public void onComplete() {
        this.b.stop();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(200);
        valueAnimator.setFloatValues(new float[]{1.0f, 0.0f});
        valueAnimator.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                SimplePushToRefreshHeader.this.b();
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = SimplePushToRefreshHeader.this.c = SimplePushToRefreshHeader.this.c * ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SimplePushToRefreshHeader.this.b.setAlpha((int) (SimplePushToRefreshHeader.this.c * 255.0f));
                SimplePushToRefreshHeader.this.invalidate();
            }
        });
        valueAnimator.start();
    }

    public void onRefresh() {
        this.c = 1.0f;
        this.b.setAlpha(255);
        this.b.start();
        if (this.d != null) {
            this.d.a();
        }
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.d = onRefreshListener;
    }
}
