package com.xiaomi.smarthome.library.common.widget.nestscroll;

import com.xiaomi.smarthome.framework.log.LogUtil;

public class HeaderController {

    /* renamed from: a  reason: collision with root package name */
    private int f19025a;
    private int b;
    private int c;
    private float d;
    private float e;
    private float f = 0.5f;
    private boolean g = false;
    private float h = 0.0f;
    private int i = 0;
    private int j = 0;

    public HeaderController(int i2, int i3, int i4) {
        if (i3 > 0) {
            a(i2, i3, i4);
            return;
        }
        throw new IllegalArgumentException("maxHeight must > 0");
    }

    public void a(int i2, int i3, int i4) {
        this.f19025a = Math.max(0, i2);
        this.b = Math.max(0, i3);
        this.c = Math.max(0, i4);
        this.d = (float) (this.b - this.f19025a);
        this.e = (float) (this.f19025a - this.c);
        this.h = 0.0f;
        this.i = this.f19025a - this.c;
        this.j = this.f19025a - this.b;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.f19025a;
    }

    public int d() {
        return (int) this.h;
    }

    public int e() {
        return this.i;
    }

    public int f() {
        return this.j;
    }

    public int g() {
        return (int) (((float) this.f19025a) - this.h);
    }

    public boolean h() {
        return this.g;
    }

    public boolean i() {
        return this.h > ((float) this.j);
    }

    public boolean j() {
        return this.h < ((float) this.i);
    }

    public int a(float f2) {
        float f3;
        if (this.h >= 0.0f) {
            f3 = this.h + f2;
            if (f3 < 0.0f) {
                f3 *= this.f;
                if (f3 < ((float) this.j)) {
                    f2 -= (f3 - ((float) this.j)) / this.f;
                    f3 = (float) this.j;
                }
            } else if (f3 > ((float) this.i)) {
                f2 -= f3 - ((float) this.i);
                f3 = (float) this.i;
            }
        } else {
            f3 = this.h + (this.f * f2);
            if (f3 > 0.0f) {
                f3 /= this.f;
                if (f3 > ((float) this.i)) {
                    f2 -= f3 - ((float) this.i);
                    f3 = (float) this.i;
                }
            } else if (f3 < ((float) this.j)) {
                f2 -= f3 - ((float) this.j);
                f3 = (float) this.j;
            }
        }
        this.h = f3;
        LogUtil.a("PullHeaderLayout", "" + this.h);
        return (int) f2;
    }

    public boolean k() {
        return this.h < 0.0f;
    }

    public float l() {
        return this.d;
    }

    public float m() {
        return (-this.h) / this.d;
    }

    public float n() {
        return (-this.h) / this.e;
    }

    public boolean o() {
        return m() > 0.2f;
    }
}
