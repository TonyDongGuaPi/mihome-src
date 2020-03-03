package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

public class DEROctetStringParser implements ASN1OctetStringParser {

    /* renamed from: a  reason: collision with root package name */
    private DefiniteLengthInputStream f14435a;

    DEROctetStringParser(DefiniteLengthInputStream definiteLengthInputStream) {
        this.f14435a = definiteLengthInputStream;
    }

    public InputStream b() {
        return this.f14435a;
    }

    public ASN1Primitive f() throws IOException {
        return new DEROctetString(this.f14435a.b());
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
