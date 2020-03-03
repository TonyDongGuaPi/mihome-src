package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import java.io.IOException;

public class DERNumericString extends ASN1Primitive implements ASN1String {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f14434a;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static DERNumericString a(Object obj) {
        if (obj == null || (obj instanceof DERNumericString)) {
            return (DERNumericString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERNumericString) b((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERNumericString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof DERNumericString)) {
            return a((Object) g);
        }
        return new DERNumericString(ASN1OctetString.a((Object) g).d());
    }

    DERNumericString(byte[] bArr) {
        this.f14434a = bArr;
    }

    public DERNumericString(String str) {
        this(str, false);
    }

    public DERNumericString(String str, boolean z) {
        if (!z || b(str)) {
            this.f14434a = Strings.d(str);
            return;
        }
        throw new IllegalArgumentException("string contains illegal characters");
    }

    public String b() {
        return Strings.b(this.f14434a);
    }

    public String toString() {
        return b();
    }

    public byte[] c() {
        return Arrays.b(this.f14434a);
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14434a.length) + 1 + this.f14434a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(18, this.f14434a);
    }

    public int hashCode() {
        return Arrays.a(this.f14434a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERNumericString)) {
            return false;
        }
        return Arrays.a(this.f14434a, ((DERNumericString) aSN1Primitive).f14434a);
    }

    public static boolean b(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (charAt > 127) {
                return false;
            }
            if (('0' > charAt || charAt > '9') && charAt != ' ') {
                return false;
            }
        }
        return true;
    }
}
