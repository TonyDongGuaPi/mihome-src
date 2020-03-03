package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;

public class ParametersWithIV implements CipherParameters {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14459a;
    private CipherParameters b;

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr) {
        this(cipherParameters, bArr, 0, bArr.length);
    }

    public ParametersWithIV(CipherParameters cipherParameters, byte[] bArr, int i, int i2) {
        this.f14459a = new byte[i2];
        this.b = cipherParameters;
        System.arraycopy(bArr, i, this.f14459a, 0, i2);
    }

    public byte[] a() {
        return this.f14459a;
    }

    public CipherParameters b() {
        return this.b;
    }
}
