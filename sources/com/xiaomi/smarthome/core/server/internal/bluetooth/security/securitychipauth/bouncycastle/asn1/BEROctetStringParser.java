package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.io.Streams;
import java.io.IOException;
import java.io.InputStream;

public class BEROctetStringParser implements ASN1OctetStringParser {

    /* renamed from: a  reason: collision with root package name */
    private ASN1StreamParser f14420a;

    BEROctetStringParser(ASN1StreamParser aSN1StreamParser) {
        this.f14420a = aSN1StreamParser;
    }

    public InputStream b() {
        return new ConstructedOctetStream(this.f14420a);
    }

    public ASN1Primitive f() throws IOException {
        return new BEROctetString(Streams.b(b()));
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
