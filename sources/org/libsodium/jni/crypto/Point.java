package org.libsodium.jni.crypto;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;
import org.libsodium.jni.encoders.Encoder;

public class Point {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3720a = "0900000000000000000000000000000000000000000000000000000000000000";
    private final byte[] b;

    public Point() {
        this.b = Encoder.b.a(f3720a);
    }

    public Point(byte[] bArr) {
        this.b = bArr;
    }

    public Point(String str, Encoder encoder) {
        this(encoder.a(str));
    }

    public Point a(byte[] bArr) {
        byte[] a2 = Util.a(32);
        NaCl.a();
        Sodium.g(a2, bArr, this.b);
        return new Point(a2);
    }

    public Point a(String str, Encoder encoder) {
        return a(encoder.a(str));
    }

    public String toString() {
        return Encoder.b.a(this.b);
    }

    public byte[] a() {
        return this.b;
    }
}
