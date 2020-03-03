package com.scwang.smartrefresh.layout.header;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;

public class BezierRadarHeader extends InternalAbstract implements RefreshHeader {
    protected static final byte PROPERTY_DOT_ALPHA = 2;
    protected static final byte PROPERTY_RADAR_ANGLE = 4;
    protected static final byte PROPERTY_RADAR_SCALE = 0;
    protected static final byte PROPERTY_RIPPLE_RADIUS = 3;
    protected static final byte PROPERTY_WAVE_HEIGHT = 1;
    protected int mAccentColor;
    protected Animator mAnimatorSet;
    protected float mDotAlpha;
    protected float mDotFraction;
    protected float mDotRadius;
    protected boolean mEnableHorizontalDrag;
    protected boolean mManualAccentColor;
    protected boolean mManualPrimaryColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mPrimaryColor;
    protected int mRadarAngle;
    protected float mRadarCircle;
    protected float mRadarRadius;
    protected RectF mRadarRect;
    protected float mRadarScale;
    protected float mRippleRadius;
    protected int mWaveHeight;
    protected int mWaveOffsetX;
    protected boolean mWavePulling;
    protected int mWaveTop;

    public BezierRadarHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnableHorizontalDrag = false;
        this.mWaveOffsetX = -1;
        this.mRadarAngle = 0;
        this.mRadarRadius = 0.0f;
        this.mRadarCircle = 0.0f;
        this.mRadarScale = 0.0f;
        this.mRadarRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mSpinnerStyle = SpinnerStyle.Scale;
        DensityUtil densityUtil = new DensityUtil();
        this.mPath = new Path();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mDotRadius = (float) densityUtil.b(7.0f);
        this.mRadarRadius = (float) densityUtil.b(20.0f);
        this.mRadarCircle = (float) densityUtil.b(7.0f);
        this.mPaint.setStrokeWidth((float) densityUtil.b(3.0f));
        setMinimumHeight(densityUtil.b(100.0f));
        if (isInEditMode()) {
            this.mWaveTop = 1000;
            this.mRadarScale = 1.0f;
            this.mRadarAngle = 270;
        } else {
            this.mRadarScale = 0.0f;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BezierRadarHeader);
        this.mEnableHorizontalDrag = obtainStyledAttributes.getBoolean(R.styleable.BezierRadarHeader_srlEnableHorizontalDrag, this.mEnableHorizontalDrag);
        setAccentColor(obtainStyledAttributes.getColor(R.styleable.BezierRadarHeader_srlAccentColor, -1));
        setPrimaryColor(obtainStyledAttributes.getColor(R.styleable.BezierRadarHeader_srlPrimaryColor, -14540254));
        this.mManualAccentColor = obtainStyledAttributes.hasValue(R.styleable.BezierRadarHeader_srlAccentColor);
        this.mManualPrimaryColor = obtainStyledAttributes.hasValue(R.styleable.BezierRadarHeader_srlPrimaryColor);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.removeAllListeners();
            this.mAnimatorSet.end();
            this.mAnimatorSet = null;
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        drawWave(canvas, width);
        drawDot(canvas, width, height);
        drawRadar(canvas, width, height);
        drawRipple(canvas, width, height);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawWave(Canvas canvas, int i) {
        this.mPath.reset();
        this.mPath.lineTo(0.0f, (float) this.mWaveTop);
        Path path = this.mPath;
        float f = (float) (this.mWaveOffsetX >= 0 ? this.mWaveOffsetX : i / 2);
        float f2 = (float) i;
        path.quadTo(f, (float) (this.mWaveTop + this.mWaveHeight), f2, (float) this.mWaveTop);
        this.mPath.lineTo(f2, 0.0f);
        this.mPaint.setColor(this.mPrimaryColor);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void drawDot(Canvas canvas, int i, int i2) {
        float f = 0.0f;
        if (this.mDotAlpha > 0.0f) {
            this.mPaint.setColor(this.mAccentColor);
            float a2 = DensityUtil.a(i2);
            float f2 = (float) (i / 7);
            float f3 = 1.0f;
            float f4 = (this.mDotFraction * f2) - (this.mDotFraction > 1.0f ? ((this.mDotFraction - 1.0f) * f2) / this.mDotFraction : 0.0f);
            float f5 = (float) i2;
            float f6 = 2.0f;
            if (this.mDotFraction > 1.0f) {
                f = (((this.mDotFraction - 1.0f) * f5) / 2.0f) / this.mDotFraction;
            }
            float f7 = f5 - f;
            int i3 = 0;
            while (i3 < 7) {
                float f8 = (((float) i3) + f3) - 4.0f;
                Paint paint = this.mPaint;
                double abs = (double) (this.mDotAlpha * (f3 - ((Math.abs(f8) / 7.0f) * f6)) * 255.0f);
                double d = (double) a2;
                Double.isNaN(d);
                Double.isNaN(abs);
                paint.setAlpha((int) (abs * (1.0d - (1.0d / Math.pow((d / 800.0d) + 1.0d, 15.0d)))));
                float f9 = this.mDotRadius * (1.0f - (1.0f / ((a2 / 10.0f) + 1.0f)));
                canvas.drawCircle((((float) (i / 2)) - (f9 / 2.0f)) + (f8 * f4), f7 / 2.0f, f9, this.mPaint);
                i3++;
                f3 = 1.0f;
                f6 = 2.0f;
            }
            this.mPaint.setAlpha(255);
        }
    }

    /* access modifiers changed from: protected */
    public void drawRadar(Canvas canvas, int i, int i2) {
        if (this.mAnimatorSet != null || isInEditMode()) {
            float f = this.mRadarRadius * this.mRadarScale;
            float f2 = this.mRadarCircle * this.mRadarScale;
            this.mPaint.setColor(this.mAccentColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            float f3 = (float) (i / 2);
            float f4 = (float) (i2 / 2);
            canvas.drawCircle(f3, f4, f, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float f5 = f2 + f;
            canvas.drawCircle(f3, f4, f5, this.mPaint);
            this.mPaint.setColor((this.mPrimaryColor & 16777215) | ReactBaseTextShadowNode.DEFAULT_TEXT_SHADOW_COLOR);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mRadarRect.set(f3 - f, f4 - f, f3 + f, f + f4);
            canvas.drawArc(this.mRadarRect, 270.0f, (float) this.mRadarAngle, true, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mRadarRect.set(f3 - f5, f4 - f5, f3 + f5, f4 + f5);
            canvas.drawArc(this.mRadarRect, 270.0f, (float) this.mRadarAngle, false, this.mPaint);
            this.mPaint.setStyle(Paint.Style.FILL);
        }
    }

    /* access modifiers changed from: protected */
    public void drawRipple(Canvas canvas, int i, int i2) {
        if (this.mRippleRadius > 0.0f) {
            this.mPaint.setColor(this.mAccentColor);
            canvas.drawCircle((float) (i / 2), (float) (i2 / 2), this.mRippleRadius, this.mPaint);
        }
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (z || this.mWavePulling) {
            this.mWavePulling = true;
            this.mWaveTop = Math.min(i2, i);
            this.mWaveHeight = (int) (((float) Math.max(0, i - i2)) * 1.9f);
            this.mDotFraction = f;
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mWaveTop = i;
        this.mWavePulling = false;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 360});
        ofInt.setDuration(720);
        ofInt.setRepeatCount(-1);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.addUpdateListener(new AnimatorUpdater((byte) 4));
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat.addUpdateListener(new AnimatorUpdater((byte) 2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat2.addUpdateListener(new AnimatorUpdater((byte) 0));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{ofFloat, ofFloat2, ofInt});
        animatorSet.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{this.mWaveHeight, 0, -((int) (((float) this.mWaveHeight) * 0.8f)), 0, -((int) (((float) this.mWaveHeight) * 0.4f)), 0});
        ofInt2.addUpdateListener(new AnimatorUpdater((byte) 1));
        ofInt2.setInterpolator(decelerateInterpolator);
        ofInt2.setDuration(800);
        ofInt2.start();
        this.mAnimatorSet = animatorSet;
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.removeAllListeners();
            this.mAnimatorSet.end();
            this.mAnimatorSet = null;
        }
        int width = getWidth();
        int height = getHeight();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, (float) Math.sqrt((double) ((width * width) + (height * height)))});
        ofFloat.setDuration(400);
        ofFloat.addUpdateListener(new AnimatorUpdater((byte) 3));
        ofFloat.start();
        return 400;
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        switch (refreshState2) {
            case None:
            case PullDownToRefresh:
                this.mDotAlpha = 1.0f;
                this.mRadarScale = 0.0f;
                this.mRippleRadius = 0.0f;
                return;
            default:
                return;
        }
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0 && !this.mManualPrimaryColor) {
            setPrimaryColor(iArr[0]);
            this.mManualPrimaryColor = false;
        }
        if (iArr.length > 1 && !this.mManualAccentColor) {
            setAccentColor(iArr[1]);
            this.mManualAccentColor = false;
        }
    }

    public boolean isSupportHorizontalDrag() {
        return this.mEnableHorizontalDrag;
    }

    public void onHorizontalDrag(float f, int i, int i2) {
        this.mWaveOffsetX = i;
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    public BezierRadarHeader setPrimaryColor(@ColorInt int i) {
        this.mPrimaryColor = i;
        this.mManualPrimaryColor = true;
        return this;
    }

    public BezierRadarHeader setAccentColor(@ColorInt int i) {
        this.mAccentColor = i;
        this.mManualAccentColor = true;
        return this;
    }

    public BezierRadarHeader setPrimaryColorId(@ColorRes int i) {
        setPrimaryColor(SmartUtil.a(getContext(), i));
        return this;
    }

    public BezierRadarHeader setAccentColorId(@ColorRes int i) {
        setAccentColor(SmartUtil.a(getContext(), i));
        return this;
    }

    public BezierRadarHeader setEnableHorizontalDrag(boolean z) {
        this.mEnableHorizontalDrag = z;
        if (!z) {
            this.mWaveOffsetX = -1;
        }
        return this;
    }

    protected class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a  reason: collision with root package name */
        byte f8793a;

        AnimatorUpdater(byte b2) {
            this.f8793a = b2;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.f8793a == 0) {
                BezierRadarHeader.this.mRadarScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (1 == this.f8793a) {
                if (BezierRadarHeader.this.mWavePulling) {
                    valueAnimator.cancel();
                    return;
                } else {
                    BezierRadarHeader.this.mWaveHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue() / 2;
                }
            } else if (2 == this.f8793a) {
                BezierRadarHeader.this.mDotAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (3 == this.f8793a) {
                BezierRadarHeader.this.mRippleRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (4 == this.f8793a) {
                BezierRadarHeader.this.mRadarAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
            BezierRadarHeader.this.invalidate();
        }
    }
}
