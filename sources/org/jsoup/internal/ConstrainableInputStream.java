package org.jsoup.internal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.Validate;

public final class ConstrainableInputStream extends BufferedInputStream {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3655a = 32768;
    private final boolean b;
    private final int c;
    private long d;
    private long e = 0;
    private int f;
    private boolean g;

    private ConstrainableInputStream(InputStream inputStream, int i, int i2) {
        super(inputStream, i);
        boolean z = false;
        Validate.a(i2 >= 0);
        this.c = i2;
        this.f = i2;
        this.b = i2 != 0 ? true : z;
        this.d = System.nanoTime();
    }

    public static ConstrainableInputStream a(InputStream inputStream, int i, int i2) {
        if (inputStream instanceof ConstrainableInputStream) {
            return (ConstrainableInputStream) inputStream;
        }
        return new ConstrainableInputStream(inputStream, i, i2);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.g || (this.b && this.f <= 0)) {
            return -1;
        }
        if (Thread.interrupted()) {
            this.g = true;
            return -1;
        } else if (!a()) {
            if (this.b && i2 > this.f) {
                i2 = this.f;
            }
            try {
                int read = super.read(bArr, i, i2);
                this.f -= read;
                return read;
            } catch (SocketTimeoutException unused) {
                return 0;
            }
        } else {
            throw new SocketTimeoutException("Read timeout");
        }
    }

    public ByteBuffer a(int i) throws IOException {
        boolean z = true;
        Validate.a(i >= 0, "maxSize must be 0 (unlimited) or larger");
        if (i <= 0) {
            z = false;
        }
        int i2 = 32768;
        if (z && i < 32768) {
            i2 = i;
        }
        byte[] bArr = new byte[i2];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2);
        while (true) {
            int read = read(bArr);
            if (read == -1) {
                break;
            }
            if (z) {
                if (read >= i) {
                    byteArrayOutputStream.write(bArr, 0, i);
                    break;
                }
                i -= read;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    public void reset() throws IOException {
        super.reset();
        this.f = this.c - this.markpos;
    }

    public ConstrainableInputStream a(long j, long j2) {
        this.d = j;
        this.e = j2 * 1000000;
        return this;
    }

    private boolean a() {
        if (this.e != 0 && System.nanoTime() - this.d > this.e) {
            return true;
        }
        return false;
    }
}
