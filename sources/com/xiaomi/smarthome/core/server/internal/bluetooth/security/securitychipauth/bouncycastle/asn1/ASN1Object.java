package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Encodable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class ASN1Object implements ASN1Encodable, Encodable {
    public abstract ASN1Primitive k();

    public byte[] l() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ASN1OutputStream(byteArrayOutputStream).a((ASN1Encodable) this);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] a(String str) throws IOException {
        if (str.equals(ASN1Encoding.f14401a)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DEROutputStream(byteArrayOutputStream).a(this);
            return byteArrayOutputStream.toByteArray();
        } else if (!str.equals(ASN1Encoding.b)) {
            return l();
        } else {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            new DLOutputStream(byteArrayOutputStream2).a(this);
            return byteArrayOutputStream2.toByteArray();
        }
    }

    public int hashCode() {
        return k().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ASN1Encodable)) {
            return false;
        }
        return k().equals(((ASN1Encodable) obj).k());
    }

    public ASN1Primitive m() {
        return k();
    }

    protected static boolean a(Object obj, int i) {
        return (obj instanceof byte[]) && ((byte[]) obj)[0] == i;
    }
}
