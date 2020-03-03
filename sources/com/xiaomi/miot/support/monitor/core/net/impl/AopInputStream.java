package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.core.net.i.IStreamCompleteListener;
import java.io.IOException;
import java.io.InputStream;

public class AopInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final String f11471a = "AopInputStream";
    private final InputStream b;
    private long c = 0;
    private IStreamCompleteListener d = null;

    public AopInputStream(InputStream inputStream) {
        this.b = inputStream;
    }

    public void a(IStreamCompleteListener iStreamCompleteListener) {
        this.d = iStreamCompleteListener;
    }

    public void a() {
        this.d = null;
    }

    private void b() {
        if (this.d != null) {
            this.d.a(this.c);
        }
    }

    private void c() {
        if (this.d != null) {
            this.d.c(this.c);
        }
    }

    public int read() throws IOException {
        try {
            int read = this.b.read();
            if (read >= 0) {
                this.c += (long) read;
            } else {
                b();
            }
            return read;
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public int read(byte[] bArr) throws IOException {
        try {
            int read = this.b.read(bArr, 0, bArr.length);
            if (read >= 0) {
                this.c += (long) read;
            } else {
                b();
            }
            return read;
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int read = this.b.read(bArr, i, i2);
            if (read >= 0) {
                this.c += (long) read;
            } else {
                b();
            }
            return read;
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public long skip(long j) throws IOException {
        try {
            long skip = this.b.skip(j);
            this.c += skip;
            return skip;
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public int available() throws IOException {
        try {
            return this.b.available();
        } catch (IOException e) {
            throw e;
        }
    }

    public void close() throws IOException {
        try {
            this.b.close();
            b();
        } catch (IOException e) {
            c();
            throw e;
        }
    }

    public void mark(int i) {
        if (markSupported()) {
            this.b.mark(i);
        }
    }

    public boolean markSupported() {
        return this.b.markSupported();
    }

    public void reset() throws IOException {
        if (markSupported()) {
            try {
                this.b.reset();
            } catch (IOException e) {
                throw e;
            }
        }
    }
}
