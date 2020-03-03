package com.xiaomi.zxing.pdf417.decoder;

final class BarcodeMetadata {

    /* renamed from: a  reason: collision with root package name */
    private final int f1733a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    BarcodeMetadata(int i, int i2, int i3, int i4) {
        this.f1733a = i;
        this.b = i4;
        this.c = i2;
        this.d = i3;
        this.e = i2 + i3;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.f1733a;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.d;
    }
}
