package com.xiaomi.infra.galaxy.fds.android.util;

import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ObjectInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private final ProgressListener f10148a;
    private final ObjectMetadata b;
    private long c;
    private long d;

    public ObjectInputStream(InputStream inputStream, ObjectMetadata objectMetadata, ProgressListener progressListener) {
        super(inputStream);
        this.b = objectMetadata;
        this.f10148a = progressListener;
    }

    private void a(boolean z) {
        if (this.f10148a != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!z || currentTimeMillis - this.c >= this.f10148a.a()) {
                this.c = currentTimeMillis;
                this.f10148a.a(this.d, this.b.b());
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.d += (long) read;
            a(true);
        }
        return read;
    }

    public int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.d++;
            a(true);
        }
        return read;
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.d = 0;
        a(true);
    }

    public void close() throws IOException {
        super.close();
        a(false);
    }
}
