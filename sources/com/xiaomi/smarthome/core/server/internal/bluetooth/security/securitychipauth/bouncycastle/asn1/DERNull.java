package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;

public class DERNull extends ASN1Null {

    /* renamed from: a  reason: collision with root package name */
    public static final DERNull f14433a = new DERNull();
    private static final byte[] b = new byte[0];

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return 2;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(5, b);
    }
}
