package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;

public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {

    /* renamed from: a  reason: collision with root package name */
    int f14415a;
    boolean b = false;
    boolean c = true;
    ASN1Encodable d = null;

    /* access modifiers changed from: package-private */
    public abstract void a(ASN1OutputStream aSN1OutputStream) throws IOException;

    public static ASN1TaggedObject a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return (ASN1TaggedObject) aSN1TaggedObject.g();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    public static ASN1TaggedObject a(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return a((Object) b((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    public ASN1TaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable instanceof ASN1Choice) {
            this.c = true;
        } else {
            this.c = z;
        }
        this.f14415a = i;
        if (this.c) {
            this.d = aSN1Encodable;
            return;
        }
        boolean z2 = aSN1Encodable.k() instanceof ASN1Set;
        this.d = aSN1Encodable;
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
        if (this.f14415a != aSN1TaggedObject.f14415a || this.b != aSN1TaggedObject.b || this.c != aSN1TaggedObject.c) {
            return false;
        }
        if (this.d == null) {
            if (aSN1TaggedObject.d != null) {
                return false;
            }
            return true;
        } else if (!this.d.k().equals(aSN1TaggedObject.d.k())) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        int i = this.f14415a;
        return this.d != null ? i ^ this.d.hashCode() : i;
    }

    public int b() {
        return this.f14415a;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.b;
    }

    public ASN1Primitive g() {
        if (this.d != null) {
            return this.d.k();
        }
        return null;
    }

    public ASN1Encodable a(int i, boolean z) throws IOException {
        if (i == 4) {
            return ASN1OctetString.a(this, z).c();
        }
        switch (i) {
            case 16:
                return ASN1Sequence.a(this, z).d();
            case 17:
                return ASN1Set.a(this, z).f();
            default:
                if (z) {
                    return g();
                }
                throw new ASN1Exception("implicit tagging not implemented for tag: " + i);
        }
    }

    public ASN1Primitive f() {
        return k();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        return new DERTaggedObject(this.c, this.f14415a, this.d);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        return new DLTaggedObject(this.c, this.f14415a, this.d);
    }

    public String toString() {
        return Operators.ARRAY_START_STR + this.f14415a + Operators.ARRAY_END_STR + this.d;
    }
}
