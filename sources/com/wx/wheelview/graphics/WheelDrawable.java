package com.wx.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.wx.wheelview.widget.WheelView;

public class WheelDrawable extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    protected int f9875a;
    protected int b;
    protected WheelView.WheelViewStyle c;
    private Paint d;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public WheelDrawable(int i, int i2, WheelView.WheelViewStyle wheelViewStyle) {
        this.f9875a = i;
        this.b = i2;
        this.c = wheelViewStyle;
        a();
    }

    private void a() {
        this.d = new Paint();
        Paint paint = this.d;
        int i = -1;
        if (this.c.f9887a != -1) {
            i = this.c.f9887a;
        }
        paint.setColor(i);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) this.f9875a, (float) this.b, this.d);
    }
}
