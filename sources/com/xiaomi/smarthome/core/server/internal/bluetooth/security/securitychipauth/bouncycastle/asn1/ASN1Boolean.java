package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.IOException;

public class ASN1Boolean extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    public static final ASN1Boolean f14399a = new ASN1Boolean(false);
    public static final ASN1Boolean b = new ASN1Boolean(true);
    private static final byte[] c = {-1};
    private static final byte[] d = {0};
    private final byte[] e;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return 3;
    }

    public static ASN1Boolean a(Object obj) {
        if (obj == null || (obj instanceof ASN1Boolean)) {
            return (ASN1Boolean) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Boolean) b((byte[]) obj);
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct boolean from byte[]: " + e2.getMessage());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Boolean a(boolean z) {
        return z ? b : f14399a;
    }

    public static ASN1Boolean a(int i) {
        return i != 0 ? b : f14399a;
    }

    public static ASN1Boolean a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1Boolean)) {
            return a((Object) g);
        }
        return a(((ASN1OctetString) g).d());
    }

    ASN1Boolean(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("byte value should have 1 byte in it");
        } else if (bArr[0] == 0) {
            this.e = d;
        } else if ((bArr[0] & 255) == 255) {
            this.e = c;
        } else {
            this.e = Arrays.b(bArr);
        }
    }

    public ASN1Boolean(boolean z) {
        this.e = z ? c : d;
    }

    public boolean b() {
        return this.e[0] != 0;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(1, this.e);
    }

    /* access modifiers changed from: protected */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Boolean) || this.e[0] != ((ASN1Boolean) aSN1Primitive).e[0]) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.e[0];
    }

    public String toString() {
        return this.e[0] != 0 ? "TRUE" : "FALSE";
    }

    static ASN1Boolean a(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
        } else if (bArr[0] == 0) {
            return f14399a;
        } else {
            if ((bArr[0] & 255) == 255) {
                return b;
            }
            return new ASN1Boolean(bArr);
        }
    }
}
