package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

public interface Encoder {
    int a(String str, OutputStream outputStream) throws IOException;

    int a(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;

    int b(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException;
}
