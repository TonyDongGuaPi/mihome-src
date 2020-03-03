package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

class IndefiniteLengthInputStream extends LimitedInputStream {
    private int b;
    private int c;
    private boolean d = false;
    private boolean e = true;

    IndefiniteLengthInputStream(InputStream inputStream, int i) throws IOException {
        super(inputStream, i);
        this.b = inputStream.read();
        this.c = inputStream.read();
        if (this.c >= 0) {
            b();
            return;
        }
        throw new EOFException();
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.e = z;
        b();
    }

    private boolean b() {
        if (!this.d && this.e && this.b == 0 && this.c == 0) {
            this.d = true;
            b(true);
        }
        return this.d;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.e || i2 < 3) {
            return super.read(bArr, i, i2);
        }
        if (this.d) {
            return -1;
        }
        int read = this.f14447a.read(bArr, i + 2, i2 - 2);
        if (read >= 0) {
            bArr[i] = (byte) this.b;
            bArr[i + 1] = (byte) this.c;
            this.b = this.f14447a.read();
            this.c = this.f14447a.read();
            if (this.c >= 0) {
                return read + 2;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public int read() throws IOException {
        if (b()) {
            return -1;
        }
        int read = this.f14447a.read();
        if (read >= 0) {
            int i = this.b;
            this.b = this.c;
            this.c = read;
            return i;
        }
        throw new EOFException();
    }
}
