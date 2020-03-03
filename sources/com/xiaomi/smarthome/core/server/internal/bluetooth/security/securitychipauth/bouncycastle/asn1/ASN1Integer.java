package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;

public class ASN1Integer extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14404a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static ASN1Integer a(Object obj) {
        if (obj == null || (obj instanceof ASN1Integer)) {
            return (ASN1Integer) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Integer) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Integer a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1Integer)) {
            return a((Object) g);
        }
        return new ASN1Integer(ASN1OctetString.a((Object) aSN1TaggedObject.g()).d());
    }

    public ASN1Integer(long j) {
        this.f14404a = BigInteger.valueOf(j).toByteArray();
    }

    public ASN1Integer(BigInteger bigInteger) {
        this.f14404a = bigInteger.toByteArray();
    }

    public ASN1Integer(byte[] bArr) {
        this(bArr, true);
    }

    ASN1Integer(byte[] bArr, boolean z) {
        if (bArr.length > 1) {
            if (bArr[0] == 0 && (bArr[1] & 128) == 0) {
                throw new IllegalArgumentException("malformed integer");
            } else if (bArr[0] == -1 && (bArr[1] & 128) != 0) {
                throw new IllegalArgumentException("malformed integer");
            }
        }
        this.f14404a = z ? Arrays.b(bArr) : bArr;
    }

    public BigInteger b() {
        return new BigInteger(this.f14404a);
    }

    public BigInteger c() {
        return new BigInteger(1, this.f14404a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14404a.length) + 1 + this.f14404a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(2, this.f14404a);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 != this.f14404a.length; i2++) {
            i ^= (this.f14404a[i2] & 255) << (i2 % 4);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Integer)) {
            return false;
        }
        return Arrays.a(this.f14404a, ((ASN1Integer) aSN1Primitive).f14404a);
    }

    public String toString() {
        return b().toString();
    }
}
