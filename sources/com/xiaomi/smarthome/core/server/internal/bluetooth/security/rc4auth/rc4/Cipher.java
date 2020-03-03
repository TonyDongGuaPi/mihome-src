package com.xiaomi.smarthome.core.server.internal.bluetooth.security.rc4auth.rc4;

public abstract class Cipher extends CryptoUtils {

    /* renamed from: a  reason: collision with root package name */
    public int f14346a;

    public abstract void a(byte[] bArr);

    public Cipher(int i) {
        this.f14346a = i;
    }

    public int a() {
        return this.f14346a;
    }

    public void a(String str) {
        a(b(str));
    }

    public byte[] b(String str) {
        byte[] bArr;
        if (this.f14346a == 0) {
            bArr = new byte[str.length()];
        } else {
            bArr = new byte[this.f14346a];
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = 0;
        }
        int i3 = 0;
        while (i < str.length()) {
            bArr[i3] = (byte) (bArr[i3] ^ ((byte) str.charAt(i)));
            i++;
            i3 = (i3 + 1) % bArr.length;
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }
}
