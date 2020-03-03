package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;

public class AEADParameters implements CipherParameters {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14457a;
    private byte[] b;
    private KeyParameter c;
    private int d;

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr) {
        this(keyParameter, i, bArr, (byte[]) null);
    }

    public AEADParameters(KeyParameter keyParameter, int i, byte[] bArr, byte[] bArr2) {
        this.c = keyParameter;
        this.b = bArr;
        this.d = i;
        this.f14457a = bArr2;
    }

    public KeyParameter a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public byte[] c() {
        return this.f14457a;
    }

    public byte[] d() {
        return this.b;
    }
}
