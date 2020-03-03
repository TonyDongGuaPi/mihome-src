package com.mi.global.shop.widget.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.mi.global.shop.R;

public class FlipLoadingLayout extends LoadingLayout {
    static final int FLIP_ANIMATION_DURATION = 150;

    /* renamed from: a  reason: collision with root package name */
    private final Animation f7271a = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
    private final Animation b;

    private float getDrawableRotationAngle() {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public void onPullImpl(float f) {
    }

    public FlipLoadingLayout(Context context, TypedArray typedArray) {
        super(context, typedArray, false);
        this.f7271a.setInterpolator(ANIMATION_INTERPOLATOR);
        this.f7271a.setDuration(150);
        this.f7271a.setFillAfter(true);
        this.b = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.b.setInterpolator(ANIMATION_INTERPOLATOR);
        this.b.setDuration(150);
        this.b.setFillAfter(true);
    }

    /* access modifiers changed from: protected */
    public void onLoadingDrawableSet(Drawable drawable) {
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            ViewGroup.LayoutParams layoutParams = this.mHeaderImage.getLayoutParams();
            int max = Math.max(intrinsicHeight, intrinsicWidth);
            layoutParams.height = max;
            layoutParams.width = max;
            this.mHeaderImage.requestLayout();
            this.mHeaderImage.setScaleType(ImageView.ScaleType.MATRIX);
            Matrix matrix = new Matrix();
            matrix.postTranslate(((float) (layoutParams.width - intrinsicWidth)) / 2.0f, ((float) (layoutParams.height - intrinsicHeight)) / 2.0f);
            matrix.postRotate(getDrawableRotationAngle(), ((float) layoutParams.width) / 2.0f, ((float) layoutParams.height) / 2.0f);
            this.mHeaderImage.setImageMatrix(matrix);
        }
    }

    /* access modifiers changed from: protected */
    public void pullToRefreshImpl() {
        if (this.f7271a == this.mHeaderImage.getAnimation()) {
            this.mHeaderImage.startAnimation(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshingImpl() {
        this.mHeaderImage.clearAnimation();
        this.mHeaderImage.setVisibility(4);
        this.mHeaderProgress.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void releaseToRefreshImpl() {
        this.mHeaderImage.startAnimation(this.f7271a);
    }

    /* access modifiers changed from: protected */
    public void resetImpl() {
        this.mHeaderImage.clearAnimation();
        this.mHeaderProgress.setVisibility(8);
        this.mHeaderImage.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public int getDefaultDrawableResId() {
        return R.drawable.shop_icon_pulltorefresh_flip;
    }
}
