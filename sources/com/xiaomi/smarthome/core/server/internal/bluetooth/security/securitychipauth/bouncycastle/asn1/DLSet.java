package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class DLSet extends ASN1Set {

    /* renamed from: a  reason: collision with root package name */
    private int f14445a = -1;

    public DLSet() {
    }

    public DLSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DLSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, false);
    }

    public DLSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, false);
    }

    private int h() throws IOException {
        if (this.f14445a < 0) {
            int i = 0;
            Enumeration b = b();
            while (b.hasMoreElements()) {
                i += ((ASN1Encodable) b.nextElement()).k().j().e();
            }
            this.f14445a = i;
        }
        return this.f14445a;
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        int h = h();
        return StreamUtil.a(h) + 1 + h;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        ASN1OutputStream e = aSN1OutputStream.e();
        int h = h();
        aSN1OutputStream.b(49);
        aSN1OutputStream.a(h);
        Enumeration b = b();
        while (b.hasMoreElements()) {
            e.a((ASN1Encodable) b.nextElement());
        }
    }
}
