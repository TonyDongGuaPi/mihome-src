package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.rc4;

public abstract class StreamCipher extends Cipher {
    public abstract byte a(byte b);

    public abstract byte b(byte b);

    public StreamCipher(int i) {
        super(i);
    }

    public void c(byte[] bArr, byte[] bArr2) {
        c(bArr, 0, bArr2, 0, bArr.length);
    }

    public void d(byte[] bArr, byte[] bArr2) {
        d(bArr, 0, bArr2, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i2 + i4] = a(bArr[i + i4]);
        }
    }

    public void d(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr2[i2 + i4] = b(bArr[i + i4]);
        }
    }
}
