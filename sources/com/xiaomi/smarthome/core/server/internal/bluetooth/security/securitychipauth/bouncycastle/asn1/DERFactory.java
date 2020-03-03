package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

class DERFactory {

    /* renamed from: a  reason: collision with root package name */
    static final ASN1Sequence f14429a = new DERSequence();
    static final ASN1Set b = new DERSet();

    DERFactory() {
    }

    static ASN1Sequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.a() < 1 ? f14429a : new DLSequence(aSN1EncodableVector);
    }

    static ASN1Set b(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.a() < 1 ? b : new DLSet(aSN1EncodableVector);
    }
}
