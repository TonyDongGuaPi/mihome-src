package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.xiaomi.smarthome.library.R;

public class DragCircleProgressView extends View {
    protected static final int STATE_DRAGGING = 2;
    protected static final int STATE_INIT = 1;
    protected static final int STATE_MAX_VALUE_REACHED = 3;
    protected static final int THUMB_ANGLE = 18;
    private static final int c = 200;
    private static final int d = 18;
    private static final int g = 30;

    /* renamed from: a  reason: collision with root package name */
    private float f18816a;
    private float b;
    private OnSeekArcChangeListener e;
    private boolean f = false;
    protected RectF mArcRect = null;
    protected int mCenterPointX;
    protected int mCenterPointY;
    protected int mCircleColor;
    protected Paint mCirclePaint;
    protected boolean mClockwise = true;
    protected int mCurrentValue = 0;
    protected double mCurrentValueD = 0.0d;
    protected int mLastValue = 0;
    protected int mLineColor;
    protected Paint mLinePaint;
    protected Resources mRes;
    protected int mRoundValue = 60;
    protected double mStartTouchAngle;
    protected int mState = 1;
    protected Paint mTextPaint;
    protected Drawable mThumb;
    protected int mThumbXPos;
    protected int mThumbYPos;
    protected int mTotalValue = 0;
    protected double mTouchAngle;
    protected double mTouchAngleAccumulated;

    public interface OnSeekArcChangeListener {
        void a(DragCircleProgressView dragCircleProgressView);

        void a(DragCircleProgressView dragCircleProgressView, int i, boolean z);

        void b(DragCircleProgressView dragCircleProgressView);
    }

    /* access modifiers changed from: protected */
    public boolean isTrackingStart() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDrawText(Canvas canvas, int i, int i2) {
    }

    /* access modifiers changed from: protected */
    public boolean updateOnDownTouch(MotionEvent motionEvent) {
        return true;
    }

    public DragCircleProgressView(Context context) {
        super(context);
        a();
    }

    public DragCircleProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public DragCircleProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.mRes = getContext().getResources();
        float f2 = this.mRes.getDisplayMetrics().density;
        setWillNotDraw(false);
        this.mCircleColor = getResources().getColor(R.color.class_text_17);
        this.mLineColor = -6710887;
        this.mCirclePaint = new Paint();
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setAntiAlias(true);
        this.mCirclePaint.setStyle(Paint.Style.STROKE);
        this.mCirclePaint.setStrokeWidth(f2);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(getResources().getColor(R.color.class_text_17));
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint.setTextSize(18.0f * f2);
        this.mLinePaint = new Paint();
        this.mLinePaint.setColor(this.mLineColor);
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setStrokeWidth(f2 * 1.0f);
        b();
    }

    /* access modifiers changed from: protected */
    public boolean updateOnNonTouch(int i, boolean z) {
        double d2 = (double) i;
        double d3 = (double) this.mRoundValue;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = (double) this.mRoundValue;
        Double.isNaN(d4);
        double d5 = ((d2 % d3) / d4) * 360.0d;
        if (i <= 0) {
            this.mState = 1;
            reset();
            if (z) {
                invalidate();
            }
            return true;
        } else if (i >= this.mTotalValue) {
            this.mState = 3;
            this.mCurrentValue = this.mTotalValue;
            this.mCurrentValueD = (double) this.mCurrentValue;
            this.mTouchAngle = (double) ((this.mTotalValue / this.mRoundValue) * 360);
            this.mStartTouchAngle = 360.0d;
            updateThumbPosition();
            if (z) {
                invalidate();
            }
            return true;
        } else {
            this.mState = 2;
            this.mTouchAngle = d5;
            this.mStartTouchAngle = d5;
            this.mCurrentValue = i;
            this.mCurrentValueD = d2;
            updateThumbPosition();
            if (z) {
                invalidate();
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean updateOnTouch(double d2) {
        if (this.mState == 3 && d2 < 180.0d) {
            this.mCurrentValue = this.mTotalValue;
            this.mCurrentValueD = (double) this.mCurrentValue;
            this.mTouchAngle = (double) ((this.mTotalValue / this.mRoundValue) * 360);
            this.mStartTouchAngle = 360.0d;
            updateThumbPosition();
            invalidate();
            notifyValueChanged(true);
            return false;
        } else if (this.mState != 1 || d2 <= 270.0d) {
            this.mState = 2;
            this.mTouchAngle = d2;
            double d3 = this.mTouchAngle - this.mStartTouchAngle;
            if (this.mTouchAngle < this.mStartTouchAngle && Math.abs(d3) > 180.0d) {
                d3 += 360.0d;
                if (this.mCurrentValue >= this.mTotalValue) {
                    this.mState = 3;
                    this.mCurrentValue = this.mTotalValue;
                    this.mCurrentValueD = (double) this.mCurrentValue;
                    this.mTouchAngle = (double) ((this.mTotalValue / this.mRoundValue) * 360);
                    invalidate();
                    notifyValueChanged(true);
                    return false;
                }
            } else if (this.mTouchAngle > this.mStartTouchAngle && Math.abs(d3) > 180.0d) {
                if (this.mCurrentValue < this.mRoundValue) {
                    this.mState = 1;
                    reset();
                    invalidate();
                    notifyValueChanged(true);
                    return false;
                }
                d3 -= 360.0d;
            }
            this.mStartTouchAngle = this.mTouchAngle;
            double d4 = this.mCurrentValueD;
            double d5 = (double) this.mRoundValue;
            Double.isNaN(d5);
            this.mCurrentValueD = d4 + ((d5 * d3) / 360.0d);
            this.mCurrentValue = (int) this.mCurrentValueD;
            if (this.mCurrentValue > this.mTotalValue) {
                this.mState = 3;
                this.mCurrentValue = this.mTotalValue;
                this.mCurrentValueD = (double) this.mCurrentValue;
                this.mTouchAngle = (double) ((this.mTotalValue / this.mRoundValue) * 360);
                updateThumbPosition();
                invalidate();
                notifyValueChanged(true);
                return false;
            } else if (this.mCurrentValueD - 1.0E-4d <= 0.0d) {
                this.mState = 1;
                reset();
                invalidate();
                notifyValueChanged(true);
                return false;
            } else {
                updateThumbPosition();
                invalidate();
                notifyValueChanged(true);
                return true;
            }
        } else {
            reset();
            invalidate();
            notifyValueChanged(true);
            return false;
        }
    }

    public void reset() {
        this.mStartTouchAngle = 0.0d;
        this.mTouchAngle = 0.0d;
        this.mCurrentValue = 0;
        this.mCurrentValueD = 0.0d;
    }

    /* access modifiers changed from: protected */
    public void notifyValueChanged(boolean z) {
        if (this.mLastValue != this.mCurrentValue) {
            this.mLastValue = this.mCurrentValue;
            if (this.e != null) {
                this.e.a(this, this.mCurrentValue, z);
            }
        }
    }

    private void b() {
        this.mThumb = this.mRes.getDrawable(R.drawable.count_down_timer_thumb_off);
        int intrinsicHeight = this.mThumb.getIntrinsicHeight() / 2;
        int intrinsicWidth = this.mThumb.getIntrinsicWidth() / 2;
        this.b = (float) intrinsicWidth;
        this.mThumb.setBounds(-intrinsicWidth, -intrinsicHeight, intrinsicWidth, intrinsicHeight);
    }

    /* access modifiers changed from: protected */
    public void updateThumbPosition() {
        double d2 = (double) this.f18816a;
        double sin = Math.sin(Math.toRadians(this.mTouchAngle));
        Double.isNaN(d2);
        this.mThumbXPos = (int) (d2 * sin);
        double d3 = (double) this.f18816a;
        double cos = Math.cos(Math.toRadians(this.mTouchAngle));
        Double.isNaN(d3);
        this.mThumbYPos = (int) (d3 * cos);
    }

    private void c() {
        int height = getHeight();
        int width = getWidth();
        int min = (int) (((float) ((Math.min(width, height) - getPaddingLeft()) - getPaddingRight())) - (this.b * 2.0f));
        this.f18816a = (float) (min / 2);
        float f2 = ((float) (height / 2)) - this.f18816a;
        float f3 = ((float) (width / 2)) - this.f18816a;
        this.mArcRect = new RectF();
        float f4 = (float) min;
        this.mArcRect.set(f3, f2, f3 + f4, f4 + f2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mCenterPointX = (getRight() - getLeft()) / 2;
        this.mCenterPointY = (getBottom() - getTop()) / 2;
        updateThumbPosition();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mArcRect == null) {
            c();
        }
        this.mCirclePaint.setColor(this.mCircleColor);
        canvas.drawArc(this.mArcRect, (((float) (this.mTouchAngle % 360.0d)) - 90.0f) + 9.0f, 342.0f, false, this.mCirclePaint);
        if (this.mThumb != null) {
            updateThumbPosition();
            canvas.save();
            canvas.translate((float) (this.mCenterPointX + this.mThumbXPos), (float) (this.mCenterPointY - this.mThumbYPos));
            canvas.rotate((float) this.mTouchAngle, this.mThumb.getBounds().exactCenterX(), this.mThumb.getBounds().exactCenterY());
            this.mThumb.draw(canvas);
            canvas.restore();
        }
        canvas.save();
        this.mLinePaint.setColor(this.mLineColor);
        float f2 = this.mRes.getDisplayMetrics().density;
        for (int i = 0; i < 200; i++) {
            if (this.mCurrentValueD - 1.0E-4d <= 0.0d) {
                this.mLinePaint.setColor(this.mLineColor);
            } else if (this.mCurrentValue <= this.mRoundValue) {
                if (((double) (((((float) i) * 1.8f) * ((float) this.mRoundValue)) / 360.0f)) > this.mCurrentValueD) {
                    this.mLinePaint.setColor(this.mLineColor);
                } else {
                    this.mLinePaint.setColor(this.mCircleColor);
                }
            }
            float f3 = 5.0f * f2;
            Canvas canvas2 = canvas;
            canvas2.drawLine((float) this.mCenterPointX, (((float) this.mCenterPointY) - this.f18816a) + this.b + f3, (float) this.mCenterPointX, (((float) this.mCenterPointY) - this.f18816a) + this.b + f3 + (18.0f * f2), this.mLinePaint);
            canvas.rotate(1.8f, (float) this.mCenterPointX, (float) this.mCenterPointY);
        }
        canvas.restore();
        onDrawText(canvas, this.mCurrentValue, this.mTotalValue);
    }

    private static int a(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil((double) fArr[i2]);
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public double getTouchDegrees(float f2, float f3) {
        double degrees = Math.toDegrees(Math.atan2((double) (f2 - ((float) this.mCenterPointX)), (double) (((float) this.mCenterPointY) - f3)));
        if (f2 >= ((float) this.mCenterPointX) || f3 <= ((float) this.mCenterPointY)) {
            return (f2 >= ((float) this.mCenterPointX) || f3 >= ((float) this.mCenterPointY)) ? degrees : degrees + 360.0d;
        }
        return degrees + 360.0d;
    }

    /* access modifiers changed from: protected */
    public void onStartTrackingTouch() {
        if (this.e != null) {
            this.e.a(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onStopTrackingTouch() {
        if (this.e != null) {
            this.e.b(this);
        }
    }

    public void setThumb(Drawable drawable) {
        this.mThumb = drawable;
        b();
    }

    public void setupValues(int i, int i2, int i3) {
        this.mTotalValue = i;
        this.mCurrentValue = i2;
        this.mRoundValue = i3;
        notifyValueChanged(false);
        postInvalidate();
    }

    public void setCircleColor(int i) {
        this.mCircleColor = i;
    }

    public void setLineColor(int i) {
        this.mLineColor = i;
    }

    public void setCurrentValue(int i) {
        if (this.mTotalValue != 0 && this.mRoundValue != 0) {
            reset();
            updateOnNonTouch(i, false);
            notifyValueChanged(false);
            postInvalidate();
        }
    }

    public int getCurrentValue() {
        return this.mCurrentValue;
    }

    public void setOnSeekArcChangeListener(OnSeekArcChangeListener onSeekArcChangeListener) {
        this.e = onSeekArcChangeListener;
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

    public boolean isLocateInThumbArea(int i, int i2) {
        int i3 = this.mCenterPointX + this.mThumbXPos;
        int i4 = this.mCenterPointY - this.mThumbYPos;
        return i3 + -30 < i && i4 + -30 < i2 && (i3 + this.mThumb.getIntrinsicWidth()) + 30 > i && (i4 + this.mThumb.getIntrinsicHeight()) + 30 > i2;
    }
}
