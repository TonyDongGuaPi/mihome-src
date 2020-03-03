package org.libsodium.jni.crypto;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.encoders.Encoder;

public class SecretBox {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f3722a;

    public SecretBox(byte[] bArr) {
        this.f3722a = bArr;
        Util.a(bArr, 32);
    }

    public SecretBox(String str, Encoder encoder) {
        this(encoder.a(str));
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        Util.a(bArr, 24);
        byte[] a2 = Util.a(32, bArr2);
        byte[] a3 = Util.a(a2.length);
        NaCl.a();
        Util.a(Sodium.j(a3, a2, a2.length, bArr, this.f3722a), "Encryption failed");
        return Util.b(16, a3);
    }

    public byte[] b(byte[] bArr, byte[] bArr2) {
        Util.a(bArr, 24);
        byte[] a2 = Util.a(16, bArr2);
        byte[] a3 = Util.a(a2.length);
        NaCl.a();
        Util.a(Sodium.k(a3, a2, a2.length, bArr, this.f3722a), "Decryption failed. Ciphertext failed verification");
        return Util.b(32, a3);
    }
}
