package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class BERApplicationSpecificParser implements ASN1ApplicationSpecificParser {

    /* renamed from: a  reason: collision with root package name */
    private final int f14417a;
    private final ASN1StreamParser b;

    BERApplicationSpecificParser(int i, ASN1StreamParser aSN1StreamParser) {
        this.f14417a = i;
        this.b = aSN1StreamParser;
    }

    public ASN1Encodable a() throws IOException {
        return this.b.a();
    }

    public ASN1Primitive f() throws IOException {
        return new BERApplicationSpecific(this.f14417a, this.b.b());
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
