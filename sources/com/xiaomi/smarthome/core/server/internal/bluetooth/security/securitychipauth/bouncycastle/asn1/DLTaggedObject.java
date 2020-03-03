package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DLTaggedObject extends ASN1TaggedObject {
    private static final byte[] e = new byte[0];

    public DLTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        if (this.b || this.c) {
            return true;
        }
        return this.d.k().j().a();
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        if (this.b) {
            return StreamUtil.b(this.f14415a) + 1;
        }
        int e2 = this.d.k().j().e();
        if (this.c) {
            return StreamUtil.b(this.f14415a) + StreamUtil.a(e2) + e2;
        }
        return StreamUtil.b(this.f14415a) + (e2 - 1);
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        int i = 160;
        if (!this.b) {
            ASN1Primitive j = this.d.k().j();
            if (this.c) {
                aSN1OutputStream.a(160, this.f14415a);
                aSN1OutputStream.a(j.e());
                aSN1OutputStream.a((ASN1Encodable) j);
                return;
            }
            if (!j.a()) {
                i = 128;
            }
            aSN1OutputStream.a(i, this.f14415a);
            aSN1OutputStream.a(j);
            return;
        }
        aSN1OutputStream.a(160, this.f14415a, e);
    }
}
