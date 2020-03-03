package com.xiaomi.zxing.qrcode.encoder;

import java.lang.reflect.Array;

public final class ByteMatrix {

    /* renamed from: a  reason: collision with root package name */
    private final byte[][] f1773a;
    private final int b;
    private final int c;

    public ByteMatrix(int i, int i2) {
        this.f1773a = (byte[][]) Array.newInstance(byte.class, new int[]{i2, i});
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public byte a(int i, int i2) {
        return this.f1773a[i2][i];
    }

    public byte[][] c() {
        return this.f1773a;
    }

    public void a(int i, int i2, byte b2) {
        this.f1773a[i2][i] = b2;
    }

    public void a(int i, int i2, int i3) {
        this.f1773a[i2][i] = (byte) i3;
    }

    public void a(int i, int i2, boolean z) {
        this.f1773a[i2][i] = z ? (byte) 1 : 0;
    }

    public void a(byte b2) {
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                this.f1773a[i][i2] = b2;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.b * 2 * this.c) + 2);
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                switch (this.f1773a[i][i2]) {
                    case 0:
                        sb.append(" 0");
                        break;
                    case 1:
                        sb.append(" 1");
                        break;
                    default:
                        sb.append("  ");
                        break;
                }
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
