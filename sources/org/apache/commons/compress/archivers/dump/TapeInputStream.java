package org.apache.commons.compress.archivers.dump;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.utils.IOUtils;

class TapeInputStream extends FilterInputStream {
    private static final int d = 1024;

    /* renamed from: a  reason: collision with root package name */
    private byte[] f3217a = new byte[1024];
    private int b = -1;
    private int c = 1024;
    private int e = 1024;
    private boolean f = false;
    private long g = 0;

    public TapeInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public void a(int i, boolean z) throws IOException {
        this.f = z;
        this.c = i * 1024;
        byte[] bArr = this.f3217a;
        this.f3217a = new byte[this.c];
        System.arraycopy(bArr, 0, this.f3217a, 0, 1024);
        a(this.f3217a, 1024, this.c - 1024);
        this.b = 0;
        this.e = 1024;
    }

    public int available() throws IOException {
        if (this.e < this.c) {
            return this.c - this.e;
        }
        return this.in.available();
    }

    public int read() throws IOException {
        throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 % 1024 == 0) {
            int i3 = 0;
            while (i3 < i2) {
                if (this.e == this.c && !a(true)) {
                    return -1;
                }
                int i4 = i2 - i3;
                if (this.e + i4 > this.c) {
                    i4 = this.c - this.e;
                }
                System.arraycopy(this.f3217a, this.e, bArr, i, i4);
                this.e += i4;
                i3 += i4;
                i += i4;
            }
            return i3;
        }
        throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
    }

    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j % 1024 == 0) {
            while (j2 < j) {
                if (this.e == this.c) {
                    if (!a(j - j2 < ((long) this.c))) {
                        return -1;
                    }
                }
                long j3 = j - j2;
                if (((long) this.e) + j3 > ((long) this.c)) {
                    j3 = ((long) this.c) - ((long) this.e);
                }
                this.e = (int) (((long) this.e) + j3);
                j2 += j3;
            }
            return j2;
        }
        throw new IllegalArgumentException("all reads must be multiple of record size (1024 bytes.");
    }

    public void close() throws IOException {
        if (this.in != null && this.in != System.in) {
            this.in.close();
        }
    }

    public byte[] a() throws IOException {
        if (this.e == this.c && !a(true)) {
            return null;
        }
        byte[] bArr = new byte[1024];
        System.arraycopy(this.f3217a, this.e, bArr, 0, bArr.length);
        return bArr;
    }

    public byte[] b() throws IOException {
        byte[] bArr = new byte[1024];
        if (-1 != read(bArr, 0, bArr.length)) {
            return bArr;
        }
        throw new ShortFileException();
    }

    private boolean a(boolean z) throws IOException {
        boolean z2;
        if (this.in != null) {
            if (!this.f || this.b == -1) {
                z2 = a(this.f3217a, 0, this.c);
                this.g += (long) this.c;
            } else if (!a(this.f3217a, 0, 4)) {
                return false;
            } else {
                this.g += 4;
                int b2 = DumpArchiveUtil.b(this.f3217a, 0);
                if (!((b2 & 1) == 1)) {
                    z2 = a(this.f3217a, 0, this.c);
                    this.g += (long) this.c;
                } else {
                    int i = (b2 >> 1) & 7;
                    int i2 = (b2 >> 4) & 268435455;
                    byte[] bArr = new byte[i2];
                    boolean a2 = a(bArr, 0, i2);
                    this.g += (long) i2;
                    if (!z) {
                        Arrays.fill(this.f3217a, (byte) 0);
                    } else {
                        switch (DumpArchiveConstants.COMPRESSION_TYPE.find(i & 3)) {
                            case ZLIB:
                                Inflater inflater = new Inflater();
                                try {
                                    inflater.setInput(bArr, 0, bArr.length);
                                    if (inflater.inflate(this.f3217a) == this.c) {
                                        inflater.end();
                                        break;
                                    } else {
                                        throw new ShortFileException();
                                    }
                                } catch (DataFormatException e2) {
                                    throw new DumpArchiveException("bad data", e2);
                                } catch (Throwable th) {
                                    inflater.end();
                                    throw th;
                                }
                            case BZLIB:
                                throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                            case LZO:
                                throw new UnsupportedCompressionAlgorithmException("LZO");
                            default:
                                throw new UnsupportedCompressionAlgorithmException();
                        }
                    }
                    z2 = a2;
                }
            }
            this.b++;
            this.e = 0;
            return z2;
        }
        throw new IOException("input buffer is closed");
    }

    private boolean a(byte[] bArr, int i, int i2) throws IOException {
        if (IOUtils.a(this.in, bArr, i, i2) >= i2) {
            return true;
        }
        throw new ShortFileException();
    }

    public long c() {
        return this.g;
    }
}
