package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;

public class KeyParameter implements CipherParameters {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14458a;

    public KeyParameter(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public KeyParameter(byte[] bArr, int i, int i2) {
        this.f14458a = new byte[i2];
        System.arraycopy(bArr, i, this.f14458a, 0, i2);
    }

    public byte[] a() {
        return this.f14458a;
    }
}
