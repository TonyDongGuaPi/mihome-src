package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class SnappyCompressorInputStream extends CompressorInputStream {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3338a = 32768;
    private static final int b = 3;
    private final byte[] c;
    private int d;
    private int e;
    private final int f;
    private final InputStream g;
    private final int h;
    private int i;
    private final byte[] j;
    private boolean k;

    public SnappyCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 32768);
    }

    public SnappyCompressorInputStream(InputStream inputStream, int i2) throws IOException {
        this.j = new byte[1];
        this.k = false;
        this.g = inputStream;
        this.f = i2;
        this.c = new byte[(i2 * 3)];
        this.e = 0;
        this.d = 0;
        int f2 = (int) f();
        this.h = f2;
        this.i = f2;
    }

    public int read() throws IOException {
        if (read(this.j, 0, 1) == -1) {
            return -1;
        }
        return this.j[0] & 255;
    }

    public void close() throws IOException {
        this.g.close();
    }

    public int available() {
        return this.d - this.e;
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.k) {
            return -1;
        }
        int available = available();
        if (i3 > available) {
            b(i3 - available);
        }
        int min = Math.min(i3, available());
        if (min == 0 && i3 > 0) {
            return -1;
        }
        System.arraycopy(this.c, this.e, bArr, i2, min);
        this.e += min;
        if (this.e > this.f) {
            d();
        }
        return min;
    }

    private void b(int i2) throws IOException {
        if (this.i == 0) {
            this.k = true;
        }
        int min = Math.min(i2, this.i);
        while (min > 0) {
            int e2 = e();
            int i3 = 0;
            switch (e2 & 3) {
                case 0:
                    i3 = c(e2);
                    if (!d(i3)) {
                        break;
                    } else {
                        return;
                    }
                case 1:
                    i3 = ((e2 >> 2) & 7) + 4;
                    if (!a(((long) ((e2 & 224) << 3)) | ((long) e()), i3)) {
                        break;
                    } else {
                        return;
                    }
                case 2:
                    i3 = (e2 >> 2) + 1;
                    if (!a(((long) e()) | ((long) (e() << 8)), i3)) {
                        break;
                    } else {
                        return;
                    }
                case 3:
                    i3 = (e2 >> 2) + 1;
                    if (!a(((long) e()) | ((long) (e() << 8)) | ((long) (e() << 16)) | (((long) e()) << 24), i3)) {
                        break;
                    } else {
                        return;
                    }
            }
            min -= i3;
            this.i -= i3;
        }
    }

    private void d() {
        System.arraycopy(this.c, this.f, this.c, 0, this.f * 2);
        this.d -= this.f;
        this.e -= this.f;
    }

    private int c(int i2) throws IOException {
        int i3 = i2 >> 2;
        switch (i3) {
            case 60:
                i3 = e();
                break;
            case 61:
                i3 = e() | (e() << 8);
                break;
            case 62:
                i3 = e() | (e() << 8) | (e() << 16);
                break;
            case 63:
                i3 = (int) (((long) (e() | (e() << 8) | (e() << 16))) | (((long) e()) << 24));
                break;
        }
        return i3 + 1;
    }

    private boolean d(int i2) throws IOException {
        int a2 = IOUtils.a(this.g, this.c, this.d, i2);
        a(a2);
        if (i2 == a2) {
            this.d += i2;
            return this.d >= this.f * 2;
        }
        throw new IOException("Premature end of stream");
    }

    private boolean a(long j2, int i2) throws IOException {
        if (j2 <= ((long) this.f)) {
            int i3 = (int) j2;
            if (i3 == 1) {
                byte b2 = this.c[this.d - 1];
                for (int i4 = 0; i4 < i2; i4++) {
                    byte[] bArr = this.c;
                    int i5 = this.d;
                    this.d = i5 + 1;
                    bArr[i5] = b2;
                }
            } else if (i2 < i3) {
                System.arraycopy(this.c, this.d - i3, this.c, this.d, i2);
                this.d += i2;
            } else {
                int i6 = i2 / i3;
                int i7 = i2 - (i3 * i6);
                while (true) {
                    int i8 = i6 - 1;
                    if (i6 == 0) {
                        break;
                    }
                    System.arraycopy(this.c, this.d - i3, this.c, this.d, i3);
                    this.d += i3;
                    i6 = i8;
                }
                if (i7 > 0) {
                    System.arraycopy(this.c, this.d - i3, this.c, this.d, i7);
                    this.d += i7;
                }
            }
            if (this.d >= this.f * 2) {
                return true;
            }
            return false;
        }
        throw new IOException("Offset is larger than block size");
    }

    private int e() throws IOException {
        int read = this.g.read();
        if (read != -1) {
            a(1);
            return read & 255;
        }
        throw new IOException("Premature end of stream");
    }

    private long f() throws IOException {
        int i2 = 0;
        long j2 = 0;
        while (true) {
            int e2 = e();
            int i3 = i2 + 1;
            j2 |= (long) ((e2 & 127) << (i2 * 7));
            if ((e2 & 128) == 0) {
                return j2;
            }
            i2 = i3;
        }
    }

    public int a() {
        return this.h;
    }
}
