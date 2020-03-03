package com.xiaomiyoupin.toast.dialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomiyoupin.toast.R;

public class CustomCircleProgressBar extends View {
    private static final int DEFAULT_BACKGROUND_COLOR = -7829368;
    private static final int DEFAULT_INSIDE_INDENT = 0;
    private static final int DEFAULT_MAX_VALUE = 100;
    private static final int DEFAULT_PAINT_COLOR = -1;
    private static final int DEFAULT_PAINT_WIDTH = 10;
    private Drawable mBackgroundPicture = null;
    private CustomCircleAttribute mCircleAttribute = new CustomCircleAttribute();
    private int mCurrent = 0;
    private boolean mIsClockWise = true;
    private boolean mIsIncrease = false;
    private int mMax = 100;

    public CustomCircleProgressBar(Context context) {
        super(context);
    }

    public CustomCircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.YPDCustomCircleProgressBar);
        this.mMax = obtainStyledAttributes.getInteger(R.styleable.YPDCustomCircleProgressBar_max, 100);
        this.mCircleAttribute.setPaintWidth(obtainStyledAttributes.getInt(R.styleable.YPDCustomCircleProgressBar_paint_width, 10));
        this.mCircleAttribute.setPaintColor(obtainStyledAttributes.getColor(R.styleable.YPDCustomCircleProgressBar_paint_color, -1));
        this.mCircleAttribute.insideIndent = obtainStyledAttributes.getInt(R.styleable.YPDCustomCircleProgressBar_inside_indent, 0);
        this.mCircleAttribute.setBackgroundColor(obtainStyledAttributes.getColor(R.styleable.YPDCustomCircleProgressBar_background_color, -7829368));
        this.mIsClockWise = obtainStyledAttributes.getBoolean(R.styleable.YPDCustomCircleProgressBar_is_clockwise, true);
        this.mIsIncrease = obtainStyledAttributes.getBoolean(R.styleable.YPDCustomCircleProgressBar_is_increase_paint, false);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.mBackgroundPicture = getBackground();
        if (this.mBackgroundPicture != null) {
            size = this.mBackgroundPicture.getMinimumWidth();
            size2 = this.mBackgroundPicture.getMinimumHeight();
        }
        setMeasuredDimension(resolveSize(size, i), resolveSize(size2, i2));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mCircleAttribute.resize(i, i2);
    }

    public void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (this.mBackgroundPicture == null) {
            canvas.drawArc(this.mCircleAttribute.roundOval, 0.0f, 360.0f, true, this.mCircleAttribute.backgroundPainter);
        }
        if (this.mIsIncrease) {
            f = 1.0f - (((float) this.mCurrent) / ((float) this.mMax));
        } else {
            f = ((float) this.mCurrent) / ((float) this.mMax);
        }
        float f2 = f * 360.0f;
        if (this.mIsClockWise) {
            canvas.drawArc(this.mCircleAttribute.roundOval, (float) this.mCircleAttribute.startDegrees, f2, false, this.mCircleAttribute.progressPainter);
            return;
        }
        canvas.drawArc(this.mCircleAttribute.roundOval, (float) this.mCircleAttribute.startDegrees, -f2, false, this.mCircleAttribute.progressPainter);
    }

    public synchronized int getMax() {
        return this.mMax;
    }

    public synchronized void setMax(int i) {
        this.mMax = i;
        if (this.mMax < 0) {
            this.mMax = 0;
        }
        if (this.mMax < this.mCurrent) {
            this.mMax = this.mCurrent;
        }
        invalidate();
    }

    public synchronized int getProgress() {
        return this.mCurrent;
    }

    public synchronized void setProgress(int i) {
        this.mCurrent = i;
        if (this.mCurrent < 0) {
            this.mCurrent = 0;
        }
        if (this.mCurrent > this.mMax) {
            this.mCurrent = this.mMax;
        }
        invalidate();
    }

    class CustomCircleAttribute {
        @ColorInt
        private int backgroundColor;
        public Paint backgroundPainter;
        public int insideIndent = 0;
        public int paintColor = -1;
        public int paintWidth = 0;
        public Paint progressPainter = new Paint();
        public RectF roundOval = new RectF();
        public int startDegrees = -90;

        public CustomCircleAttribute() {
            this.progressPainter.setAntiAlias(true);
            this.progressPainter.setStyle(Paint.Style.STROKE);
            this.progressPainter.setStrokeWidth((float) this.paintWidth);
            this.progressPainter.setColor(this.paintColor);
            this.backgroundPainter = new Paint();
            this.backgroundPainter.setAntiAlias(true);
            this.backgroundPainter.setStyle(Paint.Style.STROKE);
            this.backgroundPainter.setStrokeWidth((float) this.paintWidth);
            this.backgroundPainter.setColor(this.backgroundColor);
        }

        public void setPaintWidth(int i) {
            this.paintWidth = i;
            float f = (float) i;
            this.progressPainter.setStrokeWidth(f);
            this.backgroundPainter.setStrokeWidth(f);
        }

        public void setPaintColor(int i) {
            this.paintColor = i;
            this.progressPainter.setColor(i);
        }

        public void setBackgroundColor(@ColorInt int i) {
            this.backgroundColor = i;
            this.backgroundPainter.setColor(this.backgroundColor);
        }

        public void resize(int i, int i2) {
            if (this.insideIndent != 0) {
                this.roundOval.set((float) ((this.paintWidth / 2) + this.insideIndent), (float) ((this.paintWidth / 2) + this.insideIndent), (float) ((i - (this.paintWidth / 2)) - this.insideIndent), (float) ((i2 - (this.paintWidth / 2)) - this.insideIndent));
                return;
            }
            int paddingLeft = CustomCircleProgressBar.this.getPaddingLeft();
            int paddingRight = CustomCircleProgressBar.this.getPaddingRight();
            this.roundOval.set((float) (paddingLeft + (this.paintWidth / 2)), (float) (CustomCircleProgressBar.this.getPaddingTop() + (this.paintWidth / 2)), (float) ((i - paddingRight) - (this.paintWidth / 2)), (float) ((i2 - CustomCircleProgressBar.this.getPaddingBottom()) - (this.paintWidth / 2)));
        }
    }
}
