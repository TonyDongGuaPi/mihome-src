package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    public BERTaggedObject(int i) {
        super(false, i, new BERSequence());
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        if (this.b || this.c) {
            return true;
        }
        return this.d.k().i().a();
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        if (this.b) {
            return StreamUtil.b(this.f14415a) + 1;
        }
        int e = this.d.k().e();
        if (this.c) {
            return StreamUtil.b(this.f14415a) + StreamUtil.a(e) + e;
        }
        return StreamUtil.b(this.f14415a) + (e - 1);
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        Enumeration enumeration;
        aSN1OutputStream.a(160, this.f14415a);
        aSN1OutputStream.b(128);
        if (!this.b) {
            if (!this.c) {
                if (this.d instanceof ASN1OctetString) {
                    if (this.d instanceof BEROctetString) {
                        enumeration = ((BEROctetString) this.d).g();
                    } else {
                        enumeration = new BEROctetString(((ASN1OctetString) this.d).d()).g();
                    }
                } else if (this.d instanceof ASN1Sequence) {
                    enumeration = ((ASN1Sequence) this.d).c();
                } else if (this.d instanceof ASN1Set) {
                    enumeration = ((ASN1Set) this.d).b();
                } else {
                    throw new ASN1Exception("not implemented: " + this.d.getClass().getName());
                }
                while (enumeration.hasMoreElements()) {
                    aSN1OutputStream.a((ASN1Encodable) enumeration.nextElement());
                }
            } else {
                aSN1OutputStream.a(this.d);
            }
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
