package com.mi.global.bbs.view.tab;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class CircleDrawable extends Drawable {
    private int backgroundColor;
    private Paint mPaint;
    private float radius;
    private RectF rf;
    private boolean show;

    public CircleDrawable(int i) {
        this.backgroundColor = i;
        this.mPaint = new Paint(1);
        this.mPaint.setColor(i);
        this.rf = new RectF();
    }

    public CircleDrawable(int i, float f) {
        this(i);
        this.radius = f;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setShow(boolean z) {
        this.show = z;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        this.mPaint.setColor(i);
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    public void draw(Canvas canvas) {
        if ((this.backgroundColor >>> 24) != 0 && this.radius > 0.0f && this.show) {
            if (this.rf.isEmpty()) {
                this.rf.set(getBounds());
            }
            canvas.drawCircle(this.rf.left + this.radius, this.rf.top + this.radius, this.radius, this.mPaint);
        }
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
}
