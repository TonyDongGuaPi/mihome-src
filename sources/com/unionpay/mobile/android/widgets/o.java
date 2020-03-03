package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.view.View;
import com.libra.Color;

public final class o extends View {

    /* renamed from: a  reason: collision with root package name */
    private Paint f9799a;
    private int b;

    public o(Context context) {
        this(context, Color.c, 0);
    }

    public o(Context context, int i, int i2) {
        super(context);
        this.f9799a = new Paint();
        this.f9799a.setStyle(Paint.Style.STROKE);
        this.f9799a.setStrokeWidth(4.0f);
        this.f9799a.setColor(i);
        this.f9799a.setPathEffect((PathEffect) null);
        this.b = i2;
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b == 0) {
            float height = (float) (getHeight() >> 1);
            canvas.drawLine(0.0f, height, (float) getWidth(), height, this.f9799a);
        } else if (1 == this.b) {
            float width = (float) (getWidth() >> 1);
            canvas.drawLine(width, 0.0f, width, (float) getHeight(), this.f9799a);
        }
    }
}
