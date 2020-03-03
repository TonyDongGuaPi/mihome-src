package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DEROctetString extends ASN1OctetString {
    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    public DEROctetString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.k().a(ASN1Encoding.f14401a));
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return StreamUtil.a(this.f14407a.length) + 1 + this.f14407a.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(4, this.f14407a);
    }

    static void a(DEROutputStream dEROutputStream, byte[] bArr) throws IOException {
        dEROutputStream.a(4, bArr);
    }
}
