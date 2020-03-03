package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.xiaomi.smarthome.R;

public class SeekArc extends View {
    protected static int INVALID_PROGRESS_VALUE = -1;
    protected static final String TAG = "SeekArc";
    protected final int mAngleOffset = -90;
    protected Paint mArcPaint;
    protected int mArcRadius = 0;
    protected RectF mArcRect = new RectF();
    protected int mArcWidth = 2;
    protected boolean mClockwise = true;
    protected int mMax = 100;
    protected OnSeekArcChangeListener mOnSeekArcChangeListener;
    protected int mProgress = 0;
    protected Paint mProgressPaint;
    protected float mProgressSweep = 0.0f;
    protected int mProgressWidth = 4;
    protected int mRotation = 0;
    protected boolean mRoundedEdges = false;
    protected int mStartAngle = 0;
    protected int mSweepAngle = 360;
    protected Drawable mThumb;
    protected int mThumbXPos;
    protected int mThumbYPos;
    protected double mTouchAngle;
    protected float mTouchIgnoreRadius;
    protected boolean mTouchInside = true;
    protected int mTranslateX;
    protected int mTranslateY;

    public interface OnSeekArcChangeListener {
        void a(SeekArc seekArc);

        void a(SeekArc seekArc, int i, boolean z);

        void b(SeekArc seekArc);
    }

    public SeekArc(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public SeekArc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, R.attr.seekArcStyle);
    }

    public SeekArc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet, int i) {
        this.mArcRect.set(0.0f, 0.0f, (float) a(this), (float) b(this));
        Resources resources = getResources();
        float f = context.getResources().getDisplayMetrics().density;
        int color = resources.getColor(R.color.progress_gray);
        int color2 = resources.getColor(17170450);
        this.mThumb = resources.getDrawable(R.drawable.brightness_seekarc_thumb);
        this.mProgressWidth = (int) (((float) this.mProgressWidth) * f);
        int i2 = 0;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SeekArc, i, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(11);
            if (drawable != null) {
                this.mThumb = drawable;
            }
            int intrinsicHeight = this.mThumb.getIntrinsicHeight() / 2;
            int intrinsicWidth = this.mThumb.getIntrinsicWidth() / 2;
            this.mThumb.setBounds(-intrinsicWidth, -intrinsicHeight, intrinsicWidth, intrinsicHeight);
            this.mMax = obtainStyledAttributes.getInteger(7, this.mMax);
            this.mProgress = obtainStyledAttributes.getInteger(8, this.mProgress);
            this.mProgressWidth = (int) obtainStyledAttributes.getDimension(4, (float) this.mProgressWidth);
            this.mArcWidth = (int) obtainStyledAttributes.getDimension(1, (float) this.mArcWidth);
            this.mStartAngle = obtainStyledAttributes.getInt(9, this.mStartAngle);
            this.mSweepAngle = obtainStyledAttributes.getInt(10, this.mSweepAngle);
            this.mRotation = obtainStyledAttributes.getInt(5, this.mRotation);
            this.mRoundedEdges = obtainStyledAttributes.getBoolean(6, this.mRoundedEdges);
            this.mTouchInside = obtainStyledAttributes.getBoolean(13, this.mTouchInside);
            this.mClockwise = obtainStyledAttributes.getBoolean(2, this.mClockwise);
            color = obtainStyledAttributes.getColor(0, color);
            color2 = obtainStyledAttributes.getColor(3, color2);
            obtainStyledAttributes.recycle();
        }
        this.mProgress = this.mProgress > this.mMax ? this.mMax : this.mProgress;
        this.mProgress = this.mProgress < 0 ? 0 : this.mProgress;
        this.mSweepAngle = this.mSweepAngle > 360 ? 360 : this.mSweepAngle;
        this.mSweepAngle = this.mSweepAngle < 0 ? 0 : this.mSweepAngle;
        this.mStartAngle = this.mStartAngle > 360 ? 0 : this.mStartAngle;
        if (this.mStartAngle >= 0) {
            i2 = this.mStartAngle;
        }
        this.mStartAngle = i2;
        this.mArcPaint = new Paint();
        this.mArcPaint.setColor(color);
        this.mArcPaint.setAntiAlias(true);
        this.mArcPaint.setStyle(Paint.Style.STROKE);
        this.mArcPaint.setStrokeWidth((float) this.mArcWidth);
        this.mProgressPaint = new Paint();
        this.mProgressPaint.setColor(color2);
        this.mProgressPaint.setAntiAlias(true);
        this.mProgressPaint.setStyle(Paint.Style.STROKE);
        this.mProgressPaint.setStrokeWidth((float) this.mProgressWidth);
        if (this.mRoundedEdges) {
            this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
            this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        }
    }

    private int a(View view) {
        try {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        } catch (Exception unused) {
        }
        return view.getMeasuredWidth();
    }

    private int b(View view) {
        try {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        } catch (Exception unused) {
        }
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.mClockwise) {
            canvas.scale(-1.0f, 1.0f, this.mArcRect.centerX(), this.mArcRect.centerY());
        }
        int i = (this.mStartAngle - 90) + this.mRotation;
        float f = (float) i;
        Canvas canvas2 = canvas;
        float f2 = f;
        canvas2.drawArc(this.mArcRect, f2, (float) this.mSweepAngle, false, this.mArcPaint);
        canvas.drawArc(this.mArcRect, f, this.mProgressSweep, false, this.mProgressPaint);
        canvas.translate((float) (this.mTranslateX - this.mThumbXPos), (float) (this.mTranslateY - this.mThumbYPos));
        this.mThumb.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(getSuggestedMinimumHeight(), i2);
        int defaultSize2 = getDefaultSize(getSuggestedMinimumWidth(), i);
        int min = Math.min(defaultSize2, defaultSize);
        this.mTranslateX = (int) (((float) defaultSize2) * 0.5f);
        this.mTranslateY = (int) (((float) defaultSize) * 0.5f);
        int paddingLeft = min - getPaddingLeft();
        int i3 = paddingLeft / 2;
        this.mArcRadius = i3;
        float f = (float) ((defaultSize / 2) - i3);
        float f2 = (float) ((defaultSize2 / 2) - i3);
        float f3 = (float) paddingLeft;
        this.mArcRect.set(f2, f, f2 + f3, f3 + f);
        double d = (double) this.mArcRadius;
        double d2 = (double) (((int) this.mProgressSweep) + this.mStartAngle + this.mRotation + 90);
        double cos = Math.cos(Math.toRadians(d2));
        Double.isNaN(d);
        this.mThumbXPos = (int) (d * cos);
        double d3 = (double) this.mArcRadius;
        double sin = Math.sin(Math.toRadians(d2));
        Double.isNaN(d3);
        this.mThumbYPos = (int) (d3 * sin);
        setTouchInSide(this.mTouchInside);
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                onStartTrackingTouch();
                updateOnTouch(motionEvent);
                return true;
            case 1:
                onStopTrackingTouch();
                setPressed(false);
                return true;
            case 2:
                updateOnTouch(motionEvent);
                return true;
            case 3:
                onStopTrackingTouch();
                setPressed(false);
                return true;
            default:
                return true;
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mThumb != null && this.mThumb.isStateful()) {
            this.mThumb.setState(getDrawableState());
        }
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onStartTrackingTouch() {
        if (this.mOnSeekArcChangeListener != null) {
            this.mOnSeekArcChangeListener.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStopTrackingTouch() {
        if (this.mOnSeekArcChangeListener != null) {
            this.mOnSeekArcChangeListener.b(this);
        }
    }

    /* access modifiers changed from: protected */
    public void updateOnTouch(MotionEvent motionEvent) {
        if (!ignoreTouch(motionEvent.getX(), motionEvent.getY())) {
            setPressed(true);
            this.mTouchAngle = getTouchDegrees(motionEvent.getX(), motionEvent.getY());
            onProgressRefresh(getProgressForAngle(this.mTouchAngle), true);
        }
    }

    /* access modifiers changed from: protected */
    public boolean ignoreTouch(float f, float f2) {
        float f3 = f - ((float) this.mTranslateX);
        float f4 = f2 - ((float) this.mTranslateY);
        return ((float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)))) < this.mTouchIgnoreRadius;
    }

    /* access modifiers changed from: protected */
    public double getTouchDegrees(float f, float f2) {
        float f3 = f - ((float) this.mTranslateX);
        float f4 = f2 - ((float) this.mTranslateY);
        if (!this.mClockwise) {
            f3 = -f3;
        }
        double degrees = Math.toDegrees((Math.atan2((double) f4, (double) f3) + 1.5707963267948966d) - Math.toRadians((double) this.mRotation));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        }
        double d = (double) this.mStartAngle;
        Double.isNaN(d);
        return degrees - d;
    }

    /* access modifiers changed from: protected */
    public int getProgressForAngle(double d) {
        double valuePerDegree = (double) valuePerDegree();
        Double.isNaN(valuePerDegree);
        int round = (int) Math.round(valuePerDegree * d);
        if (round < 0) {
            round = INVALID_PROGRESS_VALUE;
        }
        return round > this.mMax ? INVALID_PROGRESS_VALUE : round;
    }

    public int getProgress() {
        return this.mProgress;
    }

    /* access modifiers changed from: protected */
    public float valuePerDegree() {
        return ((float) this.mMax) / ((float) this.mSweepAngle);
    }

    /* access modifiers changed from: protected */
    public void onProgressRefresh(int i, boolean z) {
        updateProgress(i, z);
    }

    /* access modifiers changed from: protected */
    public void updateThumbPosition() {
        double d = (double) this.mArcRadius;
        double d2 = (double) ((int) (((float) this.mStartAngle) + this.mProgressSweep + ((float) this.mRotation) + 90.0f));
        double cos = Math.cos(Math.toRadians(d2));
        Double.isNaN(d);
        this.mThumbXPos = (int) (d * cos);
        double d3 = (double) this.mArcRadius;
        double sin = Math.sin(Math.toRadians(d2));
        Double.isNaN(d3);
        this.mThumbYPos = (int) (d3 * sin);
    }

    /* access modifiers changed from: protected */
    public void updateProgress(int i, boolean z) {
        if (i != INVALID_PROGRESS_VALUE) {
            if (this.mOnSeekArcChangeListener != null) {
                this.mOnSeekArcChangeListener.a(this, i, z);
            }
            if (i > this.mMax) {
                i = this.mMax;
            }
            if (this.mProgress < 0) {
                i = 0;
            }
            this.mProgress = i;
            this.mProgressSweep = (((float) i) / ((float) this.mMax)) * ((float) this.mSweepAngle);
            updateThumbPosition();
            invalidate();
        }
    }

    public void setOnSeekArcChangeListener(OnSeekArcChangeListener onSeekArcChangeListener) {
        this.mOnSeekArcChangeListener = onSeekArcChangeListener;
    }

    public void setProgress(int i) {
        updateProgress(i, false);
    }

    public int getProgressWidth() {
        return this.mProgressWidth;
    }

    public void setProgressWidth(int i) {
        this.mProgressWidth = i;
        this.mProgressPaint.setStrokeWidth((float) i);
    }

    public int getArcWidth() {
        return this.mArcWidth;
    }

    public void setArcWidth(int i) {
        this.mArcWidth = i;
        this.mArcPaint.setStrokeWidth((float) i);
    }

    public int getArcRotation() {
        return this.mRotation;
    }

    public void setArcRotation(int i) {
        this.mRotation = i;
        updateThumbPosition();
    }

    public int getStartAngle() {
        return this.mStartAngle;
    }

    public void setStartAngle(int i) {
        this.mStartAngle = i;
        updateThumbPosition();
    }

    public int getSweepAngle() {
        return this.mSweepAngle;
    }

    public void setSweepAngle(int i) {
        this.mSweepAngle = i;
        updateThumbPosition();
    }

    public void setRoundedEdges(boolean z) {
        this.mRoundedEdges = z;
        if (this.mRoundedEdges) {
            this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
            this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
            return;
        }
        this.mArcPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.mProgressPaint.setStrokeCap(Paint.Cap.SQUARE);
    }

    public void setTouchInSide(boolean z) {
        int intrinsicHeight = this.mThumb.getIntrinsicHeight() / 2;
        int intrinsicWidth = this.mThumb.getIntrinsicWidth() / 2;
        this.mTouchInside = z;
        if (this.mTouchInside) {
            this.mTouchIgnoreRadius = ((float) this.mArcRadius) / 4.0f;
        } else {
            this.mTouchIgnoreRadius = (float) (this.mArcRadius - Math.min(intrinsicWidth, intrinsicHeight));
        }
    }

    public void setClockwise(boolean z) {
        this.mClockwise = z;
    }
}
