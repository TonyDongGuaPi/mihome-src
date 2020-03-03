package com.taobao.weex.ui.view.refresh.circlebar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ImageView;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class CircleProgressBar extends ImageView {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int DEFAULT_CIRCLE_BG_LIGHT = -328966;
    public static final int DEFAULT_CIRCLE_COLOR = -1048576;
    private static final int DEFAULT_CIRCLE_DIAMETER = 40;
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final int STROKE_WIDTH_LARGE = 3;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private int mArrowHeight;
    private int mArrowWidth;
    private int mBackGroundColor;
    private ShapeDrawable mBgCircle;
    private boolean mCircleBackgroundEnabled;
    private int[] mColors = {-16777216};
    private int mDiameter;
    private int mInnerRadius;
    private Animation.AnimationListener mListener;
    private int mMax;
    private int mProgress;
    private int mProgressColor;
    public MaterialProgressDrawable mProgressDrawable;
    private int mProgressStokeWidth;
    private int mShadowRadius;
    private boolean mShowArrow;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7264903814816451936L, "com/taobao/weex/ui/view/refresh/circlebar/CircleProgressBar", 98);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircleProgressBar(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        init(context, (AttributeSet) null, 0);
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
        init(context, attributeSet, 0);
        $jacocoInit[3] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CircleProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[4] = true;
        init(context, attributeSet, i);
        $jacocoInit[5] = true;
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = getContext().getResources().getDisplayMetrics().density;
        this.mBackGroundColor = DEFAULT_CIRCLE_BG_LIGHT;
        this.mProgressColor = DEFAULT_CIRCLE_COLOR;
        this.mColors = new int[]{this.mProgressColor};
        this.mInnerRadius = -1;
        this.mProgressStokeWidth = (int) (f * 3.0f);
        this.mArrowWidth = -1;
        this.mArrowHeight = -1;
        this.mShowArrow = true;
        this.mCircleBackgroundEnabled = true;
        this.mProgress = 0;
        this.mMax = 100;
        $jacocoInit[6] = true;
        this.mProgressDrawable = new MaterialProgressDrawable(getContext(), this);
        $jacocoInit[7] = true;
        super.setImageDrawable(this.mProgressDrawable);
        $jacocoInit[8] = true;
    }

    public void setProgressBackGroundColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mBackGroundColor = i;
        $jacocoInit[9] = true;
    }

    private boolean elevationSupported() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 21) {
            $jacocoInit[10] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
        return z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onMeasure(i, i2);
        $jacocoInit[13] = true;
        if (elevationSupported()) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            setMeasuredDimension(getMeasuredWidth() + (this.mShadowRadius * 2), getMeasuredHeight() + (this.mShadowRadius * 2));
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
    }

    public int getProgressStokeWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mProgressStokeWidth;
        $jacocoInit[18] = true;
        return i;
    }

    public void setProgressStokeWidth(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mProgressStokeWidth = (int) (((float) i) * getContext().getResources().getDisplayMetrics().density);
        $jacocoInit[19] = true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onLayout(z, i, i2, i3, i4);
        $jacocoInit[20] = true;
        float f = getContext().getResources().getDisplayMetrics().density;
        $jacocoInit[21] = true;
        this.mDiameter = Math.min(getMeasuredWidth(), getMeasuredHeight());
        if (this.mDiameter > 0) {
            $jacocoInit[22] = true;
        } else {
            this.mDiameter = ((int) f) * 40;
            $jacocoInit[23] = true;
        }
        if (getBackground() != null) {
            $jacocoInit[24] = true;
        } else if (!this.mCircleBackgroundEnabled) {
            $jacocoInit[25] = true;
        } else {
            int i5 = (int) (Y_OFFSET * f);
            int i6 = (int) (f * 0.0f);
            this.mShadowRadius = (int) (SHADOW_RADIUS * f);
            $jacocoInit[26] = true;
            if (elevationSupported()) {
                $jacocoInit[27] = true;
                this.mBgCircle = new ShapeDrawable(new OvalShape());
                $jacocoInit[28] = true;
                ViewCompat.setElevation(this, f * 4.0f);
                $jacocoInit[29] = true;
            } else {
                OvalShadow ovalShadow = new OvalShadow(this, this.mShadowRadius, this.mDiameter - (this.mShadowRadius * 2));
                $jacocoInit[30] = true;
                this.mBgCircle = new ShapeDrawable(ovalShadow);
                $jacocoInit[31] = true;
                ViewCompat.setLayerType(this, 1, this.mBgCircle.getPaint());
                $jacocoInit[32] = true;
                this.mBgCircle.getPaint().setShadowLayer((float) this.mShadowRadius, (float) i6, (float) i5, KEY_SHADOW_COLOR);
                int i7 = this.mShadowRadius;
                $jacocoInit[33] = true;
                setPadding(i7, i7, i7, i7);
                $jacocoInit[34] = true;
            }
            this.mBgCircle.getPaint().setColor(this.mBackGroundColor);
            $jacocoInit[35] = true;
            setBackgroundDrawable(this.mBgCircle);
            $jacocoInit[36] = true;
        }
        this.mProgressDrawable.setBackgroundColor(this.mBackGroundColor);
        $jacocoInit[37] = true;
        this.mProgressDrawable.setColorSchemeColors(this.mColors);
        $jacocoInit[38] = true;
        if (!isShowArrow()) {
            $jacocoInit[39] = true;
        } else {
            $jacocoInit[40] = true;
            this.mProgressDrawable.setArrowScale(1.0f);
            $jacocoInit[41] = true;
            this.mProgressDrawable.showArrow(true);
            $jacocoInit[42] = true;
        }
        super.setImageDrawable((Drawable) null);
        $jacocoInit[43] = true;
        super.setImageDrawable(this.mProgressDrawable);
        $jacocoInit[44] = true;
        this.mProgressDrawable.setAlpha(255);
        $jacocoInit[45] = true;
        if (getVisibility() != 0) {
            $jacocoInit[46] = true;
        } else {
            $jacocoInit[47] = true;
            this.mProgressDrawable.setStartEndTrim(0.0f, 0.8f);
            $jacocoInit[48] = true;
        }
        $jacocoInit[49] = true;
    }

    public boolean isShowArrow() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mShowArrow;
        $jacocoInit[50] = true;
        return z;
    }

    public void setShowArrow(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mShowArrow = z;
        $jacocoInit[51] = true;
    }

    public void setAnimationListener(Animation.AnimationListener animationListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mListener = animationListener;
        $jacocoInit[52] = true;
    }

    public void onAnimationStart() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onAnimationStart();
        if (this.mListener == null) {
            $jacocoInit[53] = true;
        } else {
            $jacocoInit[54] = true;
            this.mListener.onAnimationStart(getAnimation());
            $jacocoInit[55] = true;
        }
        $jacocoInit[56] = true;
    }

    public void onAnimationEnd() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onAnimationEnd();
        if (this.mListener == null) {
            $jacocoInit[57] = true;
        } else {
            $jacocoInit[58] = true;
            this.mListener.onAnimationEnd(getAnimation());
            $jacocoInit[59] = true;
        }
        $jacocoInit[60] = true;
    }

    public void setColorSchemeColors(int... iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mColors = iArr;
        if (this.mProgressDrawable == null) {
            $jacocoInit[61] = true;
        } else {
            $jacocoInit[62] = true;
            this.mProgressDrawable.setColorSchemeColors(iArr);
            $jacocoInit[63] = true;
        }
        $jacocoInit[64] = true;
    }

    public void setBackgroundColorResource(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(getBackground() instanceof ShapeDrawable)) {
            $jacocoInit[65] = true;
        } else {
            $jacocoInit[66] = true;
            Resources resources = getResources();
            $jacocoInit[67] = true;
            ((ShapeDrawable) getBackground()).getPaint().setColor(resources.getColor(i));
            $jacocoInit[68] = true;
        }
        $jacocoInit[69] = true;
    }

    public void setBackgroundColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(getBackground() instanceof ShapeDrawable)) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
            $jacocoInit[72] = true;
        }
        $jacocoInit[73] = true;
    }

    public int getMax() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mMax;
        $jacocoInit[74] = true;
        return i;
    }

    public void setMax(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mMax = i;
        $jacocoInit[75] = true;
    }

    public int getProgress() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mProgress;
        $jacocoInit[76] = true;
        return i;
    }

    public void setProgress(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getMax() <= 0) {
            $jacocoInit[77] = true;
        } else {
            this.mProgress = i;
            $jacocoInit[78] = true;
        }
        invalidate();
        $jacocoInit[79] = true;
    }

    public boolean circleBackgroundEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mCircleBackgroundEnabled;
        $jacocoInit[80] = true;
        return z;
    }

    public void setCircleBackgroundEnabled(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mCircleBackgroundEnabled = z;
        $jacocoInit[81] = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        super.onAttachedToWindow();
        if (this.mProgressDrawable == null) {
            $jacocoInit[82] = true;
        } else {
            $jacocoInit[83] = true;
            this.mProgressDrawable.stop();
            $jacocoInit[84] = true;
            MaterialProgressDrawable materialProgressDrawable = this.mProgressDrawable;
            if (getVisibility() == 0) {
                $jacocoInit[85] = true;
                z = true;
            } else {
                $jacocoInit[86] = true;
                z = false;
            }
            materialProgressDrawable.setVisible(z, false);
            $jacocoInit[87] = true;
        }
        $jacocoInit[88] = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDetachedFromWindow();
        if (this.mProgressDrawable == null) {
            $jacocoInit[89] = true;
        } else {
            $jacocoInit[90] = true;
            this.mProgressDrawable.stop();
            $jacocoInit[91] = true;
            this.mProgressDrawable.setVisible(false, false);
            $jacocoInit[92] = true;
        }
        $jacocoInit[93] = true;
    }

    private class OvalShadow extends OvalShape {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private int mCircleDiameter;
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint = new Paint();
        private int mShadowRadius;
        final /* synthetic */ CircleProgressBar this$0;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3614015415678101560L, "com/taobao/weex/ui/view/refresh/circlebar/CircleProgressBar$OvalShadow", 9);
            $jacocoData = a2;
            return a2;
        }

        public OvalShadow(CircleProgressBar circleProgressBar, int i, int i2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.this$0 = circleProgressBar;
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
            this.mShadowRadius = i;
            this.mCircleDiameter = i2;
            $jacocoInit[2] = true;
            this.mRadialGradient = new RadialGradient((float) (this.mCircleDiameter / 2), (float) (this.mCircleDiameter / 2), (float) this.mShadowRadius, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP);
            $jacocoInit[3] = true;
            this.mShadowPaint.setShader(this.mRadialGradient);
            $jacocoInit[4] = true;
        }

        public void draw(Canvas canvas, Paint paint) {
            boolean[] $jacocoInit = $jacocoInit();
            int width = this.this$0.getWidth();
            $jacocoInit[5] = true;
            int height = this.this$0.getHeight();
            $jacocoInit[6] = true;
            float f = (float) (width / 2);
            float f2 = (float) (height / 2);
            canvas.drawCircle(f, f2, (float) ((this.mCircleDiameter / 2) + this.mShadowRadius), this.mShadowPaint);
            $jacocoInit[7] = true;
            canvas.drawCircle(f, f2, (float) (this.mCircleDiameter / 2), paint);
            $jacocoInit[8] = true;
        }
    }

    public void start() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mProgressDrawable.start();
        $jacocoInit[94] = true;
    }

    public void setStartEndTrim(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mProgressDrawable.setStartEndTrim(f, f2);
        $jacocoInit[95] = true;
    }

    public void stop() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mProgressDrawable.stop();
        $jacocoInit[96] = true;
    }

    public void setProgressRotation(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mProgressDrawable.setProgressRotation(f);
        $jacocoInit[97] = true;
    }
}
