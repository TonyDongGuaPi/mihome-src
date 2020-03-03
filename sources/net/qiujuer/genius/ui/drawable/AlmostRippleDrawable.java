package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import net.qiujuer.genius.ui.Ui;

public class AlmostRippleDrawable extends StatePaintDrawable implements Animatable {
    private float c = 0.0f;
    /* access modifiers changed from: private */
    public Interpolator d = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public long e;
    private boolean f = false;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public int h = 250;
    private float i;
    /* access modifiers changed from: private */
    public final Runnable j = new Runnable() {
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long a2 = uptimeMillis - AlmostRippleDrawable.this.e;
            if (a2 < ((long) AlmostRippleDrawable.this.h)) {
                float interpolation = AlmostRippleDrawable.this.d.getInterpolation(((float) a2) / ((float) AlmostRippleDrawable.this.h));
                AlmostRippleDrawable.this.scheduleSelf(AlmostRippleDrawable.this.j, uptimeMillis + 16);
                AlmostRippleDrawable.this.a(interpolation);
                return;
            }
            AlmostRippleDrawable.this.unscheduleSelf(AlmostRippleDrawable.this.j);
            boolean unused = AlmostRippleDrawable.this.g = false;
            AlmostRippleDrawable.this.a(1.0f);
        }
    };
    private int k;
    private int l;

    public void start() {
    }

    public AlmostRippleDrawable(ColorStateList colorStateList) {
        super(colorStateList);
    }

    public void a(Canvas canvas, Paint paint) {
        float f2 = this.c;
        if (f2 > 0.0f) {
            Rect bounds = getBounds();
            float min = ((float) Math.min(bounds.width(), bounds.height())) / 2.0f;
            float centerX = (float) bounds.centerX();
            float centerY = (float) bounds.centerY();
            if (f2 != 1.0f && this.k > 0) {
                paint.setAlpha(this.k);
                canvas.drawCircle(centerX, centerY, min, paint);
            }
            if (this.l > 0) {
                paint.setAlpha(this.l);
                canvas.drawCircle(centerX, centerY, min * f2, paint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2) {
        super.a(i2);
        int alpha = Color.alpha(i2);
        this.k = Ui.a(alpha, 128);
        if (alpha < 255) {
            this.l = a(alpha, this.k);
        } else {
            this.l = alpha;
        }
    }

    private int a(int i2, int i3) {
        if (i3 > i2) {
            return 0;
        }
        return ((i2 - i3) * 255) / (255 - i3);
    }

    public boolean setState(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        boolean z = false;
        for (int i2 : getState()) {
            if (i2 == 16842919) {
                z = true;
            }
        }
        boolean state = super.setState(iArr);
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 : iArr) {
            if (i3 == 16842908) {
                z3 = true;
            } else if (i3 == 16842919) {
                z2 = true;
            }
        }
        if (z2) {
            a();
        } else if (z) {
            b();
        } else if (z3) {
            this.c = 1.0f;
            invalidateSelf();
        } else {
            this.c = 0.0f;
            invalidateSelf();
        }
        return state;
    }

    public void a() {
        unscheduleSelf(this.j);
        if (this.c < 1.0f) {
            this.f = false;
            this.g = true;
            this.i = this.c;
            this.h = (int) ((1.0f - this.i) * 250.0f);
            this.e = SystemClock.uptimeMillis();
            scheduleSelf(this.j, this.e + 16);
        }
    }

    public void b() {
        unscheduleSelf(this.j);
        if (this.c > 0.0f) {
            this.f = true;
            this.g = true;
            this.i = this.c;
            this.h = (int) (this.i * 250.0f);
            this.e = SystemClock.uptimeMillis();
            scheduleSelf(this.j, this.e + 16);
        }
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        float f3 = this.i;
        this.c = f3 + (((this.f ? 0.0f : 1.0f) - f3) * f2);
        invalidateSelf();
    }

    public void stop() {
        unscheduleSelf(this.j);
    }

    public boolean isRunning() {
        return this.g;
    }
}
