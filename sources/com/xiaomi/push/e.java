package com.xiaomi.push;

import java.io.IOException;

public abstract class e {
    public abstract int a();

    public abstract e a(b bVar);

    public e a(byte[] bArr) {
        return b(bArr, 0, bArr.length);
    }

    public abstract void a(c cVar);

    public void a(byte[] bArr, int i, int i2) {
        try {
            c a2 = c.a(bArr, i, i2);
            a(a2);
            a2.c();
        } catch (IOException unused) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).");
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(b bVar, int i) {
        return bVar.b(i);
    }

    public abstract int b();

    public e b(byte[] bArr, int i, int i2) {
        try {
            b a2 = b.a(bArr, i, i2);
            a(a2);
            a2.a(0);
            return this;
        } catch (d e) {
            throw e;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public byte[] c() {
        byte[] bArr = new byte[b()];
        a(bArr, 0, bArr.length);
        return bArr;
    }
}
