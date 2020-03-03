package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BERApplicationSpecific extends ASN1ApplicationSpecific {
    BERApplicationSpecific(boolean z, int i, byte[] bArr) {
        super(z, i, bArr);
    }

    public BERApplicationSpecific(int i, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, i, aSN1Encodable);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BERApplicationSpecific(boolean z, int i, ASN1Encodable aSN1Encodable) throws IOException {
        super(z || aSN1Encodable.k().a(), i, a(z, aSN1Encodable));
    }

    private static byte[] a(boolean z, ASN1Encodable aSN1Encodable) throws IOException {
        byte[] a2 = aSN1Encodable.k().a(ASN1Encoding.c);
        if (z) {
            return a2;
        }
        int a3 = a(a2);
        byte[] bArr = new byte[(a2.length - a3)];
        System.arraycopy(a2, a3, bArr, 0, bArr.length);
        return bArr;
    }

    public BERApplicationSpecific(int i, ASN1EncodableVector aSN1EncodableVector) {
        super(true, i, a(aSN1EncodableVector));
    }

    private static byte[] a(ASN1EncodableVector aSN1EncodableVector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i != aSN1EncodableVector.a()) {
            try {
                byteArrayOutputStream.write(((ASN1Object) aSN1EncodableVector.a(i)).a(ASN1Encoding.c));
                i++;
            } catch (IOException e) {
                throw new ASN1ParsingException("malformed object: " + e, e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(this.f14397a ? 96 : 64, this.b);
        aSN1OutputStream.b(128);
        aSN1OutputStream.a(this.c);
        aSN1OutputStream.b(0);
        aSN1OutputStream.b(0);
    }
}
