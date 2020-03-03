package org.libsodium.jni.crypto;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.encoders.Encoder;
import org.libsodium.jni.keys.PrivateKey;
import org.libsodium.jni.keys.PublicKey;

public class Box {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f3718a;
    private final byte[] b;

    public Box(byte[] bArr, byte[] bArr2) {
        this.b = bArr;
        this.f3718a = bArr2;
        Util.a(bArr, 32);
        Util.a(bArr2, 32);
    }

    public Box(PublicKey publicKey, PrivateKey privateKey) {
        this(publicKey.a(), privateKey.a());
    }

    public Box(String str, String str2, Encoder encoder) {
        this(encoder.a(str), encoder.a(str2));
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        Util.a(bArr, 24);
        byte[] a2 = Util.a(32, bArr2);
        byte[] bArr3 = new byte[a2.length];
        NaCl.a();
        Util.a(Sodium.e(bArr3, a2, a2.length, bArr, this.b, this.f3718a), "Encryption failed");
        return Util.b(16, bArr3);
    }

    public byte[] a(String str, String str2, Encoder encoder) {
        return a(encoder.a(str), encoder.a(str2));
    }

    public byte[] b(byte[] bArr, byte[] bArr2) {
        Util.a(bArr, 24);
        byte[] a2 = Util.a(16, bArr2);
        byte[] bArr3 = new byte[a2.length];
        NaCl.a();
        Util.a(Sodium.f(bArr3, a2, bArr3.length, bArr, this.b, this.f3718a), "Decryption failed. Ciphertext failed verification.");
        return Util.b(32, bArr3);
    }

    public byte[] b(String str, String str2, Encoder encoder) {
        return b(encoder.a(str), encoder.a(str2));
    }
}
