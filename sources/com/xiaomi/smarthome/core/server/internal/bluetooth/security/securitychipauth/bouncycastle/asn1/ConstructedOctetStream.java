package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

class ConstructedOctetStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final ASN1StreamParser f14425a;
    private boolean b = true;
    private InputStream c;

    ConstructedOctetStream(ASN1StreamParser aSN1StreamParser) {
        this.f14425a = aSN1StreamParser;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        ASN1OctetStringParser aSN1OctetStringParser;
        int i3 = 0;
        if (this.c == null) {
            if (!this.b || (aSN1OctetStringParser = (ASN1OctetStringParser) this.f14425a.a()) == null) {
                return -1;
            }
            this.b = false;
            this.c = aSN1OctetStringParser.b();
        }
        while (true) {
            int read = this.c.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                ASN1OctetStringParser aSN1OctetStringParser2 = (ASN1OctetStringParser) this.f14425a.a();
                if (aSN1OctetStringParser2 == null) {
                    this.c = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                this.c = aSN1OctetStringParser2.b();
            }
        }
    }

    public int read() throws IOException {
        ASN1OctetStringParser aSN1OctetStringParser;
        if (this.c == null) {
            if (!this.b || (aSN1OctetStringParser = (ASN1OctetStringParser) this.f14425a.a()) == null) {
                return -1;
            }
            this.b = false;
            this.c = aSN1OctetStringParser.b();
        }
        while (true) {
            int read = this.c.read();
            if (read >= 0) {
                return read;
            }
            ASN1OctetStringParser aSN1OctetStringParser2 = (ASN1OctetStringParser) this.f14425a.a();
            if (aSN1OctetStringParser2 == null) {
                this.c = null;
                return -1;
            }
            this.c = aSN1OctetStringParser2.b();
        }
    }
}
