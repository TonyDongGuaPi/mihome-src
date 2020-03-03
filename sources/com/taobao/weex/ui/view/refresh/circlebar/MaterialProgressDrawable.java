package com.taobao.weex.ui.view.refresh.circlebar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class MaterialProgressDrawable extends Drawable implements Animatable {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final int ANIMATION_DURATION = 1332;
    private static final int ARROW_HEIGHT = 5;
    private static final int ARROW_HEIGHT_LARGE = 6;
    private static final float ARROW_OFFSET_ANGLE = 5.0f;
    private static final int ARROW_WIDTH = 10;
    private static final int ARROW_WIDTH_LARGE = 12;
    private static final float CENTER_RADIUS = 8.75f;
    private static final float CENTER_RADIUS_LARGE = 12.5f;
    private static final int CIRCLE_DIAMETER = 40;
    private static final int CIRCLE_DIAMETER_LARGE = 56;
    private static final float COLOR_START_DELAY_OFFSET = 0.75f;
    static final int DEFAULT = 1;
    private static final float END_TRIM_START_DELAY_OFFSET = 0.5f;
    private static final float FULL_ROTATION = 1080.0f;
    static final int LARGE = 0;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final float MAX_PROGRESS_ARC = 0.8f;
    private static final float NUM_POINTS = 5.0f;
    private static final float START_TRIM_DURATION_OFFSET = 0.5f;
    private static final float STROKE_WIDTH = 2.5f;
    private static final float STROKE_WIDTH_LARGE = 3.0f;
    private final int[] COLORS = {-16777216};
    private Animation mAnimation;
    private final ArrayList<Animation> mAnimators;
    private final Drawable.Callback mCallback;
    boolean mFinishing;
    private double mHeight;
    private View mParent;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private float mRotationCount;
    private double mWidth;

    @Retention(RetentionPolicy.CLASS)
    public @interface ProgressDrawableSize {
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8391320790954308896L, "com/taobao/weex/ui/view/refresh/circlebar/MaterialProgressDrawable", 100);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ void access$000(MaterialProgressDrawable materialProgressDrawable, float f, Ring ring) {
        boolean[] $jacocoInit = $jacocoInit();
        materialProgressDrawable.applyFinishTranslation(f, ring);
        $jacocoInit[92] = true;
    }

    static /* synthetic */ float access$100(MaterialProgressDrawable materialProgressDrawable, Ring ring) {
        boolean[] $jacocoInit = $jacocoInit();
        float minProgressArc = materialProgressDrawable.getMinProgressArc(ring);
        $jacocoInit[93] = true;
        return minProgressArc;
    }

    static /* synthetic */ void access$200(MaterialProgressDrawable materialProgressDrawable, float f, Ring ring) {
        boolean[] $jacocoInit = $jacocoInit();
        materialProgressDrawable.updateRingColor(f, ring);
        $jacocoInit[94] = true;
    }

    static /* synthetic */ Interpolator access$300() {
        boolean[] $jacocoInit = $jacocoInit();
        Interpolator interpolator = MATERIAL_INTERPOLATOR;
        $jacocoInit[95] = true;
        return interpolator;
    }

    static /* synthetic */ float access$400(MaterialProgressDrawable materialProgressDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        float f = materialProgressDrawable.mRotationCount;
        $jacocoInit[96] = true;
        return f;
    }

    static /* synthetic */ float access$402(MaterialProgressDrawable materialProgressDrawable, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        materialProgressDrawable.mRotationCount = f;
        $jacocoInit[97] = true;
        return f;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[98] = true;
        $jacocoInit[99] = true;
    }

    public MaterialProgressDrawable(Context context, View view) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mAnimators = new ArrayList<>();
        $jacocoInit[1] = true;
        this.mCallback = new Drawable.Callback(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ MaterialProgressDrawable this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1561744057050839243L, "com/taobao/weex/ui/view/refresh/circlebar/MaterialProgressDrawable$3", 4);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void invalidateDrawable(Drawable drawable) {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0.invalidateSelf();
                $jacocoInit[1] = true;
            }

            public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0.scheduleSelf(runnable, j);
                $jacocoInit[2] = true;
            }

            public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0.unscheduleSelf(runnable);
                $jacocoInit[3] = true;
            }
        };
        this.mParent = view;
        $jacocoInit[2] = true;
        this.mResources = context.getResources();
        $jacocoInit[3] = true;
        this.mRing = new Ring(this.mCallback);
        $jacocoInit[4] = true;
        this.mRing.setColors(this.COLORS);
        $jacocoInit[5] = true;
        updateSizes(1);
        $jacocoInit[6] = true;
        setupAnimators();
        $jacocoInit[7] = true;
    }

    private void setSizeParameters(double d, double d2, double d3, double d4, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        Ring ring = this.mRing;
        $jacocoInit[8] = true;
        float f3 = this.mResources.getDisplayMetrics().density;
        double d5 = (double) f3;
        Double.isNaN(d5);
        this.mWidth = d * d5;
        Double.isNaN(d5);
        this.mHeight = d2 * d5;
        $jacocoInit[9] = true;
        ring.setStrokeWidth(((float) d4) * f3);
        $jacocoInit[10] = true;
        Double.isNaN(d5);
        ring.setCenterRadius(d5 * d3);
        $jacocoInit[11] = true;
        ring.setColorIndex(0);
        $jacocoInit[12] = true;
        ring.setArrowDimensions(f * f3, f3 * f2);
        $jacocoInit[13] = true;
        ring.setInsets((int) this.mWidth, (int) this.mHeight);
        $jacocoInit[14] = true;
    }

    public void updateSizes(@ProgressDrawableSize int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i == 0) {
            $jacocoInit[15] = true;
            setSizeParameters(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
            $jacocoInit[16] = true;
        } else {
            setSizeParameters(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
    }

    public void showArrow(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setShowArrow(z);
        $jacocoInit[19] = true;
    }

    public void setArrowScale(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setArrowScale(f);
        $jacocoInit[20] = true;
    }

    public void setStartEndTrim(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setStartTrim(f);
        $jacocoInit[21] = true;
        this.mRing.setEndTrim(f2);
        $jacocoInit[22] = true;
    }

    public void setProgressRotation(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setRotation(f);
        $jacocoInit[23] = true;
    }

    public void setBackgroundColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setBackgroundColor(i);
        $jacocoInit[24] = true;
    }

    public void setColorSchemeColors(int... iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setColors(iArr);
        $jacocoInit[25] = true;
        this.mRing.setColorIndex(0);
        $jacocoInit[26] = true;
    }

    public int getIntrinsicHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = (int) this.mHeight;
        $jacocoInit[27] = true;
        return i;
    }

    public int getIntrinsicWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = (int) this.mWidth;
        $jacocoInit[28] = true;
        return i;
    }

    public void draw(Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        Rect bounds = getBounds();
        $jacocoInit[29] = true;
        int save = canvas.save();
        $jacocoInit[30] = true;
        canvas.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        $jacocoInit[31] = true;
        this.mRing.draw(canvas, bounds);
        $jacocoInit[32] = true;
        canvas.restoreToCount(save);
        $jacocoInit[33] = true;
    }

    public void setAlpha(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setAlpha(i);
        $jacocoInit[34] = true;
    }

    public int getAlpha() {
        boolean[] $jacocoInit = $jacocoInit();
        int alpha = this.mRing.getAlpha();
        $jacocoInit[35] = true;
        return alpha;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRing.setColorFilter(colorFilter);
        $jacocoInit[36] = true;
    }

    /* access modifiers changed from: package-private */
    public void setRotation(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRotation = f;
        $jacocoInit[37] = true;
        invalidateSelf();
        $jacocoInit[38] = true;
    }

    private float getRotation() {
        boolean[] $jacocoInit = $jacocoInit();
        float f = this.mRotation;
        $jacocoInit[39] = true;
        return f;
    }

    public int getOpacity() {
        $jacocoInit()[40] = true;
        return -3;
    }

    public boolean isRunning() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList<Animation> arrayList = this.mAnimators;
        $jacocoInit[41] = true;
        int size = arrayList.size();
        $jacocoInit[42] = true;
        int i = 0;
        while (i < size) {
            $jacocoInit[43] = true;
            Animation animation = arrayList.get(i);
            $jacocoInit[44] = true;
            if (!animation.hasStarted()) {
                $jacocoInit[45] = true;
            } else if (animation.hasEnded()) {
                $jacocoInit[46] = true;
            } else {
                $jacocoInit[47] = true;
                return true;
            }
            i++;
            $jacocoInit[48] = true;
        }
        $jacocoInit[49] = true;
        return false;
    }

    public void start() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAnimation.reset();
        $jacocoInit[50] = true;
        this.mRing.storeOriginals();
        $jacocoInit[51] = true;
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            $jacocoInit[52] = true;
            this.mAnimation.setDuration(666);
            $jacocoInit[53] = true;
            this.mParent.startAnimation(this.mAnimation);
            $jacocoInit[54] = true;
        } else {
            this.mRing.setColorIndex(0);
            $jacocoInit[55] = true;
            this.mRing.resetOriginals();
            $jacocoInit[56] = true;
            this.mAnimation.setDuration(1332);
            $jacocoInit[57] = true;
            this.mParent.startAnimation(this.mAnimation);
            $jacocoInit[58] = true;
        }
        $jacocoInit[59] = true;
    }

    public void stop() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mParent.clearAnimation();
        $jacocoInit[60] = true;
        setRotation(0.0f);
        $jacocoInit[61] = true;
        this.mRing.setShowArrow(false);
        $jacocoInit[62] = true;
        this.mRing.setColorIndex(0);
        $jacocoInit[63] = true;
        this.mRing.resetOriginals();
        $jacocoInit[64] = true;
    }

    private float getMinProgressArc(Ring ring) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[65] = true;
        double strokeWidth = (double) ring.getStrokeWidth();
        Double.isNaN(strokeWidth);
        $jacocoInit[66] = true;
        float radians = (float) Math.toRadians(strokeWidth / (ring.getCenterRadius() * 6.283185307179586d));
        $jacocoInit[67] = true;
        return radians;
    }

    private int evaluateColorChange(float f, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        int intValue = Integer.valueOf(i).intValue();
        int i3 = (intValue >> 24) & 255;
        int i4 = (intValue >> 16) & 255;
        int i5 = (intValue >> 8) & 255;
        int i6 = intValue & 255;
        $jacocoInit[68] = true;
        int intValue2 = Integer.valueOf(i2).intValue();
        int i7 = ((i3 + ((int) (((float) (((intValue2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((intValue2 >> 16) & 255) - i4)) * f))) << 16) | ((i5 + ((int) (((float) (((intValue2 >> 8) & 255) - i5)) * f))) << 8) | (i6 + ((int) (f * ((float) ((intValue2 & 255) - i6)))));
        $jacocoInit[69] = true;
        return i7;
    }

    private void updateRingColor(float f, Ring ring) {
        boolean[] $jacocoInit = $jacocoInit();
        if (f <= 0.75f) {
            $jacocoInit[70] = true;
        } else {
            $jacocoInit[71] = true;
            int startingColor = ring.getStartingColor();
            $jacocoInit[72] = true;
            int nextColor = ring.getNextColor();
            $jacocoInit[73] = true;
            ring.setColor(evaluateColorChange((f - 0.75f) / 0.25f, startingColor, nextColor));
            $jacocoInit[74] = true;
        }
        $jacocoInit[75] = true;
    }

    private void applyFinishTranslation(float f, Ring ring) {
        boolean[] $jacocoInit = $jacocoInit();
        updateRingColor(f, ring);
        $jacocoInit[76] = true;
        $jacocoInit[77] = true;
        float minProgressArc = getMinProgressArc(ring);
        $jacocoInit[78] = true;
        float startingStartTrim = ring.getStartingStartTrim();
        $jacocoInit[79] = true;
        $jacocoInit[80] = true;
        ring.setStartTrim(startingStartTrim + (((ring.getStartingEndTrim() - minProgressArc) - ring.getStartingStartTrim()) * f));
        $jacocoInit[81] = true;
        ring.setEndTrim(ring.getStartingEndTrim());
        $jacocoInit[82] = true;
        float startingRotation = ring.getStartingRotation();
        $jacocoInit[83] = true;
        $jacocoInit[84] = true;
        ring.setRotation(startingRotation + ((((float) (Math.floor((double) (ring.getStartingRotation() / 0.8f)) + 1.0d)) - ring.getStartingRotation()) * f));
        $jacocoInit[85] = true;
    }

    private void setupAnimators() {
        boolean[] $jacocoInit = $jacocoInit();
        final Ring ring = this.mRing;
        $jacocoInit[86] = true;
        AnonymousClass1 r3 = new Animation(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ MaterialProgressDrawable this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(2824467171617033624L, "com/taobao/weex/ui/view/refresh/circlebar/MaterialProgressDrawable$1", 21);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void applyTransformation(float f, Transformation transformation) {
                boolean[] $jacocoInit = $jacocoInit();
                if (this.this$0.mFinishing) {
                    $jacocoInit[1] = true;
                    MaterialProgressDrawable.access$000(this.this$0, f, ring);
                    $jacocoInit[2] = true;
                } else {
                    float access$100 = MaterialProgressDrawable.access$100(this.this$0, ring);
                    $jacocoInit[3] = true;
                    float startingEndTrim = ring.getStartingEndTrim();
                    $jacocoInit[4] = true;
                    float startingStartTrim = ring.getStartingStartTrim();
                    $jacocoInit[5] = true;
                    float startingRotation = ring.getStartingRotation();
                    $jacocoInit[6] = true;
                    MaterialProgressDrawable.access$200(this.this$0, f, ring);
                    if (f > 0.5f) {
                        $jacocoInit[7] = true;
                    } else {
                        $jacocoInit[8] = true;
                        Interpolator access$300 = MaterialProgressDrawable.access$300();
                        $jacocoInit[9] = true;
                        $jacocoInit[10] = true;
                        ring.setStartTrim(startingStartTrim + ((0.8f - access$100) * access$300.getInterpolation(f / 0.5f)));
                        $jacocoInit[11] = true;
                    }
                    if (f <= 0.5f) {
                        $jacocoInit[12] = true;
                    } else {
                        $jacocoInit[13] = true;
                        Interpolator access$3002 = MaterialProgressDrawable.access$300();
                        $jacocoInit[14] = true;
                        ring.setEndTrim(startingEndTrim + ((0.8f - access$100) * access$3002.getInterpolation((f - 0.5f) / 0.5f)));
                        $jacocoInit[15] = true;
                    }
                    $jacocoInit[16] = true;
                    ring.setRotation(startingRotation + (0.25f * f));
                    MaterialProgressDrawable materialProgressDrawable = this.this$0;
                    $jacocoInit[17] = true;
                    $jacocoInit[18] = true;
                    this.this$0.setRotation((f * 216.0f) + ((MaterialProgressDrawable.access$400(materialProgressDrawable) / 5.0f) * MaterialProgressDrawable.FULL_ROTATION));
                    $jacocoInit[19] = true;
                }
                $jacocoInit[20] = true;
            }
        };
        $jacocoInit[87] = true;
        r3.setRepeatCount(-1);
        $jacocoInit[88] = true;
        r3.setRepeatMode(1);
        $jacocoInit[89] = true;
        r3.setInterpolator(LINEAR_INTERPOLATOR);
        $jacocoInit[90] = true;
        r3.setAnimationListener(new Animation.AnimationListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ MaterialProgressDrawable this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-4838437830847545468L, "com/taobao/weex/ui/view/refresh/circlebar/MaterialProgressDrawable$2", 10);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onAnimationStart(Animation animation) {
                boolean[] $jacocoInit = $jacocoInit();
                MaterialProgressDrawable.access$402(this.this$0, 0.0f);
                $jacocoInit[1] = true;
            }

            public void onAnimationEnd(Animation animation) {
                $jacocoInit()[2] = true;
            }

            public void onAnimationRepeat(Animation animation) {
                boolean[] $jacocoInit = $jacocoInit();
                ring.storeOriginals();
                $jacocoInit[3] = true;
                ring.goToNextColor();
                $jacocoInit[4] = true;
                ring.setStartTrim(ring.getEndTrim());
                if (this.this$0.mFinishing) {
                    this.this$0.mFinishing = false;
                    $jacocoInit[5] = true;
                    animation.setDuration(1332);
                    $jacocoInit[6] = true;
                    ring.setShowArrow(false);
                    $jacocoInit[7] = true;
                } else {
                    MaterialProgressDrawable.access$402(this.this$0, (MaterialProgressDrawable.access$400(this.this$0) + 1.0f) % 5.0f);
                    $jacocoInit[8] = true;
                }
                $jacocoInit[9] = true;
            }
        });
        this.mAnimation = r3;
        $jacocoInit[91] = true;
    }

    private static class Ring {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private int mAlpha;
        private Path mArrow;
        private int mArrowHeight;
        private final Paint mArrowPaint;
        private float mArrowScale;
        private int mArrowWidth;
        private int mBackgroundColor;
        private final Drawable.Callback mCallback;
        private final Paint mCirclePaint;
        private int mColorIndex;
        private int[] mColors;
        private int mCurrentColor;
        private float mEndTrim;
        private final Paint mPaint;
        private double mRingCenterRadius;
        private float mRotation;
        private boolean mShowArrow;
        private float mStartTrim;
        private float mStartingEndTrim;
        private float mStartingRotation;
        private float mStartingStartTrim;
        private float mStrokeInset;
        private float mStrokeWidth;
        private final RectF mTempBounds = new RectF();

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-1905808208831780195L, "com/taobao/weex/ui/view/refresh/circlebar/MaterialProgressDrawable$Ring", 92);
            $jacocoData = a2;
            return a2;
        }

        public Ring(Drawable.Callback callback) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
            this.mPaint = new Paint();
            $jacocoInit[2] = true;
            this.mArrowPaint = new Paint();
            this.mStartTrim = 0.0f;
            this.mEndTrim = 0.0f;
            this.mRotation = 0.0f;
            this.mStrokeWidth = 5.0f;
            this.mStrokeInset = MaterialProgressDrawable.STROKE_WIDTH;
            $jacocoInit[3] = true;
            this.mCirclePaint = new Paint(1);
            this.mCallback = callback;
            $jacocoInit[4] = true;
            this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
            $jacocoInit[5] = true;
            this.mPaint.setAntiAlias(true);
            $jacocoInit[6] = true;
            this.mPaint.setStyle(Paint.Style.STROKE);
            $jacocoInit[7] = true;
            this.mArrowPaint.setStyle(Paint.Style.FILL);
            $jacocoInit[8] = true;
            this.mArrowPaint.setAntiAlias(true);
            $jacocoInit[9] = true;
        }

        public void setBackgroundColor(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mBackgroundColor = i;
            $jacocoInit[10] = true;
        }

        public void setArrowDimensions(float f, float f2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mArrowWidth = (int) f;
            this.mArrowHeight = (int) f2;
            $jacocoInit[11] = true;
        }

        public void draw(Canvas canvas, Rect rect) {
            boolean[] $jacocoInit = $jacocoInit();
            RectF rectF = this.mTempBounds;
            $jacocoInit[12] = true;
            rectF.set(rect);
            $jacocoInit[13] = true;
            rectF.inset(this.mStrokeInset, this.mStrokeInset);
            float f = (this.mStartTrim + this.mRotation) * 360.0f;
            float f2 = ((this.mEndTrim + this.mRotation) * 360.0f) - f;
            $jacocoInit[14] = true;
            this.mPaint.setColor(this.mCurrentColor);
            $jacocoInit[15] = true;
            canvas.drawArc(rectF, f, f2, false, this.mPaint);
            $jacocoInit[16] = true;
            drawTriangle(canvas, f, f2, rect);
            if (this.mAlpha >= 255) {
                $jacocoInit[17] = true;
            } else {
                $jacocoInit[18] = true;
                this.mCirclePaint.setColor(this.mBackgroundColor);
                $jacocoInit[19] = true;
                this.mCirclePaint.setAlpha(255 - this.mAlpha);
                $jacocoInit[20] = true;
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.mCirclePaint);
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
        }

        private void drawTriangle(Canvas canvas, float f, float f2, Rect rect) {
            boolean[] $jacocoInit = $jacocoInit();
            if (!this.mShowArrow) {
                $jacocoInit[23] = true;
            } else {
                if (this.mArrow == null) {
                    $jacocoInit[24] = true;
                    this.mArrow = new Path();
                    $jacocoInit[25] = true;
                    this.mArrow.setFillType(Path.FillType.EVEN_ODD);
                    $jacocoInit[26] = true;
                } else {
                    this.mArrow.reset();
                    $jacocoInit[27] = true;
                }
                float f3 = ((float) (((int) this.mStrokeInset) / 2)) * this.mArrowScale;
                $jacocoInit[28] = true;
                double cos = this.mRingCenterRadius * Math.cos(0.0d);
                double exactCenterX = (double) rect.exactCenterX();
                Double.isNaN(exactCenterX);
                $jacocoInit[29] = true;
                double sin = this.mRingCenterRadius * Math.sin(0.0d);
                double exactCenterY = (double) rect.exactCenterY();
                Double.isNaN(exactCenterY);
                $jacocoInit[30] = true;
                this.mArrow.moveTo(0.0f, 0.0f);
                $jacocoInit[31] = true;
                this.mArrow.lineTo(((float) this.mArrowWidth) * this.mArrowScale, 0.0f);
                $jacocoInit[32] = true;
                this.mArrow.lineTo((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, ((float) this.mArrowHeight) * this.mArrowScale);
                $jacocoInit[33] = true;
                this.mArrow.offset(((float) (cos + exactCenterX)) - f3, (float) (sin + exactCenterY));
                $jacocoInit[34] = true;
                this.mArrow.close();
                $jacocoInit[35] = true;
                this.mArrowPaint.setColor(this.mCurrentColor);
                $jacocoInit[36] = true;
                float exactCenterX2 = rect.exactCenterX();
                $jacocoInit[37] = true;
                float exactCenterY2 = rect.exactCenterY();
                $jacocoInit[38] = true;
                canvas.rotate((f + f2) - 5.0f, exactCenterX2, exactCenterY2);
                $jacocoInit[39] = true;
                canvas.drawPath(this.mArrow, this.mArrowPaint);
                $jacocoInit[40] = true;
            }
            $jacocoInit[41] = true;
        }

        public void setColors(@NonNull int[] iArr) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mColors = iArr;
            $jacocoInit[42] = true;
            setColorIndex(0);
            $jacocoInit[43] = true;
        }

        public void setColor(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mCurrentColor = i;
            $jacocoInit[44] = true;
        }

        public void setColorIndex(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mColorIndex = i;
            this.mCurrentColor = this.mColors[this.mColorIndex];
            $jacocoInit[45] = true;
        }

        public int getNextColor() {
            boolean[] $jacocoInit = $jacocoInit();
            int i = this.mColors[getNextColorIndex()];
            $jacocoInit[46] = true;
            return i;
        }

        private int getNextColorIndex() {
            boolean[] $jacocoInit = $jacocoInit();
            int length = (this.mColorIndex + 1) % this.mColors.length;
            $jacocoInit[47] = true;
            return length;
        }

        public void goToNextColor() {
            boolean[] $jacocoInit = $jacocoInit();
            setColorIndex(getNextColorIndex());
            $jacocoInit[48] = true;
        }

        public void setColorFilter(ColorFilter colorFilter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mPaint.setColorFilter(colorFilter);
            $jacocoInit[49] = true;
            invalidateSelf();
            $jacocoInit[50] = true;
        }

        public void setAlpha(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mAlpha = i;
            $jacocoInit[51] = true;
        }

        public int getAlpha() {
            boolean[] $jacocoInit = $jacocoInit();
            int i = this.mAlpha;
            $jacocoInit[52] = true;
            return i;
        }

        public void setStrokeWidth(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mStrokeWidth = f;
            $jacocoInit[53] = true;
            this.mPaint.setStrokeWidth(f);
            $jacocoInit[54] = true;
            invalidateSelf();
            $jacocoInit[55] = true;
        }

        public float getStrokeWidth() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mStrokeWidth;
            $jacocoInit[56] = true;
            return f;
        }

        public void setStartTrim(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mStartTrim = f;
            $jacocoInit[57] = true;
            invalidateSelf();
            $jacocoInit[58] = true;
        }

        public float getStartTrim() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mStartTrim;
            $jacocoInit[59] = true;
            return f;
        }

        public float getStartingStartTrim() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mStartingStartTrim;
            $jacocoInit[60] = true;
            return f;
        }

        public float getStartingEndTrim() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mStartingEndTrim;
            $jacocoInit[61] = true;
            return f;
        }

        public int getStartingColor() {
            boolean[] $jacocoInit = $jacocoInit();
            int i = this.mColors[this.mColorIndex];
            $jacocoInit[62] = true;
            return i;
        }

        public void setEndTrim(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mEndTrim = f;
            $jacocoInit[63] = true;
            invalidateSelf();
            $jacocoInit[64] = true;
        }

        public float getEndTrim() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mEndTrim;
            $jacocoInit[65] = true;
            return f;
        }

        public void setRotation(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mRotation = f;
            $jacocoInit[66] = true;
            invalidateSelf();
            $jacocoInit[67] = true;
        }

        public float getRotation() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mRotation;
            $jacocoInit[68] = true;
            return f;
        }

        public void setInsets(int i, int i2) {
            float f;
            boolean[] $jacocoInit = $jacocoInit();
            float min = (float) Math.min(i, i2);
            if (this.mRingCenterRadius <= 0.0d) {
                $jacocoInit[69] = true;
            } else if (min < 0.0f) {
                $jacocoInit[70] = true;
            } else {
                double d = (double) (min / 2.0f);
                double d2 = this.mRingCenterRadius;
                Double.isNaN(d);
                f = (float) (d - d2);
                $jacocoInit[72] = true;
                this.mStrokeInset = f;
                $jacocoInit[73] = true;
            }
            f = (float) Math.ceil((double) (this.mStrokeWidth / 2.0f));
            $jacocoInit[71] = true;
            this.mStrokeInset = f;
            $jacocoInit[73] = true;
        }

        public float getInsets() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mStrokeInset;
            $jacocoInit[74] = true;
            return f;
        }

        public void setCenterRadius(double d) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mRingCenterRadius = d;
            $jacocoInit[75] = true;
        }

        public double getCenterRadius() {
            boolean[] $jacocoInit = $jacocoInit();
            double d = this.mRingCenterRadius;
            $jacocoInit[76] = true;
            return d;
        }

        public void setShowArrow(boolean z) {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mShowArrow == z) {
                $jacocoInit[77] = true;
            } else {
                this.mShowArrow = z;
                $jacocoInit[78] = true;
                invalidateSelf();
                $jacocoInit[79] = true;
            }
            $jacocoInit[80] = true;
        }

        public void setArrowScale(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            if (f == this.mArrowScale) {
                $jacocoInit[81] = true;
            } else {
                this.mArrowScale = f;
                $jacocoInit[82] = true;
                invalidateSelf();
                $jacocoInit[83] = true;
            }
            $jacocoInit[84] = true;
        }

        public float getStartingRotation() {
            boolean[] $jacocoInit = $jacocoInit();
            float f = this.mStartingRotation;
            $jacocoInit[85] = true;
            return f;
        }

        public void storeOriginals() {
            boolean[] $jacocoInit = $jacocoInit();
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
            $jacocoInit[86] = true;
        }

        public void resetOriginals() {
            boolean[] $jacocoInit = $jacocoInit();
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.mStartingRotation = 0.0f;
            $jacocoInit[87] = true;
            setStartTrim(0.0f);
            $jacocoInit[88] = true;
            setEndTrim(0.0f);
            $jacocoInit[89] = true;
            setRotation(0.0f);
            $jacocoInit[90] = true;
        }

        private void invalidateSelf() {
            boolean[] $jacocoInit = $jacocoInit();
            this.mCallback.invalidateDrawable((Drawable) null);
            $jacocoInit[91] = true;
        }
    }
}
