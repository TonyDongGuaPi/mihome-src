package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.io.Streams;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] b = new byte[0];
    private final int c;
    private int d;

    DefiniteLengthInputStream(InputStream inputStream, int i) {
        super(inputStream, i);
        if (i >= 0) {
            this.c = i;
            this.d = i;
            if (i == 0) {
                b(true);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("negative lengths not allowed");
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.d;
    }

    public int read() throws IOException {
        if (this.d == 0) {
            return -1;
        }
        int read = this.f14447a.read();
        if (read >= 0) {
            int i = this.d - 1;
            this.d = i;
            if (i == 0) {
                b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.c + " object truncated by " + this.d);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.d == 0) {
            return -1;
        }
        int read = this.f14447a.read(bArr, i, Math.min(i2, this.d));
        if (read >= 0) {
            int i3 = this.d - read;
            this.d = i3;
            if (i3 == 0) {
                b(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.c + " object truncated by " + this.d);
    }

    /* access modifiers changed from: package-private */
    public byte[] b() throws IOException {
        if (this.d == 0) {
            return b;
        }
        byte[] bArr = new byte[this.d];
        int a2 = this.d - Streams.a(this.f14447a, bArr);
        this.d = a2;
        if (a2 == 0) {
            b(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this.c + " object truncated by " + this.d);
    }
}
