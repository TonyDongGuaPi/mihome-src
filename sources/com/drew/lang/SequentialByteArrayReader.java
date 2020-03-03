package com.drew.lang;

import com.drew.lang.annotations.NotNull;
import java.io.EOFException;
import java.io.IOException;

public class SequentialByteArrayReader extends SequentialReader {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f5201a;
    private int b;

    public long a() {
        return (long) this.b;
    }

    public SequentialByteArrayReader(@NotNull byte[] bArr) {
        this(bArr, 0);
    }

    public SequentialByteArrayReader(@NotNull byte[] bArr, int i) {
        if (bArr != null) {
            this.f5201a = bArr;
            this.b = i;
            return;
        }
        throw new NullPointerException();
    }

    public byte b() throws IOException {
        if (this.b < this.f5201a.length) {
            byte[] bArr = this.f5201a;
            int i = this.b;
            this.b = i + 1;
            return bArr[i];
        }
        throw new EOFException("End of data reached.");
    }

    @NotNull
    public byte[] a(int i) throws IOException {
        if (this.b + i <= this.f5201a.length) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.f5201a, this.b, bArr, 0, i);
            this.b += i;
            return bArr;
        }
        throw new EOFException("End of data reached.");
    }

    public void a(@NotNull byte[] bArr, int i, int i2) throws IOException {
        if (this.b + i2 <= this.f5201a.length) {
            System.arraycopy(this.f5201a, this.b, bArr, i, i2);
            this.b += i2;
            return;
        }
        throw new EOFException("End of data reached.");
    }

    public void a(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("n must be zero or greater.");
        } else if (((long) this.b) + j <= ((long) this.f5201a.length)) {
            this.b = (int) (((long) this.b) + j);
        } else {
            throw new EOFException("End of data reached.");
        }
    }

    public boolean b(long j) throws IOException {
        if (j >= 0) {
            this.b = (int) (((long) this.b) + j);
            if (this.b <= this.f5201a.length) {
                return true;
            }
            this.b = this.f5201a.length;
            return false;
        }
        throw new IllegalArgumentException("n must be zero or greater.");
    }

    public int c() {
        return this.f5201a.length - this.b;
    }
}
