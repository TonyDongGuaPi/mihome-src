package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import net.qiujuer.genius.ui.Ui;

public class CircleCheckDrawable extends CheckStateDrawable implements Animatable {
    private Paint d = new Paint(1);
    private Paint e;
    private RectF f = new RectF();
    private int g;
    private int h;
    /* access modifiers changed from: private */
    public final Interpolator i = new DecelerateInterpolator(0.8f);
    /* access modifiers changed from: private */
    public long j;
    /* access modifiers changed from: private */
    public int k = 250;
    /* access modifiers changed from: private */
    public boolean l = false;
    private int m = 4;
    private int n = 4;
    private int o = 50;
    private boolean p;
    private int q;
    private float r;
    private int s;
    private int t;
    private int u;
    private float v = 0.0f;
    private float w;
    /* access modifiers changed from: private */
    public final Runnable x = new Runnable() {
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long a2 = uptimeMillis - CircleCheckDrawable.this.j;
            if (a2 < ((long) CircleCheckDrawable.this.k)) {
                float interpolation = CircleCheckDrawable.this.i.getInterpolation(((float) a2) / ((float) CircleCheckDrawable.this.k));
                CircleCheckDrawable.this.scheduleSelf(CircleCheckDrawable.this.x, uptimeMillis + 16);
                CircleCheckDrawable.this.a(interpolation);
                return;
            }
            CircleCheckDrawable.this.unscheduleSelf(CircleCheckDrawable.this.x);
            boolean unused = CircleCheckDrawable.this.l = false;
            CircleCheckDrawable.this.a(1.0f);
        }
    };

    public CircleCheckDrawable(ColorStateList colorStateList) {
        super(colorStateList);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setAntiAlias(true);
        this.d.setDither(true);
        this.e = new Paint();
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setStrokeJoin(Paint.Join.ROUND);
        this.e.setStrokeCap(Paint.Cap.ROUND);
        this.e.setAntiAlias(true);
        this.e.setDither(true);
        this.e.setStrokeWidth((float) this.m);
        onStateChange(getState());
        a(b(), this.r_, this.r_);
        j();
    }

    public void c(int i2) {
        if (this.m != i2) {
            this.m = i2;
            this.e.setStrokeWidth((float) this.m);
            j();
            invalidateSelf();
        }
    }

    public void d(int i2) {
        if (this.n != i2) {
            this.n = i2;
            j();
            invalidateSelf();
        }
    }

    public void a(int i2, boolean z) {
        if (this.o != i2 || this.p != z) {
            this.o = i2;
            this.p = z;
            j();
            invalidateSelf();
        }
    }

    public int g() {
        return this.m;
    }

    public int h() {
        return this.n;
    }

    public int i() {
        return this.o;
    }

    private void j() {
        if (!this.p) {
            int i2 = (this.m + this.n) * 2;
            Rect bounds = getBounds();
            int width = bounds.width();
            int height = bounds.height();
            if (width <= 0 || height <= 0) {
                int max = Math.max(width, height);
                if (max > 0) {
                    this.o = max;
                }
            } else {
                this.o = Math.min(width, height);
            }
            this.o = Math.max(this.o, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, boolean z, boolean z2) {
        if (z != z2) {
            if (z2) {
                this.s = i2;
            } else {
                this.u = i2;
            }
            start();
            return;
        }
        this.u = i2;
        this.s = i2;
        invalidateSelf();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (!this.p) {
            j();
        }
        if (!rect.isEmpty()) {
            this.g = rect.centerX();
            this.h = rect.centerY();
            int min = Math.min(rect.width(), rect.height()) >> 1;
            int i2 = min - ((this.m + 1) >> 1);
            this.f.set((float) (this.g - i2), (float) (this.h - i2), (float) (this.g + i2), (float) (this.h + i2));
            this.q = (min - this.m) - this.n;
        }
    }

    public int getIntrinsicWidth() {
        return this.o;
    }

    public int getIntrinsicHeight() {
        return this.o;
    }

    public void start() {
        if (!this.c) {
            unscheduleSelf(this.x);
            this.l = true;
            this.w = this.v;
            this.k = (int) ((this.r_ ? 1.0f - this.v : this.v) * 250.0f);
            this.j = SystemClock.uptimeMillis();
            scheduleSelf(this.x, this.j + 16);
        }
    }

    public void stop() {
        unscheduleSelf(this.x);
    }

    public boolean isRunning() {
        return this.l;
    }

    public void draw(Canvas canvas) {
        this.e.setColor(this.u);
        canvas.drawArc(this.f, 0.0f, 360.0f, false, this.e);
        if (this.r > 0.0f && this.s != 0) {
            this.e.setColor(this.s);
            canvas.drawArc(this.f, 0.0f, this.r, false, this.e);
            this.d.setColor(this.s);
            this.d.setAlpha(this.t);
            canvas.drawCircle((float) this.g, (float) this.h, (float) this.q, this.d);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        boolean z;
        Paint paint = this.d;
        Paint paint2 = this.e;
        if (paint == null || paint.getColorFilter() == colorFilter) {
            z = false;
        } else {
            paint.setColorFilter(colorFilter);
            z = true;
        }
        if (!(paint2 == null || paint2.getColorFilter() == colorFilter)) {
            paint2.setColorFilter(colorFilter);
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
    }

    public int getOpacity() {
        Paint paint = this.d;
        Paint paint2 = this.e;
        if (paint.getXfermode() != null || paint2.getXfermode() != null) {
            return -3;
        }
        int alpha = Color.alpha(b());
        if (alpha == 0) {
            return -2;
        }
        return alpha == 255 ? -1 : -3;
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        float f3 = this.w;
        this.v = f3 + (((this.r_ ? 1.0f : 0.0f) - f3) * f2);
        this.r = this.v * 360.0f;
        this.t = Ui.a(Color.alpha(this.s), (int) (this.v * 255.0f));
        invalidateSelf();
    }
}
