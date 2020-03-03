package com.xiaomi.smarthome.library.common.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.R;

public class CircleIndicator extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18782a = 5;
    /* access modifiers changed from: private */
    public ViewPager b;
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = R.animator.scale_with_alpha;
    private int g = 0;
    /* access modifiers changed from: private */
    public int h = R.drawable.white_radius;
    /* access modifiers changed from: private */
    public int i = R.drawable.white_radius;
    /* access modifiers changed from: private */
    public Animator j;
    /* access modifiers changed from: private */
    public Animator k;
    private Animator l;
    private Animator m;
    /* access modifiers changed from: private */
    public int n = -1;
    private final ViewPager.OnPageChangeListener o = new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (CircleIndicator.this.b.getAdapter() != null && CircleIndicator.this.b.getAdapter().getCount() > 0) {
                if (CircleIndicator.this.k.isRunning()) {
                    CircleIndicator.this.k.cancel();
                }
                if (CircleIndicator.this.j.isRunning()) {
                    CircleIndicator.this.j.cancel();
                }
                if (CircleIndicator.this.n >= 0) {
                    View childAt = CircleIndicator.this.getChildAt(CircleIndicator.this.n);
                    childAt.setBackgroundResource(CircleIndicator.this.i);
                    CircleIndicator.this.k.setTarget(childAt);
                    CircleIndicator.this.k.start();
                }
                View childAt2 = CircleIndicator.this.getChildAt(i);
                childAt2.setBackgroundResource(CircleIndicator.this.h);
                CircleIndicator.this.j.setTarget(childAt2);
                CircleIndicator.this.j.start();
                int unused = CircleIndicator.this.n = i;
            }
        }
    };
    private DataSetObserver p = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            int count = CircleIndicator.this.b.getAdapter().getCount();
            if (count != CircleIndicator.this.getChildCount()) {
                if (CircleIndicator.this.n < count) {
                    int unused = CircleIndicator.this.n = CircleIndicator.this.b.getCurrentItem();
                } else {
                    int unused2 = CircleIndicator.this.n = -1;
                }
                CircleIndicator.this.a();
            }
        }
    };

    public CircleIndicator(Context context) {
        super(context);
        a(context, (AttributeSet) null);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        setOrientation(0);
        setGravity(17);
        b(context, attributeSet);
        a(context);
    }

    private void b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleIndicator);
            this.d = obtainStyledAttributes.getDimensionPixelSize(6, -1);
            this.e = obtainStyledAttributes.getDimensionPixelSize(4, -1);
            this.c = obtainStyledAttributes.getDimensionPixelSize(5, -1);
            this.f = obtainStyledAttributes.getResourceId(0, R.animator.scale_with_alpha);
            this.g = obtainStyledAttributes.getResourceId(1, 0);
            this.h = obtainStyledAttributes.getResourceId(2, R.drawable.white_radius);
            this.i = obtainStyledAttributes.getResourceId(3, this.h);
            obtainStyledAttributes.recycle();
        }
    }

    public void configureIndicator(int i2, int i3, int i4) {
        configureIndicator(i2, i3, i4, R.animator.scale_with_alpha, 0, R.drawable.white_radius, R.drawable.white_radius);
    }

    public void configureIndicator(int i2, int i3, int i4, @AnimatorRes int i5, @AnimatorRes int i6, @DrawableRes int i7, @DrawableRes int i8) {
        this.d = i2;
        this.e = i3;
        this.c = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        a(getContext());
    }

    private void a(Context context) {
        this.d = this.d < 0 ? dip2px(5.0f) : this.d;
        this.e = this.e < 0 ? dip2px(5.0f) : this.e;
        this.c = this.c < 0 ? dip2px(5.0f) : this.c;
        this.f = this.f == 0 ? R.animator.scale_with_alpha : this.f;
        this.j = b(context);
        this.l = b(context);
        this.l.setDuration(0);
        this.k = c(context);
        this.m = c(context);
        this.m.setDuration(0);
        this.h = this.h == 0 ? R.drawable.white_radius : this.h;
        this.i = this.i == 0 ? this.h : this.i;
    }

    private Animator b(Context context) {
        return AnimatorInflater.loadAnimator(context, this.f);
    }

    private Animator c(Context context) {
        if (this.g != 0) {
            return AnimatorInflater.loadAnimator(context, this.g);
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, this.f);
        loadAnimator.setInterpolator(new ReverseInterpolator());
        return loadAnimator;
    }

    public void setViewPager(ViewPager viewPager) {
        this.b = viewPager;
        if (this.b != null && this.b.getAdapter() != null) {
            a();
            this.b.removeOnPageChangeListener(this.o);
            this.b.addOnPageChangeListener(this.o);
            this.b.getAdapter().registerDataSetObserver(this.p);
            this.o.onPageSelected(this.b.getCurrentItem());
        }
    }

    @Deprecated
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (this.b != null) {
            this.b.removeOnPageChangeListener(onPageChangeListener);
            this.b.addOnPageChangeListener(onPageChangeListener);
            return;
        }
        throw new NullPointerException("can not find Viewpager , setViewPager first");
    }

    /* access modifiers changed from: private */
    public void a() {
        removeAllViews();
        int count = this.b.getAdapter().getCount();
        if (count > 0) {
            int currentItem = this.b.getCurrentItem();
            for (int i2 = 0; i2 < count; i2++) {
                if (currentItem == i2) {
                    a(this.h, this.l);
                } else {
                    a(this.i, this.m);
                }
            }
        }
    }

    private void a(@DrawableRes int i2, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i2);
        addView(view, this.d, this.e);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.leftMargin = this.c;
        layoutParams.rightMargin = this.c;
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
    }

    private class ReverseInterpolator implements Interpolator {
        private ReverseInterpolator() {
        }

        public float getInterpolation(float f) {
            return Math.abs(1.0f - f);
        }
    }

    public int dip2px(float f2) {
        return (int) ((f2 * getResources().getDisplayMetrics().density) + 0.5f);
    }
}
