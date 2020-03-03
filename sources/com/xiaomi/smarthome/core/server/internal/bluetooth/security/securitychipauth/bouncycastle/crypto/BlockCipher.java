package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto;

public interface BlockCipher {
    int a(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException;

    String a();

    void a(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

    int b();

    void c();
}
