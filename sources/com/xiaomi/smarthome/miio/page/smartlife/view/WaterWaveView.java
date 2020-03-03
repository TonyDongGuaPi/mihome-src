package com.xiaomi.smarthome.miio.page.smartlife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;

public class WaterWaveView extends View implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private static final int f19943a = 16;
    private static final int b = 15;
    private Path c;
    private Path d;
    private Paint e;
    private Paint f;
    private int g = -1644101888;
    private int h = 1577123584;
    private int i;
    private int j;
    private float k = 0.0f;
    private float l;
    private double m;
    private float n;
    private float o = 0.0f;
    private boolean p = true;
    private boolean q = false;

    public WaterWaveView(Context context) {
        super(context);
    }

    public WaterWaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public WaterWaveView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.g = getResources().getColor(R.color.white_40_transparent);
        this.h = getResources().getColor(R.color.white_20_transparent);
        new Thread(this).start();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setOmegaByProgress(80);
        setHeightOffsetByProgress(82);
        setMoveSpeedByProgress(35);
        setWaveHeightByProgress(18);
        canvas.drawPath(getSecondWavePath(), getSecondLayerPaint());
        canvas.drawPath(getFristWavePath(), getFristLayerPaint());
    }

    public void cancelAnim() {
        this.q = true;
    }

    private Path getFristWavePath() {
        if (this.c == null) {
            this.c = new Path();
        }
        if (this.q) {
            return this.c;
        }
        this.c.reset();
        this.c.moveTo(0.0f, (float) this.j);
        for (float f2 = 0.0f; f2 <= ((float) this.i); f2 += 15.0f) {
            this.c.lineTo(f2, ((float) ((this.m * Math.sin((double) ((this.l * f2) + this.k))) + this.m)) + this.o);
        }
        return this.c;
    }

    private Path getSecondWavePath() {
        if (this.d == null) {
            this.d = new Path();
        }
        if (this.q) {
            return this.d;
        }
        this.d.reset();
        this.d.moveTo(0.0f, (float) this.j);
        for (float f2 = 0.0f; f2 <= ((float) this.i); f2 += 15.0f) {
            double d2 = this.m;
            double d3 = (double) ((this.l * f2) + this.k);
            Double.isNaN(d3);
            this.d.lineTo(f2, ((float) ((d2 * Math.cos(d3 + 0.3d)) + this.m)) + this.o);
        }
        return this.d;
    }

    private Paint getSecondLayerPaint() {
        if (this.f == null) {
            this.f = new Paint();
        }
        this.f.setColor(this.h);
        this.f.setStyle(Paint.Style.STROKE);
        this.f.setAntiAlias(true);
        this.f.setStrokeWidth(getResources().getDisplayMetrics().density * 1.0f);
        return this.f;
    }

    private Paint getFristLayerPaint() {
        if (this.e == null) {
            this.e = new Paint();
        }
        this.e.setColor(this.g);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setAntiAlias(true);
        this.e.setStrokeWidth(getResources().getDisplayMetrics().density * 1.0f);
        return this.e;
    }

    public void run() {
        while (this.p) {
            long currentTimeMillis = System.currentTimeMillis();
            this.k += this.n;
            postInvalidate();
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis2 < 16) {
                try {
                    Thread.sleep(16 - currentTimeMillis2);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        a();
        super.onWindowFocusChanged(z);
    }

    private void a() {
        this.i = getWidth();
        this.j = getHeight();
        this.m = (double) (this.j / 30);
        double d2 = (double) this.i;
        Double.isNaN(d2);
        this.l = (float) (10.995574287564276d / d2);
        this.n = 0.15f;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.p = false;
        super.onDetachedFromWindow();
    }

    public void setOmega(float f2) {
        this.l = f2;
    }

    public void setWaveHeight(double d2) {
        this.m = d2;
    }

    public void setMoveSpeed(float f2) {
        this.n = f2;
    }

    public void setFirstColor(int i2) {
        this.g = i2;
    }

    public void setSecondColor(int i2) {
        this.h = i2;
    }

    public void setMoveSpeedByProgress(int i2) {
        if (i2 >= 0 && i2 <= 100) {
            this.n = ((float) i2) * 0.003f;
        }
    }

    public void setOmegaByProgress(int i2) {
        if (i2 >= 0 && i2 <= 100) {
            double d2 = (double) this.i;
            Double.isNaN(d2);
            this.l = ((float) i2) * ((float) (0.3141592700403172d / d2));
        }
    }

    public void setWaveHeightByProgress(int i2) {
        if (i2 >= 0 && i2 <= 100) {
            this.m = (double) (((float) (i2 * this.j)) * 0.005f);
        }
    }

    public void setHeightOffsetByProgress(int i2) {
        if (i2 >= 0 && i2 <= 100) {
            this.o = ((float) (i2 * this.j)) * 0.005f;
        }
    }
}
