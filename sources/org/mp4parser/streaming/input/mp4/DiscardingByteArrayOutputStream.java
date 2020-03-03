package org.mp4parser.streaming.input.mp4;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.mp4parser.tools.CastUtils;

public class DiscardingByteArrayOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f4091a;
    protected int b;
    protected long c;

    public void close() throws IOException {
    }

    public DiscardingByteArrayOutputStream() {
        this(32);
    }

    public DiscardingByteArrayOutputStream(int i) {
        this.c = 0;
        if (i >= 0) {
            this.f4091a = new byte[i];
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i);
    }

    public byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        try {
            System.arraycopy(this.f4091a, CastUtils.a(j - this.c), bArr, 0, i);
            return bArr;
        } catch (ArrayIndexOutOfBoundsException e) {
            PrintStream printStream = System.out;
            printStream.println("start: " + j + " count: " + i + " startOffset:" + this.c + " count:" + i + " len(buf):" + this.f4091a.length + " (start - startOffset):" + (j - this.c));
            throw e;
        }
    }

    private void a(int i) {
        if (i - this.f4091a.length > 0) {
            b(i);
        }
    }

    private void b(int i) {
        int length = this.f4091a.length << 1;
        if (length - i < 0) {
            length = i;
        }
        if (length < 0) {
            if (i >= 0) {
                length = Integer.MAX_VALUE;
            } else {
                throw new OutOfMemoryError();
            }
        }
        this.f4091a = Arrays.copyOf(this.f4091a, length);
    }

    public synchronized void write(int i) {
        a(this.b + 1);
        this.f4091a[this.b] = (byte) i;
        this.b++;
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        if (i >= 0) {
            if (i <= bArr.length && i2 >= 0 && (i + i2) - bArr.length <= 0) {
                a(this.b + i2);
                System.arraycopy(bArr, i, this.f4091a, this.b, i2);
                this.b += i2;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public synchronized void a() {
        this.b = 0;
    }

    public synchronized byte[] b() {
        return Arrays.copyOf(this.f4091a, this.b);
    }

    public synchronized int c() {
        return this.b;
    }

    public synchronized String toString() {
        return new String(this.f4091a, 0, this.b);
    }

    public synchronized long d() {
        return this.c + ((long) this.b);
    }

    public synchronized void a(long j) {
        System.arraycopy(this.f4091a, CastUtils.a(j - this.c), this.f4091a, 0, CastUtils.a(((long) this.f4091a.length) - (j - this.c)));
        this.b = (int) (((long) this.b) - (j - this.c));
        this.c = j;
    }
}
