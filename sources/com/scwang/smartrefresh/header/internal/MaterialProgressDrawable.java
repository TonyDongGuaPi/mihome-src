package com.scwang.smartrefresh.header.internal;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final byte A = 6;
    private static final float B = 0.8f;

    /* renamed from: a  reason: collision with root package name */
    static final Interpolator f8750a = new FastOutSlowInInterpolator();
    public static final byte b = 0;
    public static final byte c = 1;
    private static final Interpolator f = new LinearInterpolator();
    private static final float g = 1080.0f;
    private static final byte h = 40;
    private static final float i = 8.75f;
    private static final float j = 2.5f;
    private static final byte k = 56;
    private static final float l = 12.5f;
    private static final float m = 3.0f;
    private static final int[] n = {-16777216};
    private static final float o = 0.75f;
    private static final float p = 0.5f;
    private static final float q = 0.5f;
    private static final int r = 1332;
    private static final byte s = 5;
    private static final byte w = 10;
    private static final byte x = 5;
    private static final float y = 5.0f;
    private static final byte z = 12;
    private View C;
    private Animation D;
    private float E;
    private float F;
    float d;
    boolean e;
    private final List<Animation> t = new ArrayList();
    private final Ring u = new Ring();
    private float v;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProgressDrawableSize {
    }

    public int getOpacity() {
        return -3;
    }

    public MaterialProgressDrawable(View view) {
        this.C = view;
        a(n);
        a(1);
        a();
    }

    private void a(int i2, int i3, float f2, float f3, float f4, float f5) {
        float f6 = Resources.getSystem().getDisplayMetrics().density;
        this.E = ((float) i2) * f6;
        this.F = ((float) i3) * f6;
        this.u.a(0);
        float f7 = f3 * f6;
        this.u.b.setStrokeWidth(f7);
        this.u.g = f7;
        this.u.q = (double) (f2 * f6);
        this.u.r = (int) (f4 * f6);
        this.u.s = (int) (f5 * f6);
        this.u.a((int) this.E, (int) this.F);
        invalidateSelf();
    }

    public void a(int i2) {
        if (i2 == 0) {
            a(56, 56, l, m, 12.0f, 6.0f);
        } else {
            a(40, 40, i, j, 10.0f, y);
        }
    }

    public void a(boolean z2) {
        if (this.u.n != z2) {
            this.u.n = z2;
            invalidateSelf();
        }
    }

    public void a(float f2) {
        if (this.u.p != f2) {
            this.u.p = f2;
            invalidateSelf();
        }
    }

    public void a(float f2, float f3) {
        this.u.d = f2;
        this.u.e = f3;
        invalidateSelf();
    }

    public void b(float f2) {
        this.u.f = f2;
        invalidateSelf();
    }

    public void b(@ColorInt int i2) {
        this.u.v = i2;
    }

    public void a(@ColorInt int... iArr) {
        this.u.i = iArr;
        this.u.a(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.F;
    }

    public int getIntrinsicWidth() {
        return (int) this.E;
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.v, bounds.exactCenterX(), bounds.exactCenterY());
        this.u.a(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public void setAlpha(int i2) {
        this.u.t = i2;
    }

    public int getAlpha() {
        return this.u.t;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.u.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public void c(float f2) {
        this.v = f2;
        invalidateSelf();
    }

    public boolean isRunning() {
        List<Animation> list = this.t;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Animation animation = list.get(i2);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.D.reset();
        this.u.d();
        if (this.u.e != this.u.d) {
            this.e = true;
            this.D.setDuration(666);
            this.C.startAnimation(this.D);
            return;
        }
        this.u.a(0);
        this.u.e();
        this.D.setDuration(1332);
        this.C.startAnimation(this.D);
    }

    public void stop() {
        this.C.clearAnimation();
        this.u.a(0);
        this.u.e();
        a(false);
        c(0.0f);
    }

    /* access modifiers changed from: package-private */
    public float a(Ring ring) {
        double d2 = (double) ring.g;
        Double.isNaN(d2);
        return (float) Math.toRadians(d2 / (ring.q * 6.283185307179586d));
    }

    private int a(float f2, int i2, int i3) {
        int intValue = Integer.valueOf(i2).intValue();
        int i4 = (intValue >> 24) & 255;
        int i5 = (intValue >> 16) & 255;
        int i6 = (intValue >> 8) & 255;
        int i7 = intValue & 255;
        int intValue2 = Integer.valueOf(i3).intValue();
        return ((i4 + ((int) (((float) (((intValue2 >> 24) & 255) - i4)) * f2))) << 24) | ((i5 + ((int) (((float) (((intValue2 >> 16) & 255) - i5)) * f2))) << 16) | ((i6 + ((int) (((float) (((intValue2 >> 8) & 255) - i6)) * f2))) << 8) | (i7 + ((int) (f2 * ((float) ((intValue2 & 255) - i7)))));
    }

    /* access modifiers changed from: package-private */
    public void a(float f2, Ring ring) {
        if (f2 > 0.75f) {
            ring.w = a((f2 - 0.75f) / 0.25f, ring.c(), ring.a());
        }
    }

    /* access modifiers changed from: package-private */
    public void b(float f2, Ring ring) {
        a(f2, ring);
        a(ring.k + (((ring.l - a(ring)) - ring.k) * f2), ring.l);
        b(ring.m + ((((float) (Math.floor((double) (ring.m / 0.8f)) + 1.0d)) - ring.m) * f2));
    }

    private void a() {
        final Ring ring = this.u;
        AnonymousClass1 r1 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                if (MaterialProgressDrawable.this.e) {
                    MaterialProgressDrawable.this.b(f, ring);
                    return;
                }
                float a2 = MaterialProgressDrawable.this.a(ring);
                float f2 = ring.l;
                float f3 = ring.k;
                float f4 = ring.m;
                MaterialProgressDrawable.this.a(f, ring);
                if (f <= 0.5f) {
                    ring.d = f3 + ((0.8f - a2) * MaterialProgressDrawable.f8750a.getInterpolation(f / 0.5f));
                }
                if (f > 0.5f) {
                    ring.e = f2 + ((0.8f - a2) * MaterialProgressDrawable.f8750a.getInterpolation((f - 0.5f) / 0.5f));
                }
                MaterialProgressDrawable.this.b(f4 + (0.25f * f));
                MaterialProgressDrawable.this.c((f * 216.0f) + ((MaterialProgressDrawable.this.d / MaterialProgressDrawable.y) * MaterialProgressDrawable.g));
            }
        };
        r1.setRepeatCount(-1);
        r1.setRepeatMode(1);
        r1.setInterpolator(f);
        r1.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                MaterialProgressDrawable.this.d = 0.0f;
            }

            public void onAnimationRepeat(Animation animation) {
                ring.d();
                ring.b();
                ring.d = ring.e;
                if (MaterialProgressDrawable.this.e) {
                    MaterialProgressDrawable.this.e = false;
                    animation.setDuration(1332);
                    MaterialProgressDrawable.this.a(false);
                    return;
                }
                MaterialProgressDrawable.this.d = (MaterialProgressDrawable.this.d + 1.0f) % MaterialProgressDrawable.y;
            }
        });
        this.D = r1;
    }

    private class Ring {

        /* renamed from: a  reason: collision with root package name */
        final RectF f8753a = new RectF();
        final Paint b = new Paint();
        final Paint c = new Paint();
        float d = 0.0f;
        float e = 0.0f;
        float f = 0.0f;
        float g = MaterialProgressDrawable.y;
        float h = MaterialProgressDrawable.j;
        int[] i;
        int j;
        float k;
        float l;
        float m;
        boolean n;
        Path o;
        float p;
        double q;
        int r;
        int s;
        int t;
        final Paint u = new Paint(1);
        int v;
        int w;

        Ring() {
            this.b.setStrokeCap(Paint.Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Paint.Style.STROKE);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setAntiAlias(true);
        }

        public void a(Canvas canvas, Rect rect) {
            RectF rectF = this.f8753a;
            rectF.set(rect);
            rectF.inset(this.h, this.h);
            float f2 = (this.d + this.f) * 360.0f;
            float f3 = ((this.e + this.f) * 360.0f) - f2;
            if (f3 != 0.0f) {
                this.b.setColor(this.w);
                canvas.drawArc(rectF, f2, f3, false, this.b);
            }
            a(canvas, f2, f3, rect);
            if (this.t < 255) {
                this.u.setColor(this.v);
                this.u.setAlpha(255 - this.t);
                canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.u);
            }
        }

        private void a(Canvas canvas, float f2, float f3, Rect rect) {
            if (this.n) {
                if (this.o == null) {
                    this.o = new Path();
                    this.o.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    this.o.reset();
                }
                float f4 = ((float) (((int) this.h) / 2)) * this.p;
                double cos = this.q * Math.cos(0.0d);
                double exactCenterX = (double) rect.exactCenterX();
                Double.isNaN(exactCenterX);
                double sin = this.q * Math.sin(0.0d);
                double exactCenterY = (double) rect.exactCenterY();
                Double.isNaN(exactCenterY);
                this.o.moveTo(0.0f, 0.0f);
                this.o.lineTo(((float) this.r) * this.p, 0.0f);
                this.o.lineTo((((float) this.r) * this.p) / 2.0f, ((float) this.s) * this.p);
                this.o.offset(((float) (cos + exactCenterX)) - f4, (float) (sin + exactCenterY));
                this.o.close();
                this.c.setColor(this.w);
                canvas.rotate((f2 + f3) - MaterialProgressDrawable.y, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.o, this.c);
            }
        }

        public void a(int i2) {
            this.j = i2;
            this.w = this.i[this.j];
        }

        public int a() {
            return this.i[f()];
        }

        private int f() {
            return (this.j + 1) % this.i.length;
        }

        public void b() {
            a(f());
        }

        public int c() {
            return this.i[this.j];
        }

        public void a(int i2, int i3) {
            float f2;
            float min = (float) Math.min(i2, i3);
            if (this.q <= 0.0d || min < 0.0f) {
                f2 = (float) Math.ceil((double) (this.g / 2.0f));
            } else {
                double d2 = (double) (min / 2.0f);
                double d3 = this.q;
                Double.isNaN(d2);
                f2 = (float) (d2 - d3);
            }
            this.h = f2;
        }

        public void d() {
            this.k = this.d;
            this.l = this.e;
            this.m = this.f;
        }

        public void e() {
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 0.0f;
        }
    }
}
