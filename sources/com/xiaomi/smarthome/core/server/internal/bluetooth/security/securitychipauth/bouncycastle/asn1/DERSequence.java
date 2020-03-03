package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class DERSequence extends ASN1Sequence {
    private int b = -1;

    public DERSequence() {
    }

    public DERSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DERSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    public DERSequence(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr);
    }

    private int g() throws IOException {
        if (this.b < 0) {
            int i = 0;
            Enumeration c = c();
            while (c.hasMoreElements()) {
                i += ((ASN1Encodable) c.nextElement()).k().i().e();
            }
            this.b = i;
        }
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        int g = g();
        return StreamUtil.a(g) + 1 + g;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        ASN1OutputStream d = aSN1OutputStream.d();
        int g = g();
        aSN1OutputStream.b(48);
        aSN1OutputStream.a(g);
        Enumeration c = c();
        while (c.hasMoreElements()) {
            d.a((ASN1Encodable) c.nextElement());
        }
    }
}
