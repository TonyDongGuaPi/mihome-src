package com.mibi.common.component;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mibi.common.R;
import com.mibi.common.data.ReusableGifDrawable;
import miuipub.graphics.drawable.GifAnimationDrawable;

public class FloatingProgressView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7485a = 300;
    private static final String b = "alpha";
    private static final String c = "translationY";
    private TextView d;
    private ProgressBar e;
    private String f;
    private GifAnimationDrawable g;

    public FloatingProgressView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatingProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Mibi_FloatingProgressView, i, 0);
        this.f = obtainStyledAttributes.getString(R.styleable.Mibi_FloatingProgressView_gifProgressDrawable);
        obtainStyledAttributes.recycle();
        setBackgroundResource(R.drawable.mibi_progress_bg);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.mibi_progress, this);
        this.d = (TextView) findViewById(R.id.message);
        this.e = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private void setProgressDrawable(Drawable drawable) {
        if (drawable != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.e.getLayoutParams();
            layoutParams.width = getResources().getDimensionPixelSize(R.dimen.mibi_progress_drawable_width);
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.mibi_progress_drawable_height);
            this.e.setLayoutParams(layoutParams);
            this.e.setIndeterminateDrawable(drawable);
        }
    }

    public void showError(String str) {
        showError(str, (View.OnClickListener) null);
    }

    public void showError(String str, View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.d.setText(getResources().getString(R.string.mibi_click_retry, new Object[]{str}));
            this.d.setOnClickListener(onClickListener);
        } else {
            this.d.setText(str);
        }
        int visibility = this.d.getVisibility();
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        if (getVisibility() != 0) {
            setVisibility(0);
            c();
        } else if (visibility != 0) {
            f();
        }
    }

    public void startProgress() {
        a();
        int visibility = this.e.getVisibility();
        this.d.setVisibility(8);
        this.e.setVisibility(0);
        if (getVisibility() != 0) {
            setVisibility(0);
            c();
        } else if (visibility != 0) {
            e();
        }
    }

    public void stopProgress() {
        if (getVisibility() == 0) {
            d();
        }
    }

    private void a() {
        if (this.g == null) {
            this.g = ReusableGifDrawable.a(getContext(), this.f);
            if (this.g != null) {
                setProgressDrawable(this.g);
            }
        }
    }

    private float getTopMargin() {
        return (float) ((FrameLayout.LayoutParams) getLayoutParams()).topMargin;
    }

    private Animator b() {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getWidth(), getTargetWidth()});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.isRunning()) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    ViewGroup.LayoutParams layoutParams = FloatingProgressView.this.getLayoutParams();
                    layoutParams.width = intValue;
                    FloatingProgressView.this.setLayoutParams(layoutParams);
                    return;
                }
                ViewGroup.LayoutParams layoutParams2 = FloatingProgressView.this.getLayoutParams();
                layoutParams2.width = -2;
                FloatingProgressView.this.setLayoutParams(layoutParams2);
            }
        });
        return ofInt;
    }

    private void c() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, c, new float[]{-getTopMargin(), 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, b, new float[]{0.0f, 1.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setDuration(300);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.start();
    }

    private void d() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, c, new float[]{0.0f, -getTopMargin()});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, b, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setDuration(300);
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                FloatingProgressView.this.setVisibility(8);
            }
        });
        animatorSet.start();
    }

    private int getTargetWidth() {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return getMeasuredWidth();
    }

    private void e() {
        ((TransitionDrawable) getBackground()).reverseTransition(300);
        Animator b2 = b();
        b2.setDuration(300);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.d, b, new float[]{1.0f, 0.0f});
        ofFloat.setDuration(300);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.e, b, new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(b2).before(ofFloat2);
        animatorSet.start();
    }

    private void f() {
        ((TransitionDrawable) getBackground()).startTransition(300);
        this.d.setAlpha(0.0f);
        Animator b2 = b();
        b2.setDuration(300);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, b, new float[]{1.0f, 0.0f});
        ofFloat.setDuration(300);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.d, b, new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).with(b2).before(ofFloat2);
        animatorSet.start();
    }
}
