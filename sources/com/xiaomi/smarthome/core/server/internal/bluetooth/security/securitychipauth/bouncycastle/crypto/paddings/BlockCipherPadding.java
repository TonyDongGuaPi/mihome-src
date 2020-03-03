package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.paddings;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

public interface BlockCipherPadding {
    int a(byte[] bArr) throws InvalidCipherTextException;

    int a(byte[] bArr, int i);

    String a();

    void a(SecureRandom secureRandom) throws IllegalArgumentException;
}
