package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class BERSequence extends ASN1Sequence {
    public BERSequence() {
    }

    public BERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    public BERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        Enumeration c = c();
        int i = 0;
        while (c.hasMoreElements()) {
            i += ((ASN1Encodable) c.nextElement()).k().e();
        }
        return i + 2 + 2;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.b(48);
        aSN1OutputStream.b(128);
        Enumeration c = c();
        while (c.hasMoreElements()) {
            aSN1OutputStream.a((ASN1Encodable) c.nextElement());
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
