package net.qiujuer.genius.ui.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class BalloonMarkerDrawable extends StatePaintDrawable implements Animatable {
    Path c = new Path();
    RectF d = new RectF();
    Matrix e = new Matrix();
    private float f = 0.0f;
    /* access modifiers changed from: private */
    public Interpolator g = new AccelerateDecelerateInterpolator();
    /* access modifiers changed from: private */
    public long h;
    private boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public int k = 250;
    private float l;
    private float m;
    private int n;
    private int o;
    private int p;
    private int q;
    private MarkerAnimationListener r;
    /* access modifiers changed from: private */
    public final Runnable s = new Runnable() {
        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            long a2 = uptimeMillis - BalloonMarkerDrawable.this.h;
            if (a2 < ((long) BalloonMarkerDrawable.this.k)) {
                float interpolation = BalloonMarkerDrawable.this.g.getInterpolation(((float) a2) / ((float) BalloonMarkerDrawable.this.k));
                BalloonMarkerDrawable.this.scheduleSelf(BalloonMarkerDrawable.this.s, uptimeMillis + 16);
                BalloonMarkerDrawable.this.b(interpolation);
                return;
            }
            BalloonMarkerDrawable.this.unscheduleSelf(BalloonMarkerDrawable.this.s);
            boolean unused = BalloonMarkerDrawable.this.j = false;
            BalloonMarkerDrawable.this.b(1.0f);
            BalloonMarkerDrawable.this.g();
        }
    };

    public interface MarkerAnimationListener {
        void onClosingComplete();

        void onOpeningComplete();
    }

    public void start() {
    }

    public BalloonMarkerDrawable(ColorStateList colorStateList, int i2) {
        super(colorStateList);
        this.l = (float) i2;
        f().setStyle(Paint.Style.FILL);
    }

    private static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((((float) Color.alpha(i2)) * f2) + (((float) Color.alpha(i3)) * f3)), (int) ((((float) Color.red(i2)) * f2) + (((float) Color.red(i3)) * f3)), (int) ((((float) Color.green(i2)) * f2) + (((float) Color.green(i3)) * f3)), (int) ((((float) Color.blue(i2)) * f2) + (((float) Color.blue(i3)) * f3)));
    }

    public void b(int i2) {
        this.n = i2;
    }

    public void a(float f2) {
        this.l = f2;
    }

    public void a(int i2, int i3) {
        this.o = i2;
        this.p = i3;
    }

    public void a(ColorStateList colorStateList) {
        super.a(colorStateList);
        this.p = colorStateList.getDefaultColor();
        this.o = colorStateList.getColorForState(new int[]{16842919}, this.p);
    }

    public void a(Canvas canvas, Paint paint) {
        if (!this.c.isEmpty()) {
            paint.setColor(this.q);
            canvas.drawPath(this.c, paint);
        }
    }

    public Path a() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect);
    }

    private void a(Rect rect) {
        float f2 = this.f;
        Path path = this.c;
        RectF rectF = this.d;
        Matrix matrix = this.e;
        path.reset();
        int min = Math.min(rect.width(), rect.height());
        float f3 = this.l;
        float f4 = f3 + ((((float) min) - f3) * f2);
        float f5 = f4 / 2.0f;
        float f6 = 1.0f - f2;
        float f7 = f5 * f6;
        rectF.set((float) rect.left, (float) rect.top, ((float) rect.left) + f4, ((float) rect.top) + f4);
        path.addRoundRect(rectF, new float[]{f5, f5, f5, f5, f5, f5, f7, f7}, Path.Direction.CCW);
        matrix.reset();
        matrix.postRotate(-45.0f, ((float) rect.left) + f5, ((float) rect.top) + f5);
        matrix.postTranslate((((float) rect.width()) - f4) / 2.0f, 0.0f);
        matrix.postTranslate(0.0f, ((((float) rect.bottom) - f4) - ((float) this.n)) * f6);
        path.transform(matrix);
    }

    /* access modifiers changed from: private */
    public void b(float f2) {
        float f3 = this.m;
        this.f = f3 + (((this.i ? 0.0f : 1.0f) - f3) * f2);
        this.q = a(this.o, this.p, this.f);
        a(getBounds());
        invalidateSelf();
    }

    public void b() {
        unscheduleSelf(this.s);
        this.i = false;
        if (this.f < 1.0f) {
            this.j = true;
            this.m = this.f;
            this.k = (int) ((1.0f - this.f) * 250.0f);
            this.h = SystemClock.uptimeMillis();
            scheduleSelf(this.s, this.h + 16);
            return;
        }
        g();
    }

    public void c() {
        this.i = true;
        unscheduleSelf(this.s);
        if (this.f > 0.0f) {
            this.j = true;
            this.m = this.f;
            this.k = 250 - ((int) ((1.0f - this.f) * 250.0f));
            this.h = SystemClock.uptimeMillis();
            scheduleSelf(this.s, this.h + 16);
            return;
        }
        g();
    }

    public void a(MarkerAnimationListener markerAnimationListener) {
        this.r = markerAnimationListener;
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.r == null) {
            return;
        }
        if (this.i) {
            this.r.onClosingComplete();
        } else {
            this.r.onOpeningComplete();
        }
    }

    public void stop() {
        unscheduleSelf(this.s);
    }

    public boolean isRunning() {
        return this.j;
    }
}
