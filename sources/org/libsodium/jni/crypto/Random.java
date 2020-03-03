package org.libsodium.jni.crypto;

import org.libsodium.jni.NaCl;
import org.libsodium.jni.Sodium;

public class Random {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3721a = 32;

    public byte[] a(int i) {
        byte[] bArr = new byte[i];
        NaCl.a();
        Sodium.a(bArr, i);
        return bArr;
    }

    public byte[] a() {
        byte[] bArr = new byte[32];
        NaCl.a();
        Sodium.a(bArr, 32);
        return bArr;
    }
}
