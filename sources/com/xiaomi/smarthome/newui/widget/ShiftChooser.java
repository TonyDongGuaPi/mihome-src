package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import java.util.ArrayList;
import java.util.List;

public class ShiftChooser extends AppCompatImageView {
    private static final String TAG = "ShiftChooser";
    private boolean canThumbSeek;
    private boolean hasSeeked;
    private boolean inited;
    private int mBgColor;
    private Paint mBgPaint;
    private Path mBgPath;
    private boolean mCanChoose;
    private int mCurrentShift;
    private int mHeight;
    private OnShiftChangedListener mOnShiftChangedListener;
    private int mPadding;
    private int mRadius;
    private int mShiftColor;
    private Paint mShiftPaint;
    private int mShiftRadius;
    private List<Integer> mShiftsCoordinatesX;
    private int mStartX;
    private String mText;
    private int mTextColor;
    private Paint mTextPaint;
    private int mTextSize;
    private int mThumbColor;
    private Paint mThumbPaint;
    private Path mThumbPath;
    private int mThumbRadius;
    private int mThumbUnableColor;
    private int mThumbWidth;
    private long mTimeMillisDown;
    private String[] mTitles;
    private int mTotalShifts;
    private int mTouchSlop;
    private int mTranslateLength;
    private int mWidth;

    public interface OnShiftChangedListener {
        void onShiftChanged(ShiftChooser shiftChooser, int i);
    }

    public ShiftChooser(Context context) {
        this(context, (AttributeSet) null);
    }

    public ShiftChooser(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShiftChooser(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        String str;
        this.inited = false;
        this.mTotalShifts = 5;
        this.mCurrentShift = 0;
        this.canThumbSeek = false;
        this.mText = "";
        this.mCanChoose = true;
        this.mShiftsCoordinatesX = new ArrayList();
        this.mTitles = new String[0];
        this.hasSeeked = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShiftChooser, i, 0);
        this.mBgColor = obtainStyledAttributes.getColor(R.styleable.ShiftChooser_bg_color, getResources().getColor(R.color.shiftbar_bg));
        this.mThumbColor = obtainStyledAttributes.getColor(R.styleable.ShiftChooser_thumb_color, getResources().getColor(R.color.shiftbar_thumb_bg));
        this.mShiftColor = obtainStyledAttributes.getColor(R.styleable.ShiftChooser_shift_color, getResources().getColor(R.color.shiftbar_shift));
        this.mThumbUnableColor = obtainStyledAttributes.getColor(R.styleable.ShiftChooser_shift_color, getResources().getColor(R.color.shiftbar_shift));
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.ShiftChooser_text_color, getResources().getColor(R.color.shiftbar_bar_text));
        this.mTextSize = (int) obtainStyledAttributes.getDimension(R.styleable.ShiftChooser_text_size, (float) DisplayUtils.d(getContext(), 16.0f));
        this.mShiftRadius = (int) obtainStyledAttributes.getDimension(R.styleable.ShiftChooser_shift_radius, (float) DisplayUtils.d(getContext(), 5.0f));
        this.mTotalShifts = obtainStyledAttributes.getInteger(R.styleable.ShiftChooser_max_shifts, 5);
        CharSequence text = obtainStyledAttributes.getText(R.styleable.ShiftChooser_text);
        if (text == null) {
            str = "";
        } else {
            str = text.toString();
        }
        this.mText = str;
        obtainStyledAttributes.recycle();
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        this.mBgPaint = new Paint(1);
        this.mBgPaint.setColor(this.mBgColor);
        this.mThumbPaint = new Paint(1);
        this.mThumbPaint.setStyle(Paint.Style.FILL);
        this.mThumbPaint.setColor(this.mThumbColor);
        this.mTextPaint = new Paint(1);
        this.mTextPaint.setColor(this.mTextColor);
        this.mTextPaint.setTextSize((float) this.mTextSize);
        this.mTextPaint.setTextAlign(Paint.Align.LEFT);
        refreshLocation();
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /* access modifiers changed from: package-private */
    public void refreshLocation() {
        this.mRadius = this.mHeight / 2;
        this.mPadding = this.mRadius / 5;
        this.mTranslateLength = this.mPadding;
        this.mThumbWidth = (this.mWidth - (this.mPadding * 2)) / this.mTotalShifts;
        this.mThumbRadius = this.mRadius - this.mPadding;
        resetShiftCoordinateX();
        this.mTranslateLength = getTranslateStartX(this.mCurrentShift);
        RectF rectF = new RectF(0.0f, 0.0f, (float) (this.mRadius * 2), (float) (this.mRadius * 2));
        RectF rectF2 = new RectF((float) (this.mWidth - (this.mRadius * 2)), 0.0f, (float) this.mWidth, (float) this.mHeight);
        RectF rectF3 = new RectF((float) this.mRadius, 0.0f, (float) (this.mWidth - this.mRadius), (float) this.mHeight);
        this.mBgPath = new Path();
        this.mBgPath.addArc(rectF, 90.0f, 180.0f);
        this.mBgPath.addRect(rectF3, Path.Direction.CCW);
        this.mBgPath.addArc(rectF2, -90.0f, 180.0f);
        this.mShiftPaint = new Paint(1);
        this.mShiftPaint.setColor(this.mShiftColor);
        refreshThumbSize();
    }

    /* access modifiers changed from: package-private */
    public void refreshThumbSize() {
        int i = this.mWidth - (this.mPadding * 2);
        int length = (this.mText.length() <= 4 || this.mCurrentShift <= 0 || this.mCurrentShift >= this.mTotalShifts + -1) ? 0 : (this.mText.length() - 4) * 20;
        this.mThumbWidth = i / this.mTotalShifts;
        this.mThumbRadius = this.mRadius - this.mPadding;
        this.mThumbPath = new Path();
        RectF rectF = new RectF(0.0f, 0.0f, (float) (this.mThumbRadius * 2), (float) (this.mThumbRadius * 2));
        RectF rectF2 = new RectF((float) ((this.mThumbWidth + length) - (this.mThumbRadius * 2)), 0.0f, (float) (this.mThumbWidth + length), (float) (this.mThumbRadius * 2));
        RectF rectF3 = new RectF((float) this.mThumbRadius, 0.0f, (float) ((this.mThumbWidth + length) - this.mThumbRadius), (float) (this.mThumbRadius * 2));
        this.mThumbPath.addArc(rectF, 90.0f, 180.0f);
        this.mThumbPath.addRect(rectF3, Path.Direction.CCW);
        this.mThumbPath.addArc(rectF2, -90.0f, 180.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        refreshThumbSize();
        canvas.clipPath(this.mBgPath);
        canvas.drawRect(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight, this.mBgPaint);
        drawShifts(canvas);
        canvas.save();
        canvas.translate((float) this.mTranslateLength, (float) this.mPadding);
        canvas.drawPath(this.mThumbPath, this.mThumbPaint);
        drawText(canvas);
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetricsInt fontMetricsInt = this.mTextPaint.getFontMetricsInt();
        RectF rectF = new RectF();
        this.mThumbPath.computeBounds(rectF, true);
        int i = (int) ((((rectF.bottom + rectF.top) - ((float) fontMetricsInt.bottom)) - ((float) fontMetricsInt.top)) / 2.0f);
        int measureText = (int) this.mTextPaint.measureText(this.mText);
        int i2 = 0;
        int length = (this.mText.length() <= 4 || this.mCurrentShift <= 0 || this.mCurrentShift >= this.mTotalShifts - 1) ? 0 : (this.mText.length() - 4) * 20;
        if (measureText <= this.mThumbWidth + length) {
            i2 = ((this.mThumbWidth + length) - measureText) / 2;
        }
        canvas.drawText(this.mText, (float) i2, (float) i, this.mTextPaint);
    }

    private void drawShifts(Canvas canvas) {
        resetShiftCoordinateX();
        for (Integer intValue : this.mShiftsCoordinatesX) {
            canvas.drawCircle((float) intValue.intValue(), (float) (this.mHeight / 2), (float) this.mShiftRadius, this.mShiftPaint);
        }
    }

    public void setText(String str) {
        this.mText = str;
        invalidate();
    }

    public void scrollToShift(int i, boolean z) {
        Log.e(TAG, "which: " + i);
        if (i < 0) {
            i = 0;
        }
        if (i > this.mTotalShifts - 1) {
            i = this.mTotalShifts - 1;
        }
        this.mCurrentShift = i;
        this.mTranslateLength = getTranslateStartX(i);
        if (this.mTitles != null && i >= 0 && i < this.mTitles.length) {
            this.mText = this.mTitles[i];
        }
        invalidate();
        if (this.mOnShiftChangedListener != null && z) {
            this.mOnShiftChangedListener.onShiftChanged(this, this.mCurrentShift);
        }
    }

    private void resetShiftCoordinateX() {
        int i;
        this.mShiftsCoordinatesX.clear();
        int i2 = (this.mThumbWidth / 2) + this.mPadding;
        for (int i3 = 0; i3 < this.mTotalShifts; i3++) {
            if (i3 == 0) {
                i = 0;
            } else {
                i = this.mThumbWidth;
            }
            i2 += i;
            this.mShiftsCoordinatesX.add(Integer.valueOf(i2));
        }
    }

    private int getTranslateStartX(int i) {
        return (this.mShiftsCoordinatesX.get(i).intValue() - (this.mThumbWidth / 2)) - (((this.mTitles.length <= 0 || this.mTitles[i].length() <= 4 || this.mCurrentShift <= 0 || this.mCurrentShift >= this.mTotalShifts + -1) ? 0 : (this.mText.length() - 4) * 20) / 2);
    }

    public int getCurrentShift() {
        return this.mCurrentShift;
    }

    public int getTotalShifts() {
        return this.mTotalShifts;
    }

    public void setTotalShifts(int i) {
        this.mTotalShifts = i;
        this.inited = false;
        resetShiftCoordinateX();
        invalidate();
    }

    public void setShiftsTitles(String[] strArr) {
        if (strArr.length == this.mTotalShifts) {
            this.mTitles = strArr;
            this.mText = this.mTitles[0];
            invalidate();
            return;
        }
        throw new IllegalArgumentException("titles length not equals mTotalShifts!");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mCanChoose) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mStartX = (int) motionEvent.getX();
                this.mTimeMillisDown = System.currentTimeMillis();
                if (!hitThumb((float) this.mStartX)) {
                    this.canThumbSeek = false;
                    break;
                } else {
                    this.canThumbSeek = true;
                    this.mTranslateLength = getTranslateStartX(getCurrentShift());
                    break;
                }
            case 1:
            case 3:
                this.canThumbSeek = false;
                if (motionEvent.getX() - ((float) this.mStartX) >= ((float) this.mTouchSlop) || System.currentTimeMillis() - this.mTimeMillisDown >= 500) {
                    Log.e(TAG, "seek true: ");
                    this.hasSeeked = true;
                } else {
                    this.hasSeeked = false;
                    Log.e(TAG, "seek false: ");
                }
                int calcWhichShiftToScroll = calcWhichShiftToScroll((int) motionEvent.getX());
                this.mText = this.mTitles[calcWhichShiftToScroll];
                Log.e(TAG, "which: " + calcWhichShiftToScroll);
                scrollToShift(calcWhichShiftToScroll, true);
                break;
            case 2:
                if (this.canThumbSeek) {
                    float x = motionEvent.getX();
                    this.mTranslateLength = getTranslateStartX(getCurrentShift()) + ((int) (x - ((float) this.mStartX)));
                    if (this.mTranslateLength < this.mPadding) {
                        this.mTranslateLength = this.mPadding;
                    }
                    if (this.mTranslateLength > (this.mWidth - this.mPadding) - this.mThumbWidth) {
                        this.mTranslateLength = (this.mWidth - this.mPadding) - this.mThumbWidth;
                    }
                    this.mText = this.mTitles[calcWhichShiftToScroll((int) x)];
                    invalidate();
                    break;
                }
                break;
        }
        return true;
    }

    public void setCanChoose(boolean z) {
        this.mCanChoose = z;
    }

    public boolean canChoose() {
        return this.mCanChoose;
    }

    private int calcWhichShiftToScroll(int i) {
        if (this.hasSeeked) {
            i = this.mTranslateLength + (this.mThumbWidth / 2);
        }
        int size = this.mShiftsCoordinatesX.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (Math.abs(this.mShiftsCoordinatesX.get(i2).intValue() - i) <= this.mThumbWidth / 2) {
                return i2;
            }
        }
        return this.mCurrentShift;
    }

    private double getDistance(double d, double d2, double d3, double d4) {
        double d5 = d3 - d;
        double d6 = d4 - d2;
        return Math.sqrt((d5 * d5) - (d6 * d6));
    }

    private boolean hitThumb(float f) {
        return new Rect(this.mTranslateLength, this.mPadding, this.mTranslateLength + this.mThumbWidth, (this.mThumbRadius * 2) + this.mPadding).contains((int) f, this.mPadding);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != this.mWidth || i2 != this.mHeight) {
            this.mWidth = i;
            this.mHeight = i2;
            refreshLocation();
        }
    }

    public void updateEnableUI(boolean z) {
        if (z) {
            this.mThumbPaint.setColor(this.mThumbColor);
        } else {
            this.mThumbPaint.setColor(this.mThumbUnableColor);
        }
        invalidate();
    }

    public void setOnShiftChangedListener(OnShiftChangedListener onShiftChangedListener) {
        this.mOnShiftChangedListener = onShiftChangedListener;
    }
}
