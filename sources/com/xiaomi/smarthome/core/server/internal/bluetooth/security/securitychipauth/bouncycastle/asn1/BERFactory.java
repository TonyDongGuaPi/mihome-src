package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

class BERFactory {

    /* renamed from: a  reason: collision with root package name */
    static final BERSequence f14418a = new BERSequence();
    static final BERSet b = new BERSet();

    BERFactory() {
    }

    static BERSequence a(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.a() < 1 ? f14418a : new BERSequence(aSN1EncodableVector);
    }

    static BERSet b(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.a() < 1 ? b : new BERSet(aSN1EncodableVector);
    }
}
