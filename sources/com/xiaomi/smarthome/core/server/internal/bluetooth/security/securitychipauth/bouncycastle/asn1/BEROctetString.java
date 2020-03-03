package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class BEROctetString extends ASN1OctetString {
    private static final int b = 1000;
    /* access modifiers changed from: private */
    public ASN1OctetString[] c;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    private static byte[] a(ASN1OctetString[] aSN1OctetStringArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i != aSN1OctetStringArr.length) {
            try {
                byteArrayOutputStream.write(aSN1OctetStringArr[i].d());
                i++;
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(aSN1OctetStringArr[i].getClass().getName() + " found in input should only contain DEROctetString");
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public BEROctetString(byte[] bArr) {
        super(bArr);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        super(a(aSN1OctetStringArr));
        this.c = aSN1OctetStringArr;
    }

    public byte[] d() {
        return this.f14407a;
    }

    public Enumeration g() {
        if (this.c == null) {
            return h().elements();
        }
        return new Enumeration() {

            /* renamed from: a  reason: collision with root package name */
            int f14419a = 0;

            public boolean hasMoreElements() {
                return this.f14419a < BEROctetString.this.c.length;
            }

            public Object nextElement() {
                ASN1OctetString[] a2 = BEROctetString.this.c;
                int i = this.f14419a;
                this.f14419a = i + 1;
                return a2[i];
            }
        };
    }

    private Vector h() {
        Vector vector = new Vector();
        int i = 0;
        while (i < this.f14407a.length) {
            int i2 = i + 1000;
            byte[] bArr = new byte[((i2 > this.f14407a.length ? this.f14407a.length : i2) - i)];
            System.arraycopy(this.f14407a, i, bArr, 0, bArr.length);
            vector.addElement(new DEROctetString(bArr));
            i = i2;
        }
        return vector;
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        Enumeration g = g();
        int i = 0;
        while (g.hasMoreElements()) {
            i += ((ASN1Encodable) g.nextElement()).k().e();
        }
        return i + 2 + 2;
    }

    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.b(36);
        aSN1OutputStream.b(128);
        Enumeration g = g();
        while (g.hasMoreElements()) {
            aSN1OutputStream.a((ASN1Encodable) g.nextElement());
        }
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }

    static BEROctetString a(ASN1Sequence aSN1Sequence) {
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[aSN1Sequence.f()];
        Enumeration c2 = aSN1Sequence.c();
        int i = 0;
        while (c2.hasMoreElements()) {
            aSN1OctetStringArr[i] = (ASN1OctetString) c2.nextElement();
            i++;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }
}
