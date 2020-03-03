package org.libsodium.jni.keys;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.crypto.Random;
import org.libsodium.jni.crypto.Util;
import org.libsodium.jni.encoders.Encoder;

public class SigningKey {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f3728a;
    private final byte[] b;
    private VerifyKey c;

    public SigningKey(byte[] bArr) {
        Util.a(bArr, 32);
        this.f3728a = bArr;
        this.b = Util.a(64);
        byte[] a2 = Util.a(32);
        NaCl.a();
        Util.a(Sodium.h(a2, this.b, bArr), "Failed to generate a key pair");
        this.c = new VerifyKey(a2);
    }

    public SigningKey() {
        this(new Random().a(32));
    }

    public SigningKey(String str, Encoder encoder) {
        this(encoder.a(str));
    }

    public VerifyKey a() {
        return this.c;
    }

    public byte[] a(byte[] bArr) {
        byte[] a2 = Util.a(64, bArr);
        NaCl.a();
        Sodium.d(a2, new int[1], bArr, bArr.length, this.b);
        return Util.a(a2, 0, 64);
    }

    public String a(String str, Encoder encoder) {
        return encoder.a(a(encoder.a(str)));
    }

    public byte[] b() {
        return this.f3728a;
    }

    public String toString() {
        return Encoder.b.a(this.f3728a);
    }
}
