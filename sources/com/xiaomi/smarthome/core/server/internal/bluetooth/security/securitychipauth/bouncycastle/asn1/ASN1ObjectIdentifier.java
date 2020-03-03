package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ASN1ObjectIdentifier extends ASN1Primitive {
    private static final long c = 72057594037927808L;
    private static final ConcurrentMap<OidHandle, ASN1ObjectIdentifier> d = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    private final String f14405a;
    private byte[] b;

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public static ASN1ObjectIdentifier a(Object obj) {
        if (obj == null || (obj instanceof ASN1ObjectIdentifier)) {
            return (ASN1ObjectIdentifier) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) obj;
            if (aSN1Encodable.k() instanceof ASN1ObjectIdentifier) {
                return (ASN1ObjectIdentifier) aSN1Encodable.k();
            }
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1ObjectIdentifier) b((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1ObjectIdentifier a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive g = aSN1TaggedObject.g();
        if (z || (g instanceof ASN1ObjectIdentifier)) {
            return a((Object) g);
        }
        return a(ASN1OctetString.a((Object) aSN1TaggedObject.g()).d());
    }

    ASN1ObjectIdentifier(byte[] bArr) {
        byte[] bArr2 = bArr;
        StringBuffer stringBuffer = new StringBuffer();
        long j = 0;
        BigInteger bigInteger = null;
        boolean z = true;
        for (int i = 0; i != bArr2.length; i++) {
            byte b2 = bArr2[i] & 255;
            if (j <= c) {
                long j2 = j + ((long) (b2 & Byte.MAX_VALUE));
                if ((b2 & 128) == 0) {
                    if (z) {
                        if (j2 < 40) {
                            stringBuffer.append('0');
                        } else if (j2 < 80) {
                            stringBuffer.append('1');
                            j2 -= 40;
                        } else {
                            stringBuffer.append('2');
                            j2 -= 80;
                        }
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j2);
                    j = 0;
                } else {
                    j = j2 << 7;
                }
            } else {
                BigInteger or = (bigInteger == null ? BigInteger.valueOf(j) : bigInteger).or(BigInteger.valueOf((long) (b2 & Byte.MAX_VALUE)));
                if ((b2 & 128) == 0) {
                    if (z) {
                        stringBuffer.append('2');
                        or = or.subtract(BigInteger.valueOf(80));
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(or);
                    j = 0;
                    bigInteger = null;
                } else {
                    bigInteger = or.shiftLeft(7);
                }
            }
        }
        this.f14405a = stringBuffer.toString();
        this.b = Arrays.b(bArr);
    }

    public ASN1ObjectIdentifier(String str) {
        if (str == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        } else if (c(str)) {
            this.f14405a = str;
        } else {
            throw new IllegalArgumentException("string " + str + " not an OID");
        }
    }

    ASN1ObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (a(str, 0)) {
            this.f14405a = aSN1ObjectIdentifier.b() + "." + str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not a valid OID branch");
    }

    public String b() {
        return this.f14405a;
    }

    public ASN1ObjectIdentifier b(String str) {
        return new ASN1ObjectIdentifier(this, str);
    }

    public boolean a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String b2 = b();
        String b3 = aSN1ObjectIdentifier.b();
        return b2.length() > b3.length() && b2.charAt(b3.length()) == '.' && b2.startsWith(b3);
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) ((((int) j) & 127) | 128);
        }
        byteArrayOutputStream.write(bArr, i, 9 - i);
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        byte[] bArr = new byte[bitLength];
        int i = bitLength - 1;
        BigInteger bigInteger2 = bigInteger;
        for (int i2 = i; i2 >= 0; i2--) {
            bArr[i2] = (byte) ((bigInteger2.intValue() & 127) | 128);
            bigInteger2 = bigInteger2.shiftRight(7);
        }
        bArr[i] = (byte) (bArr[i] & Byte.MAX_VALUE);
        byteArrayOutputStream.write(bArr, 0, bArr.length);
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream) {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.f14405a);
        int parseInt = Integer.parseInt(oIDTokenizer.b()) * 40;
        String b2 = oIDTokenizer.b();
        if (b2.length() <= 18) {
            a(byteArrayOutputStream, ((long) parseInt) + Long.parseLong(b2));
        } else {
            a(byteArrayOutputStream, new BigInteger(b2).add(BigInteger.valueOf((long) parseInt)));
        }
        while (oIDTokenizer.a()) {
            String b3 = oIDTokenizer.b();
            if (b3.length() <= 18) {
                a(byteArrayOutputStream, Long.parseLong(b3));
            } else {
                a(byteArrayOutputStream, new BigInteger(b3));
            }
        }
    }

    private synchronized byte[] d() {
        if (this.b == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            a(byteArrayOutputStream);
            this.b = byteArrayOutputStream.toByteArray();
        }
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public int e() throws IOException {
        int length = d().length;
        return StreamUtil.a(length) + 1 + length;
    }

    /* access modifiers changed from: package-private */
    public void a(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] d2 = d();
        aSN1OutputStream.b(6);
        aSN1OutputStream.a(d2.length);
        aSN1OutputStream.a(d2);
    }

    public int hashCode() {
        return this.f14405a.hashCode();
    }

    /* access modifiers changed from: package-private */
    public boolean a(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive == this) {
            return true;
        }
        if (!(aSN1Primitive instanceof ASN1ObjectIdentifier)) {
            return false;
        }
        return this.f14405a.equals(((ASN1ObjectIdentifier) aSN1Primitive).f14405a);
    }

    public String toString() {
        return b();
    }

    private static boolean a(String str, int i) {
        boolean z;
        char charAt;
        int length = str.length();
        do {
            z = false;
            while (true) {
                length--;
                if (length < i) {
                    return z;
                }
                charAt = str.charAt(length);
                if ('0' <= charAt && charAt <= '9') {
                    z = true;
                }
            }
            if (charAt != '.') {
                return false;
            }
        } while (z);
        return false;
    }

    private static boolean c(String str) {
        char charAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (charAt = str.charAt(0)) < '0' || charAt > '2') {
            return false;
        }
        return a(str, 2);
    }

    public ASN1ObjectIdentifier c() {
        OidHandle oidHandle = new OidHandle(d());
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.get(oidHandle);
        if (aSN1ObjectIdentifier != null) {
            return aSN1ObjectIdentifier;
        }
        ASN1ObjectIdentifier putIfAbsent = d.putIfAbsent(oidHandle, this);
        return putIfAbsent == null ? this : putIfAbsent;
    }

    private static class OidHandle {

        /* renamed from: a  reason: collision with root package name */
        private final int f14406a;
        private final byte[] b;

        OidHandle(byte[] bArr) {
            this.f14406a = Arrays.a(bArr);
            this.b = bArr;
        }

        public int hashCode() {
            return this.f14406a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof OidHandle) {
                return Arrays.a(this.b, ((OidHandle) obj).b);
            }
            return false;
        }
    }

    static ASN1ObjectIdentifier a(byte[] bArr) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) d.get(new OidHandle(bArr));
        return aSN1ObjectIdentifier == null ? new ASN1ObjectIdentifier(bArr) : aSN1ObjectIdentifier;
    }
}
