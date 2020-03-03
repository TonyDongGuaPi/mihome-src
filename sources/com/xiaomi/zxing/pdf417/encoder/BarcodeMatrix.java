package com.xiaomi.zxing.pdf417.encoder;

import java.lang.reflect.Array;

public final class BarcodeMatrix {

    /* renamed from: a  reason: collision with root package name */
    private final BarcodeRow[] f1749a;
    private int b;
    private final int c;
    private final int d;

    BarcodeMatrix(int i, int i2) {
        this.f1749a = new BarcodeRow[i];
        int length = this.f1749a.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.f1749a[i3] = new BarcodeRow(((i2 + 4) * 17) + 1);
        }
        this.d = i2 * 17;
        this.c = i;
        this.b = -1;
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, byte b2) {
        this.f1749a[i2].a(i, b2);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.b++;
    }

    /* access modifiers changed from: package-private */
    public BarcodeRow b() {
        return this.f1749a[this.b];
    }

    public byte[][] c() {
        return a(1, 1);
    }

    public byte[][] a(int i, int i2) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, new int[]{this.c * i2, this.d * i});
        int i3 = this.c * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[(i3 - i4) - 1] = this.f1749a[i4 / i2].a(i);
        }
        return bArr;
    }
}
