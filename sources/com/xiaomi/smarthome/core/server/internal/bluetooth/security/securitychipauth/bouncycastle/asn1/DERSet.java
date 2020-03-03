package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class DERSet extends ASN1Set {

    /* renamed from: a  reason: collision with root package name */
    private int f14438a = -1;

    public DERSet() {
    }

    public DERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, true);
    }

    public DERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, true);
    }

    DERSet(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        super(aSN1EncodableVector, z);
    }

    private int h() throws IOException {
        if (this.f14438a < 0) {
            int i = 0;
            Enumeration b = b();
            while (b.hasMoreElements()) {
                i += ((ASN1Encodable) b.nextElement()).k().i().e();
            }
            this.f14438a = i;
        }
        return this.f14438a;
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        int h = h();
        return StreamUtil.a(h) + 1 + h;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        ASN1OutputStream d = aSN1OutputStream.d();
        int h = h();
        aSN1OutputStream.b(49);
        aSN1OutputStream.a(h);
        Enumeration b = b();
        while (b.hasMoreElements()) {
            d.a((ASN1Encodable) b.nextElement());
        }
    }
}
