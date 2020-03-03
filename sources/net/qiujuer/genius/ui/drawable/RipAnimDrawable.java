package net.qiujuer.genius.ui.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class RipAnimDrawable extends RipDrawable implements Animatable {
    private static final int c = 500;
    private Point d = new Point();
    private float e;
    private float f;
    /* access modifiers changed from: private */
    public Interpolator g = new DecelerateInterpolator(1.2f);
    /* access modifiers changed from: private */
    public long h;
    private boolean i = true;
    /* access modifiers changed from: private */
    public boolean j = false;
    private final Runnable k = new Runnable() {
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long a2 = uptimeMillis - RipAnimDrawable.this.h;
            if (a2 <= ((long) 500)) {
                RipAnimDrawable.this.a(RipAnimDrawable.this.g.getInterpolation(((float) a2) / ((float) 500)));
                RipAnimDrawable.this.scheduleSelf(this, uptimeMillis + 16);
                return;
            }
            RipAnimDrawable.this.unscheduleSelf(this);
            RipAnimDrawable.this.a(1.0f);
            boolean unused = RipAnimDrawable.this.j = false;
        }
    };

    /* access modifiers changed from: protected */
    public void a(Canvas canvas, Path path, Paint paint) {
        if (this.i) {
            this.i = false;
            start();
            return;
        }
        int save = canvas.save();
        canvas.clipPath(path);
        canvas.drawCircle((float) this.d.x, (float) this.d.y, this.e, paint);
        canvas.restoreToCount(save);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.d.set(rect.left, rect.top);
        int i2 = rect.right - rect.left;
        int i3 = rect.bottom - rect.top;
        this.f = (float) Math.sqrt((double) ((i2 * i2) + (i3 * i3)));
        this.e = this.f;
    }

    /* access modifiers changed from: protected */
    public void a(float f2) {
        this.e = this.f * f2;
        invalidateSelf();
    }

    public void start() {
        if (this.j) {
            unscheduleSelf(this.k);
        }
        this.j = true;
        this.h = SystemClock.uptimeMillis() + 96;
        scheduleSelf(this.k, this.h);
    }

    public void stop() {
        unscheduleSelf(this.k);
    }

    public boolean isRunning() {
        return this.j;
    }
}
