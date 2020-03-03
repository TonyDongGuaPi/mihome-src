package com.xiaomi.zxing;

public final class InvertedLuminanceSource extends LuminanceSource {

    /* renamed from: a  reason: collision with root package name */
    private final LuminanceSource f1625a;

    public InvertedLuminanceSource(LuminanceSource luminanceSource) {
        super(luminanceSource.g(), luminanceSource.h());
        this.f1625a = luminanceSource;
    }

    public byte[] a(int i, byte[] bArr) {
        byte[] a2 = this.f1625a.a(i, bArr);
        int g = g();
        for (int i2 = 0; i2 < g; i2++) {
            a2[i2] = (byte) (255 - (a2[i2] & 255));
        }
        return a2;
    }

    public byte[] a() {
        byte[] a2 = this.f1625a.a();
        int g = g() * h();
        byte[] bArr = new byte[g];
        for (int i = 0; i < g; i++) {
            bArr[i] = (byte) (255 - (a2[i] & 255));
        }
        return bArr;
    }

    public boolean b() {
        return this.f1625a.b();
    }

    public LuminanceSource a(int i, int i2, int i3, int i4) {
        return new InvertedLuminanceSource(this.f1625a.a(i, i2, i3, i4));
    }

    public boolean c() {
        return this.f1625a.c();
    }

    public LuminanceSource d() {
        return this.f1625a;
    }

    public LuminanceSource e() {
        return new InvertedLuminanceSource(this.f1625a.e());
    }

    public LuminanceSource f() {
        return new InvertedLuminanceSource(this.f1625a.f());
    }
}
