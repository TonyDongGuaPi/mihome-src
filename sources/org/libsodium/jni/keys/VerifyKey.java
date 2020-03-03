package org.libsodium.jni.keys;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.crypto.Util;
import org.libsodium.jni.encoders.Encoder;

public class VerifyKey {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f3729a;

    public VerifyKey(byte[] bArr) {
        Util.a(bArr, 32);
        this.f3729a = bArr;
    }

    public VerifyKey(String str, Encoder encoder) {
        this(encoder.a(str));
    }

    public boolean a(byte[] bArr, byte[] bArr2) {
        Util.a(bArr2, 64);
        byte[] a2 = Util.a(bArr2, bArr);
        NaCl.a();
        return Util.a(Sodium.e(Util.a(a2.length), new int[1], a2, a2.length, this.f3729a), "signature was forged or corrupted");
    }

    public boolean a(String str, String str2, Encoder encoder) {
        return a(encoder.a(str), encoder.a(str2));
    }

    public byte[] a() {
        return this.f3729a;
    }

    public String toString() {
        return Encoder.b.a(this.f3729a);
    }
}
