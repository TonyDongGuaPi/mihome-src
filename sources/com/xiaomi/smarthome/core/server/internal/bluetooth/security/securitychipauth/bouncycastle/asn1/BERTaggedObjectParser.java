package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class BERTaggedObjectParser implements ASN1TaggedObjectParser {

    /* renamed from: a  reason: collision with root package name */
    private boolean f14423a;
    private int b;
    private ASN1StreamParser c;

    BERTaggedObjectParser(boolean z, int i, ASN1StreamParser aSN1StreamParser) {
        this.f14423a = z;
        this.b = i;
        this.c = aSN1StreamParser;
    }

    public boolean a() {
        return this.f14423a;
    }

    public int b() {
        return this.b;
    }

    public ASN1Encodable a(int i, boolean z) throws IOException {
        if (!z) {
            return this.c.a(this.f14423a, i);
        }
        if (this.f14423a) {
            return this.c.a();
        }
        throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
    }

    public ASN1Primitive f() throws IOException {
        return this.c.b(this.f14423a, this.b);
    }

    public ASN1Primitive k() {
        try {
            return f();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }
}
