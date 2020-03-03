package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERUTF8String extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14441a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERUTF8String a(Object obj) {
        if (obj == null || (obj instanceof DERUTF8String)) {
            return (DERUTF8String) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERUTF8String) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERUTF8String a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERUTF8String)) {
            return a((Object) g);
        }
        return new DERUTF8String(ASN1OctetString.a((Object) g).d());
    }

    DERUTF8String(byte[] bArr) {
        this.f14441a = bArr;
    }

    public DERUTF8String(String str) {
        this.f14441a = Strings.a(str);
    }

    public String b() {
        return Strings.a(this.f14441a);
    }

    public String toString() {
        return b();
    }

    public int hashCode() {
        return Arrays.a(this.f14441a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUTF8String)) {
            return false;
        }
        return Arrays.a(this.f14441a, ((DERUTF8String) aSN1Primitive).f14441a);
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        return StreamUtil.a(this.f14441a.length) + 1 + this.f14441a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(12, this.f14441a);
    }
}
