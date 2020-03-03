package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.annotation.Nullable;

final class MultiInputStream extends InputStream {

    /* renamed from: in  reason: collision with root package name */
    private InputStream f5683in;
    private Iterator<? extends ByteSource> it;

    public boolean markSupported() {
        return false;
    }

    public MultiInputStream(Iterator<? extends ByteSource> it2) throws IOException {
        this.it = (Iterator) Preconditions.checkNotNull(it2);
        advance();
    }

    public void close() throws IOException {
        if (this.f5683in != null) {
            try {
                this.f5683in.close();
            } finally {
                this.f5683in = null;
            }
        }
    }

    private void advance() throws IOException {
        close();
        if (this.it.hasNext()) {
            this.f5683in = ((ByteSource) this.it.next()).openStream();
        }
    }

    public int available() throws IOException {
        if (this.f5683in == null) {
            return 0;
        }
        return this.f5683in.available();
    }

    public int read() throws IOException {
        if (this.f5683in == null) {
            return -1;
        }
        int read = this.f5683in.read();
        if (read != -1) {
            return read;
        }
        advance();
        return read();
    }

    public int read(@Nullable byte[] bArr, int i, int i2) throws IOException {
        if (this.f5683in == null) {
            return -1;
        }
        int read = this.f5683in.read(bArr, i, i2);
        if (read != -1) {
            return read;
        }
        advance();
        return read(bArr, i, i2);
    }

    public long skip(long j) throws IOException {
        if (this.f5683in == null || j <= 0) {
            return 0;
        }
        long skip = this.f5683in.skip(j);
        if (skip != 0) {
            return skip;
        }
        if (read() == -1) {
            return 0;
        }
        return this.f5683in.skip(j - 1) + 1;
    }
}
