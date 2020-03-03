package com.xiaomi.smarthome.miio.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class WifiLogStatBackground extends View {

    /* renamed from: a  reason: collision with root package name */
    private Paint f11833a;
    private Paint b;

    public WifiLogStatBackground(Context context) {
        super(context);
        init();
    }

    public WifiLogStatBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WifiLogStatBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.f11833a = new Paint(1);
        this.f11833a.setStyle(Paint.Style.STROKE);
        this.f11833a.setColor(1090519039);
        this.f11833a.setStrokeWidth(1.0f);
        this.f11833a.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f, 5.0f, 5.0f}, 1.0f));
        this.b = new Paint(1);
        this.b.setColor(-2130706433);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setTextAlign(Paint.Align.RIGHT);
        this.b.setTextSize(getContext().getResources().getDisplayMetrics().density * 12.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        float f = getContext().getResources().getDisplayMetrics().density;
        float f2 = 30.0f * f;
        this.f11833a.setStyle(Paint.Style.STROKE);
        for (int i = 1; i < 8; i++) {
            Path path = new Path();
            float f3 = (float) ((i * height) / 8);
            path.moveTo(0.0f, f3);
            float f4 = (float) width;
            path.lineTo(f4 - f2, f3);
            canvas.drawPath(path, this.f11833a);
            canvas.drawText(String.format("%02d:00", new Object[]{Integer.valueOf(24 - (i * 3))}), f4, f3 + (5.0f * f), this.b);
        }
    }
}
