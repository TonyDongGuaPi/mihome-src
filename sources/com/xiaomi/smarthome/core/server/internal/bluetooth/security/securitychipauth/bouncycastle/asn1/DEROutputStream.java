package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class DEROutputStream extends ASN1OutputStream {
    /* access modifiers changed from: package-private */
    public ASN1OutputStream d() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ASN1OutputStream e() {
        return this;
    }

    public DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void a(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            aSN1Encodable.k().i().a((ASN1OutputStream) this);
            return;
        }
        throw new IOException("null object detected");
    }
}
