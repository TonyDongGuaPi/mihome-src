package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1Primitive extends ASN1Object {
    /* access modifiers changed from: package-private */
    public abstract void a(ASN1OutputStream aSN1OutputStream) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract boolean a();

    /* access modifiers changed from: package-private */
    public abstract boolean a(ASN1Primitive aSN1Primitive);

    /* access modifiers changed from: package-private */
    public abstract int e() throws IOException;

    public abstract int hashCode();

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        return this;
    }

    public ASN1Primitive k() {
        return this;
    }

    ASN1Primitive() {
    }

    public static ASN1Primitive b(byte[] bArr) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        try {
            ASN1Primitive d = aSN1InputStream.d();
            if (aSN1InputStream.available() == 0) {
                return d;
            }
            throw new IOException("Extra data detected in stream");
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ASN1Encodable) && a(((ASN1Encodable) obj).k());
    }
}
