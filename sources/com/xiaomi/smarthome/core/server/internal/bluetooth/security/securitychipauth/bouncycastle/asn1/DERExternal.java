package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DERExternal extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    private ASN1ObjectIdentifier f14427a;
    private ASN1Integer b;
    private ASN1Primitive c;
    private int d;
    private ASN1Primitive e;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    public DERExternal(ASN1EncodableVector aSN1EncodableVector) {
        int i = 0;
        ASN1Primitive a2 = a(aSN1EncodableVector, 0);
        if (a2 instanceof ASN1ObjectIdentifier) {
            this.f14427a = (ASN1ObjectIdentifier) a2;
            a2 = a(aSN1EncodableVector, 1);
            i = 1;
        }
        if (a2 instanceof ASN1Integer) {
            this.b = (ASN1Integer) a2;
            i++;
            a2 = a(aSN1EncodableVector, i);
        }
        if (!(a2 instanceof ASN1TaggedObject)) {
            this.c = a2;
            i++;
            a2 = a(aSN1EncodableVector, i);
        }
        if (aSN1EncodableVector.a() != i + 1) {
            throw new IllegalArgumentException("input vector too large");
        } else if (a2 instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) a2;
            a(aSN1TaggedObject.b());
            this.e = aSN1TaggedObject.g();
        } else {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
    }

    private ASN1Primitive a(ASN1EncodableVector aSN1EncodableVector, int i) {
        if (aSN1EncodableVector.a() > i) {
            return aSN1EncodableVector.a(i).k();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public DERExternal(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, DERTaggedObject dERTaggedObject) {
        this(aSN1ObjectIdentifier, aSN1Integer, aSN1Primitive, dERTaggedObject.b(), dERTaggedObject.k());
    }

    public DERExternal(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, int i, ASN1Primitive aSN1Primitive2) {
        a(aSN1ObjectIdentifier);
        a(aSN1Integer);
        b(aSN1Primitive);
        a(i);
        c(aSN1Primitive2.k());
    }

    public int hashCode() {
        int hashCode = this.f14427a != null ? this.f14427a.hashCode() : 0;
        if (this.b != null) {
            hashCode ^= this.b.hashCode();
        }
        if (this.c != null) {
            hashCode ^= this.c.hashCode();
        }
        return hashCode ^ this.e.hashCode();
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        return l().length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.f14427a != null) {
            byteArrayOutputStream.write(this.f14427a.a(ASN1Encoding.f14401a));
        }
        if (this.b != null) {
            byteArrayOutputStream.write(this.b.a(ASN1Encoding.f14401a));
        }
        if (this.c != null) {
            byteArrayOutputStream.write(this.c.a(ASN1Encoding.f14401a));
        }
        byteArrayOutputStream.write(new DERTaggedObject(true, this.d, this.e).a(ASN1Encoding.f14401a));
        aSN1OutputStream.a(32, 8, byteArrayOutputStream.toByteArray());
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERExternal)) {
            return false;
        }
        if (this == aSN1Primitive) {
            return true;
        }
        DERExternal dERExternal = (DERExternal) aSN1Primitive;
        if (this.f14427a != null && (dERExternal.f14427a == null || !dERExternal.f14427a.equals(this.f14427a))) {
            return false;
        }
        if (this.b != null && (dERExternal.b == null || !dERExternal.b.equals(this.b))) {
            return false;
        }
        if (this.c == null || (dERExternal.c != null && dERExternal.c.equals(this.c))) {
            return this.e.equals(dERExternal.e);
        }
        return false;
    }

    public ASN1Primitive b() {
        return this.c;
    }

    public ASN1ObjectIdentifier c() {
        return this.f14427a;
    }

    public int d() {
        return this.d;
    }

    public ASN1Primitive f() {
        return this.e;
    }

    public ASN1Integer g() {
        return this.b;
    }

    private void b(ASN1Primitive aSN1Primitive) {
        this.c = aSN1Primitive;
    }

    private void a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f14427a = aSN1ObjectIdentifier;
    }

    private void a(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("invalid encoding value: " + i);
        }
        this.d = i;
    }

    private void c(ASN1Primitive aSN1Primitive) {
        this.e = aSN1Primitive;
    }

    private void a(ASN1Integer aSN1Integer) {
        this.b = aSN1Integer;
    }
}
