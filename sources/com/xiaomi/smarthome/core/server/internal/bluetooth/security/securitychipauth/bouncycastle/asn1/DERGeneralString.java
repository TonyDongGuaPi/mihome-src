package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERGeneralString extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14430a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERGeneralString a(Object obj) {
        if (obj == null || (obj instanceof DERGeneralString)) {
            return (DERGeneralString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERGeneralString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERGeneralString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERGeneralString)) {
            return a((Object) g);
        }
        return new DERGeneralString(((ASN1OctetString) g).d());
    }

    DERGeneralString(byte[] bArr) {
        this.f14430a = bArr;
    }

    public DERGeneralString(String str) {
        this.f14430a = Strings.d(str);
    }

    public String b() {
        return Strings.b(this.f14430a);
    }

    public String toString() {
        return b();
    }

    public byte[] c() {
        return Arrays.b(this.f14430a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14430a.length) + 1 + this.f14430a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(27, this.f14430a);
    }

    public int hashCode() {
        return Arrays.a(this.f14430a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERGeneralString)) {
            return false;
        }
        return Arrays.a(this.f14430a, ((DERGeneralString) aSN1Primitive).f14430a);
    }
}
