package com.scwang.smartrefresh.layout.internal;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;

public abstract class InternalClassics<T extends InternalClassics> extends InternalAbstract implements RefreshInternal {
    public static final byte ID_IMAGE_ARROW = 2;
    public static final byte ID_IMAGE_PROGRESS = 3;
    public static final byte ID_TEXT_TITLE = 1;
    protected Integer mAccentColor;
    protected ArrowDrawable mArrowDrawable;
    protected ImageView mArrowView;
    protected int mBackgroundColor;
    protected LinearLayout mCenterLayout;
    protected int mFinishDuration = 500;
    protected int mPaddingBottom = 20;
    protected int mPaddingTop = 20;
    protected Integer mPrimaryColor;
    protected ProgressDrawable mProgressDrawable;
    protected ImageView mProgressView;
    protected RefreshKernel mRefreshKernel;
    protected TextView mTitleText;

    /* access modifiers changed from: protected */
    public T self() {
        return this;
    }

    public InternalClassics(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSpinnerStyle = SpinnerStyle.Translate;
        this.mArrowView = new ImageView(context);
        this.mProgressView = new ImageView(context);
        this.mTitleText = new TextView(context);
        this.mTitleText.setTextColor(-10066330);
        this.mCenterLayout = new LinearLayout(context);
        this.mCenterLayout.setGravity(1);
        this.mCenterLayout.setOrientation(1);
        ImageView imageView = this.mArrowView;
        TextView textView = this.mTitleText;
        ImageView imageView2 = this.mProgressView;
        LinearLayout linearLayout = this.mCenterLayout;
        DensityUtil densityUtil = new DensityUtil();
        textView.setId(1);
        imageView.setId(2);
        imageView2.setId(3);
        linearLayout.setId(16908312);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(linearLayout, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(densityUtil.b(20.0f), densityUtil.b(20.0f));
        layoutParams2.addRule(15);
        layoutParams2.addRule(0, 16908312);
        addView(imageView, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(layoutParams2);
        layoutParams3.addRule(15);
        layoutParams3.addRule(0, 16908312);
        imageView2.animate().setInterpolator(new LinearInterpolator());
        addView(imageView2, layoutParams3);
        if (getPaddingTop() == 0) {
            if (getPaddingBottom() == 0) {
                int paddingLeft = getPaddingLeft();
                int b = densityUtil.b(20.0f);
                this.mPaddingTop = b;
                int paddingRight = getPaddingRight();
                int b2 = densityUtil.b(20.0f);
                this.mPaddingBottom = b2;
                setPadding(paddingLeft, b, paddingRight, b2);
            } else {
                int paddingLeft2 = getPaddingLeft();
                int b3 = densityUtil.b(20.0f);
                this.mPaddingTop = b3;
                int paddingRight2 = getPaddingRight();
                int paddingBottom = getPaddingBottom();
                this.mPaddingBottom = paddingBottom;
                setPadding(paddingLeft2, b3, paddingRight2, paddingBottom);
            }
        } else if (getPaddingBottom() == 0) {
            int paddingLeft3 = getPaddingLeft();
            int paddingTop = getPaddingTop();
            this.mPaddingTop = paddingTop;
            int paddingRight3 = getPaddingRight();
            int b4 = densityUtil.b(20.0f);
            this.mPaddingBottom = b4;
            setPadding(paddingLeft3, paddingTop, paddingRight3, b4);
        } else {
            this.mPaddingTop = getPaddingTop();
            this.mPaddingBottom = getPaddingBottom();
        }
        if (isInEditMode()) {
            imageView.setVisibility(8);
        } else {
            imageView2.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
        } else {
            setPadding(getPaddingLeft(), this.mPaddingTop, getPaddingRight(), this.mPaddingBottom);
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT >= 14) {
            ImageView imageView = this.mArrowView;
            ImageView imageView2 = this.mProgressView;
            imageView.animate().cancel();
            imageView2.animate().cancel();
        }
        Drawable drawable = this.mProgressView.getDrawable();
        if (drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            if (animatable.isRunning()) {
                animatable.stop();
            }
        }
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        this.mRefreshKernel = refreshKernel;
        this.mRefreshKernel.a((RefreshInternal) this, this.mBackgroundColor);
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        ImageView imageView = this.mProgressView;
        if (imageView.getVisibility() != 0) {
            imageView.setVisibility(0);
            Drawable drawable = this.mProgressView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            } else {
                imageView.animate().rotation(36000.0f).setDuration(100000);
            }
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        onStartAnimator(refreshLayout, i, i2);
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        ImageView imageView = this.mProgressView;
        Drawable drawable = this.mProgressView.getDrawable();
        if (drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            if (animatable.isRunning()) {
                animatable.stop();
            }
        } else {
            imageView.animate().rotation(0.0f).setDuration(0);
        }
        imageView.setVisibility(8);
        return this.mFinishDuration;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            if (!(getBackground() instanceof BitmapDrawable) && this.mPrimaryColor == null) {
                setPrimaryColor(iArr[0]);
                this.mPrimaryColor = null;
            }
            if (this.mAccentColor == null) {
                if (iArr.length > 1) {
                    setAccentColor(iArr[1]);
                } else {
                    int i = -1;
                    if (iArr[0] == -1) {
                        i = -10066330;
                    }
                    setAccentColor(i);
                }
                this.mAccentColor = null;
            }
        }
    }

    public T setProgressDrawable(Drawable drawable) {
        this.mProgressDrawable = null;
        this.mProgressView.setImageDrawable(drawable);
        return self();
    }

    public T setProgressResource(@DrawableRes int i) {
        this.mProgressDrawable = null;
        this.mProgressView.setImageResource(i);
        return self();
    }

    public T setArrowDrawable(Drawable drawable) {
        this.mArrowDrawable = null;
        this.mArrowView.setImageDrawable(drawable);
        return self();
    }

    public T setArrowResource(@DrawableRes int i) {
        this.mArrowDrawable = null;
        this.mArrowView.setImageResource(i);
        return self();
    }

    public T setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.mSpinnerStyle = spinnerStyle;
        return self();
    }

    public T setPrimaryColor(@ColorInt int i) {
        Integer valueOf = Integer.valueOf(i);
        this.mPrimaryColor = valueOf;
        this.mBackgroundColor = valueOf.intValue();
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.a((RefreshInternal) this, this.mPrimaryColor.intValue());
        }
        return self();
    }

    public T setAccentColor(@ColorInt int i) {
        this.mAccentColor = Integer.valueOf(i);
        this.mTitleText.setTextColor(i);
        if (this.mArrowDrawable != null) {
            this.mArrowDrawable.c(i);
        }
        if (this.mProgressDrawable != null) {
            this.mProgressDrawable.c(i);
        }
        return self();
    }

    public T setPrimaryColorId(@ColorRes int i) {
        setPrimaryColor(SmartUtil.a(getContext(), i));
        return self();
    }

    public T setAccentColorId(@ColorRes int i) {
        setAccentColor(SmartUtil.a(getContext(), i));
        return self();
    }

    public T setFinishDuration(int i) {
        this.mFinishDuration = i;
        return self();
    }

    public T setTextSizeTitle(float f) {
        this.mTitleText.setTextSize(f);
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.a((RefreshInternal) this);
        }
        return self();
    }

    public T setDrawableMarginRight(float f) {
        ImageView imageView = this.mArrowView;
        ImageView imageView2 = this.mProgressView;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) imageView2.getLayoutParams();
        int a2 = DensityUtil.a(f);
        marginLayoutParams2.rightMargin = a2;
        marginLayoutParams.rightMargin = a2;
        imageView.setLayoutParams(marginLayoutParams);
        imageView2.setLayoutParams(marginLayoutParams2);
        return self();
    }

    public T setDrawableSize(float f) {
        ImageView imageView = this.mArrowView;
        ImageView imageView2 = this.mProgressView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = imageView2.getLayoutParams();
        int a2 = DensityUtil.a(f);
        layoutParams2.width = a2;
        layoutParams.width = a2;
        int a3 = DensityUtil.a(f);
        layoutParams2.height = a3;
        layoutParams.height = a3;
        imageView.setLayoutParams(layoutParams);
        imageView2.setLayoutParams(layoutParams2);
        return self();
    }

    public T setDrawableArrowSize(float f) {
        ImageView imageView = this.mArrowView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int a2 = DensityUtil.a(f);
        layoutParams.width = a2;
        layoutParams.height = a2;
        imageView.setLayoutParams(layoutParams);
        return self();
    }

    public T setDrawableProgressSize(float f) {
        ImageView imageView = this.mProgressView;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        int a2 = DensityUtil.a(f);
        layoutParams.width = a2;
        layoutParams.height = a2;
        imageView.setLayoutParams(layoutParams);
        return self();
    }
}
