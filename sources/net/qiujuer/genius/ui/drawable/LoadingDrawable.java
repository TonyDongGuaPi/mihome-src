package net.qiujuer.genius.ui.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

public abstract class LoadingDrawable extends Drawable implements Animatable, Animatable {
    private static final int f = 4;
    protected Paint c = new Paint(1);
    protected Paint d = new Paint(1);
    protected float e;
    /* access modifiers changed from: private */
    public boolean g;
    private int[] h = {-872415232, -100251, -8117352};
    private int i = 0;
    private final Runnable j = new Runnable() {
        public void run() {
            if (LoadingDrawable.this.g) {
                LoadingDrawable.this.a();
                LoadingDrawable.this.invalidateSelf();
                return;
            }
            LoadingDrawable.this.unscheduleSelf(this);
        }
    };

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public abstract void a(float f2);

    /* access modifiers changed from: protected */
    public abstract void a(Canvas canvas, Paint paint);

    /* access modifiers changed from: protected */
    public abstract void b(Canvas canvas, Paint paint);

    public LoadingDrawable() {
        Paint paint = this.d;
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(4.0f);
        paint.setColor(838860800);
        Paint paint2 = this.c;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        paint2.setStrokeWidth(4.0f);
        paint2.setColor(this.h[0]);
    }

    public int getIntrinsicHeight() {
        return (int) (Math.max(this.d.getStrokeWidth(), this.c.getStrokeWidth()) * 2.0f);
    }

    public int getIntrinsicWidth() {
        return (int) (Math.max(this.d.getStrokeWidth(), this.c.getStrokeWidth()) * 2.0f);
    }

    public void b(float f2) {
        this.d.setStrokeWidth(f2);
        onBoundsChange(getBounds());
    }

    public void c(float f2) {
        this.c.setStrokeWidth(f2);
        onBoundsChange(getBounds());
    }

    public float b() {
        return this.d.getStrokeWidth();
    }

    public float c() {
        return this.c.getStrokeWidth();
    }

    public void a(int i2) {
        this.d.setColor(i2);
    }

    public int d() {
        return this.d.getColor();
    }

    public void a(int[] iArr) {
        if (iArr != null) {
            this.h = iArr;
            this.i = -1;
            f();
        }
    }

    public int[] e() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        int[] iArr = this.h;
        Paint paint = this.c;
        if (iArr.length > 1) {
            int i2 = this.i + 1;
            if (i2 >= iArr.length) {
                i2 = 0;
            }
            paint.setColor(iArr[i2]);
            this.i = i2;
        } else {
            paint.setColor(iArr[0]);
        }
        return paint.getColor();
    }

    public float g() {
        return this.e;
    }

    public void d(float f2) {
        if (f2 < 0.0f) {
            this.e = 0.0f;
        } else if (this.e > 1.0f) {
            this.e = 1.0f;
        } else {
            this.e = f2;
        }
        stop();
        a(this.e);
        invalidateSelf();
    }

    public boolean isRunning() {
        return this.g;
    }

    public void start() {
        if (!this.g) {
            this.g = true;
            scheduleSelf(this.j, SystemClock.uptimeMillis() + 16);
        }
    }

    public void stop() {
        if (this.g) {
            this.g = false;
            unscheduleSelf(this.j);
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        int save = canvas.save();
        Paint paint = this.d;
        if (paint.getColor() != 0 && paint.getStrokeWidth() > 0.0f) {
            a(canvas, paint);
        }
        Paint paint2 = this.c;
        if (this.g) {
            if (paint2.getColor() != 0 && paint2.getStrokeWidth() > 0.0f) {
                b(canvas, paint2);
            }
            scheduleSelf(this.j, SystemClock.uptimeMillis() + 16);
        } else if (this.e > 0.0f && paint2.getColor() != 0 && paint2.getStrokeWidth() > 0.0f) {
            b(canvas, paint2);
        }
        canvas.restoreToCount(save);
    }

    public void setAlpha(int i2) {
        this.c.setAlpha(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        boolean z;
        Paint paint = this.d;
        if (paint.getColorFilter() != colorFilter) {
            paint.setColorFilter(colorFilter);
            z = true;
        } else {
            z = false;
        }
        Paint paint2 = this.c;
        if (paint2.getColorFilter() != colorFilter) {
            paint2.setColorFilter(colorFilter);
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
    }

    public int getOpacity() {
        Paint paint = this.d;
        Paint paint2 = this.c;
        if (paint.getXfermode() != null || paint2.getXfermode() != null) {
            return -3;
        }
        int alpha = Color.alpha(paint2.getColor());
        if (alpha == 0) {
            return -2;
        }
        return alpha == 255 ? -1 : -3;
    }
}
