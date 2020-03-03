package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DERBitString extends ASN1BitString {
    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERBitString a(Object obj) {
        if (obj == null || (obj instanceof DERBitString)) {
            return (DERBitString) obj;
        }
        if (obj instanceof DLBitString) {
            DLBitString dLBitString = (DLBitString) obj;
            return new DERBitString(dLBitString.f14398a, dLBitString.b);
        } else if (obj instanceof byte[]) {
            try {
                return (DERBitString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERBitString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERBitString)) {
            return a((Object) g);
        }
        return a(((ASN1OctetString) g).d());
    }

    protected DERBitString(byte b, int i) {
        this(a(b), i);
    }

    private static byte[] a(byte b) {
        return new byte[]{b};
    }

    public DERBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(int i) {
        super(b(i), a(i));
    }

    public DERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.k().a(ASN1Encoding.f14401a), 0);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14398a.length + 1) + 1 + this.f14398a.length + 1;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] a2 = a(this.f14398a, this.b);
        byte[] bArr = new byte[(a2.length + 1)];
        bArr[0] = (byte) g();
        System.arraycopy(a2, 0, bArr, 1, bArr.length - 1);
        aSN1OutputStream.a(3, bArr);
    }

    static DERBitString a(byte[] bArr) {
        if (bArr.length >= 1) {
            byte b = bArr[0];
            byte[] bArr2 = new byte[(bArr.length - 1)];
            if (bArr2.length != 0) {
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
            }
            return new DERBitString(bArr2, (int) b);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }
}
