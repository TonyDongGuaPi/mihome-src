package com.sina.weibo.sdk.web.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class WbSdkProgressBar extends View {

    /* renamed from: a  reason: collision with root package name */
    private int f8866a;
    private int b;
    private int c;
    private RectF d;
    private Paint e;
    private float f;
    private final int g;
    private final int h;
    private float i;
    private long j;
    private float k;
    private long l;
    private long m;
    private double n;
    private double o;
    private boolean p;
    /* access modifiers changed from: private */
    public boolean q;
    private Handler r;
    int stopNum;

    public WbSdkProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public WbSdkProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WbSdkProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.g = 20;
        this.h = 300;
        this.j = 0;
        this.k = 200.0f;
        this.l = 180;
        this.m = 0;
        this.n = 490.0d;
        this.p = false;
        this.q = true;
        this.stopNum = 0;
        this.r = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 0) {
                    boolean unused = WbSdkProgressBar.this.q = false;
                }
            }
        };
        this.f8866a = a(context, 50);
        this.b = a(context, 5);
        this.c = a(context, 3);
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(-48861);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeWidth((float) this.b);
        this.d = new RectF((float) this.c, (float) this.c, (float) (this.f8866a - this.c), (float) (this.f8866a - this.c));
    }

    private int a(Context context, int i2) {
        return (int) (context.getResources().getDisplayMetrics().density * ((float) i2));
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        long abs = Math.abs(SystemClock.uptimeMillis() - this.j) % 360;
        a(abs);
        this.j = SystemClock.uptimeMillis();
        this.f += (this.k * ((float) abs)) / 1000.0f;
        if (this.f >= 360.0f) {
            this.f -= 360.0f;
        }
        canvas.drawArc(this.d, this.f - 90.0f, this.i + 20.0f, false, this.e);
        if (this.q) {
            if (Build.VERSION.SDK_INT >= 21) {
                postInvalidate();
            } else {
                invalidate();
            }
        }
    }

    public void setProgressColor(int i2) {
        this.e.setColor(i2);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (i2 == 8) {
            this.r.sendEmptyMessageDelayed(0, 1000);
        } else if (i2 == 0 && getVisibility() == 0) {
            this.r.removeMessages(0);
            this.q = true;
            invalidate();
        }
    }

    private void a(long j2) {
        if (this.m >= this.l) {
            double d2 = this.o;
            double d3 = (double) j2;
            Double.isNaN(d3);
            this.o = d2 + d3;
            if (this.o >= this.n) {
                this.o -= this.n;
                this.m = 0;
                this.p = !this.p;
            }
            float cos = (((float) Math.cos(((this.o / this.n) + 1.0d) * 3.141592653589793d)) / 2.0f) + 0.5f;
            if (!this.p) {
                this.i = cos * ((float) 280);
                return;
            }
            float f2 = ((float) 280) * (1.0f - cos);
            this.f += this.i - f2;
            this.i = f2;
            return;
        }
        this.m += j2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(this.f8866a, this.f8866a);
    }
}
