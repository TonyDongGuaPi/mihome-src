package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERGraphicString extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14431a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERGraphicString a(Object obj) {
        if (obj == null || (obj instanceof DERGraphicString)) {
            return (DERGraphicString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERGraphicString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERGraphicString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERGraphicString)) {
            return a((Object) g);
        }
        return new DERGraphicString(((ASN1OctetString) g).d());
    }

    public DERGraphicString(byte[] bArr) {
        this.f14431a = Arrays.b(bArr);
    }

    public byte[] c() {
        return Arrays.b(this.f14431a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14431a.length) + 1 + this.f14431a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(25, this.f14431a);
    }

    public int hashCode() {
        return Arrays.a(this.f14431a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERGraphicString)) {
            return false;
        }
        return Arrays.a(this.f14431a, ((DERGraphicString) aSN1Primitive).f14431a);
    }

    public String b() {
        return Strings.b(this.f14431a);
    }
}
