package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Iterable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public abstract class ASN1Sequence extends ASN1Primitive implements Iterable<ASN1Encodable> {

    /* renamed from: a  reason: collision with root package name */
    protected Vector f14410a = new Vector();

    /* access modifiers changed from: package-private */
    public abstract void a(ASN1OutputStream aSN1OutputStream) throws IOException;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    public static ASN1Sequence a(Object obj) {
        if (obj == null || (obj instanceof ASN1Sequence)) {
            return (ASN1Sequence) obj;
        }
        if (obj instanceof ASN1SequenceParser) {
            return a((Object) ((ASN1SequenceParser) obj).k());
        }
        if (obj instanceof byte[]) {
            try {
                return a((Object) b((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
            }
        } else {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive k = ((ASN1Encodable) obj).k();
                if (k instanceof ASN1Sequence) {
                    return (ASN1Sequence) k;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Sequence a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.c()) {
                return a((Object) aSN1TaggedObject.g().k());
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (aSN1TaggedObject.c()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSequence((ASN1Encodable) aSN1TaggedObject.g());
            }
            return new DLSequence((ASN1Encodable) aSN1TaggedObject.g());
        } else if (aSN1TaggedObject.g() instanceof ASN1Sequence) {
            return (ASN1Sequence) aSN1TaggedObject.g();
        } else {
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
    }

    protected ASN1Sequence() {
    }

    protected ASN1Sequence(ASN1Encodable aSN1Encodable) {
        this.f14410a.addElement(aSN1Encodable);
    }

    protected ASN1Sequence(ASN1EncodableVector aSN1EncodableVector) {
        for (int i = 0; i != aSN1EncodableVector.a(); i++) {
            this.f14410a.addElement(aSN1EncodableVector.a(i));
        }
    }

    protected ASN1Sequence(ASN1Encodable[] aSN1EncodableArr) {
        for (int i = 0; i != aSN1EncodableArr.length; i++) {
            this.f14410a.addElement(aSN1EncodableArr[i]);
        }
    }

    public ASN1Encodable[] b() {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[f()];
        for (int i = 0; i != f(); i++) {
            aSN1EncodableArr[i] = a(i);
        }
        return aSN1EncodableArr;
    }

    public Enumeration c() {
        return this.f14410a.elements();
    }

    public ASN1SequenceParser d() {
        return new ASN1SequenceParser() {
            private final int c = ASN1Sequence.this.f();
            private int d;

            public ASN1Encodable a() throws IOException {
                if (this.d == this.c) {
                    return null;
                }
                ASN1Sequence aSN1Sequence = ASN1Sequence.this;
                int i = this.d;
                this.d = i + 1;
                ASN1Encodable a2 = aSN1Sequence.a(i);
                if (a2 instanceof ASN1Sequence) {
                    return ((ASN1Sequence) a2).d();
                }
                return a2 instanceof ASN1Set ? ((ASN1Set) a2).f() : a2;
            }

            public ASN1Primitive f() {
                return this;
            }

            public ASN1Primitive k() {
                return this;
            }
        };
    }

    public ASN1Encodable a(int i) {
        return (ASN1Encodable) this.f14410a.elementAt(i);
    }

    public int f() {
        return this.f14410a.size();
    }

    public int hashCode() {
        Enumeration c = c();
        int f = f();
        while (c.hasMoreElements()) {
            f = (f * 17) ^ a(c).hashCode();
        }
        return f;
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1Primitive;
        if (f() != aSN1Sequence.f()) {
            return false;
        }
        Enumeration c = c();
        Enumeration c2 = aSN1Sequence.c();
        while (c.hasMoreElements()) {
            ASN1Encodable a2 = a(c);
            ASN1Encodable a3 = a(c2);
            ASN1Primitive k = a2.k();
            ASN1Primitive k2 = a3.k();
            if (k != k2 && !k.equals(k2)) {
                return false;
            }
        }
        return true;
    }

    private ASN1Encodable a(Enumeration enumeration) {
        return (ASN1Encodable) enumeration.nextElement();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        DERSequence dERSequence = new DERSequence();
        dERSequence.f14410a = this.f14410a;
        return dERSequence;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        DLSequence dLSequence = new DLSequence();
        dLSequence.f14410a = this.f14410a;
        return dLSequence;
    }

    public String toString() {
        return this.f14410a.toString();
    }

    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(b());
    }
}
