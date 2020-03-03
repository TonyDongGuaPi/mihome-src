package com.xiaomi.zxing;

public abstract class LuminanceSource {

    /* renamed from: a  reason: collision with root package name */
    private final int f1626a;
    private final int b;

    public abstract byte[] a();

    public abstract byte[] a(int i, byte[] bArr);

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    protected LuminanceSource(int i, int i2) {
        this.f1626a = i;
        this.b = i2;
    }

    public final int g() {
        return this.f1626a;
    }

    public final int h() {
        return this.b;
    }

    public LuminanceSource a(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    public LuminanceSource d() {
        return new InvertedLuminanceSource(this);
    }

    public LuminanceSource e() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public LuminanceSource f() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(this.b * (this.f1626a + 1));
        byte[] bArr = new byte[this.f1626a];
        for (int i = 0; i < this.b; i++) {
            bArr = a(i, bArr);
            for (int i2 = 0; i2 < this.f1626a; i2++) {
                byte b2 = bArr[i2] & 255;
                sb.append(b2 < 64 ? '#' : b2 < 128 ? '+' : b2 < 192 ? '.' : ' ');
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
