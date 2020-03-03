package org.libsodium.jni.crypto;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.encoders.Encoder;

public class Hash {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3719a = 64;
    private static final int b = 32;
    private byte[] c;

    public byte[] a(byte[] bArr) {
        this.c = new byte[32];
        NaCl.a();
        Sodium.l(this.c, bArr, bArr.length);
        return this.c;
    }

    public byte[] b(byte[] bArr) {
        this.c = new byte[64];
        NaCl.a();
        Sodium.n(this.c, bArr, bArr.length);
        return this.c;
    }

    public String a(String str, Encoder encoder) {
        return encoder.a(a(str.getBytes()));
    }

    public String b(String str, Encoder encoder) {
        return encoder.a(b(str.getBytes()));
    }

    public String a(String str, Encoder encoder, byte[] bArr, int i, int i2) {
        this.c = new byte[64];
        NaCl.a();
        byte[] bArr2 = this.c;
        int length = this.c.length;
        byte[] bytes = str.getBytes();
        int length2 = str.length();
        NaCl.a();
        Sodium.a(bArr2, length, bytes, length2, bArr, i, i2, Sodium.aO());
        return encoder.a(this.c);
    }

    public byte[] c(byte[] bArr) throws UnsupportedOperationException {
        if (a()) {
            this.c = new byte[64];
            NaCl.a();
            Sodium.b(this.c, 64, bArr, bArr.length, new byte[0], 0);
            return this.c;
        }
        throw new UnsupportedOperationException();
    }

    public String c(String str, Encoder encoder) throws UnsupportedOperationException {
        if (a()) {
            return encoder.a(c(str.getBytes()));
        }
        throw new UnsupportedOperationException();
    }

    private boolean a() {
        return new String("0.4.1").compareTo("0.4.0") >= 0;
    }
}
