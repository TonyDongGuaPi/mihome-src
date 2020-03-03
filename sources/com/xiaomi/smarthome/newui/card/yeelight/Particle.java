package com.xiaomi.smarthome.newui.card.yeelight;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.xiaomi.smarthome.newui.card.yeelight.modifiers.ParticleModifier;
import java.util.List;
import java.util.Random;

public class Particle {

    /* renamed from: a  reason: collision with root package name */
    public static final int f20612a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 0;
    private float A;
    private Matrix B;
    private Paint C;
    private float D;
    private float E;
    private float F;
    private long G;
    private int H;
    private int I;
    private int J;
    private List<ParticleModifier> K;
    private int L;
    private ValueAnimator M;
    protected Bitmap f;
    public float g;
    public float h;
    public float i;
    public float j;
    public int k;
    public int l;
    int m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public float s;
    public int t;
    DecelerateInterpolator u;
    AccelerateInterpolator v;
    Random w;
    protected long x;
    private float y;
    private float z;

    protected Particle(float f2) {
        this.y = 1.0f;
        this.i = 1.0f;
        this.j = 1.0f;
        this.k = 255;
        this.l = 255;
        this.m = 100;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.t = 17170443;
        this.B = new Matrix();
        this.C = new Paint();
        this.C.setAntiAlias(true);
        this.C.setFilterBitmap(true);
        this.w = new Random();
        this.u = new DecelerateInterpolator();
        this.v = new AccelerateInterpolator();
        this.m = (int) ((20.0f * f2 * this.w.nextFloat()) + (80.0f * f2));
        this.y = f2;
        this.M = new ValueAnimator();
    }

    public Particle() {
        this(1.0f);
    }

    public Particle(Bitmap bitmap, float f2) {
        this(f2);
        this.f = bitmap;
    }

    public void a() {
        this.i = 1.0f;
        this.j = this.i;
        this.k = 255;
        this.l = this.k;
    }

    public void a(long j2, float f2, float f3) {
        long j3 = j2;
        this.H = this.f.getWidth() / 2;
        this.I = this.f.getHeight() / 2;
        if (this.J == 3) {
            float nextFloat = (((this.w.nextFloat() - 0.5f) * 2.0f) + 10.0f) * this.y;
            this.D = (f2 - ((float) this.H)) + nextFloat;
            this.E = (f3 - ((float) this.I)) + nextFloat;
            this.g = this.D;
            this.h = this.E;
            double nextFloat2 = (double) this.w.nextFloat();
            Double.isNaN(nextFloat2);
            double d2 = (double) this.y;
            Double.isNaN(d2);
            double nextFloat3 = (double) (this.w.nextFloat() * 2.0f);
            Double.isNaN(nextFloat3);
            double d3 = (double) this.D;
            double d4 = (double) ((int) ((((nextFloat2 - 0.5d) * 2.0d * 40.0d) + 10.0d) * d2));
            double d5 = (double) ((float) (nextFloat3 * 3.141592653589793d));
            double cos = Math.cos(d5);
            Double.isNaN(d4);
            Double.isNaN(d3);
            this.z = (float) (d3 + (cos * d4));
            double d6 = (double) this.E;
            double sin = Math.sin(d5);
            Double.isNaN(d4);
            Double.isNaN(d6);
            this.A = (float) (d6 + (d4 * sin));
            if (j3 > 0) {
                long j4 = j3 / 2;
                this.G = ((long) this.w.nextInt((int) j4)) + j4;
                return;
            }
            this.G = j3;
            return;
        }
        float nextFloat4 = (this.w.nextFloat() - 0.5f) * 2.0f * this.y * 20.0f;
        this.D = (f2 - ((float) this.H)) + nextFloat4;
        this.E = (f3 - ((float) this.I)) + nextFloat4;
        this.g = this.D;
        this.h = this.E;
        double nextFloat5 = (double) this.w.nextFloat();
        Double.isNaN(nextFloat5);
        double d7 = (double) (((1920.0f - this.E) / 1920.0f) * 170.0f);
        Double.isNaN(d7);
        double nextFloat6 = (double) (this.w.nextFloat() * 2.0f);
        Double.isNaN(nextFloat6);
        double d8 = (double) this.D;
        double d9 = (double) ((int) (((nextFloat5 - 0.5d) * 2.0d * 20.0d) + 10.0d + d7));
        double d10 = (double) ((float) (nextFloat6 * 3.141592653589793d));
        double cos2 = Math.cos(d10);
        Double.isNaN(d9);
        Double.isNaN(d8);
        this.z = (float) (d8 + (cos2 * d9));
        double d11 = (double) this.E;
        double sin2 = Math.sin(d10);
        Double.isNaN(d9);
        Double.isNaN(d11);
        this.A = (float) (d11 + (d9 * sin2));
        this.G = j3;
    }

    public boolean a(long j2) {
        long j3 = j2 - this.x;
        if (j3 > this.G) {
            return false;
        }
        if (this.J == 2) {
            float f2 = (float) j3;
            float f3 = f2 * 1.0f;
            this.h = this.E + (this.q * ((float) this.m) * this.u.getInterpolation(f3 / ((float) (this.G + 100)))) + (this.s * f2 * f2);
            this.g = this.D + (this.p * ((float) this.m) * this.u.getInterpolation(f3 / ((float) (this.G + 100)))) + (this.r * f2 * f2);
            this.i = (this.u.getInterpolation((((float) (this.G - j3)) * 1.0f) / ((float) this.G)) * 0.6f) + 0.2f;
        } else if (this.J == 4) {
            this.h += ((((this.q * 1.5f) * ((float) (this.G - j3))) * 1.0f) / ((float) (this.G + 100))) + 0.0f;
            this.g += ((((this.p * 1.5f) * ((float) (this.G - j3))) * 1.0f) / ((float) (this.G + 100))) + 0.0f;
        } else if (this.J == 1) {
            this.g = this.z;
            this.h = this.A;
        } else if (this.J == 3) {
            this.g = this.z;
            this.h = this.A;
            if (this.l > 0) {
                this.l = (int) (this.u.getInterpolation((((float) (this.G - j3)) * 1.0f) / ((float) this.G)) * ((float) this.k));
            }
            this.j = this.v.getInterpolation((((float) (this.G - j3)) * 1.0f) / ((float) this.G)) * this.i;
        } else {
            float f4 = (float) j3;
            this.g = this.D + (this.p * f4) + (this.r * f4 * f4);
            this.h = this.E + (this.q * f4) + (this.s * f4 * f4);
        }
        this.F = this.n + ((this.o * ((float) j3)) / 1000.0f);
        for (int i2 = 0; i2 < this.K.size(); i2++) {
            this.K.get(i2).a(this, j3);
        }
        return true;
    }

    public void a(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 2));
        this.B.reset();
        this.B.postRotate(this.F, (float) this.H, (float) this.I);
        if (this.J == 3) {
            this.B.postScale(this.j, this.j, (float) this.H, (float) this.I);
            this.B.postTranslate(this.g, this.h);
            this.C.setAlpha(this.l);
        } else if (this.J == 4) {
            this.B.postScale(this.i, this.i, (float) this.H, (float) this.I);
            this.B.postTranslate(this.g, this.h);
            if (this.w.nextInt() % 2 == 0) {
                this.C.setAlpha(this.k);
            }
        } else {
            this.B.postScale(this.i, this.i, (float) this.H, (float) this.I);
            this.B.postTranslate(this.g, this.h);
            this.C.setAlpha(this.k);
        }
        canvas.drawBitmap(this.f, this.B, this.C);
    }

    public Particle a(long j2, List<ParticleModifier> list) {
        this.x = j2;
        this.K = list;
        return this;
    }

    public int b() {
        return this.J;
    }

    public void a(int i2) {
        this.J = i2;
    }
}
