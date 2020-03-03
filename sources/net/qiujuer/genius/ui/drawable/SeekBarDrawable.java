package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;

public class SeekBarDrawable extends SeekBarStateDrawable implements Animatable {

    /* renamed from: a  reason: collision with root package name */
    private Point f3127a = new Point();
    private int b;
    private float c;
    private float d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    /* access modifiers changed from: private */
    public boolean l;
    /* access modifiers changed from: private */
    public boolean m;
    private Runnable n = new Runnable() {
        public void run() {
            boolean unused = SeekBarDrawable.this.l = true;
            SeekBarDrawable.this.invalidateSelf();
            boolean unused2 = SeekBarDrawable.this.m = false;
        }
    };

    public void start() {
    }

    public SeekBarDrawable(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3) {
        super(colorStateList, colorStateList2, colorStateList3);
    }

    public void a(boolean z) {
        this.k = z;
    }

    public void a(int i2) {
        this.e = i2;
        this.c = ((float) this.b) / ((float) i2);
    }

    public int a() {
        return this.i;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public int b() {
        return this.h;
    }

    public void c(int i2) {
        this.h = i2;
    }

    public int c() {
        return this.j;
    }

    public void d(int i2) {
        this.j = i2;
    }

    public int d() {
        return this.g;
    }

    public void e(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.g = i2;
    }

    public int e() {
        return this.f;
    }

    public void f(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.f = i2;
    }

    public float f() {
        return this.d;
    }

    public void a(float f2) {
        int i2;
        this.d = f2;
        int p = p();
        Rect bounds = getBounds();
        if (this.k) {
            i2 = (bounds.right - this.j) - p;
        } else {
            i2 = bounds.left + this.j + p;
        }
        this.f3127a.set(i2, bounds.centerY());
    }

    public boolean g() {
        return this.i != 0;
    }

    public Point h() {
        return this.f3127a;
    }

    public void a(Rect rect) {
        int i2;
        Rect bounds = getBounds();
        int p = p();
        if (this.k) {
            i2 = (bounds.right - this.j) - p;
        } else {
            i2 = bounds.left + this.j + p;
        }
        rect.set(i2 - this.j, bounds.top, i2 + this.j, bounds.bottom);
    }

    public void i() {
        scheduleSelf(this.n, SystemClock.uptimeMillis() + 100);
        this.m = true;
    }

    public void j() {
        this.l = false;
        this.m = false;
        unscheduleSelf(this.n);
        invalidateSelf();
    }

    public void stop() {
        j();
    }

    public boolean isRunning() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.b = ((rect.right - rect.left) - this.j) - this.j;
        this.c = ((float) this.b) / ((float) this.e);
        a(this.d);
    }

    public int getIntrinsicHeight() {
        return Math.max(Math.max(Math.max(Math.max(this.f, this.g), this.h * 2), this.i * 2), this.j * 2);
    }

    private int p() {
        return (int) (((float) this.b) * this.d);
    }

    public void a(Canvas canvas, Paint paint, int i2, int i3, int i4) {
        float f2 = (float) (this.f >> 1);
        float f3 = (float) (this.g >> 1);
        if (this.k) {
            a(canvas, paint, i4, i2, i3, f2, f3);
        } else {
            a(canvas, paint, i4, i3, i2, f3, f2);
        }
    }

    private void a(Canvas canvas, Paint paint, int i2, int i3, int i4, float f2, float f3) {
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        int i5 = i3;
        Rect bounds = getBounds();
        int i6 = this.f3127a.x;
        int i7 = this.f3127a.y;
        int i8 = bounds.left + this.j;
        int i9 = bounds.right - this.j;
        if (f2 > 0.0f) {
            paint2.setColor(i5);
            float f4 = (float) i7;
            float f5 = f4 + f2;
            canvas.drawRect((float) i8, f4 - f2, (float) i6, f5, paint);
        }
        if (f3 > 0.0f) {
            paint2.setColor(i4);
            float f6 = (float) i7;
            float f7 = f6 + f3;
            canvas.drawRect((float) i6, f6 - f3, (float) i9, f7, paint);
        }
        if (((float) this.i) > f3) {
            for (int i10 = 0; i10 <= this.e; i10++) {
                float f8 = ((float) i9) - (((float) i10) * this.c);
                if (f8 <= ((float) i6)) {
                    break;
                }
                canvas2.drawCircle(f8, (float) i7, (float) this.i, paint2);
            }
        }
        if (((float) this.i) > f2) {
            paint2.setColor(i5);
            for (int i11 = 0; i11 <= this.e; i11++) {
                float f9 = (((float) i11) * this.c) + ((float) i8);
                if (f9 > ((float) i6)) {
                    break;
                }
                canvas2.drawCircle(f9, (float) i7, (float) this.i, paint2);
            }
        }
        if (!this.l && this.h > 0) {
            paint.setColor(i2);
            canvas2.drawCircle((float) i6, (float) i7, (float) this.h, paint2);
        }
    }
}
