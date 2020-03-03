package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public interface InMemoryRepresentable {
    ASN1Primitive f() throws IOException;
}
