package com.chipsea.healthscale;

public class CsAlgoBuilderEx {

    /* renamed from: a  reason: collision with root package name */
    private int f5146a;
    private byte b;
    private int c;
    private float d;
    private float e;
    private float f;
    private float g;

    public float a() {
        float f2 = ((this.f / this.d) * 100.0f) - this.g;
        if (f2 > 32.0f) {
            return 32.0f;
        }
        if (f2 < 5.0f) {
            return 5.0f;
        }
        return f2;
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.f = f3;
        this.g = f5;
    }

    public void a(int i, byte b2, int i2, float f2, float f3) {
        this.f5146a = i;
        this.b = b2;
        this.c = i2;
        this.d = f2;
        this.e = f3;
    }

    public float b() {
        float f2;
        float f3;
        if (this.b > 0) {
            f2 = ((float) this.f5146a) - 80.0f;
            f3 = 0.7f;
        } else {
            f2 = ((float) this.f5146a) - 70.0f;
            f3 = 0.6f;
        }
        return f2 * f3;
    }

    public int c() {
        float f2;
        float f3;
        if (this.b > 0) {
            f2 = (((float) this.f5146a) * -0.7471f) + (this.d * 0.9161f) + (((float) this.c) * 0.4184f) + (this.e * 0.0517f);
            f3 = 54.2267f;
        } else {
            f2 = (((float) this.f5146a) * -1.1165f) + (this.d * 1.5784f) + (((float) this.c) * 0.4615f) + (this.e * 0.0415f);
            f3 = 83.2548f;
        }
        float f4 = f2 + f3;
        float f5 = 15.0f;
        if (f4 >= 15.0f) {
            f5 = f4 > 80.0f ? 80.0f : f4;
        }
        return (int) f5;
    }
}
