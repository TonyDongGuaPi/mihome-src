package com.xiaomi.smarthome.fastvideo;

import java.util.Arrays;

public class ColorSpaceMatrix {
    private static final float b = 0.3086f;
    private static final float c = 0.6094f;
    private static final float d = 0.082f;

    /* renamed from: a  reason: collision with root package name */
    private final float[] f15879a = new float[16];

    public ColorSpaceMatrix() {
        b();
    }

    public ColorSpaceMatrix(ColorSpaceMatrix colorSpaceMatrix) {
        System.arraycopy(colorSpaceMatrix.f15879a, 0, this.f15879a, 0, colorSpaceMatrix.f15879a.length);
    }

    public float[] a() {
        return this.f15879a;
    }

    public void b() {
        Arrays.fill(this.f15879a, 0.0f);
        float[] fArr = this.f15879a;
        float[] fArr2 = this.f15879a;
        float[] fArr3 = this.f15879a;
        this.f15879a[15] = 1.0f;
        fArr3[10] = 1.0f;
        fArr2[5] = 1.0f;
        fArr[0] = 1.0f;
    }

    public void c() {
        float[] fArr = this.f15879a;
        float[] fArr2 = this.f15879a;
        this.f15879a[2] = 0.3086f;
        fArr2[1] = 0.3086f;
        fArr[0] = 0.3086f;
        float[] fArr3 = this.f15879a;
        float[] fArr4 = this.f15879a;
        this.f15879a[6] = 0.6094f;
        fArr4[5] = 0.6094f;
        fArr3[4] = 0.6094f;
        float[] fArr5 = this.f15879a;
        float[] fArr6 = this.f15879a;
        this.f15879a[10] = 0.082f;
        fArr6[9] = 0.082f;
        fArr5[8] = 0.082f;
    }

    private void a(float[] fArr) {
        float[] fArr2 = new float[16];
        for (int i = 0; i < 4; i++) {
            int i2 = i * 4;
            for (int i3 = 0; i3 < 4; i3++) {
                fArr2[i2 + i3] = (this.f15879a[i2 + 0] * fArr[i3]) + (this.f15879a[i2 + 1] * fArr[i3 + 4]) + (this.f15879a[i2 + 2] * fArr[i3 + 8]) + (this.f15879a[i2 + 3] * fArr[i3 + 12]);
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            this.f15879a[i4] = fArr2[i4];
        }
    }

    private void a(float f, float f2) {
        float[] fArr = new ColorSpaceMatrix().f15879a;
        fArr[5] = f2;
        fArr[6] = f;
        fArr[9] = -f;
        fArr[10] = f2;
        a(fArr);
    }

    private void b(float f, float f2) {
        float[] fArr = new ColorSpaceMatrix().f15879a;
        fArr[0] = f2;
        fArr[2] = -f;
        fArr[8] = f;
        fArr[10] = f2;
        a(fArr);
    }

    private void c(float f, float f2) {
        float[] fArr = new ColorSpaceMatrix().f15879a;
        fArr[0] = f2;
        fArr[1] = f;
        fArr[4] = -f;
        fArr[5] = f2;
        a(fArr);
    }

    private void d(float f, float f2) {
        float[] fArr = new ColorSpaceMatrix().f15879a;
        fArr[2] = f;
        fArr[6] = f2;
        a(fArr);
    }

    public void a(float f) {
        float sqrt = 1.0f / ((float) Math.sqrt(2.0d));
        a(sqrt, sqrt);
        float sqrt2 = (float) Math.sqrt(3.0d);
        float f2 = -1.0f / sqrt2;
        float sqrt3 = ((float) Math.sqrt(2.0d)) / sqrt2;
        b(f2, sqrt3);
        float a2 = a((float) b, (float) c, (float) d);
        float b2 = b((float) b, (float) c, (float) d);
        float c2 = c((float) b, (float) c, (float) d);
        float f3 = a2 / c2;
        float f4 = b2 / c2;
        d(f3, f4);
        double d2 = (double) f;
        Double.isNaN(d2);
        double d3 = (d2 * 3.141592653589793d) / 180.0d;
        c((float) Math.sin(d3), (float) Math.cos(d3));
        d(-f3, -f4);
        b(-f2, sqrt3);
        a(-sqrt, sqrt);
    }

    public void b(float f) {
        float[] fArr = this.f15879a;
        float f2 = 1.0f - f;
        float f3 = b * f2;
        fArr[0] = f3 + f;
        this.f15879a[1] = f3;
        this.f15879a[2] = f3;
        float[] fArr2 = this.f15879a;
        float f4 = c * f2;
        fArr2[4] = f4;
        this.f15879a[5] = f4 + f;
        this.f15879a[6] = f4;
        float[] fArr3 = this.f15879a;
        float f5 = f2 * d;
        fArr3[8] = f5;
        this.f15879a[9] = f5;
        this.f15879a[10] = f5 + f;
    }

    public float a(int i, int i2, int i3) {
        return (((float) i) * this.f15879a[0]) + (((float) i2) * this.f15879a[4]) + (((float) i3) * this.f15879a[8]) + this.f15879a[12];
    }

    public float b(int i, int i2, int i3) {
        return (((float) i) * this.f15879a[1]) + (((float) i2) * this.f15879a[5]) + (((float) i3) * this.f15879a[9]) + this.f15879a[13];
    }

    public float c(int i, int i2, int i3) {
        return (((float) i) * this.f15879a[2]) + (((float) i2) * this.f15879a[6]) + (((float) i3) * this.f15879a[10]) + this.f15879a[14];
    }

    private float a(float f, float f2, float f3) {
        return (f * this.f15879a[0]) + (f2 * this.f15879a[4]) + (f3 * this.f15879a[8]) + this.f15879a[12];
    }

    private float b(float f, float f2, float f3) {
        return (f * this.f15879a[1]) + (f2 * this.f15879a[5]) + (f3 * this.f15879a[9]) + this.f15879a[13];
    }

    private float c(float f, float f2, float f3) {
        return (f * this.f15879a[2]) + (f2 * this.f15879a[6]) + (f3 * this.f15879a[10]) + this.f15879a[14];
    }
}
