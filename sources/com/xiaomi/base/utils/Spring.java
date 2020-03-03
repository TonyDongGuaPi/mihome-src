package com.xiaomi.base.utils;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class Spring {

    /* renamed from: a  reason: collision with root package name */
    private static int f10040a = 0;
    private static final double b = 0.064d;
    private static final double c = 0.001d;
    private final PhysicsState d = new PhysicsState();
    private double e = 0.005d;
    private double f;
    private final String g;
    private CopyOnWriteArraySet<SpringListener> h = new CopyOnWriteArraySet<>();
    private boolean i;
    private final PhysicsState j = new PhysicsState();
    private double k = 0.005d;
    private SpringConfig l;
    private final BaseSpringSystem m;
    private double n;
    private final PhysicsState o = new PhysicsState();
    private double p = 0.0d;
    private boolean q = true;

    Spring(BaseSpringSystem baseSpringSystem) {
        if (baseSpringSystem != null) {
            this.m = baseSpringSystem;
            StringBuilder sb = new StringBuilder();
            sb.append("spring:");
            int i2 = f10040a;
            f10040a = i2 + 1;
            sb.append(i2);
            this.g = sb.toString();
            a(SpringConfig.f10043a);
            return;
        }
        throw new IllegalArgumentException("Spring cannot be created outside of a BaseSpringSystem");
    }

    private double a(PhysicsState physicsState) {
        return Math.abs(this.f - physicsState.f10041a);
    }

    private void h(double d2) {
        double d3 = 1.0d - d2;
        this.d.f10041a = (this.d.f10041a * d2) + (this.j.f10041a * d3);
        this.d.b = (d2 * this.d.b) + (this.j.b * d3);
    }

    public Spring a(SpringListener springListener) {
        if (springListener != null) {
            this.h.add(springListener);
            return this;
        }
        throw new IllegalArgumentException("newListener is required");
    }

    /* access modifiers changed from: package-private */
    public void a(double d2) {
        boolean z;
        boolean k2 = k();
        if (!k2 || !this.q) {
            double d3 = b;
            if (d2 <= b) {
                d3 = d2;
            }
            this.p += d3;
            double d4 = this.l.c;
            double d5 = this.l.b;
            double d6 = this.d.f10041a;
            double d7 = this.d.b;
            double d8 = this.o.f10041a;
            double d9 = this.o.b;
            while (this.p >= c) {
                this.p -= c;
                if (this.p < c) {
                    this.j.f10041a = d6;
                    this.j.b = d7;
                }
                double d10 = ((this.f - d8) * d4) - (d5 * d7);
                double d11 = d7 + (d10 * c * 0.5d);
                double d12 = ((this.f - (((d7 * c) * 0.5d) + d6)) * d4) - (d5 * d11);
                double d13 = d7 + (d12 * c * 0.5d);
                double d14 = d10;
                double d15 = ((this.f - (((d11 * c) * 0.5d) + d6)) * d4) - (d5 * d13);
                d8 = (d13 * c) + d6;
                double d16 = d7 + (d15 * c);
                d7 += ((((this.f - d8) * d4) - (d5 * d16)) + d14 + ((d12 + d15) * 2.0d)) * 0.16666666666666666d * c;
                d6 += (d16 + d7 + ((d11 + d13) * 2.0d)) * 0.16666666666666666d * c;
                d9 = d16;
            }
            double d17 = d6;
            this.o.f10041a = d8;
            this.o.b = d9;
            this.d.f10041a = d6;
            this.d.b = d7;
            if (this.p > 0.0d) {
                h(this.p / c);
            }
            if (k() || (this.i && m())) {
                if (d4 > 0.0d) {
                    this.n = this.f;
                    this.d.f10041a = this.f;
                } else {
                    this.f = this.d.f10041a;
                    this.n = this.f;
                }
                g(0.0d);
                k2 = true;
            }
            boolean z2 = false;
            if (this.q) {
                this.q = false;
                z = true;
            } else {
                z = false;
            }
            if (k2) {
                this.q = true;
                z2 = true;
            }
            Iterator<SpringListener> it = this.h.iterator();
            while (it.hasNext()) {
                SpringListener next = it.next();
                if (z) {
                    next.a(this);
                }
                next.d(this);
                if (z2) {
                    next.b(this);
                }
            }
        }
    }

    public boolean b(double d2) {
        return Math.abs(c() - d2) <= f();
    }

    public void a() {
        this.h.clear();
        this.m.a(this);
    }

    public double b() {
        return a(this.d);
    }

    public double c() {
        return this.d.f10041a;
    }

    public double d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public double f() {
        return this.e;
    }

    public double g() {
        return this.k;
    }

    public SpringConfig h() {
        return this.l;
    }

    public double i() {
        return this.n;
    }

    public double j() {
        return this.d.b;
    }

    public boolean k() {
        return Math.abs(this.d.b) <= this.k && (a(this.d) <= this.e || this.l.c == 0.0d);
    }

    public boolean l() {
        return this.i;
    }

    public boolean m() {
        return this.l.c > 0.0d && ((this.n < this.f && c() > this.f) || (this.n > this.f && c() < this.f));
    }

    public Spring n() {
        this.h.clear();
        return this;
    }

    public Spring b(SpringListener springListener) {
        if (springListener != null) {
            this.h.remove(springListener);
            return this;
        }
        throw new IllegalArgumentException("listenerToRemove is required");
    }

    public Spring o() {
        this.f = this.d.f10041a;
        this.o.f10041a = this.d.f10041a;
        this.d.b = 0.0d;
        return this;
    }

    public Spring c(double d2) {
        return a(d2, true);
    }

    public Spring a(double d2, boolean z) {
        this.n = d2;
        this.d.f10041a = d2;
        this.m.a(e());
        Iterator<SpringListener> it = this.h.iterator();
        while (it.hasNext()) {
            it.next().d(this);
        }
        if (z) {
            o();
        }
        return this;
    }

    public Spring d(double d2) {
        if (this.f != d2 || !k()) {
            this.n = c();
            this.f = d2;
            this.m.a(e());
            Iterator<SpringListener> it = this.h.iterator();
            while (it.hasNext()) {
                it.next().c(this);
            }
        }
        return this;
    }

    public Spring a(boolean z) {
        this.i = z;
        return this;
    }

    public Spring e(double d2) {
        this.e = d2;
        return this;
    }

    public Spring f(double d2) {
        this.k = d2;
        return this;
    }

    public Spring a(SpringConfig springConfig) {
        if (springConfig != null) {
            this.l = springConfig;
            return this;
        }
        throw new IllegalArgumentException("springConfig is required");
    }

    public Spring g(double d2) {
        if (d2 != this.d.b) {
            this.d.b = d2;
            this.m.a(e());
        }
        return this;
    }

    public boolean p() {
        return !k() || !q();
    }

    public boolean q() {
        return this.q;
    }

    private static class PhysicsState {

        /* renamed from: a  reason: collision with root package name */
        double f10041a;
        double b;

        private PhysicsState() {
        }
    }
}
