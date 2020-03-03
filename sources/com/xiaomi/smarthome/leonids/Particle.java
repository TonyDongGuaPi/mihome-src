package com.xiaomi.smarthome.leonids;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.xiaomi.smarthome.leonids.ParticleSystem;
import com.xiaomi.smarthome.leonids.modifiers.ParticleModifier;
import java.util.List;
import java.util.Random;

public class Particle {

    /* renamed from: a  reason: collision with root package name */
    protected Bitmap f18429a;
    public float b;
    public float c;
    public float d;
    public int e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    protected long l;
    private Matrix m;
    private Paint n;
    private float o;
    private float p;
    private float q;
    private long r;
    private int s;
    private int t;
    private List<ParticleModifier> u;
    private int v;
    private ParticleSystem.ParticleSystemType w;
    private Random x;
    private float y;
    private long z;

    protected Particle() {
        this.d = 1.0f;
        this.e = 255;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.w = ParticleSystem.ParticleSystemType.Normal;
        this.x = new Random();
        this.y = 1.0f;
        this.m = new Matrix();
        this.n = new Paint();
    }

    public Particle(Bitmap bitmap) {
        this();
        this.f18429a = bitmap;
    }

    public void a() {
        this.d = 1.0f;
        this.e = 255;
    }

    public void a(long j2, float f2, float f3) {
        this.s = this.f18429a.getWidth() / 2;
        this.t = this.f18429a.getHeight() / 2;
        this.o = f2 - ((float) this.s);
        this.p = f3 - ((float) this.t);
        this.b = this.o;
        this.c = this.p;
        this.r = j2;
    }

    public void a(long j2, float f2, float f3, int i2, ParticleSystem.ParticleSystemType particleSystemType) {
        this.s = this.f18429a.getWidth() / 2;
        this.t = this.f18429a.getHeight() / 2;
        this.o = f2 - ((float) this.s);
        this.p = f3 - ((float) this.t);
        this.b = this.o;
        this.c = this.p;
        this.r = j2;
        this.v = i2;
        this.w = particleSystemType;
        if (this.w == ParticleSystem.ParticleSystemType.AirPurifier) {
            this.e = this.x.nextInt(50) + 50;
        }
    }

    public boolean a(long j2) {
        long j3 = j2 - this.l;
        if (j3 > this.r) {
            return false;
        }
        if (this.w == ParticleSystem.ParticleSystemType.Sweeper) {
            float f2 = (float) j3;
            float f3 = (this.h * f2) + (this.j * f2 * f2);
            double d2 = (double) this.v;
            Double.isNaN(d2);
            double d3 = (double) this.o;
            double d4 = (double) f3;
            double d5 = (double) ((float) ((d2 * 3.141592653589793d) / 180.0d));
            double sin = Math.sin(d5);
            Double.isNaN(d4);
            Double.isNaN(d3);
            this.b = (float) (d3 - (sin * d4));
            double d6 = (double) this.p;
            double cos = Math.cos(d5);
            Double.isNaN(d4);
            Double.isNaN(d6);
            this.c = (float) (d6 + (d4 * cos));
        } else if (this.w == ParticleSystem.ParticleSystemType.AirPurifier) {
            float f4 = (float) j3;
            this.b += (this.h * this.y * ((float) (j2 - this.z))) + (this.j * f4 * f4);
            this.c += (this.i * this.y * ((float) (j2 - this.z))) + (this.k * f4 * f4);
        } else {
            float f5 = (float) j3;
            this.b = this.o + (this.h * f5) + (this.j * f5 * f5);
            this.c = this.p + (this.i * f5) + (this.k * f5 * f5);
        }
        this.q = this.f + ((this.g * ((float) j3)) / 1000.0f);
        for (int i2 = 0; i2 < this.u.size(); i2++) {
            this.u.get(i2).a(this, j3);
        }
        this.z = j2;
        return true;
    }

    public void a(Canvas canvas) {
        this.m.reset();
        this.m.postRotate(this.q, (float) this.s, (float) this.t);
        this.m.postScale(this.d, this.d, (float) this.s, (float) this.t);
        this.m.postTranslate(this.b, this.c);
        this.n.setAlpha(this.e);
        canvas.drawBitmap(this.f18429a, this.m, this.n);
    }

    public Particle a(long j2, List<ParticleModifier> list) {
        this.l = j2;
        this.u = list;
        this.z = j2;
        return this;
    }

    public Bitmap b() {
        return this.f18429a;
    }

    public void a(Bitmap bitmap) {
        this.f18429a = bitmap;
    }

    public void a(float f2) {
        this.y = f2;
    }
}
