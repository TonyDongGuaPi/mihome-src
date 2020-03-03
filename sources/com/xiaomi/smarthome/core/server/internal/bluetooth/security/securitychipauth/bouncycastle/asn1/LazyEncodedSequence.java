package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

class LazyEncodedSequence extends ASN1Sequence {
    private byte[] b;

    LazyEncodedSequence(byte[] bArr) throws IOException {
        this.b = bArr;
    }

    private void g() {
        LazyConstructionEnumeration lazyConstructionEnumeration = new LazyConstructionEnumeration(this.b);
        while (lazyConstructionEnumeration.hasMoreElements()) {
            this.f14410a.addElement(lazyConstructionEnumeration.nextElement());
        }
        this.b = null;
    }

    public synchronized ASN1Encodable a(int i) {
        if (this.b != null) {
            g();
        }
        return super.a(i);
    }

    public synchronized Enumeration c() {
        if (this.b == null) {
            return super.c();
        }
        return new LazyConstructionEnumeration(this.b);
    }

    public synchronized int f() {
        if (this.b != null) {
            g();
        }
        return super.f();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        if (this.b != null) {
            g();
        }
        return super.i();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        if (this.b != null) {
            g();
        }
        return super.j();
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        if (this.b != null) {
            return StreamUtil.a(this.b.length) + 1 + this.b.length;
        }
        return super.j().e();
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        if (this.b != null) {
            aSN1OutputStream.a(48, this.b);
        } else {
            super.j().a(aSN1OutputStream);
        }
    }
}
