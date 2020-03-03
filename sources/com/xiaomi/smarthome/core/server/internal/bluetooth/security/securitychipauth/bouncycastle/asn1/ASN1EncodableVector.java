package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;

public class ASN1EncodableVector {

    /* renamed from: a  reason: collision with root package name */
    private final Vector f14400a = new Vector();

    public void a(ASN1Encodable aSN1Encodable) {
        this.f14400a.addElement(aSN1Encodable);
    }

    public void a(ASN1EncodableVector aSN1EncodableVector) {
        Enumeration elements = aSN1EncodableVector.f14400a.elements();
        while (elements.hasMoreElements()) {
            this.f14400a.addElement(elements.nextElement());
        }
    }

    public ASN1Encodable a(int i) {
        return (ASN1Encodable) this.f14400a.elementAt(i);
    }

    public int a() {
        return this.f14400a.size();
    }
}
