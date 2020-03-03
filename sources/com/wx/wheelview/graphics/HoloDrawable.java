package com.wx.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.wx.wheelview.common.WheelConstants;
import com.wx.wheelview.widget.WheelView;

public class HoloDrawable extends WheelDrawable {
    private Paint d;
    private Paint e;
    private int f;
    private int g;

    public HoloDrawable(int i, int i2, WheelView.WheelViewStyle wheelViewStyle, int i3, int i4) {
        super(i, i2, wheelViewStyle);
        this.f = i3;
        this.g = i4;
        a();
    }

    private void a() {
        this.d = new Paint();
        this.d.setColor(this.c.f9887a != -1 ? this.c.f9887a : -1);
        this.e = new Paint();
        this.e.setStrokeWidth(3.0f);
        this.e.setColor(this.c.b != -1 ? this.c.b : WheelConstants.j);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) this.f9875a, (float) this.b, this.d);
        if (this.g != 0) {
            canvas.drawLine(0.0f, (float) (this.g * (this.f / 2)), (float) this.f9875a, (float) (this.g * (this.f / 2)), this.e);
            canvas.drawLine(0.0f, (float) (this.g * ((this.f / 2) + 1)), (float) this.f9875a, (float) (this.g * ((this.f / 2) + 1)), this.e);
        }
    }
}
