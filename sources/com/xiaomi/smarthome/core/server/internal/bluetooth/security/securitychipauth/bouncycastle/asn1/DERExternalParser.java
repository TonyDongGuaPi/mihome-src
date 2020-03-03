package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DERExternalParser implements ASN1Encodable, InMemoryRepresentable {

    /* renamed from: a  reason: collision with root package name */
    private ASN1StreamParser f14428a;

    public DERExternalParser(ASN1StreamParser aSN1StreamParser) {
        this.f14428a = aSN1StreamParser;
    }

    public ASN1Encodable a() throws IOException {
        return this.f14428a.a();
    }

    public ASN1Primitive f() throws IOException {
        try {
            return new DERExternal(this.f14428a.b());
        } catch (IllegalArgumentException e) {
            throw new ASN1Exception(e.getMessage(), e);
        }
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new ASN1ParsingException("unable to get DER object", e);
        } catch (IllegalArgumentException e2) {
            throw new ASN1ParsingException("unable to get DER object", e2);
        }
    }
}
