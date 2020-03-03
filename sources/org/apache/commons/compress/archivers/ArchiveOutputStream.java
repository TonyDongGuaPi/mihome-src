package org.apache.commons.compress.archivers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public abstract class ArchiveOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    static final int f3198a = 255;
    private final byte[] b = new byte[1];
    private long c = 0;

    public abstract ArchiveEntry a(File file, String str) throws IOException;

    public abstract void a() throws IOException;

    public abstract void a(ArchiveEntry archiveEntry) throws IOException;

    public abstract void b() throws IOException;

    public boolean b(ArchiveEntry archiveEntry) {
        return true;
    }

    public void write(int i) throws IOException {
        this.b[0] = (byte) (i & 255);
        write(this.b, 0, 1);
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

    @Deprecated
    public int c() {
        return (int) this.c;
    }

    public long d() {
        return this.c;
    }
}
