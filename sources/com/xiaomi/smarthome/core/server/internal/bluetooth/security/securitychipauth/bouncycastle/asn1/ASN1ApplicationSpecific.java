package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.IOException;

public abstract class ASN1ApplicationSpecific extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    protected final boolean f14397a;
    protected final int b;
    protected final byte[] c;

    ASN1ApplicationSpecific(boolean z, int i, byte[] bArr) {
        this.f14397a = z;
        this.b = i;
        this.c = Arrays.b(bArr);
    }

    public static ASN1ApplicationSpecific a(Object obj) {
        if (obj == null || (obj instanceof ASN1ApplicationSpecific)) {
            return (ASN1ApplicationSpecific) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return a((Object) ASN1Primitive.b((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to construct object from byte[]: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    protected static int a(byte[] bArr) {
        byte b2 = bArr[1] & 255;
        if (b2 == 128 || b2 <= Byte.MAX_VALUE) {
            return 2;
        }
        byte b3 = b2 & Byte.MAX_VALUE;
        if (b3 <= 4) {
            return b3 + 2;
        }
        throw new IllegalStateException("DER length more than 4 bytes: " + b3);
    }

    public boolean a() {
        return this.f14397a;
    }

    public byte[] b() {
        return Arrays.b(this.c);
    }

    public int c() {
        return this.b;
    }

    public ASN1Primitive d() throws IOException {
        return ASN1Primitive.b(b());
    }

    public ASN1Primitive a(int i) throws IOException {
        if (i < 31) {
            byte[] l = l();
            byte[] a2 = a(i, l);
            if ((l[0] & 32) != 0) {
                a2[0] = (byte) (a2[0] | 32);
            }
            return ASN1Primitive.b(a2);
        }
        throw new IOException("unsupported tag number");
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        return StreamUtil.b(this.b) + StreamUtil.a(this.c.length) + this.c.length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.a(this.f14397a ? 96 : 64, this.b, this.c);
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1ApplicationSpecific)) {
            return false;
        }
        ASN1ApplicationSpecific aSN1ApplicationSpecific = (ASN1ApplicationSpecific) aSN1Primitive;
        if (this.f14397a == aSN1ApplicationSpecific.f14397a && this.b == aSN1ApplicationSpecific.b && Arrays.a(this.c, aSN1ApplicationSpecific.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f14397a ^ this.b) ^ Arrays.a(this.c) ? 1 : 0;
    }

    private byte[] a(int i, byte[] bArr) throws IOException {
        int i2;
        if ((bArr[0] & 31) == 31) {
            i2 = 2;
            byte b2 = bArr[1] & 255;
            if ((b2 & Byte.MAX_VALUE) != 0) {
                while (b2 >= 0 && (b2 & 128) != 0) {
                    int i3 = i2 + 1;
                    b2 = bArr[i2] & 255;
                    i2 = i3;
                }
            } else {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
        } else {
            i2 = 1;
        }
        byte[] bArr2 = new byte[((bArr.length - i2) + 1)];
        System.arraycopy(bArr, i2, bArr2, 1, bArr2.length - 1);
        bArr2[0] = (byte) i;
        return bArr2;
    }
}
