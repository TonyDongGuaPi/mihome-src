package org.libsodium.jni.keys;

import org.libsodium.jni.crypto.Util;
import org.libsodium.jni.encoders.Encoder;

public class PublicKey {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f3727a;

    public PublicKey(byte[] bArr) {
        this.f3727a = bArr;
        Util.a(bArr, 32);
    }

    public PublicKey(String str) {
        this.f3727a = Encoder.b.a(str);
    }

    public byte[] a() {
        return this.f3727a;
    }

    public String toString() {
        return Encoder.b.a(this.f3727a);
    }
}
