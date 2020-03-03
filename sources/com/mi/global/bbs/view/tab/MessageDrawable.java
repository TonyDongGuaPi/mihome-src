package com.mi.global.bbs.view.tab;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

public class MessageDrawable extends Drawable {
    private int backgroundColor;
    private float fontHeight;
    private float fontWidth;
    private Paint mPaint;
    private TextPaint mTextPaint;
    private int msgCount;
    private float padding;
    private float radius;

    public MessageDrawable(int i, float f, int i2, float f2) {
        this.backgroundColor = i;
        this.padding = f;
        this.msgCount = i2;
        initPaint(i, f2);
        calculateMsgValue(f);
    }

    private void initPaint(int i, float f) {
        this.mPaint = new Paint(1);
        this.mPaint.setColor(i);
        this.mTextPaint = new TextPaint(1);
        this.mTextPaint.setTextSize(f);
        this.mTextPaint.setColor(-1);
    }

    private void calculateMsgValue(float f) {
        Paint.FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
        this.fontHeight = fontMetrics.leading + fontMetrics.ascent + fontMetrics.descent;
        this.fontWidth = this.mTextPaint.measureText("99");
        this.radius = (Math.max(this.fontHeight, this.fontWidth) / 2.0f) + f;
    }

    public void draw(Canvas canvas) {
        drawMsgCount(canvas, this.msgCount);
    }

    public float getRadius() {
        return this.radius;
    }

    private void drawMsgCount(Canvas canvas, int i) {
        String valueOf = String.valueOf(i);
        float measureText = this.mTextPaint.measureText(valueOf);
        Rect bounds = getBounds();
        if (i <= 99) {
            float f = ((float) bounds.left) + this.radius;
            float f2 = ((float) bounds.top) + this.radius;
            canvas.drawCircle(f, f2, this.radius, this.mPaint);
            canvas.drawText(valueOf, f - (measureText / 2.0f), f2 - (this.fontHeight / 2.0f), this.mTextPaint);
            return;
        }
        float f3 = ((float) bounds.left) + measureText + (this.padding * 2.0f);
        canvas.drawRoundRect(new RectF((float) bounds.left, (float) bounds.top, f3, ((float) bounds.top) + (this.radius * 2.0f)), this.radius, this.radius, this.mPaint);
        canvas.drawText("99+", (f3 - measureText) / 2.0f, (((float) bounds.top) + this.radius) - (this.fontHeight / 2.0f), this.mTextPaint);
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        int i = this.backgroundColor >>> 24;
        if (i != 0) {
            return i != 255 ? -3 : -1;
        }
        return -2;
    }

    public void setBackgroundColor(int i) {
        this.mPaint.setColor(i);
    }

    public void setPadding(float f) {
        this.padding = f;
        calculateMsgValue(f);
    }

    public void setMsgTextSize(float f) {
        this.mTextPaint.setTextSize(f);
        calculateMsgValue(this.padding);
    }

    public void setMsgTextColor(int i) {
        this.mTextPaint.setColor(i);
    }

    public int getMsgCount() {
        return this.msgCount;
    }

    public void setMsgCount(int i) {
        this.msgCount = i;
    }
}
