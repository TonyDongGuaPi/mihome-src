package com.mi.global.bbs.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import com.mi.global.bbs.R;
import java.util.Arrays;

public class InkPageIndicator extends View implements ViewPager.OnPageChangeListener, View.OnAttachStateChangeListener {
    private static final int DEFAULT_ANIM_DURATION = 400;
    private static final int DEFAULT_DOT_SIZE = 8;
    private static final int DEFAULT_GAP = 12;
    private static final int DEFAULT_SELECTED_COLOUR = -1;
    private static final int DEFAULT_UNSELECTED_COLOUR = -2130706433;
    private static final float INVALID_FRACTION = -1.0f;
    private static final float MINIMAL_REVEAL = 1.0E-5f;
    private long animDuration;
    /* access modifiers changed from: private */
    public long animHalfDuration;
    private final Path combinedUnselectedPath;
    float controlX1;
    float controlX2;
    float controlY1;
    float controlY2;
    private int currentPage;
    private float dotBottomY;
    /* access modifiers changed from: private */
    public float[] dotCenterX;
    private float dotCenterY;
    private int dotDiameter;
    /* access modifiers changed from: private */
    public float dotRadius;
    private float[] dotRevealFractions;
    private float dotTopY;
    float endX1;
    float endX2;
    float endY1;
    float endY2;
    private int gap;
    private float halfDotRadius;
    /* access modifiers changed from: private */
    public Interpolator interpolator;
    private boolean isAttachedToWindow;
    private AnimatorSet joiningAnimationSet;
    private float[] joiningFractions;
    private ValueAnimator moveAnimation;
    /* access modifiers changed from: private */
    public boolean pageChanging;
    private int pageCount;
    private int previousPage;
    private final RectF rectF;
    /* access modifiers changed from: private */
    public PendingRetreatAnimator retreatAnimation;
    /* access modifiers changed from: private */
    public float retreatingJoinX1;
    /* access modifiers changed from: private */
    public float retreatingJoinX2;
    /* access modifiers changed from: private */
    public PendingRevealAnimator[] revealAnimations;
    private int selectedColour;
    /* access modifiers changed from: private */
    public boolean selectedDotInPosition;
    /* access modifiers changed from: private */
    public float selectedDotX;
    private final Paint selectedPaint;
    private int unselectedColour;
    private final Path unselectedDotLeftPath;
    private final Path unselectedDotPath;
    private final Path unselectedDotRightPath;
    private final Paint unselectedPaint;
    /* access modifiers changed from: private */
    public ViewPager viewPager;

    public void onPageScrollStateChanged(int i) {
    }

    public InkPageIndicator(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public InkPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InkPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2 = (int) context.getResources().getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.InkPageIndicator, i, 0);
        this.dotDiameter = obtainStyledAttributes.getDimensionPixelSize(R.styleable.InkPageIndicator_dotDiameter, i2 * 8);
        this.dotRadius = (float) (this.dotDiameter / 2);
        this.halfDotRadius = this.dotRadius / 2.0f;
        this.gap = obtainStyledAttributes.getDimensionPixelSize(R.styleable.InkPageIndicator_dotGap, i2 * 12);
        this.animDuration = (long) obtainStyledAttributes.getInteger(R.styleable.InkPageIndicator_animationDuration, 400);
        this.animHalfDuration = this.animDuration / 2;
        this.unselectedColour = obtainStyledAttributes.getColor(R.styleable.InkPageIndicator_pageIndicatorColor, DEFAULT_UNSELECTED_COLOUR);
        this.selectedColour = obtainStyledAttributes.getColor(R.styleable.InkPageIndicator_currentPageIndicatorColor, -1);
        obtainStyledAttributes.recycle();
        this.unselectedPaint = new Paint(1);
        this.unselectedPaint.setColor(this.unselectedColour);
        this.selectedPaint = new Paint(1);
        this.selectedPaint.setColor(this.selectedColour);
        if (Build.VERSION.SDK_INT > 19) {
            this.interpolator = getFastOutSlowInInterpolator(context);
        }
        this.combinedUnselectedPath = new Path();
        this.unselectedDotPath = new Path();
        this.unselectedDotLeftPath = new Path();
        this.unselectedDotRightPath = new Path();
        this.rectF = new RectF();
        addOnAttachStateChangeListener(this);
    }

    public static Interpolator getFastOutSlowInInterpolator(Context context) {
        return AnimationUtils.loadInterpolator(context, R.interpolator.fast_out_slow_in);
    }

    public void setUnselectedColour(int i) {
        this.unselectedColour = i;
        this.unselectedPaint.setColor(i);
    }

    public void setSelectedColour(int i) {
        this.selectedColour = i;
        this.selectedPaint.setColor(i);
    }

    public void setViewPager(ViewPager viewPager2) {
        this.viewPager = viewPager2;
        viewPager2.addOnPageChangeListener(this);
        setPageCount(viewPager2.getAdapter().getCount());
        viewPager2.getAdapter().registerDataSetObserver(new DataSetObserver() {
            public void onChanged() {
                InkPageIndicator.this.setPageCount(InkPageIndicator.this.viewPager.getAdapter().getCount());
            }
        });
        setCurrentPageImmediate();
    }

    @RequiresApi(api = 16)
    public void onPageScrolled(int i, float f, int i2) {
        if (this.isAttachedToWindow) {
            int i3 = this.pageChanging ? this.previousPage : this.currentPage;
            if (i3 != i) {
                f = 1.0f - f;
                if (f == 1.0f) {
                    i = Math.min(i3, i);
                }
            }
            setJoiningFraction(i, f);
        }
    }

    @RequiresApi(api = 16)
    public void onPageSelected(int i) {
        if (this.isAttachedToWindow) {
            setSelectedPage(i);
        } else {
            setCurrentPageImmediate();
        }
    }

    /* access modifiers changed from: private */
    public void setPageCount(int i) {
        this.pageCount = i;
        resetState();
        requestLayout();
    }

    private void calculateDotPositions(int i, int i2) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i - getPaddingRight();
        getPaddingBottom();
        float requiredWidth = ((float) (paddingLeft + (((paddingRight - paddingLeft) - getRequiredWidth()) / 2))) + this.dotRadius;
        this.dotCenterX = new float[this.pageCount];
        for (int i3 = 0; i3 < this.pageCount; i3++) {
            this.dotCenterX[i3] = ((float) ((this.dotDiameter + this.gap) * i3)) + requiredWidth;
        }
        float f = (float) paddingTop;
        this.dotTopY = f;
        this.dotCenterY = f + this.dotRadius;
        this.dotBottomY = (float) (paddingTop + this.dotDiameter);
        setCurrentPageImmediate();
    }

    private void setCurrentPageImmediate() {
        if (this.viewPager != null) {
            this.currentPage = this.viewPager.getCurrentItem();
        } else {
            this.currentPage = 0;
        }
        if (this.dotCenterX != null) {
            this.selectedDotX = this.dotCenterX[this.currentPage];
        }
    }

    /* access modifiers changed from: private */
    public void resetState() {
        this.joiningFractions = new float[(this.pageCount - 1)];
        Arrays.fill(this.joiningFractions, 0.0f);
        this.dotRevealFractions = new float[this.pageCount];
        Arrays.fill(this.dotRevealFractions, 0.0f);
        this.retreatingJoinX1 = -1.0f;
        this.retreatingJoinX2 = -1.0f;
        this.selectedDotInPosition = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int desiredHeight = getDesiredHeight();
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            desiredHeight = Math.min(desiredHeight, View.MeasureSpec.getSize(i2));
        } else if (mode == 1073741824) {
            desiredHeight = View.MeasureSpec.getSize(i2);
        }
        int desiredWidth = getDesiredWidth();
        int mode2 = View.MeasureSpec.getMode(i);
        if (mode2 == Integer.MIN_VALUE) {
            desiredWidth = Math.min(desiredWidth, View.MeasureSpec.getSize(i));
        } else if (mode2 == 1073741824) {
            desiredWidth = View.MeasureSpec.getSize(i);
        }
        setMeasuredDimension(desiredWidth, desiredHeight);
        calculateDotPositions(desiredWidth, desiredHeight);
    }

    private int getDesiredHeight() {
        return getPaddingTop() + this.dotDiameter + getPaddingBottom();
    }

    private int getRequiredWidth() {
        return (this.pageCount * this.dotDiameter) + ((this.pageCount - 1) * this.gap);
    }

    private int getDesiredWidth() {
        return getPaddingLeft() + getRequiredWidth() + getPaddingRight();
    }

    public void onViewAttachedToWindow(View view) {
        this.isAttachedToWindow = true;
    }

    public void onViewDetachedFromWindow(View view) {
        this.isAttachedToWindow = false;
    }

    /* access modifiers changed from: protected */
    @RequiresApi(api = 19)
    public void onDraw(Canvas canvas) {
        if (this.viewPager != null && this.pageCount != 0) {
            drawUnselected(canvas);
            drawSelected(canvas);
        }
    }

    @RequiresApi(api = 19)
    private void drawUnselected(Canvas canvas) {
        this.combinedUnselectedPath.rewind();
        int i = 0;
        while (i < this.pageCount) {
            this.combinedUnselectedPath.op(getUnselectedPath(i, this.dotCenterX[i], this.dotCenterX[i == this.pageCount + -1 ? i : i + 1], i == this.pageCount + -1 ? -1.0f : this.joiningFractions[i], this.dotRevealFractions[i]), Path.Op.UNION);
            i++;
        }
        if (this.retreatingJoinX1 != -1.0f) {
            this.combinedUnselectedPath.op(getRetreatingJoinPath(), Path.Op.UNION);
        }
        canvas.drawPath(this.combinedUnselectedPath, this.unselectedPaint);
    }

    @RequiresApi(api = 19)
    private Path getUnselectedPath(int i, float f, float f2, float f3, float f4) {
        int i2 = i;
        float f5 = f;
        float f6 = f2;
        this.unselectedDotPath.rewind();
        if ((f3 == 0.0f || f3 == -1.0f) && f4 == 0.0f && !(i2 == this.currentPage && this.selectedDotInPosition)) {
            this.unselectedDotPath.addCircle(this.dotCenterX[i2], this.dotCenterY, this.dotRadius, Path.Direction.CW);
        }
        if (f3 > 0.0f && f3 <= 0.5f && this.retreatingJoinX1 == -1.0f) {
            this.unselectedDotLeftPath.rewind();
            this.unselectedDotLeftPath.moveTo(f5, this.dotBottomY);
            this.rectF.set(f5 - this.dotRadius, this.dotTopY, this.dotRadius + f5, this.dotBottomY);
            this.unselectedDotLeftPath.arcTo(this.rectF, 90.0f, 180.0f, true);
            this.endX1 = this.dotRadius + f5 + (((float) this.gap) * f3);
            this.endY1 = this.dotCenterY;
            this.controlX1 = this.halfDotRadius + f5;
            this.controlY1 = this.dotTopY;
            this.controlX2 = this.endX1;
            this.controlY2 = this.endY1 - this.halfDotRadius;
            this.unselectedDotLeftPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX1, this.endY1);
            this.endX2 = f5;
            this.endY2 = this.dotBottomY;
            this.controlX1 = this.endX1;
            this.controlY1 = this.endY1 + this.halfDotRadius;
            this.controlX2 = this.halfDotRadius + f5;
            this.controlY2 = this.dotBottomY;
            this.unselectedDotLeftPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, this.endY2);
            this.unselectedDotPath.op(this.unselectedDotLeftPath, Path.Op.UNION);
            this.unselectedDotRightPath.rewind();
            this.unselectedDotRightPath.moveTo(f6, this.dotBottomY);
            this.rectF.set(f6 - this.dotRadius, this.dotTopY, this.dotRadius + f6, this.dotBottomY);
            this.unselectedDotRightPath.arcTo(this.rectF, 90.0f, -180.0f, true);
            this.endX1 = (f6 - this.dotRadius) - (((float) this.gap) * f3);
            this.endY1 = this.dotCenterY;
            this.controlX1 = f6 - this.halfDotRadius;
            this.controlY1 = this.dotTopY;
            this.controlX2 = this.endX1;
            this.controlY2 = this.endY1 - this.halfDotRadius;
            this.unselectedDotRightPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX1, this.endY1);
            this.endX2 = f6;
            this.endY2 = this.dotBottomY;
            this.controlX1 = this.endX1;
            this.controlY1 = this.endY1 + this.halfDotRadius;
            this.controlX2 = this.endX2 - this.halfDotRadius;
            this.controlY2 = this.dotBottomY;
            this.unselectedDotRightPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, this.endY2);
            this.unselectedDotPath.op(this.unselectedDotRightPath, Path.Op.UNION);
        }
        if (f3 > 0.5f && f3 < 1.0f && this.retreatingJoinX1 == -1.0f) {
            float f7 = (f3 - 0.2f) * 1.25f;
            this.unselectedDotPath.moveTo(f5, this.dotBottomY);
            this.rectF.set(f5 - this.dotRadius, this.dotTopY, this.dotRadius + f5, this.dotBottomY);
            this.unselectedDotPath.arcTo(this.rectF, 90.0f, 180.0f, true);
            this.endX1 = this.dotRadius + f5 + ((float) (this.gap / 2));
            this.endY1 = this.dotCenterY - (this.dotRadius * f7);
            this.controlX1 = this.endX1 - (this.dotRadius * f7);
            this.controlY1 = this.dotTopY;
            float f8 = 1.0f - f7;
            this.controlX2 = this.endX1 - (this.dotRadius * f8);
            this.controlY2 = this.endY1;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX1, this.endY1);
            this.endX2 = f6;
            this.endY2 = this.dotTopY;
            this.controlX1 = this.endX1 + (this.dotRadius * f8);
            this.controlY1 = this.endY1;
            this.controlX2 = this.endX1 + (this.dotRadius * f7);
            this.controlY2 = this.dotTopY;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, this.endY2);
            this.rectF.set(f6 - this.dotRadius, this.dotTopY, this.dotRadius + f6, this.dotBottomY);
            this.unselectedDotPath.arcTo(this.rectF, 270.0f, 180.0f, true);
            this.endY1 = this.dotCenterY + (this.dotRadius * f7);
            this.controlX1 = this.endX1 + (this.dotRadius * f7);
            this.controlY1 = this.dotBottomY;
            this.controlX2 = this.endX1 + (this.dotRadius * f8);
            this.controlY2 = this.endY1;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX1, this.endY1);
            this.endX2 = f5;
            this.endY2 = this.dotBottomY;
            this.controlX1 = this.endX1 - (f8 * this.dotRadius);
            this.controlY1 = this.endY1;
            this.controlX2 = this.endX1 - (f7 * this.dotRadius);
            this.controlY2 = this.endY2;
            this.unselectedDotPath.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX2, this.endY2);
        }
        if (f3 == 1.0f && this.retreatingJoinX1 == -1.0f) {
            this.rectF.set(f5 - this.dotRadius, this.dotTopY, f6 + this.dotRadius, this.dotBottomY);
            this.unselectedDotPath.addRoundRect(this.rectF, this.dotRadius, this.dotRadius, Path.Direction.CW);
        }
        if (f4 > MINIMAL_REVEAL) {
            this.unselectedDotPath.addCircle(f5, this.dotCenterY, this.dotRadius * f4, Path.Direction.CW);
        }
        return this.unselectedDotPath;
    }

    private Path getRetreatingJoinPath() {
        this.unselectedDotPath.rewind();
        this.rectF.set(this.retreatingJoinX1, this.dotTopY, this.retreatingJoinX2, this.dotBottomY);
        this.unselectedDotPath.addRoundRect(this.rectF, this.dotRadius, this.dotRadius, Path.Direction.CW);
        return this.unselectedDotPath;
    }

    private void drawSelected(Canvas canvas) {
        canvas.drawCircle(this.selectedDotX, this.dotCenterY, this.dotRadius, this.selectedPaint);
    }

    @RequiresApi(api = 16)
    private void setSelectedPage(int i) {
        if (i != this.currentPage) {
            this.pageChanging = true;
            this.previousPage = this.currentPage;
            this.currentPage = i;
            int abs = Math.abs(i - this.previousPage);
            if (abs > 1) {
                if (i > this.previousPage) {
                    for (int i2 = 0; i2 < abs; i2++) {
                        setJoiningFraction(this.previousPage + i2, 1.0f);
                    }
                } else {
                    for (int i3 = -1; i3 > (-abs); i3--) {
                        setJoiningFraction(this.previousPage + i3, 1.0f);
                    }
                }
            }
            this.moveAnimation = createMoveSelectedAnimator(this.dotCenterX[i], this.previousPage, i, abs);
            this.moveAnimation.start();
        }
    }

    private ValueAnimator createMoveSelectedAnimator(float f, int i, int i2, int i3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.selectedDotX, f});
        this.retreatAnimation = new PendingRetreatAnimator(i, i2, i3, i2 > i ? new RightwardStartPredicate(f - ((f - this.selectedDotX) * 0.25f)) : new LeftwardStartPredicate(f + ((this.selectedDotX - f) * 0.25f)));
        this.retreatAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                InkPageIndicator.this.resetState();
                boolean unused = InkPageIndicator.this.pageChanging = false;
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @RequiresApi(api = 16)
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = InkPageIndicator.this.selectedDotX = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                InkPageIndicator.this.retreatAnimation.startIfNecessary(InkPageIndicator.this.selectedDotX);
                InkPageIndicator.this.postInvalidateOnAnimation();
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                boolean unused = InkPageIndicator.this.selectedDotInPosition = false;
            }

            public void onAnimationEnd(Animator animator) {
                boolean unused = InkPageIndicator.this.selectedDotInPosition = true;
            }
        });
        ofFloat.setStartDelay(this.selectedDotInPosition ? this.animDuration / 4 : 0);
        ofFloat.setDuration((this.animDuration * 3) / 4);
        if (this.interpolator != null) {
            ofFloat.setInterpolator(this.interpolator);
        }
        return ofFloat;
    }

    @RequiresApi(api = 16)
    private void setJoiningFraction(int i, float f) {
        if (i < this.joiningFractions.length) {
            if (i == 1) {
                Log.d("PageIndicator", "dot 1 fraction:\t" + f);
            }
            this.joiningFractions[i] = f;
            postInvalidateOnAnimation();
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 16)
    public void clearJoiningFractions() {
        Arrays.fill(this.joiningFractions, 0.0f);
        postInvalidateOnAnimation();
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 16)
    public void setDotRevealFraction(int i, float f) {
        this.dotRevealFractions[i] = f;
        postInvalidateOnAnimation();
    }

    /* access modifiers changed from: private */
    public void cancelJoiningAnimations() {
        if (this.joiningAnimationSet != null && this.joiningAnimationSet.isRunning()) {
            this.joiningAnimationSet.cancel();
        }
    }

    public abstract class PendingStartAnimator extends ValueAnimator {
        protected boolean hasStarted = false;
        protected StartPredicate predicate;

        public PendingStartAnimator(StartPredicate startPredicate) {
            this.predicate = startPredicate;
        }

        public void startIfNecessary(float f) {
            if (!this.hasStarted && this.predicate.shouldStart(f)) {
                start();
                this.hasStarted = true;
            }
        }
    }

    public class PendingRetreatAnimator extends PendingStartAnimator {
        public PendingRetreatAnimator(int i, int i2, int i3, StartPredicate startPredicate) {
            super(startPredicate);
            float access$1000;
            float f;
            float max;
            float f2;
            setDuration(InkPageIndicator.this.animHalfDuration);
            if (InkPageIndicator.this.interpolator != null) {
                setInterpolator(InkPageIndicator.this.interpolator);
            }
            if (i2 > i) {
                access$1000 = Math.min(InkPageIndicator.this.dotCenterX[i], InkPageIndicator.this.selectedDotX) - InkPageIndicator.this.dotRadius;
            } else {
                access$1000 = InkPageIndicator.this.dotCenterX[i2] - InkPageIndicator.this.dotRadius;
            }
            final float f3 = access$1000;
            if (i2 > i) {
                f = InkPageIndicator.this.dotCenterX[i2] - InkPageIndicator.this.dotRadius;
            } else {
                f = InkPageIndicator.this.dotCenterX[i2] - InkPageIndicator.this.dotRadius;
            }
            if (i2 > i) {
                max = InkPageIndicator.this.dotCenterX[i2] + InkPageIndicator.this.dotRadius;
            } else {
                max = Math.max(InkPageIndicator.this.dotCenterX[i], InkPageIndicator.this.selectedDotX) + InkPageIndicator.this.dotRadius;
            }
            final float f4 = max;
            if (i2 > i) {
                f2 = InkPageIndicator.this.dotCenterX[i2] + InkPageIndicator.this.dotRadius;
            } else {
                f2 = InkPageIndicator.this.dotCenterX[i2] + InkPageIndicator.this.dotRadius;
            }
            PendingRevealAnimator[] unused = InkPageIndicator.this.revealAnimations = new PendingRevealAnimator[i3];
            final int[] iArr = new int[i3];
            int i4 = 0;
            if (f3 != f) {
                setFloatValues(new float[]{f3, f});
                while (i4 < i3) {
                    int i5 = i + i4;
                    InkPageIndicator.this.revealAnimations[i4] = new PendingRevealAnimator(i5, new RightwardStartPredicate(InkPageIndicator.this.dotCenterX[i5]));
                    iArr[i4] = i5;
                    i4++;
                }
                addUpdateListener(new ValueAnimator.AnimatorUpdateListener(InkPageIndicator.this) {
                    @RequiresApi(api = 16)
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float unused = InkPageIndicator.this.retreatingJoinX1 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        InkPageIndicator.this.postInvalidateOnAnimation();
                        for (PendingRevealAnimator startIfNecessary : InkPageIndicator.this.revealAnimations) {
                            startIfNecessary.startIfNecessary(InkPageIndicator.this.retreatingJoinX1);
                        }
                    }
                });
            } else {
                setFloatValues(new float[]{f4, f2});
                while (i4 < i3) {
                    int i6 = i - i4;
                    InkPageIndicator.this.revealAnimations[i4] = new PendingRevealAnimator(i6, new LeftwardStartPredicate(InkPageIndicator.this.dotCenterX[i6]));
                    iArr[i4] = i6;
                    i4++;
                }
                addUpdateListener(new ValueAnimator.AnimatorUpdateListener(InkPageIndicator.this) {
                    @RequiresApi(api = 16)
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float unused = InkPageIndicator.this.retreatingJoinX2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        InkPageIndicator.this.postInvalidateOnAnimation();
                        for (PendingRevealAnimator startIfNecessary : InkPageIndicator.this.revealAnimations) {
                            startIfNecessary.startIfNecessary(InkPageIndicator.this.retreatingJoinX2);
                        }
                    }
                });
            }
            final InkPageIndicator inkPageIndicator = InkPageIndicator.this;
            addListener(new AnimatorListenerAdapter() {
                @RequiresApi(api = 16)
                public void onAnimationStart(Animator animator) {
                    InkPageIndicator.this.cancelJoiningAnimations();
                    InkPageIndicator.this.clearJoiningFractions();
                    for (int access$1600 : iArr) {
                        InkPageIndicator.this.setDotRevealFraction(access$1600, InkPageIndicator.MINIMAL_REVEAL);
                    }
                    float unused = InkPageIndicator.this.retreatingJoinX1 = f3;
                    float unused2 = InkPageIndicator.this.retreatingJoinX2 = f4;
                    InkPageIndicator.this.postInvalidateOnAnimation();
                }

                @RequiresApi(api = 16)
                public void onAnimationEnd(Animator animator) {
                    float unused = InkPageIndicator.this.retreatingJoinX1 = -1.0f;
                    float unused2 = InkPageIndicator.this.retreatingJoinX2 = -1.0f;
                    InkPageIndicator.this.postInvalidateOnAnimation();
                }
            });
        }
    }

    public class PendingRevealAnimator extends PendingStartAnimator {
        /* access modifiers changed from: private */
        public int dot;

        public PendingRevealAnimator(int i, StartPredicate startPredicate) {
            super(startPredicate);
            setFloatValues(new float[]{InkPageIndicator.MINIMAL_REVEAL, 1.0f});
            this.dot = i;
            setDuration(InkPageIndicator.this.animHalfDuration);
            if (InkPageIndicator.this.interpolator != null) {
                setInterpolator(InkPageIndicator.this.interpolator);
            }
            addUpdateListener(new ValueAnimator.AnimatorUpdateListener(InkPageIndicator.this) {
                @RequiresApi(api = 16)
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    InkPageIndicator.this.setDotRevealFraction(PendingRevealAnimator.this.dot, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            addListener(new AnimatorListenerAdapter(InkPageIndicator.this) {
                @RequiresApi(api = 16)
                public void onAnimationEnd(Animator animator) {
                    InkPageIndicator.this.setDotRevealFraction(PendingRevealAnimator.this.dot, 0.0f);
                    InkPageIndicator.this.postInvalidateOnAnimation();
                }
            });
        }
    }

    public abstract class StartPredicate {
        protected float thresholdValue;

        /* access modifiers changed from: package-private */
        public abstract boolean shouldStart(float f);

        public StartPredicate(float f) {
            this.thresholdValue = f;
        }
    }

    public class RightwardStartPredicate extends StartPredicate {
        public RightwardStartPredicate(float f) {
            super(f);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldStart(float f) {
            return f > this.thresholdValue;
        }
    }

    public class LeftwardStartPredicate extends StartPredicate {
        public LeftwardStartPredicate(float f) {
            super(f);
        }

        /* access modifiers changed from: package-private */
        public boolean shouldStart(float f) {
            return f < this.thresholdValue;
        }
    }
}
