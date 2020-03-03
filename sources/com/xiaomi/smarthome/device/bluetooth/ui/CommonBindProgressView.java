package com.xiaomi.smarthome.device.bluetooth.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CommonBindProgressView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15260a = 135;
    private static final int b = 270;
    private static final int c = 6;
    private Paint d;
    private int e;

    public CommonBindProgressView(Context context) {
        super(context);
        a();
    }

    public CommonBindProgressView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CommonBindProgressView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeWidth(6.0f);
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > 100) {
            i = 100;
        }
        this.e = i;
        invalidate();
    }

    public int getProgress() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.d.setColor(Color.parseColor("#fafafa"));
        RectF rectF = new RectF(3.0f, 3.0f, (float) (getWidth() - 3), (float) (getHeight() - 3));
        canvas.drawArc(rectF, 135.0f, 270.0f, false, this.d);
        this.d.setColor(Color.parseColor("#0099ff"));
        canvas.drawArc(rectF, 135.0f, (float) ((this.e * 270) / 100), false, this.d);
    }
}
