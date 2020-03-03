package com.xiaomi.shopviews.adapter.bigvision;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.shopviews.widget.R;

public class GravityImageView extends ImageView {
    public static final byte ORIENTATION_HORIZONTAL = 0;
    public static final byte ORIENTATION_NONE = -1;
    public static final byte ORIENTATION_VERTICAL = 1;
    private int mDrawableHeight;
    private int mDrawableWidth;
    private boolean mEnablePanoramaMode;
    private boolean mEnableScrollbar;
    private int mHeight;
    private boolean mInvertScrollDirection;
    private float mMaxOffset;
    private OnPanoramaScrollListener mOnPanoramaScrollListener;
    private byte mOrientation;
    private Paint mScrollbarPaint;
    private int mWidth;
    private float mXProgress;
    private float mYProgress;

    public interface OnPanoramaScrollListener {
        void a(GravityImageView gravityImageView, float f, float f2);
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
    }

    public GravityImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GravityImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GravityImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOrientation = -1;
        super.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GravityImageView);
        this.mEnablePanoramaMode = obtainStyledAttributes.getBoolean(R.styleable.GravityImageView_piv_enablePanoramaMode, true);
        this.mInvertScrollDirection = obtainStyledAttributes.getBoolean(R.styleable.GravityImageView_piv_invertScrollDirection, false);
        this.mEnableScrollbar = obtainStyledAttributes.getBoolean(R.styleable.GravityImageView_piv_show_scrollbar, true);
        obtainStyledAttributes.recycle();
        if (this.mEnableScrollbar) {
            initScrollbarPaint();
        }
    }

    private void initScrollbarPaint() {
        this.mScrollbarPaint = new Paint(1);
        this.mScrollbarPaint.setColor(-1);
        this.mScrollbarPaint.setStrokeWidth(dp2px(1.5f));
    }

    public void setGyroscopeObserver(GyroscopeObserver gyroscopeObserver) {
        if (gyroscopeObserver != null) {
            gyroscopeObserver.a(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateProgress(float f, float f2) {
        if (this.mEnablePanoramaMode) {
            if (this.mInvertScrollDirection) {
                f = -f;
            }
            this.mXProgress = f;
            if (this.mInvertScrollDirection) {
                f2 = -f2;
            }
            this.mYProgress = f2;
            invalidate();
            if (this.mOnPanoramaScrollListener != null) {
                this.mOnPanoramaScrollListener.a(this, -this.mXProgress, -this.mYProgress);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        this.mHeight = (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        if (getDrawable() != null) {
            this.mDrawableWidth = getDrawable().getIntrinsicWidth();
            this.mDrawableHeight = getDrawable().getIntrinsicHeight();
            if (this.mDrawableWidth * this.mHeight > this.mDrawableHeight * this.mWidth) {
                this.mMaxOffset = Math.abs(((((float) this.mDrawableWidth) * (((float) this.mHeight) / ((float) this.mDrawableHeight))) - ((float) this.mWidth)) * 0.5f);
            } else if (this.mDrawableWidth * this.mHeight < this.mDrawableHeight * this.mWidth) {
                this.mMaxOffset = Math.abs(((((float) this.mDrawableHeight) * (((float) this.mWidth) / ((float) this.mDrawableWidth))) - ((float) this.mHeight)) * 0.5f);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.mEnablePanoramaMode || getDrawable() == null || isInEditMode()) {
            super.onDraw(canvas);
            return;
        }
        float f = this.mMaxOffset * this.mXProgress;
        canvas.save();
        canvas.translate(this.mMaxOffset * this.mYProgress, f);
        super.onDraw(canvas);
        canvas.restore();
    }

    private float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public void setEnablePanoramaMode(boolean z) {
        this.mEnablePanoramaMode = z;
    }

    public boolean isPanoramaModeEnabled() {
        return this.mEnablePanoramaMode;
    }

    public void setInvertScrollDirection(boolean z) {
        if (this.mInvertScrollDirection != z) {
            this.mInvertScrollDirection = z;
        }
    }

    public boolean isInvertScrollDirection() {
        return this.mInvertScrollDirection;
    }

    public void setEnableScrollbar(boolean z) {
        if (this.mEnableScrollbar != z) {
            this.mEnableScrollbar = z;
            if (this.mEnableScrollbar) {
                initScrollbarPaint();
            } else {
                this.mScrollbarPaint = null;
            }
        }
    }

    public boolean isScrollbarEnabled() {
        return this.mEnableScrollbar;
    }

    public byte getOrientation() {
        return this.mOrientation;
    }

    public byte setOrientation(byte b) {
        this.mOrientation = b;
        return b;
    }

    public void setOnPanoramaScrollListener(OnPanoramaScrollListener onPanoramaScrollListener) {
        this.mOnPanoramaScrollListener = onPanoramaScrollListener;
    }
}
