package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.rc4;

public class Rc4Cipher extends StreamCipher {
    private byte[] b = new byte[256];
    private int c;
    private int d;

    public Rc4Cipher(String str) {
        super(256);
        a(str);
    }

    public Rc4Cipher(byte[] bArr) {
        super(256);
        a(bArr);
    }

    public void a(byte[] bArr) {
        this.c = 0;
        this.d = 0;
        for (int i = 0; i < 256; i++) {
            this.b[i] = (byte) i;
        }
        byte b2 = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b2 = (b2 + this.b[i2] + bArr[i2 % bArr.length]) & 255;
            a(this.b, i2, b2);
        }
    }

    public byte a(byte b2) {
        return (byte) (b2 ^ this.b[b()]);
    }

    public byte b(byte b2) {
        return (byte) (b2 ^ this.b[b()]);
    }

    public void c(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i2 + i4] = (byte) (bArr[i + i4] ^ this.b[b()]);
        }
    }

    public void d(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i2 + i4] = (byte) (bArr[i + i4] ^ this.b[b()]);
        }
    }

    private int b() {
        this.c = (this.c + 1) & 255;
        this.d = (this.d + this.b[this.c]) & 255;
        a(this.b, this.c, this.d);
        return (this.b[this.c] + this.b[this.d]) & 255;
    }
}
