package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class BERSet extends ASN1Set {
    public BERSet() {
    }

    public BERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, false);
    }

    public BERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, false);
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        Enumeration b = b();
        int i = 0;
        while (b.hasMoreElements()) {
            i += ((ASN1Encodable) b.nextElement()).k().e();
        }
        return i + 2 + 2;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.b(49);
        aSN1OutputStream.b(128);
        Enumeration b = b();
        while (b.hasMoreElements()) {
            aSN1OutputStream.a((ASN1Encodable) b.nextElement());
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
