package com.wx.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import com.wx.wheelview.common.WheelConstants;
import com.wx.wheelview.widget.WheelView;

public class CommonDrawable extends WheelDrawable {
    private static final int[] d = {-15658735, 11184810, 11184810};
    private GradientDrawable e = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, d);
    private GradientDrawable f = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, d);
    private Paint g;
    private Paint h;
    private Paint i;
    private Paint j;
    private int k;
    private int l;

    public CommonDrawable(int i2, int i3, WheelView.WheelViewStyle wheelViewStyle, int i4, int i5) {
        super(i2, i3, wheelViewStyle);
        this.k = i4;
        this.l = i5;
        a();
    }

    private void a() {
        this.g = new Paint();
        this.g.setColor(this.c.f9887a != -1 ? this.c.f9887a : WheelConstants.h);
        this.h = new Paint();
        this.h.setColor(WheelConstants.o);
        this.i = new Paint();
        this.i.setColor(WheelConstants.p);
        this.i.setStrokeWidth(2.0f);
        this.j = new Paint();
        this.j.setStrokeWidth(6.0f);
        this.j.setColor(WheelConstants.q);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) this.f9875a, (float) this.b, this.g);
        if (this.l != 0) {
            canvas.drawRect(0.0f, (float) (this.l * (this.k / 2)), (float) this.f9875a, (float) (this.l * ((this.k / 2) + 1)), this.h);
            canvas.drawLine(0.0f, (float) (this.l * (this.k / 2)), (float) this.f9875a, (float) (this.l * (this.k / 2)), this.i);
            canvas.drawLine(0.0f, (float) (this.l * ((this.k / 2) + 1)), (float) this.f9875a, (float) (this.l * ((this.k / 2) + 1)), this.i);
            this.e.setBounds(0, 0, this.f9875a, this.l);
            this.e.draw(canvas);
            this.f.setBounds(0, this.b - this.l, this.f9875a, this.b);
            this.f.draw(canvas);
            Canvas canvas2 = canvas;
            canvas2.drawLine(0.0f, 0.0f, 0.0f, (float) this.b, this.j);
            canvas2.drawLine((float) this.f9875a, 0.0f, (float) this.f9875a, (float) this.b, this.j);
        }
    }
}
