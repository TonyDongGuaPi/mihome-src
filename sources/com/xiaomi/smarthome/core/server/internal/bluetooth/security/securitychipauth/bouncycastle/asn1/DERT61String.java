package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERT61String extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f14440a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERT61String a(Object obj) {
        if (obj == null || (obj instanceof DERT61String)) {
            return (DERT61String) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERT61String) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERT61String a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERT61String)) {
            return a((Object) g);
        }
        return new DERT61String(ASN1OctetString.a((Object) g).d());
    }

    public DERT61String(byte[] bArr) {
        this.f14440a = Arrays.b(bArr);
    }

    public DERT61String(String str) {
        this.f14440a = Strings.d(str);
    }

    public String b() {
        return Strings.b(this.f14440a);
    }

    public String toString() {
        return b();
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14440a.length) + 1 + this.f14440a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(20, this.f14440a);
    }

    public byte[] c() {
        return Arrays.b(this.f14440a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERT61String)) {
            return false;
        }
        return Arrays.a(this.f14440a, ((DERT61String) aSN1Primitive).f14440a);
    }

    public int hashCode() {
        return Arrays.a(this.f14440a);
    }
}
