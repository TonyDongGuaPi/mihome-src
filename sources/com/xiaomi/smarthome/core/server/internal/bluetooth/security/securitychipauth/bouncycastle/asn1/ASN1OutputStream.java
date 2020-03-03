package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

public class ASN1OutputStream {

    /* renamed from: a  reason: collision with root package name */
    private OutputStream f14408a;

    public ASN1OutputStream(OutputStream outputStream) {
        this.f14408a = outputStream;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) throws IOException {
        if (i > 127) {
            int i2 = i;
            int i3 = 1;
            while (true) {
                i2 >>>= 8;
                if (i2 == 0) {
                    break;
                }
                i3++;
            }
            b((byte) (i3 | 128));
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                b((byte) (i >> i4));
            }
            return;
        }
        b((byte) i);
    }

    /* access modifiers changed from: package-private */
    public void b(int i) throws IOException {
        this.f14408a.write(i);
    }

    /* access modifiers changed from: package-private */
    public void a(byte[] bArr) throws IOException {
        this.f14408a.write(bArr);
    }

    /* access modifiers changed from: package-private */
    public void a(byte[] bArr, int i, int i2) throws IOException {
        this.f14408a.write(bArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, byte[] bArr) throws IOException {
        b(i);
        a(bArr.length);
        a(bArr);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2) throws IOException {
        if (i2 < 31) {
            b(i | i2);
            return;
        }
        b(i | 31);
        if (i2 < 128) {
            b(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            length--;
            bArr[length] = (byte) ((i2 & 127) | 128);
        } while (i2 > 127);
        a(bArr, length, bArr.length - length);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, byte[] bArr) throws IOException {
        a(i, i2);
        a(bArr.length);
        a(bArr);
    }

    /* access modifiers changed from: protected */
    public void a() throws IOException {
        this.f14408a.write(5);
        this.f14408a.write(0);
    }

    public void a(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            aSN1Encodable.k().a(this);
            return;
        }
        throw new IOException("null object detected");
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1Primitive aSN1Primitive) throws IOException {
        if (aSN1Primitive != null) {
            aSN1Primitive.a((ASN1OutputStream) new ImplicitOutputStream(this.f14408a));
            return;
        }
        throw new IOException("null object detected");
    }

    public void b() throws IOException {
        this.f14408a.close();
    }

    public void c() throws IOException {
        this.f14408a.flush();
    }

    /* access modifiers changed from: package-private */
    public ASN1OutputStream d() {
        return new DEROutputStream(this.f14408a);
    }

    /* access modifiers changed from: package-private */
    public ASN1OutputStream e() {
        return new DLOutputStream(this.f14408a);
    }

    private class ImplicitOutputStream extends ASN1OutputStream {
        private boolean b = true;

        public ImplicitOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        public void b(int i) throws IOException {
            if (this.b) {
                this.b = false;
            } else {
                ASN1OutputStream.super.b(i);
            }
        }
    }
}
