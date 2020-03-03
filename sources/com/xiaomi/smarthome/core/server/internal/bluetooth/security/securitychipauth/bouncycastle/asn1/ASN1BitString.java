package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.io.Streams;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public abstract class ASN1BitString extends ASN1Primitive implements ASN1String {
    private static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f14398a;
    protected final int b;

    protected static int a(int i) {
        int i2;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                i2 = 0;
                break;
            }
            if (i3 != 0) {
                int i4 = i >> (i3 * 8);
                if (i4 != 0) {
                    i2 = i4 & 255;
                    break;
                }
            } else if (i != 0) {
                i2 = i & 255;
                break;
            }
            i3--;
        }
        if (i2 == 0) {
            return 0;
        }
        int i5 = 1;
        while (true) {
            i2 <<= 1;
            if ((i2 & 255) == 0) {
                return 8 - i5;
            }
            i5++;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void a(ASN1OutputStream aSN1OutputStream) throws IOException;

    protected static byte[] b(int i) {
        if (i == 0) {
            return new byte[0];
        }
        int i2 = 4;
        int i3 = 3;
        while (i3 >= 1 && ((255 << (i3 * 8)) & i) == 0) {
            i2--;
            i3--;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    public ASN1BitString(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("data cannot be null");
        } else if (bArr.length == 0 && i != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        } else if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        } else {
            this.f14398a = Arrays.b(bArr);
            this.b = i;
        }
    }

    public String b() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).a((ASN1Encodable) this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(c[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(c[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    public int c() {
        byte[] bArr = this.f14398a;
        if (this.b > 0 && this.f14398a.length <= 4) {
            bArr = a(this.f14398a, this.b);
        }
        int i = 0;
        int i2 = 0;
        while (i != bArr.length && i != 4) {
            i2 |= (bArr[i] & 255) << (i * 8);
            i++;
        }
        return i2;
    }

    public byte[] d() {
        if (this.b == 0) {
            return Arrays.b(this.f14398a);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    public byte[] f() {
        return a(this.f14398a, this.b);
    }

    public int g() {
        return this.b;
    }

    public String toString() {
        return b();
    }

    public int hashCode() {
        return this.b ^ Arrays.a(f());
    }

    /* access modifiers changed from: protected */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1BitString)) {
            return false;
        }
        ASN1BitString aSN1BitString = (ASN1BitString) aSN1Primitive;
        if (this.b != aSN1BitString.b || !Arrays.a(f(), aSN1BitString.f())) {
            return false;
        }
        return true;
    }

    protected static byte[] a(byte[] bArr, int i) {
        byte[] b2 = Arrays.b(bArr);
        if (i > 0) {
            int length = bArr.length - 1;
            b2[length] = (byte) ((255 << i) & b2[length]);
        }
        return b2;
    }

    static ASN1BitString a(int i, InputStream inputStream) throws IOException {
        if (i >= 1) {
            int read = inputStream.read();
            byte[] bArr = new byte[(i - 1)];
            if (bArr.length != 0) {
                if (Streams.a(inputStream, bArr) != bArr.length) {
                    throw new EOFException("EOF encountered in middle of BIT STRING");
                } else if (read > 0 && read < 8 && bArr[bArr.length - 1] != ((byte) (bArr[bArr.length - 1] & (255 << read)))) {
                    return new DLBitString(bArr, read);
                }
            }
            return new DERBitString(bArr, read);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    public ASN1Primitive h() {
        return k();
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive i() {
        return new DERBitString(this.f14398a, this.b);
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive j() {
        return new DLBitString(this.f14398a, this.b);
    }
}
