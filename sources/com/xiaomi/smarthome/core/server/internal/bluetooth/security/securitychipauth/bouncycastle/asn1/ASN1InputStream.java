package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.io.Streams;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final int C;
    private final boolean D;
    private final byte[][] E;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.a(inputStream));
    }

    public ASN1InputStream(byte[] bArr) {
        this((InputStream) new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.a(inputStream), z);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.C = i;
        this.D = z;
        this.E = new byte[11][];
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.C;
    }

    /* access modifiers changed from: protected */
    public int b() throws IOException {
        return b(this, this.C);
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr) throws IOException {
        if (Streams.a((InputStream) this, bArr) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    /* access modifiers changed from: protected */
    public ASN1Primitive a(int i, int i2, int i3) throws IOException {
        boolean z = (i & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i3);
        if ((i & 64) != 0) {
            return new DERApplicationSpecific(z, i2, definiteLengthInputStream.b());
        }
        if ((i & 128) != 0) {
            return new ASN1StreamParser((InputStream) definiteLengthInputStream).b(z, i2);
        }
        if (!z) {
            return a(i2, definiteLengthInputStream, this.E);
        }
        if (i2 == 4) {
            ASN1EncodableVector a2 = a(definiteLengthInputStream);
            ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[a2.a()];
            for (int i4 = 0; i4 != aSN1OctetStringArr.length; i4++) {
                aSN1OctetStringArr[i4] = (ASN1OctetString) a2.a(i4);
            }
            return new BEROctetString(aSN1OctetStringArr);
        } else if (i2 == 8) {
            return new DERExternal(a(definiteLengthInputStream));
        } else {
            switch (i2) {
                case 16:
                    if (this.D) {
                        return new LazyEncodedSequence(definiteLengthInputStream.b());
                    }
                    return DERFactory.a(a(definiteLengthInputStream));
                case 17:
                    return DERFactory.b(a(definiteLengthInputStream));
                default:
                    throw new IOException("unknown tag " + i2 + " encountered");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1EncodableVector c() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Primitive d = d();
            if (d == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.a((ASN1Encodable) d);
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1EncodableVector a(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        return new ASN1InputStream((InputStream) definiteLengthInputStream).c();
    }

    public ASN1Primitive d() throws IOException {
        int read = read();
        if (read > 0) {
            int a2 = a((InputStream) this, read);
            boolean z = (read & 32) != 0;
            int b = b();
            if (b >= 0) {
                try {
                    return a(read, a2, b);
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            } else if (z) {
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.C), this.C);
                if ((read & 64) != 0) {
                    return new BERApplicationSpecificParser(a2, aSN1StreamParser).f();
                }
                if ((read & 128) != 0) {
                    return new BERTaggedObjectParser(true, a2, aSN1StreamParser).f();
                }
                if (a2 == 4) {
                    return new BEROctetStringParser(aSN1StreamParser).f();
                }
                if (a2 == 8) {
                    return new DERExternalParser(aSN1StreamParser).f();
                }
                switch (a2) {
                    case 16:
                        return new BERSequenceParser(aSN1StreamParser).f();
                    case 17:
                        return new BERSetParser(aSN1StreamParser).f();
                    default:
                        throw new IOException("unknown BER object encountered");
                }
            } else {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
        } else if (read != 0) {
            return null;
        } else {
            throw new IOException("unexpected end-of-contents marker");
        }
    }

    static int a(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 != 31) {
            return i2;
        }
        int i3 = 0;
        int read = inputStream.read();
        if ((read & 127) != 0) {
            while (read >= 0 && (read & 128) != 0) {
                i3 = (i3 | (read & 127)) << 7;
                read = inputStream.read();
            }
            if (read >= 0) {
                return i3 | (read & 127);
            }
            throw new EOFException("EOF found inside tag value.");
        }
        throw new IOException("corrupted stream - invalid high tag number found");
    }

    static int b(InputStream inputStream, int i) throws IOException {
        int read = inputStream.read();
        if (read < 0) {
            throw new EOFException("EOF found when length expected");
        } else if (read == 128) {
            return -1;
        } else {
            if (read <= 127) {
                return read;
            }
            int i2 = read & 127;
            if (i2 <= 4) {
                int i3 = 0;
                int i4 = 0;
                while (i3 < i2) {
                    int read2 = inputStream.read();
                    if (read2 >= 0) {
                        i4 = (i4 << 8) + read2;
                        i3++;
                    } else {
                        throw new EOFException("EOF found reading length");
                    }
                }
                if (i4 < 0) {
                    throw new IOException("corrupted stream - negative length found");
                } else if (i4 < i) {
                    return i4;
                } else {
                    throw new IOException("corrupted stream - out of bounds length found");
                }
            } else {
                throw new IOException("DER length more than 4 bytes: " + i2);
            }
        }
    }

    private static byte[] a(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        int a2 = definiteLengthInputStream.a();
        if (definiteLengthInputStream.a() >= bArr.length) {
            return definiteLengthInputStream.b();
        }
        byte[] bArr2 = bArr[a2];
        if (bArr2 == null) {
            bArr2 = new byte[a2];
            bArr[a2] = bArr2;
        }
        Streams.a((InputStream) definiteLengthInputStream, bArr2);
        return bArr2;
    }

    private static char[] b(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int read;
        int a2 = definiteLengthInputStream.a() / 2;
        char[] cArr = new char[a2];
        for (int i = 0; i < a2; i++) {
            int read2 = definiteLengthInputStream.read();
            if (read2 < 0 || (read = definiteLengthInputStream.read()) < 0) {
                break;
            }
            cArr[i] = (char) ((read2 << 8) | (read & 255));
        }
        return cArr;
    }

    static ASN1Primitive a(int i, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        if (i == 10) {
            return ASN1Enumerated.a(a(definiteLengthInputStream, bArr));
        }
        if (i == 12) {
            return new DERUTF8String(definiteLengthInputStream.b());
        }
        if (i == 30) {
            return new DERBMPString(b(definiteLengthInputStream));
        }
        switch (i) {
            case 1:
                return ASN1Boolean.a(a(definiteLengthInputStream, bArr));
            case 2:
                return new ASN1Integer(definiteLengthInputStream.b(), false);
            case 3:
                return ASN1BitString.a(definiteLengthInputStream.a(), (InputStream) definiteLengthInputStream);
            case 4:
                return new DEROctetString(definiteLengthInputStream.b());
            case 5:
                return DERNull.f14433a;
            case 6:
                return ASN1ObjectIdentifier.a(a(definiteLengthInputStream, bArr));
            default:
                switch (i) {
                    case 18:
                        return new DERNumericString(definiteLengthInputStream.b());
                    case 19:
                        return new DERPrintableString(definiteLengthInputStream.b());
                    case 20:
                        return new DERT61String(definiteLengthInputStream.b());
                    case 21:
                        return new DERVideotexString(definiteLengthInputStream.b());
                    case 22:
                        return new DERIA5String(definiteLengthInputStream.b());
                    case 23:
                        return new ASN1UTCTime(definiteLengthInputStream.b());
                    case 24:
                        return new ASN1GeneralizedTime(definiteLengthInputStream.b());
                    case 25:
                        return new DERGraphicString(definiteLengthInputStream.b());
                    case 26:
                        return new DERVisibleString(definiteLengthInputStream.b());
                    case 27:
                        return new DERGeneralString(definiteLengthInputStream.b());
                    case 28:
                        return new DERUniversalString(definiteLengthInputStream.b());
                    default:
                        throw new IOException("unknown tag " + i + " encountered");
                }
        }
    }
}
