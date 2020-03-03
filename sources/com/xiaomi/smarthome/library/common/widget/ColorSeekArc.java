package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;

public class ColorSeekArc extends SeekArc {
    Paint mSweepPaint;
    Shader mSweepShader = null;

    public ColorSeekArc(Context context) {
        super(context);
    }

    public ColorSeekArc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ColorSeekArc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attributeSet, int i) {
        super.init(context, attributeSet, i);
        this.mSweepPaint = new Paint(1);
        this.mSweepPaint.setStyle(Paint.Style.STROKE);
        this.mSweepPaint.setStrokeWidth((float) this.mArcWidth);
    }

    public void setArcColor(int i) {
        int i2 = -16777216 | i;
        int i3 = i & 16777215;
        this.mSweepShader = new SweepGradient(this.mArcRect.centerX(), this.mArcRect.centerY(), new int[]{i3, i3, i2}, new float[]{0.0f, 0.45833334f, 1.0f});
        this.mSweepPaint.setShader(this.mSweepShader);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mSweepShader == null) {
            super.onDraw(canvas);
            return;
        }
        if (!this.mClockwise) {
            canvas.scale(-1.0f, 1.0f, this.mArcRect.centerX(), this.mArcRect.centerY());
        }
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.mArcRect, (float) ((this.mStartAngle - 90) + this.mRotation), (float) this.mSweepAngle, false, this.mSweepPaint);
        canvas.translate((float) (this.mTranslateX - this.mThumbXPos), (float) (this.mTranslateY - this.mThumbYPos));
        this.mThumb.draw(canvas);
    }
}
