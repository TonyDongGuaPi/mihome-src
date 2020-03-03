package com.xiaomi.youpin.common.util.crypto.rc4coder;

final class RC4 {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f23299a = new byte[256];
    private int b;
    private int c;

    public RC4(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < 256; i++) {
            this.f23299a[i] = (byte) i;
        }
        byte b2 = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b2 = (b2 + bArr[i2 % length] + this.f23299a[i2]) & 255;
            byte b3 = this.f23299a[i2];
            this.f23299a[i2] = this.f23299a[b2];
            this.f23299a[b2] = b3;
        }
        this.b = 0;
        this.c = 0;
    }

    public byte a() {
        this.b = (this.b + 1) & 255;
        this.c = (this.c + this.f23299a[this.b]) & 255;
        byte b2 = this.f23299a[this.b];
        this.f23299a[this.b] = this.f23299a[this.c];
        this.f23299a[this.c] = b2;
        return this.f23299a[(this.f23299a[this.b] + this.f23299a[this.c]) & 255];
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
