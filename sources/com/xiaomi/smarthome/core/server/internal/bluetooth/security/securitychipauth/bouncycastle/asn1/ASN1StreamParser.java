package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ASN1StreamParser {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f14414a;
    private final int b;
    private final byte[][] c;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.a(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i) {
        this.f14414a = inputStream;
        this.b = i;
        this.c = new byte[11][];
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable a(int i) throws IOException {
        if (i == 4) {
            return new BEROctetStringParser(this);
        }
        if (i == 8) {
            return new DERExternalParser(this);
        }
        switch (i) {
            case 16:
                return new BERSequenceParser(this);
            case 17:
                return new BERSetParser(this);
            default:
                throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i));
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Encodable a(boolean z, int i) throws IOException {
        if (!(this.f14414a instanceof IndefiniteLengthInputStream)) {
            if (z) {
                if (i == 4) {
                    return new BEROctetStringParser(this);
                }
                switch (i) {
                    case 16:
                        return new DERSequenceParser(this);
                    case 17:
                        return new DERSetParser(this);
                }
            } else if (i == 4) {
                return new DEROctetStringParser((DefiniteLengthInputStream) this.f14414a);
            } else {
                switch (i) {
                    case 16:
                        throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
                    case 17:
                        throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
                }
            }
            throw new ASN1Exception("implicit tagging not implemented");
        } else if (z) {
            return a(i);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1Primitive b(boolean z, int i) throws IOException {
        if (!z) {
            return new DERTaggedObject(false, i, new DEROctetString(((DefiniteLengthInputStream) this.f14414a).b()));
        }
        ASN1EncodableVector b2 = b();
        if (this.f14414a instanceof IndefiniteLengthInputStream) {
            if (b2.a() == 1) {
                return new BERTaggedObject(true, i, b2.a(0));
            }
            return new BERTaggedObject(false, i, BERFactory.a(b2));
        } else if (b2.a() == 1) {
            return new DERTaggedObject(true, i, b2.a(0));
        } else {
            return new DERTaggedObject(false, i, DERFactory.a(b2));
        }
    }

    public ASN1Encodable a() throws IOException {
        int read = this.f14414a.read();
        if (read == -1) {
            return null;
        }
        boolean z = false;
        a(false);
        int a2 = ASN1InputStream.a(this.f14414a, read);
        if ((read & 32) != 0) {
            z = true;
        }
        int b2 = ASN1InputStream.b(this.f14414a, this.b);
        if (b2 >= 0) {
            DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this.f14414a, b2);
            if ((read & 64) != 0) {
                return new DERApplicationSpecific(z, a2, definiteLengthInputStream.b());
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(z, a2, new ASN1StreamParser((InputStream) definiteLengthInputStream));
            }
            if (z) {
                if (a2 == 4) {
                    return new BEROctetStringParser(new ASN1StreamParser((InputStream) definiteLengthInputStream));
                }
                if (a2 == 8) {
                    return new DERExternalParser(new ASN1StreamParser((InputStream) definiteLengthInputStream));
                }
                switch (a2) {
                    case 16:
                        return new DERSequenceParser(new ASN1StreamParser((InputStream) definiteLengthInputStream));
                    case 17:
                        return new DERSetParser(new ASN1StreamParser((InputStream) definiteLengthInputStream));
                    default:
                        throw new IOException("unknown tag " + a2 + " encountered");
                }
            } else if (a2 == 4) {
                return new DEROctetStringParser(definiteLengthInputStream);
            } else {
                try {
                    return ASN1InputStream.a(a2, definiteLengthInputStream, this.c);
                } catch (IllegalArgumentException e) {
                    throw new ASN1Exception("corrupted stream detected", e);
                }
            }
        } else if (z) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this.f14414a, this.b), this.b);
            if ((read & 64) != 0) {
                return new BERApplicationSpecificParser(a2, aSN1StreamParser);
            }
            if ((read & 128) != 0) {
                return new BERTaggedObjectParser(true, a2, aSN1StreamParser);
            }
            return aSN1StreamParser.a(a2);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    private void a(boolean z) {
        if (this.f14414a instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) this.f14414a).a(z);
        }
    }

    /* access modifiers changed from: package-private */
    public ASN1EncodableVector b() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Encodable a2 = a();
            if (a2 == null) {
                return aSN1EncodableVector;
            }
            if (a2 instanceof InMemoryRepresentable) {
                aSN1EncodableVector.a((ASN1Encodable) ((InMemoryRepresentable) a2).f());
            } else {
                aSN1EncodableVector.a((ASN1Encodable) a2.k());
            }
        }
    }
}
