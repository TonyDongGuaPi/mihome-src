package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DERSetParser implements ASN1SetParser {

    /* renamed from: a  reason: collision with root package name */
    private ASN1StreamParser f14439a;

    DERSetParser(ASN1StreamParser aSN1StreamParser) {
        this.f14439a = aSN1StreamParser;
    }

    public ASN1Encodable a() throws IOException {
        return this.f14439a.a();
    }

    public ASN1Primitive f() throws IOException {
        return new DERSet(this.f14439a.b(), false);
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
