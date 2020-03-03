package org.apache.commons.compress.archivers;

import java.io.IOException;
import java.io.InputStream;

public abstract class ArchiveInputStream extends InputStream {
    private static final int b = 255;

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f3197a = new byte[1];
    private long c = 0;

    public abstract ArchiveEntry a() throws IOException;

    public boolean a(ArchiveEntry archiveEntry) {
        return true;
    }

    public int read() throws IOException {
        if (read(this.f3197a, 0, 1) == -1) {
            return -1;
        }
        return this.f3197a[0] & 255;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        a((long) i);
    }

    /* access modifiers changed from: protected */
    public void a(long j) {
        if (j != -1) {
            this.c += j;
        }
    }

    /* access modifiers changed from: protected */
    public void b(long j) {
        this.c -= j;
    }

    @Deprecated
    public int b() {
        return (int) this.c;
    }

    public long c() {
        return this.c;
    }
}
