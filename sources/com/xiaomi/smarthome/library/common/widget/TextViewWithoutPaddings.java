package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class TextViewWithoutPaddings extends AppCompatTextView {
    private final Rect mBounds = new Rect();
    private final Paint mPaint = new Paint();

    public TextViewWithoutPaddings(Context context) {
        super(context);
    }

    public TextViewWithoutPaddings(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TextViewWithoutPaddings(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(@NonNull Canvas canvas) {
        String calculateTextParams = calculateTextParams();
        int i = this.mBounds.left;
        int i2 = this.mBounds.bottom;
        this.mBounds.offset(-this.mBounds.left, -this.mBounds.top);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getCurrentTextColor());
        canvas.drawText(calculateTextParams, (float) (-i), (float) (this.mBounds.bottom - i2), this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        calculateTextParams();
        setMeasuredDimension(this.mBounds.width() + 1, (-this.mBounds.top) + this.mBounds.bottom);
    }

    private String calculateTextParams() {
        String charSequence = getText().toString();
        int length = charSequence.length();
        this.mPaint.setTextSize(getTextSize());
        this.mPaint.getTextBounds(charSequence, 0, length, this.mBounds);
        if (length == 0) {
            this.mBounds.right = this.mBounds.left;
        }
        return charSequence;
    }
}
