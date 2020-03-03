package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

class LazyConstructionEnumeration implements Enumeration {

    /* renamed from: a  reason: collision with root package name */
    private ASN1InputStream f14446a;
    private Object b = a();

    public LazyConstructionEnumeration(byte[] bArr) {
        this.f14446a = new ASN1InputStream(bArr, true);
    }

    public boolean hasMoreElements() {
        return this.b != null;
    }

    public Object nextElement() {
        Object obj = this.b;
        this.b = a();
        return obj;
    }

    private Object a() {
        try {
            return this.f14446a.d();
        } catch (IOException e) {
            throw new ASN1ParsingException("malformed DER construction: " + e, e);
        }
    }
}
