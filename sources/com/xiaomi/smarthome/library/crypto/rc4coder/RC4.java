package com.xiaomi.smarthome.library.crypto.rc4coder;

final class RC4 {

    /* renamed from: a  reason: collision with root package name */
    private int f19092a;
    private int b;
    private final byte[] c = new byte[256];

    public RC4(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < 256; i++) {
            this.c[i] = (byte) i;
        }
        byte b2 = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b2 = (b2 + bArr[i2 % length] + this.c[i2]) & 255;
            byte b3 = this.c[i2];
            this.c[i2] = this.c[b2];
            this.c[b2] = b3;
        }
        this.f19092a = 0;
        this.b = 0;
    }

    public byte a() {
        this.f19092a = (this.f19092a + 1) & 255;
        this.b = (this.b + this.c[this.f19092a]) & 255;
        byte b2 = this.c[this.f19092a];
        this.c[this.f19092a] = this.c[this.b];
        this.c[this.b] = b2;
        return this.c[(this.c[this.f19092a] + this.c[this.b]) & 255];
    }

    public void a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ a());
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            bArr[i] = (byte) (bArr[i] ^ a());
            i++;
        }
    }
}
