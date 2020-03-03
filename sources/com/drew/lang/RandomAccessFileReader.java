package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.SuppressWarnings;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileReader extends RandomAccessReader {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5198a = (!RandomAccessFileReader.class.desiredAssertionStatus());
    @NotNull
    private final RandomAccessFile b;
    private final long c;
    private int d;
    private final int e;

    @SuppressWarnings(justification = "Design intent", value = "EI_EXPOSE_REP2")
    public RandomAccessFileReader(@NotNull RandomAccessFile randomAccessFile) throws IOException {
        this(randomAccessFile, 0);
    }

    @SuppressWarnings(justification = "Design intent", value = "EI_EXPOSE_REP2")
    public RandomAccessFileReader(@NotNull RandomAccessFile randomAccessFile, int i) throws IOException {
        if (randomAccessFile != null) {
            this.b = randomAccessFile;
            this.e = i;
            this.c = this.b.length();
            return;
        }
        throw new NullPointerException();
    }

    public int a(int i) {
        return i + this.e;
    }

    public long a() {
        return this.c;
    }

    public byte b(int i) throws IOException {
        if (i != this.d) {
            o(i);
        }
        int read = this.b.read();
        if (read < 0) {
            throw new BufferBoundsException("Unexpected end of file encountered.");
        } else if (f5198a || read <= 255) {
            this.d++;
            return (byte) read;
        } else {
            throw new AssertionError();
        }
    }

    @NotNull
    public byte[] c(int i, int i2) throws IOException {
        a(i, i2);
        if (i != this.d) {
            o(i);
        }
        byte[] bArr = new byte[i2];
        int read = this.b.read(bArr);
        this.d += read;
        if (read == i2) {
            return bArr;
        }
        throw new BufferBoundsException("Unexpected end of file encountered.");
    }

    private void o(int i) throws IOException {
        if (i != this.d) {
            this.b.seek((long) i);
            this.d = i;
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(int i, int i2) throws IOException {
        return i2 >= 0 && i >= 0 && (((long) i) + ((long) i2)) - 1 < this.c;
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2) throws IOException {
        if (!b(i, i2)) {
            throw new BufferBoundsException(i, i2, this.c);
        }
    }
}
