package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DLBitString extends ASN1BitString {
    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static ASN1BitString a(Object obj) {
        if (obj == null || (obj instanceof DLBitString)) {
            return (DLBitString) obj;
        }
        if (obj instanceof DERBitString) {
            return (DERBitString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1BitString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1BitString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DLBitString)) {
            return a((Object) g);
        }
        return a(((ASN1OctetString) g).d());
    }

    protected DLBitString(byte b, int i) {
        this(a(b), i);
    }

    private static byte[] a(byte b) {
        return new byte[]{b};
    }

    public DLBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    public DLBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DLBitString(int i) {
        super(b(i), a(i));
    }

    public DLBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.k().a(ASN1Encoding.f14401a), 0);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14398a.length + 1) + 1 + this.f14398a.length + 1;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArr = this.f14398a;
        byte[] bArr2 = new byte[(bArr.length + 1)];
        bArr2[0] = (byte) g();
        System.arraycopy(bArr, 0, bArr2, 1, bArr2.length - 1);
        aSN1OutputStream.a(3, bArr2);
    }

    static DLBitString a(byte[] bArr) {
        if (bArr.length >= 1) {
            byte b = bArr[0];
            byte[] bArr2 = new byte[(bArr.length - 1)];
            if (bArr2.length != 0) {
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
            }
            return new DLBitString(bArr2, (int) b);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }
}
