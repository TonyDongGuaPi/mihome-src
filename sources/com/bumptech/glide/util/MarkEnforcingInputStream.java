package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkEnforcingInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5103a = Integer.MIN_VALUE;
    private static final int b = -1;
    private int c = Integer.MIN_VALUE;

    public MarkEnforcingInputStream(@NonNull InputStream inputStream) {
        super(inputStream);
    }

    public synchronized void mark(int i) {
        super.mark(i);
        this.c = i;
    }

    public int read() throws IOException {
        if (a(1) == -1) {
            return -1;
        }
        int read = super.read();
        b(1);
        return read;
    }

    public int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int a2 = (int) a((long) i2);
        if (a2 == -1) {
            return -1;
        }
        int read = super.read(bArr, i, a2);
        b((long) read);
        return read;
    }

    public synchronized void reset() throws IOException {
        super.reset();
        this.c = Integer.MIN_VALUE;
    }

    public long skip(long j) throws IOException {
        long a2 = a(j);
        if (a2 == -1) {
            return 0;
        }
        long skip = super.skip(a2);
        b(skip);
        return skip;
    }

    public int available() throws IOException {
        if (this.c == Integer.MIN_VALUE) {
            return super.available();
        }
        return Math.min(this.c, super.available());
    }

    private long a(long j) {
        if (this.c == 0) {
            return -1;
        }
        return (this.c == Integer.MIN_VALUE || j <= ((long) this.c)) ? j : (long) this.c;
    }

    private void b(long j) {
        if (this.c != Integer.MIN_VALUE && j != -1) {
            this.c = (int) (((long) this.c) - j);
        }
    }
}
