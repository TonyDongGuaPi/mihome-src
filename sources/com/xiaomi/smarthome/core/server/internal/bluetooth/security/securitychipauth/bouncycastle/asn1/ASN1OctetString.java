package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Strings;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.encoders.Hex;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class ASN1OctetString extends ASN1Primitive implements ASN1OctetStringParser {

    /* renamed from: a  reason: collision with root package name */
    byte[] f14407a;

    /* access modifiers changed from: package-private */
    public abstract void a(ASN1OutputStream aSN1OutputStream) throws IOException;

    public ASN1OctetStringParser c() {
        return this;
    }

    public static ASN1OctetString a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1OctetString)) {
            return a((Object) g);
        }
        return BEROctetString.a(ASN1Sequence.a((Object) g));
    }

    public static ASN1OctetString a(Object obj) {
        if (obj == null || (obj instanceof ASN1OctetString)) {
            return (ASN1OctetString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return a((Object) ASN1Primitive.b((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct OCTET STRING from byte[]: " + e.getMessage());
            }
        } else {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive k = ((ASN1Encodable) obj).k();
                if (k instanceof ASN1OctetString) {
                    return (ASN1OctetString) k;
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public ASN1OctetString(byte[] bArr) {
        if (bArr != null) {
            this.f14407a = bArr;
            return;
        }
        throw new NullPointerException("string cannot be null");
    }

    public InputStream b() {
        return new ByteArrayInputStream(this.f14407a);
    }

    public byte[] d() {
        return this.f14407a;
    }

    public int hashCode() {
        return Arrays.a(d());
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1OctetString)) {
            return false;
        }
        return Arrays.a(this.f14407a, ((ASN1OctetString) aSN1Primitive).f14407a);
    }

    public ASN1Primitive f() {
        return k();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        return new DEROctetString(this.f14407a);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        return new DEROctetString(this.f14407a);
    }

    public String toString() {
        return "#" + Strings.b(Hex.b(this.f14407a));
    }
}
