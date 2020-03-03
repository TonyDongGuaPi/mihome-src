package org.libsodium.jni.keys;

import org.libsodium.jni.crypto.Util;
import org.libsodium.jni.encoders.Encoder;

public class PrivateKey {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f3726a;

    public PrivateKey(byte[] bArr) {
        this.f3726a = bArr;
        Util.a(bArr, 32);
    }

    public PrivateKey(String str) {
        this.f3726a = Encoder.b.a(str);
        Util.a(this.f3726a, 32);
    }

    public byte[] a() {
        return this.f3726a;
    }

    public String toString() {
        return Encoder.b.a(this.f3726a);
    }
}
