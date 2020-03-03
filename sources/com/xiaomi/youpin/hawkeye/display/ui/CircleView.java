package com.xiaomi.youpin.hawkeye.display.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CircleView extends View {

    /* renamed from: a  reason: collision with root package name */
    private float f23353a = 2.0f;
    private Paint b = new Paint();
    private Paint c = new Paint();
    private int d;
    private int e;
    private int f;

    public CircleView(Context context, float f2) {
        super(context);
        this.f23353a = f2;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.d = getHeight() / 2;
        this.e = getWidth() / 2;
        this.f = (getHeight() / 2) - ((int) (this.f23353a * 2.0f));
        this.c.setAntiAlias(true);
        this.c.setColor(-14047744);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(this.f23353a * 2.0f);
        canvas.drawCircle((float) this.e, (float) this.d, (float) this.f, this.c);
        this.b.setColor(-14047744);
        this.b.setStrokeWidth(this.f23353a * 2.0f);
        this.b.setTextSize(this.f23353a * 8.0f);
        this.b.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = this.b.getFontMetrics();
        canvas.drawText("HawkEye", (float) this.e, (float) ((int) ((((float) this.d) - (fontMetrics.top / 2.0f)) - (fontMetrics.bottom / 2.0f))), this.b);
    }
}
