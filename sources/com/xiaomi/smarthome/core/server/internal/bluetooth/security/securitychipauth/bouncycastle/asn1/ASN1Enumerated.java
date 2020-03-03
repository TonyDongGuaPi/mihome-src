package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;

public class ASN1Enumerated extends ASN1Primitive {
    private static ASN1Enumerated[] b = new ASN1Enumerated[12];

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14402a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static ASN1Enumerated a(Object obj) {
        if (obj == null || (obj instanceof ASN1Enumerated)) {
            return (ASN1Enumerated) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Enumerated) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Enumerated a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1Enumerated)) {
            return a((Object) g);
        }
        return a(((ASN1OctetString) g).d());
    }

    public ASN1Enumerated(int i) {
        this.f14402a = BigInteger.valueOf((long) i).toByteArray();
    }

    public ASN1Enumerated(BigInteger bigInteger) {
        this.f14402a = bigInteger.toByteArray();
    }

    public ASN1Enumerated(byte[] bArr) {
        if (bArr.length > 1) {
            if (bArr[0] == 0 && (bArr[1] & 128) == 0) {
                throw new IllegalArgumentException("malformed enumerated");
            } else if (bArr[0] == -1 && (bArr[1] & 128) != 0) {
                throw new IllegalArgumentException("malformed enumerated");
            }
        }
        this.f14402a = Arrays.b(bArr);
    }

    public BigInteger b() {
        return new BigInteger(this.f14402a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14402a.length) + 1 + this.f14402a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(10, this.f14402a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Enumerated)) {
            return false;
        }
        return Arrays.a(this.f14402a, ((ASN1Enumerated) aSN1Primitive).f14402a);
    }

    public int hashCode() {
        return Arrays.a(this.f14402a);
    }

    static ASN1Enumerated a(byte[] bArr) {
        if (bArr.length > 1) {
            return new ASN1Enumerated(bArr);
        }
        if (bArr.length != 0) {
            byte b2 = bArr[0] & 255;
            if (b2 >= b.length) {
                return new ASN1Enumerated(Arrays.b(bArr));
            }
            ASN1Enumerated aSN1Enumerated = b[b2];
            if (aSN1Enumerated != null) {
                return aSN1Enumerated;
            }
            ASN1Enumerated[] aSN1EnumeratedArr = b;
            ASN1Enumerated aSN1Enumerated2 = new ASN1Enumerated(Arrays.b(bArr));
            aSN1EnumeratedArr[b2] = aSN1Enumerated2;
            return aSN1Enumerated2;
        }
        throw new IllegalArgumentException("ENUMERATED has zero length");
    }
}
