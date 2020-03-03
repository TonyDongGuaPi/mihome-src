package com.xiaomi.smarthome.device.bluetooth.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class LoopRunView extends View {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15278a = 6;
    private static final int b = 1;
    private int c;
    private int d;
    private long e;
    private Direction f;
    private int g;
    private long h;
    private Bitmap i;
    private Bitmap j;
    private Paint k;
    private Handler l;
    private int m;
    private Rect n;
    private Rect o;
    private boolean p;

    public static class Configs {

        /* renamed from: a  reason: collision with root package name */
        public long f15280a;
        public Direction b;
        public int c;
    }

    public LoopRunView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public LoopRunView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public LoopRunView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.k = new Paint(1);
        this.l = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                LoopRunView.this.a(message);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!this.p) {
            super.onDraw(canvas);
            return;
        }
        this.n.top = this.m;
        this.n.bottom = this.m + this.d;
        canvas.drawColor(0);
        canvas.drawBitmap(this.j, this.n, this.o, this.k);
        super.onDraw(canvas);
        this.l.sendEmptyMessageDelayed(1, this.h);
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        if (message.what == 1) {
            if (this.f == Direction.UP) {
                if (this.m >= this.d + this.g) {
                    this.m -= this.d + this.g;
                }
                this.m += 6;
            } else if (this.f == Direction.DOWN) {
                if (this.m <= 0) {
                    this.m += this.d + this.g;
                }
                this.m -= 6;
            } else {
                return;
            }
            invalidate();
        }
    }

    public enum Direction {
        UP(0),
        DOWN(1);
        
        int value;

        private Direction(int i) {
            this.value = i;
        }
    }

    public void startCycleAnimation(Bitmap bitmap, Configs configs) {
        this.i = bitmap;
        this.e = configs.f15280a;
        this.f = configs.b;
        this.g = configs.c;
        this.c = this.i.getWidth();
        this.d = this.i.getHeight();
        this.h = (this.e * 6) / ((long) (this.d + this.g));
        this.j = Bitmap.createBitmap(this.c, (this.d * 2) + this.g, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.j);
        Paint paint = new Paint(1);
        Rect rect = new Rect(0, 0, this.c, this.d);
        canvas.drawBitmap(this.i, rect, new Rect(0, 0, this.c, this.d), paint);
        canvas.drawBitmap(this.i, rect, new Rect(0, this.d + this.g, this.c, (this.d * 2) + this.g), paint);
        if (this.f == Direction.UP) {
            this.m = 0;
        } else if (this.f == Direction.DOWN) {
            this.m = this.d + this.g;
        }
        this.n = new Rect(0, 0, this.c, this.d);
        this.o = new Rect(0, 0, this.c, this.d);
        this.p = true;
        b();
        invalidate();
    }

    private void b() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.width = this.c;
        layoutParams.height = this.d;
        setLayoutParams(layoutParams);
    }

    public void destroy() {
        this.l.removeCallbacksAndMessages((Object) null);
    }
}
