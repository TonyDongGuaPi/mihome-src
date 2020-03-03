package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DERSequenceParser implements ASN1SequenceParser {

    /* renamed from: a  reason: collision with root package name */
    private ASN1StreamParser f14437a;

    DERSequenceParser(ASN1StreamParser aSN1StreamParser) {
        this.f14437a = aSN1StreamParser;
    }

    public ASN1Encodable a() throws IOException {
        return this.f14437a.a();
    }

    public ASN1Primitive f() throws IOException {
        return new DERSequence(this.f14437a.b());
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
