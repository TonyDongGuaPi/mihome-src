package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERVisibleString extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14444a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERVisibleString a(Object obj) {
        if (obj == null || (obj instanceof DERVisibleString)) {
            return (DERVisibleString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERVisibleString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERVisibleString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERVisibleString)) {
            return a((Object) g);
        }
        return new DERVisibleString(ASN1OctetString.a((Object) g).d());
    }

    DERVisibleString(byte[] bArr) {
        this.f14444a = bArr;
    }

    public DERVisibleString(String str) {
        this.f14444a = Strings.d(str);
    }

    public String b() {
        return Strings.b(this.f14444a);
    }

    public String toString() {
        return b();
    }

    public byte[] c() {
        return Arrays.b(this.f14444a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14444a.length) + 1 + this.f14444a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(26, this.f14444a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERVisibleString)) {
            return false;
        }
        return Arrays.a(this.f14444a, ((DERVisibleString) aSN1Primitive).f14444a);
    }

    public int hashCode() {
        return Arrays.a(this.f14444a);
    }
}
