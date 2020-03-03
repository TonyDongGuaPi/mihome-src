package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto;

public interface Mac {
    int a(byte[] bArr, int i) throws DataLengthException, IllegalStateException;

    String a();

    void a(byte b) throws IllegalStateException;

    void a(CipherParameters cipherParameters) throws IllegalArgumentException;

    void a(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException;

    int b();

    void c();
}
