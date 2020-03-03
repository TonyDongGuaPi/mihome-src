package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;

public class ColorBar extends ImageView {
    public static final int CALCULATION_HSV = 1;
    public static final int CALCULATION_INT = 0;
    public static final float WHITE_STRIP_BAND = 0.05f;
    private Drawable mArrowPicture;
    int mColor;
    private Drawable mColorPicker;
    private float mDensity;
    private float mH;
    private boolean mHasPicker;
    Paint mPaint = new Paint();
    private int mState;
    private Drawable mTapePicture;
    private int mX;

    public ColorBar(Context context) {
        super(context);
    }

    public ColorBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ColorBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mDensity = getContext().getResources().getDisplayMetrics().density;
        this.mArrowPicture = getContext().getResources().getDrawable(R.drawable.color_seekbar_thum);
        this.mTapePicture = getContext().getResources().getDrawable(R.drawable.remote_contro_lamp_bg);
        this.mColorPicker = getContext().getResources().getDrawable(R.drawable.color_seekbar_pop_bg);
    }

    private int dip2px(float f) {
        return (int) ((f * this.mDensity) + 0.5f);
    }

    public void setPicker(boolean z) {
        this.mHasPicker = z;
    }

    public void movePoint(int i) {
        if (i >= 0 && i <= getMeasuredPoint()) {
            int height = getHeight();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int i2 = height + paddingTop + paddingBottom;
            int intrinsicWidth = this.mArrowPicture.getIntrinsicWidth();
            int intrinsicHeight = this.mArrowPicture.getIntrinsicHeight();
            int intrinsicWidth2 = this.mColorPicker.getIntrinsicWidth();
            int intrinsicHeight2 = this.mColorPicker.getIntrinsicHeight();
            this.mState = 0;
            this.mX = i;
            int i3 = (i2 - paddingBottom) + (-dip2px(20.0f));
            int i4 = i3 - intrinsicHeight;
            this.mColorPicker.setBounds(this.mX + paddingLeft, i4 - intrinsicHeight2, this.mX + intrinsicWidth2, i4);
            int i5 = paddingLeft + ((intrinsicWidth2 - intrinsicWidth) / 2);
            this.mArrowPicture.setBounds(this.mX + i5, i4, i5 + intrinsicWidth + this.mX, i3);
            invalidate();
        }
    }

    public void movePointHSV(float f) {
        this.mState = 1;
        this.mH = f;
        requestLayout();
        invalidate();
    }

    public int getMeasuredPoint() {
        return (int) (((float) (getMeasuredWidth() - dip2px(60.0f))) + 0.5f);
    }

    public int getCircleDim() {
        return this.mColorPicker.getIntrinsicWidth();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int resolveSize = resolveSize(size + paddingRight + paddingLeft, i);
        int resolveSize2 = resolveSize(size2 + paddingTop + paddingBottom, i2);
        setMeasuredDimension(resolveSize, resolveSize2);
        if (this.mState == 1) {
            this.mX = (int) ((((360.0f - this.mH) * (((float) getMeasuredPoint()) * 0.95f)) / 360.0f) + (((float) getMeasuredPoint()) * 0.05f));
            if (this.mX < 0) {
                this.mX = 0;
            }
            if (this.mX > getMeasuredPoint()) {
                this.mX = getMeasuredPoint();
            }
        }
        int intrinsicWidth = this.mArrowPicture.getIntrinsicWidth();
        int intrinsicHeight = this.mArrowPicture.getIntrinsicHeight();
        int intrinsicWidth2 = this.mColorPicker.getIntrinsicWidth();
        int intrinsicHeight2 = this.mColorPicker.getIntrinsicHeight();
        int intrinsicHeight3 = this.mTapePicture.getIntrinsicHeight();
        int i3 = (resolveSize2 - paddingBottom) + (-dip2px(20.0f));
        int i4 = i3 - intrinsicHeight;
        this.mColorPicker.setBounds(this.mX + paddingLeft, i4 - intrinsicHeight2, this.mX + intrinsicWidth2, i4);
        int i5 = paddingLeft + ((intrinsicWidth2 - intrinsicWidth) / 2);
        this.mArrowPicture.setBounds(this.mX + i5, i4, i5 + intrinsicWidth + this.mX, i3);
        int i6 = intrinsicHeight / 2;
        int i7 = intrinsicHeight3 / 2;
        this.mTapePicture.setBounds(i5 + (intrinsicWidth / 2), (i4 + i6) - i7, resolveSize - dip2px(30.0f), (i3 - i6) + i7);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mHasPicker) {
            this.mColorPicker.draw(canvas);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(this.mColor);
            this.mPaint.setStrokeWidth(3.0f);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle((float) ((this.mColorPicker.getBounds().width() / 2) + this.mX), (float) ((this.mColorPicker.getBounds().top + (this.mColorPicker.getBounds().height() / 2)) - dip2px(5.5f)), (float) ((this.mColorPicker.getBounds().width() / 2) - dip2px(10.0f)), this.mPaint);
        }
        this.mTapePicture.draw(canvas);
        this.mArrowPicture.draw(canvas);
    }

    public void setColor(int i) {
        this.mColor = i;
    }
}
