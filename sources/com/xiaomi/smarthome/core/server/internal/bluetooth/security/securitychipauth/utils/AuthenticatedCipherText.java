package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils;

public final class AuthenticatedCipherText {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14465a;
    private final byte[] b;

    public AuthenticatedCipherText(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            this.f14465a = bArr;
            if (bArr2 != null) {
                this.b = bArr2;
                return;
            }
            throw new IllegalArgumentException("The authentication tag must not be null");
        }
        throw new IllegalArgumentException("The cipher text must not be null");
    }

    public byte[] a() {
        return this.f14465a;
    }

    public byte[] b() {
        return this.b;
    }

    public byte[] c() {
        byte[] bArr = new byte[(this.f14465a.length + this.b.length)];
        System.arraycopy(this.f14465a, 0, bArr, 0, this.f14465a.length);
        System.arraycopy(this.b, 0, bArr, this.f14465a.length, this.b.length);
        return bArr;
    }
}
