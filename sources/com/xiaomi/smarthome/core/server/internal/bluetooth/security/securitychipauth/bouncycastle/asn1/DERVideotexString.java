package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERVideotexString extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14443a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERVideotexString a(Object obj) {
        if (obj == null || (obj instanceof DERVideotexString)) {
            return (DERVideotexString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERVideotexString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERVideotexString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERVideotexString)) {
            return a((Object) g);
        }
        return new DERVideotexString(((ASN1OctetString) g).d());
    }

    public DERVideotexString(byte[] bArr) {
        this.f14443a = Arrays.b(bArr);
    }

    public byte[] c() {
        return Arrays.b(this.f14443a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14443a.length) + 1 + this.f14443a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(21, this.f14443a);
    }

    public int hashCode() {
        return Arrays.a(this.f14443a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERVideotexString)) {
            return false;
        }
        return Arrays.a(this.f14443a, ((DERVideotexString) aSN1Primitive).f14443a);
    }

    public String b() {
        return Strings.b(this.f14443a);
    }
}
