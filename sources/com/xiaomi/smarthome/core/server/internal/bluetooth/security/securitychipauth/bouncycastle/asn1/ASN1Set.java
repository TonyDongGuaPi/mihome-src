package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Iterable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {

    /* renamed from: a  reason: collision with root package name */
    private Vector f14412a;
    private boolean b;

    /* access modifiers changed from: package-private */
    public abstract void a(ASN1OutputStream aSN1OutputStream) throws IOException;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    public static ASN1Set a(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1SetParser) {
            return a((Object) ((ASN1SetParser) obj).k());
        }
        if (obj instanceof byte[]) {
            try {
                return a((Object) ASN1Primitive.b((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
            }
        } else {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive k = ((ASN1Encodable) obj).k();
                if (k instanceof ASN1Set) {
                    return (ASN1Set) k;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Set a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.c()) {
                return (ASN1Set) aSN1TaggedObject.g();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (aSN1TaggedObject.c()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet((ASN1Encodable) aSN1TaggedObject.g());
            }
            return new DLSet((ASN1Encodable) aSN1TaggedObject.g());
        } else if (aSN1TaggedObject.g() instanceof ASN1Set) {
            return (ASN1Set) aSN1TaggedObject.g();
        } else {
            if (aSN1TaggedObject.g() instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1TaggedObject.g();
                if (aSN1TaggedObject instanceof BERTaggedObject) {
                    return new BERSet(aSN1Sequence.b());
                }
                return new DLSet(aSN1Sequence.b());
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
    }

    protected ASN1Set() {
        this.f14412a = new Vector();
        this.b = false;
    }

    protected ASN1Set(ASN1Encodable aSN1Encodable) {
        this.f14412a = new Vector();
        this.b = false;
        this.f14412a.addElement(aSN1Encodable);
    }

    protected ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        this.f14412a = new Vector();
        this.b = false;
        for (int i = 0; i != aSN1EncodableVector.a(); i++) {
            this.f14412a.addElement(aSN1EncodableVector.a(i));
        }
        if (z) {
            g();
        }
    }

    protected ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        this.f14412a = new Vector();
        this.b = false;
        for (int i = 0; i != aSN1EncodableArr.length; i++) {
            this.f14412a.addElement(aSN1EncodableArr[i]);
        }
        if (z) {
            g();
        }
    }

    public Enumeration b() {
        return this.f14412a.elements();
    }

    public ASN1Encodable a(int i) {
        return (ASN1Encodable) this.f14412a.elementAt(i);
    }

    public int c() {
        return this.f14412a.size();
    }

    public ASN1Encodable[] d() {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[c()];
        for (int i = 0; i != c(); i++) {
            aSN1EncodableArr[i] = a(i);
        }
        return aSN1EncodableArr;
    }

    public ASN1SetParser f() {
        return new ASN1SetParser() {
            private final int c = ASN1Set.this.c();
            private int d;

            public ASN1Encodable a() throws IOException {
                if (this.d == this.c) {
                    return null;
                }
                ASN1Set aSN1Set = ASN1Set.this;
                int i = this.d;
                this.d = i + 1;
                ASN1Encodable a2 = aSN1Set.a(i);
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

    public int hashCode() {
        Enumeration b2 = b();
        int c = c();
        while (b2.hasMoreElements()) {
            c = (c * 17) ^ a(b2).hashCode();
        }
        return c;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        if (this.b) {
            DERSet dERSet = new DERSet();
            dERSet.f14412a = this.f14412a;
            return dERSet;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.f14412a.size(); i++) {
            vector.addElement(this.f14412a.elementAt(i));
        }
        DERSet dERSet2 = new DERSet();
        dERSet2.f14412a = vector;
        dERSet2.g();
        return dERSet2;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        DLSet dLSet = new DLSet();
        dLSet.f14412a = this.f14412a;
        return dLSet;
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
        if (c() != aSN1Set.c()) {
            return false;
        }
        Enumeration b2 = b();
        Enumeration b3 = aSN1Set.b();
        while (b2.hasMoreElements()) {
            ASN1Encodable a2 = a(b2);
            ASN1Encodable a3 = a(b3);
            ASN1Primitive k = a2.k();
            ASN1Primitive k2 = a3.k();
            if (k != k2 && !k.equals(k2)) {
                return false;
            }
        }
        return true;
    }

    private ASN1Encodable a(Enumeration enumeration) {
        ASN1Encodable aSN1Encodable = (ASN1Encodable) enumeration.nextElement();
        return aSN1Encodable == null ? DERNull.f14433a : aSN1Encodable;
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        int i = 0;
        while (i != min) {
            if (bArr[i] == bArr2[i]) {
                i++;
            } else if ((bArr[i] & 255) < (bArr2[i] & 255)) {
                return true;
            } else {
                return false;
            }
        }
        if (min == bArr.length) {
            return true;
        }
        return false;
    }

    private byte[] a(ASN1Encodable aSN1Encodable) {
        try {
            return aSN1Encodable.k().a(ASN1Encoding.f14401a);
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    /* access modifiers changed from: protected */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void g() {
        /*
            r9 = this;
            boolean r0 = r9.b
            if (r0 != 0) goto L_0x005e
            r0 = 1
            r9.b = r0
            java.util.Vector r1 = r9.f14412a
            int r1 = r1.size()
            if (r1 <= r0) goto L_0x005e
            java.util.Vector r1 = r9.f14412a
            int r1 = r1.size()
            int r1 = r1 - r0
            r2 = r1
            r1 = 1
        L_0x0018:
            if (r1 == 0) goto L_0x005e
            java.util.Vector r1 = r9.f14412a
            r3 = 0
            java.lang.Object r1 = r1.elementAt(r3)
            com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable r1 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable) r1
            byte[] r1 = r9.a((com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable) r1)
            r4 = 0
            r5 = 0
        L_0x0029:
            if (r3 == r2) goto L_0x005b
            java.util.Vector r6 = r9.f14412a
            int r7 = r3 + 1
            java.lang.Object r6 = r6.elementAt(r7)
            com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable r6 = (com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable) r6
            byte[] r6 = r9.a((com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Encodable) r6)
            boolean r8 = r9.a((byte[]) r1, (byte[]) r6)
            if (r8 == 0) goto L_0x0041
            r1 = r6
            goto L_0x0059
        L_0x0041:
            java.util.Vector r4 = r9.f14412a
            java.lang.Object r4 = r4.elementAt(r3)
            java.util.Vector r5 = r9.f14412a
            java.util.Vector r6 = r9.f14412a
            java.lang.Object r6 = r6.elementAt(r7)
            r5.setElementAt(r6, r3)
            java.util.Vector r5 = r9.f14412a
            r5.setElementAt(r4, r7)
            r4 = r3
            r5 = 1
        L_0x0059:
            r3 = r7
            goto L_0x0029
        L_0x005b:
            r2 = r4
            r1 = r5
            goto L_0x0018
        L_0x005e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1.ASN1Set.g():void");
    }

    public String toString() {
        return this.f14412a.toString();
    }

    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(d());
    }
}
