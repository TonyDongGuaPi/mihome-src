package org.libsodium.jni.keys;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.crypto.Point;
import org.libsodium.jni.crypto.Util;
import org.libsodium.jni.encoders.Encoder;

public class KeyPair {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f3725a;
    private byte[] b;
    private final byte[] c;

    public KeyPair() {
        this.c = Util.a(32);
        this.f3725a = Util.a(32);
        NaCl.a();
        Sodium.k(this.f3725a, this.c);
    }

    public KeyPair(byte[] bArr) {
        Util.a(bArr, 32);
        this.b = bArr;
        this.c = Util.a(32);
        this.f3725a = Util.a(32);
        NaCl.a();
        Util.a(Sodium.e(this.f3725a, this.c, bArr), "Failed to generate a key pair");
    }

    public KeyPair(String str, Encoder encoder) {
        this(encoder.a(str));
    }

    public PublicKey a() {
        return new PublicKey(this.f3725a != null ? this.f3725a : new Point().a(this.c).a());
    }

    public PrivateKey b() {
        return new PrivateKey(this.c);
    }
}
