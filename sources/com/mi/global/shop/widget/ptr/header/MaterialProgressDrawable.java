package com.mi.global.shop.widget.ptr.header;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.mi.global.shop.widget.ptr.util.PtrLocalDisplay;
import java.util.ArrayList;

public class MaterialProgressDrawable extends Drawable implements Animatable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7258a = 0;
    public static final int b = 1;
    private static final Interpolator c = new LinearInterpolator();
    /* access modifiers changed from: private */
    public static final Interpolator d = new EndCurveInterpolator();
    /* access modifiers changed from: private */
    public static final Interpolator e = new StartCurveInterpolator();
    private static final Interpolator f = new AccelerateDecelerateInterpolator();
    private static final int g = 40;
    private static final float h = 8.75f;
    private static final float i = 2.5f;
    private static final int j = 56;
    private static final float k = 12.5f;
    private static final float l = 3.0f;
    private static final int m = 1333;
    private static final float n = 5.0f;
    private static final int o = 10;
    private static final int p = 5;
    private static final float q = 5.0f;
    private static final int r = 12;
    private static final int s = 6;
    private static final float t = 0.8f;
    private static final int u = 503316480;
    private static final int v = 1023410176;
    private static final float w = 3.5f;
    private static final float x = 0.0f;
    private static final float y = 1.75f;
    private final ArrayList<Animation> A = new ArrayList<>();
    private final Ring B;
    private final Drawable.Callback C = new Drawable.Callback() {
        public void invalidateDrawable(Drawable drawable) {
            MaterialProgressDrawable.this.invalidateSelf();
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            MaterialProgressDrawable.this.scheduleSelf(runnable, j);
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            MaterialProgressDrawable.this.unscheduleSelf(runnable);
        }
    };
    private float D;
    private Resources E;
    /* access modifiers changed from: private */
    public View F;
    /* access modifiers changed from: private */
    public Animation G;
    /* access modifiers changed from: private */
    public float H;
    private double I;
    private double J;
    private Animation K;
    private int L;
    private ShapeDrawable M;
    private final int[] z = {-3591113, -13149199, -536002, -13327536};

    public int getOpacity() {
        return -3;
    }

    public MaterialProgressDrawable(Context context, View view) {
        this.F = view;
        this.E = context.getResources();
        this.B = new Ring(this.C);
        this.B.a(this.z);
        a(1);
        d();
    }

    private void a(double d2, double d3, double d4, double d5, float f2, float f3) {
        Ring ring = this.B;
        float f4 = this.E.getDisplayMetrics().density;
        double d6 = (double) f4;
        Double.isNaN(d6);
        this.I = d2 * d6;
        Double.isNaN(d6);
        this.J = d3 * d6;
        ring.a(((float) d5) * f4);
        Double.isNaN(d6);
        ring.a(d4 * d6);
        ring.b(0);
        ring.a(f2 * f4, f3 * f4);
        ring.a((int) this.I, (int) this.J);
        a(this.I);
    }

    private void a(double d2) {
        int a2 = PtrLocalDisplay.a((float) y);
        int a3 = PtrLocalDisplay.a(0.0f);
        int a4 = PtrLocalDisplay.a((float) w);
        this.M = new ShapeDrawable(new OvalShadow(a4, (int) d2));
        if (Build.VERSION.SDK_INT >= 11) {
            this.F.setLayerType(1, this.M.getPaint());
        }
        this.M.getPaint().setShadowLayer((float) a4, (float) a3, (float) a2, u);
    }

    public void a(int i2) {
        if (i2 == 0) {
            a(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void a(boolean z2) {
        this.B.a(z2);
    }

    public void a(float f2) {
        this.B.e(f2);
    }

    public void a(float f2, float f3) {
        this.B.b(f2);
        this.B.c(f3);
    }

    public void b(float f2) {
        this.B.d(f2);
    }

    public void b(int i2) {
        this.L = i2;
        this.B.a(i2);
    }

    public void a(int... iArr) {
        this.B.a(iArr);
        this.B.b(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.J;
    }

    public int getIntrinsicWidth() {
        return (int) this.I;
    }

    public void draw(Canvas canvas) {
        if (this.M != null) {
            this.M.getPaint().setColor(this.L);
            this.M.draw(canvas);
        }
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.rotate(this.D, bounds.exactCenterX(), bounds.exactCenterY());
        this.B.a(canvas, bounds);
        canvas.restoreToCount(save);
    }

    public int getAlpha() {
        return this.B.b();
    }

    public void setAlpha(int i2) {
        this.B.c(i2);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.B.a(colorFilter);
    }

    private float c() {
        return this.D;
    }

    /* access modifiers changed from: package-private */
    public void c(float f2) {
        this.D = f2;
        invalidateSelf();
    }

    public boolean isRunning() {
        ArrayList<Animation> arrayList = this.A;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Animation animation = arrayList.get(i2);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.G.reset();
        this.B.l();
        if (this.B.g() != this.B.d()) {
            this.F.startAnimation(this.K);
            return;
        }
        this.B.b(0);
        this.B.m();
        this.F.startAnimation(this.G);
    }

    public void stop() {
        this.F.clearAnimation();
        c(0.0f);
        this.B.a(false);
        this.B.b(0);
        this.B.m();
    }

    private void d() {
        final Ring ring = this.B;
        AnonymousClass2 r1 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                float floor = (float) (Math.floor((double) (ring.k() / 0.8f)) + 1.0d);
                ring.b(ring.e() + ((ring.f() - ring.e()) * f));
                ring.d(ring.k() + ((floor - ring.k()) * f));
                ring.e(1.0f - f);
            }
        };
        r1.setInterpolator(f);
        r1.setDuration(666);
        r1.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ring.a();
                ring.l();
                ring.a(false);
                MaterialProgressDrawable.this.F.startAnimation(MaterialProgressDrawable.this.G);
            }
        });
        AnonymousClass4 r2 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                double c = (double) ring.c();
                Double.isNaN(c);
                float radians = (float) Math.toRadians(c / (ring.j() * 6.283185307179586d));
                float f2 = ring.f();
                float e = ring.e();
                float k = ring.k();
                ring.c(f2 + ((0.8f - radians) * MaterialProgressDrawable.e.getInterpolation(f)));
                ring.b(e + (MaterialProgressDrawable.d.getInterpolation(f) * 0.8f));
                ring.d(k + (0.25f * f));
                MaterialProgressDrawable.this.c((f * 144.0f) + ((MaterialProgressDrawable.this.H / 5.0f) * 720.0f));
            }
        };
        r2.setRepeatCount(-1);
        r2.setRepeatMode(1);
        r2.setInterpolator(c);
        r2.setDuration(1333);
        r2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                float unused = MaterialProgressDrawable.this.H = 0.0f;
            }

            public void onAnimationRepeat(Animation animation) {
                ring.l();
                ring.a();
                ring.b(ring.g());
                float unused = MaterialProgressDrawable.this.H = (MaterialProgressDrawable.this.H + 1.0f) % 5.0f;
            }
        });
        this.K = r1;
        this.G = r2;
    }

    private static class Ring {

        /* renamed from: a  reason: collision with root package name */
        private final RectF f7265a = new RectF();
        private final Paint b = new Paint();
        private final Paint c = new Paint();
        private final Drawable.Callback d;
        private final Paint e = new Paint();
        private float f = 0.0f;
        private float g = 0.0f;
        private float h = 0.0f;
        private float i = 5.0f;
        private float j = MaterialProgressDrawable.i;
        private int[] k;
        private int l;
        private float m;
        private float n;
        private float o;
        private boolean p;
        private Path q;
        private float r;
        private double s;
        private int t;
        private int u;
        private int v;
        private int w;

        public Ring(Drawable.Callback callback) {
            this.d = callback;
            this.b.setStrokeCap(Paint.Cap.SQUARE);
            this.b.setAntiAlias(true);
            this.b.setStyle(Paint.Style.STROKE);
            this.c.setStyle(Paint.Style.FILL);
            this.c.setAntiAlias(true);
            this.e.setAntiAlias(true);
        }

        public void a(int i2) {
            this.w = i2;
        }

        public void a(float f2, float f3) {
            this.t = (int) f2;
            this.u = (int) f3;
        }

        public void a(Canvas canvas, Rect rect) {
            this.e.setColor(this.w);
            this.e.setAlpha(this.v);
            canvas.drawCircle(rect.exactCenterX(), rect.exactCenterY(), (float) (rect.width() / 2), this.e);
            RectF rectF = this.f7265a;
            rectF.set(rect);
            rectF.inset(this.j, this.j);
            float f2 = (this.f + this.h) * 360.0f;
            float f3 = ((this.g + this.h) * 360.0f) - f2;
            this.b.setColor(this.k[this.l]);
            this.b.setAlpha(this.v);
            canvas.drawArc(rectF, f2, f3, false, this.b);
            a(canvas, f2, f3, rect);
        }

        private void a(Canvas canvas, float f2, float f3, Rect rect) {
            if (this.p) {
                if (this.q == null) {
                    this.q = new Path();
                    this.q.setFillType(Path.FillType.EVEN_ODD);
                } else {
                    this.q.reset();
                }
                float f4 = ((float) (((int) this.j) / 2)) * this.r;
                double cos = this.s * Math.cos(0.0d);
                double exactCenterX = (double) rect.exactCenterX();
                Double.isNaN(exactCenterX);
                double sin = this.s * Math.sin(0.0d);
                double exactCenterY = (double) rect.exactCenterY();
                Double.isNaN(exactCenterY);
                this.q.moveTo(0.0f, 0.0f);
                this.q.lineTo(((float) this.t) * this.r, 0.0f);
                this.q.lineTo((((float) this.t) * this.r) / 2.0f, ((float) this.u) * this.r);
                this.q.offset(((float) (cos + exactCenterX)) - f4, (float) (sin + exactCenterY));
                this.q.close();
                this.c.setColor(this.k[this.l]);
                this.c.setAlpha(this.v);
                canvas.rotate((f2 + f3) - 5.0f, rect.exactCenterX(), rect.exactCenterY());
                canvas.drawPath(this.q, this.c);
            }
        }

        public void a(int[] iArr) {
            this.k = iArr;
            b(0);
        }

        public void b(int i2) {
            this.l = i2;
        }

        public void a() {
            this.l = (this.l + 1) % this.k.length;
        }

        public void a(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
            n();
        }

        public int b() {
            return this.v;
        }

        public void c(int i2) {
            this.v = i2;
        }

        public float c() {
            return this.i;
        }

        public void a(float f2) {
            this.i = f2;
            this.b.setStrokeWidth(f2);
            n();
        }

        public float d() {
            return this.f;
        }

        public void b(float f2) {
            this.f = f2;
            n();
        }

        public float e() {
            return this.m;
        }

        public float f() {
            return this.n;
        }

        public float g() {
            return this.g;
        }

        public void c(float f2) {
            this.g = f2;
            n();
        }

        public float h() {
            return this.h;
        }

        public void d(float f2) {
            this.h = f2;
            n();
        }

        public void a(int i2, int i3) {
            float f2;
            float min = (float) Math.min(i2, i3);
            if (this.s <= 0.0d || min < 0.0f) {
                f2 = (float) Math.ceil((double) (this.i / 2.0f));
            } else {
                double d2 = (double) (min / 2.0f);
                double d3 = this.s;
                Double.isNaN(d2);
                f2 = (float) (d2 - d3);
            }
            this.j = f2;
        }

        public float i() {
            return this.j;
        }

        public double j() {
            return this.s;
        }

        public void a(double d2) {
            this.s = d2;
        }

        public void a(boolean z) {
            if (this.p != z) {
                this.p = z;
                n();
            }
        }

        public void e(float f2) {
            if (f2 != this.r) {
                this.r = f2;
                n();
            }
        }

        public float k() {
            return this.o;
        }

        public void l() {
            this.m = this.f;
            this.n = this.g;
            this.o = this.h;
        }

        public void m() {
            this.m = 0.0f;
            this.n = 0.0f;
            this.o = 0.0f;
            b(0.0f);
            c(0.0f);
            d(0.0f);
        }

        private void n() {
            this.d.invalidateDrawable((Drawable) null);
        }
    }

    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
        private EndCurveInterpolator() {
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.max(0.0f, (f - 0.5f) * 2.0f));
        }
    }

    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
        private StartCurveInterpolator() {
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.min(1.0f, f * 2.0f));
        }
    }

    private class OvalShadow extends OvalShape {
        private RadialGradient b;
        private int c;
        private Paint d = new Paint();
        private int e;

        public OvalShadow(int i, int i2) {
            this.c = i;
            this.e = i2;
            this.b = new RadialGradient((float) (this.e / 2), (float) (this.e / 2), (float) this.c, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.d.setShader(this.b);
        }

        public void draw(Canvas canvas, Paint paint) {
            float width = (float) (MaterialProgressDrawable.this.getBounds().width() / 2);
            float height = (float) (MaterialProgressDrawable.this.getBounds().height() / 2);
            canvas.drawCircle(width, height, (float) ((this.e / 2) + this.c), this.d);
            canvas.drawCircle(width, height, (float) (this.e / 2), paint);
        }
    }
}
