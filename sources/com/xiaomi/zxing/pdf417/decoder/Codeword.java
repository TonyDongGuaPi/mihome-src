package com.xiaomi.zxing.pdf417.decoder;

final class Codeword {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1736a = -1;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private int f = -1;

    Codeword(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return a(this.f);
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i) {
        return i != -1 && this.d == (i % 3) * 3;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.f = ((this.e / 30) * 3) + (this.d / 3);
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.c - this.b;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public int g() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int h() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        this.f = i;
    }

    public String toString() {
        return this.f + "|" + this.e;
    }
}
