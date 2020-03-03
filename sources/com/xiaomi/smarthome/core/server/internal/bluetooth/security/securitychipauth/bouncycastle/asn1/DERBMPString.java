package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.IOException;

public class DERBMPString extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final char[] f14426a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERBMPString a(Object obj) {
        if (obj == null || (obj instanceof DERBMPString)) {
            return (DERBMPString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERBMPString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERBMPString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERBMPString)) {
            return a((Object) g);
        }
        return new DERBMPString(ASN1OctetString.a((Object) g).d());
    }

    DERBMPString(byte[] bArr) {
        char[] cArr = new char[(bArr.length / 2)];
        for (int i = 0; i != cArr.length; i++) {
            int i2 = i * 2;
            cArr[i] = (char) ((bArr[i2 + 1] & 255) | (bArr[i2] << 8));
        }
        this.f14426a = cArr;
    }

    DERBMPString(char[] cArr) {
        this.f14426a = cArr;
    }

    public DERBMPString(String str) {
        this.f14426a = str.toCharArray();
    }

    public String b() {
        return new String(this.f14426a);
    }

    public String toString() {
        return b();
    }

    public int hashCode() {
        return Arrays.a(this.f14426a);
    }

    /* access modifiers changed from: protected */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERBMPString)) {
            return false;
        }
        return Arrays.a(this.f14426a, ((DERBMPString) aSN1Primitive).f14426a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14426a.length * 2) + 1 + (this.f14426a.length * 2);
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.b(30);
        aSN1OutputStream.a(this.f14426a.length * 2);
        for (int i = 0; i != this.f14426a.length; i++) {
            char c = this.f14426a[i];
            aSN1OutputStream.b((byte) (c >> 8));
            aSN1OutputStream.b((byte) c);
        }
    }
}
