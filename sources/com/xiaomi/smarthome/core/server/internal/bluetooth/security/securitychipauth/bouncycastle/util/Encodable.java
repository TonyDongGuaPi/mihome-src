package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util;

import java.io.IOException;

public interface Encodable {
    byte[] l() throws IOException;
}
