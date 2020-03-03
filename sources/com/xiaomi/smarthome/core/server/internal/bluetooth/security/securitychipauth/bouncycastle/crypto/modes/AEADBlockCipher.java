package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.modes;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.BlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.DataLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.InvalidCipherTextException;

public interface AEADBlockCipher {
    int a(byte b, byte[] bArr, int i) throws DataLengthException;

    int a(int i);

    int a(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException;

    int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;

    String a();

    void a(byte b);

    void a(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

    void a(byte[] bArr, int i, int i2);

    int b(int i);

    BlockCipher b();

    byte[] c();

    void d();
}
