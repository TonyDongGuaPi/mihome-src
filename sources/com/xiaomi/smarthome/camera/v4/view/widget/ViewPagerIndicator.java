package com.xiaomi.smarthome.camera.v4.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.mijia.app.AppConfig;
import com.xiaomi.smarthome.R;

public class ViewPagerIndicator extends View {
    private int mCount;
    private int mCurrentIndex;
    private int mLineWidth;
    private Paint mNormalPain;
    private float mOffset;
    private int mOffsetWidth;
    private float mRadius;
    private Paint mSelectPain;

    public ViewPagerIndicator(Context context) {
        this(context, (AttributeSet) null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public ViewPagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLineWidth = 0;
        this.mOffsetWidth = 0;
        this.mRadius = 0.0f;
        this.mCount = 2;
        this.mCurrentIndex = 0;
        this.mOffset = 0.0f;
        init();
    }

    private void init() {
        this.mSelectPain = new Paint();
        this.mSelectPain.setAntiAlias(true);
        this.mSelectPain.setStyle(Paint.Style.FILL);
        this.mSelectPain.setColor(getResources().getColor(R.color.indicator_select));
        this.mNormalPain = new Paint();
        this.mNormalPain.setAntiAlias(true);
        this.mNormalPain.setStyle(Paint.Style.FILL);
        this.mNormalPain.setColor(getResources().getColor(R.color.indicator_normal));
        this.mRadius = AppConfig.d * 4.0f;
        this.mLineWidth = (int) (this.mRadius * 2.0f);
        this.mOffsetWidth = (int) (AppConfig.d * 8.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.mCount > 1) {
            i = (this.mLineWidth * this.mCount) + (this.mOffsetWidth * (this.mCount - 1));
        } else {
            i = this.mLineWidth;
        }
        int i2 = (width / 2) - (i / 2);
        int i3 = 0;
        for (int i4 = 0; i4 < this.mCount; i4++) {
            canvas.drawCircle((float) i2, (float) (height / 2), this.mRadius, this.mNormalPain);
            if (i4 == this.mCurrentIndex) {
                i3 = (this.mOffset != 0.0f ? (int) (((float) (this.mLineWidth + this.mOffsetWidth)) * this.mOffset) : 0) + i2;
            }
            i2 = i2 + this.mLineWidth + this.mOffsetWidth;
        }
        canvas.drawCircle((float) i3, (float) (height / 2), this.mRadius, this.mSelectPain);
    }

    public void setCount(int i) {
        this.mCount = i;
    }

    public void setCurrentIndex(int i) {
        this.mCurrentIndex = i;
        this.mOffset = 0.0f;
        invalidate();
    }

    public void changeOffset(int i, float f) {
        if (i == this.mCurrentIndex) {
            this.mOffset = f;
        } else {
            this.mOffset = -(1.0f - f);
        }
        invalidate();
    }
}
