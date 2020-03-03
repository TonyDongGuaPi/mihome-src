package com.xiaomi.zxing.pdf417.encoder;

final class BarcodeRow {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1750a;
    private int b = 0;

    BarcodeRow(int i) {
        this.f1750a = new byte[i];
    }

    /* access modifiers changed from: package-private */
    public void a(int i, byte b2) {
        this.f1750a[i] = b2;
    }

    /* access modifiers changed from: package-private */
    public void a(int i, boolean z) {
        this.f1750a[i] = z ? (byte) 1 : 0;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.b;
            this.b = i3 + 1;
            a(i3, z);
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] a(int i) {
        byte[] bArr = new byte[(this.f1750a.length * i)];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = this.f1750a[i2 / i];
        }
        return bArr;
    }
}
