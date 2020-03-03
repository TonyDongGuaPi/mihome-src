package com.xiaomi.zxing.pdf417.decoder.ec;

public final class ModulusGF {

    /* renamed from: a  reason: collision with root package name */
    public static final ModulusGF f1745a = new ModulusGF(929, 3);
    private final int[] b;
    private final int[] c;
    private final ModulusPoly d;
    private final ModulusPoly e;
    private final int f;

    private ModulusGF(int i, int i2) {
        this.f = i;
        this.b = new int[i];
        this.c = new int[i];
        int i3 = 1;
        for (int i4 = 0; i4 < i; i4++) {
            this.b[i4] = i3;
            i3 = (i3 * i2) % i;
        }
        for (int i5 = 0; i5 < i - 1; i5++) {
            this.c[this.b[i5]] = i5;
        }
        this.d = new ModulusPoly(this, new int[]{0});
        this.e = new ModulusPoly(this, new int[]{1});
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly a() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly b() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.d;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new ModulusPoly(this, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public int b(int i, int i2) {
        return (i + i2) % this.f;
    }

    /* access modifiers changed from: package-private */
    public int c(int i, int i2) {
        return ((this.f + i) - i2) % this.f;
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        return this.b[i];
    }

    /* access modifiers changed from: package-private */
    public int b(int i) {
        if (i != 0) {
            return this.c[i];
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int c(int i) {
        if (i != 0) {
            return this.b[(this.f - this.c[i]) - 1];
        }
        throw new ArithmeticException();
    }

    /* access modifiers changed from: package-private */
    public int d(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return this.b[(this.c[i] + this.c[i2]) % (this.f - 1)];
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.f;
    }
}
