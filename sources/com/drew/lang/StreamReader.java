package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class StreamReader extends SequentialReader {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5203a = (!StreamReader.class.desiredAssertionStatus());
    @NotNull
    private final InputStream b;
    private long c;

    public long a() {
        return this.c;
    }

    public StreamReader(@NotNull InputStream inputStream) {
        if (inputStream != null) {
            this.b = inputStream;
            this.c = 0;
            return;
        }
        throw new NullPointerException();
    }

    public byte b() throws IOException {
        int read = this.b.read();
        if (read != -1) {
            this.c++;
            return (byte) read;
        }
        throw new EOFException("End of data reached.");
    }

    @NotNull
    public byte[] a(int i) throws IOException {
        byte[] bArr = new byte[i];
        a(bArr, 0, i);
        return bArr;
    }

    public void a(@NotNull byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 != i2) {
            int read = this.b.read(bArr, i + i3, i2 - i3);
            if (read != -1) {
                i3 += read;
                if (!f5203a && i3 > i2) {
                    throw new AssertionError();
                }
            } else {
                throw new EOFException("End of data reached.");
            }
        }
        this.c += (long) i3;
    }

    public void a(long j) throws IOException {
        if (j >= 0) {
            long c2 = c(j);
            if (c2 != j) {
                throw new EOFException(String.format("Unable to skip. Requested %d bytes but skipped %d.", new Object[]{Long.valueOf(j), Long.valueOf(c2)}));
            }
            return;
        }
        throw new IllegalArgumentException("n must be zero or greater.");
    }

    public boolean b(long j) throws IOException {
        if (j >= 0) {
            return c(j) == j;
        }
        throw new IllegalArgumentException("n must be zero or greater.");
    }

    public int c() {
        try {
            return this.b.available();
        } catch (IOException unused) {
            return 0;
        }
    }

    private long c(long j) throws IOException {
        long j2 = 0;
        while (j2 != j) {
            long skip = this.b.skip(j - j2);
            if (f5203a || skip >= 0) {
                j2 += skip;
                if (skip == 0) {
                    break;
                }
            } else {
                throw new AssertionError();
            }
        }
        this.c += j2;
        return j2;
    }
}
