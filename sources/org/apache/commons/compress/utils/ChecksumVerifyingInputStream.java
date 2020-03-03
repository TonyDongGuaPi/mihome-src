package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;

public class ChecksumVerifyingInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final InputStream f3348a;
    private long b;
    private final long c;
    private final Checksum d;

    public ChecksumVerifyingInputStream(Checksum checksum, InputStream inputStream, long j, long j2) {
        this.d = checksum;
        this.f3348a = inputStream;
        this.c = j2;
        this.b = j;
    }

    public int read() throws IOException {
        if (this.b <= 0) {
            return -1;
        }
        int read = this.f3348a.read();
        if (read >= 0) {
            this.d.update(read);
            this.b--;
        }
        if (this.b != 0 || this.c == this.d.getValue()) {
            return read;
        }
        throw new IOException("Checksum verification failed");
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f3348a.read(bArr, i, i2);
        if (read >= 0) {
            this.d.update(bArr, i, read);
            this.b -= (long) read;
        }
        if (this.b > 0 || this.c == this.d.getValue()) {
            return read;
        }
        throw new IOException("Checksum verification failed");
    }

    public long skip(long j) throws IOException {
        return read() >= 0 ? 1 : 0;
    }

    public void close() throws IOException {
        this.f3348a.close();
    }
}
