package org.apache.commons.compress.compressors.snappy;

import cn.com.fmsh.communication.core.MessageHead;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class FramedSnappyCompressorInputStream extends CompressorInputStream {

    /* renamed from: a  reason: collision with root package name */
    static final long f3336a = 2726488792L;
    private static final int b = 255;
    private static final int c = 0;
    private static final int d = 1;
    private static final int e = 254;
    private static final int f = 2;
    private static final int g = 127;
    private static final int h = 253;
    private static final byte[] i = {-1, 6, 0, 0, Constants.TagName.ELECTRONIC_TYPE, 78, 97, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ELECTRONIC_ID, 89};
    private final PushbackInputStream j;
    private final FramedSnappyDialect k;
    private SnappyCompressorInputStream l;
    private final byte[] m;
    private boolean n;
    private boolean o;
    private int p;
    private long q;
    private final PureJavaCrc32C r;

    static long c(long j2) {
        long j3 = (j2 - f3336a) & MessageHead.SERIAL_MAK;
        return ((j3 << 15) | (j3 >> 17)) & MessageHead.SERIAL_MAK;
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, FramedSnappyDialect.STANDARD);
    }

    public FramedSnappyCompressorInputStream(InputStream inputStream, FramedSnappyDialect framedSnappyDialect) throws IOException {
        this.m = new byte[1];
        this.q = -1;
        this.r = new PureJavaCrc32C();
        this.j = new PushbackInputStream(inputStream, 1);
        this.k = framedSnappyDialect;
        if (framedSnappyDialect.hasStreamIdentifier()) {
            g();
        }
    }

    public int read() throws IOException {
        if (read(this.m, 0, 1) == -1) {
            return -1;
        }
        return this.m[0] & 255;
    }

    public void close() throws IOException {
        if (this.l != null) {
            this.l.close();
            this.l = null;
        }
        this.j.close();
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int a2 = a(bArr, i2, i3);
        if (a2 != -1) {
            return a2;
        }
        a();
        if (this.n) {
            return -1;
        }
        return a(bArr, i2, i3);
    }

    public int available() throws IOException {
        if (this.o) {
            return Math.min(this.p, this.j.available());
        }
        if (this.l != null) {
            return this.l.available();
        }
        return 0;
    }

    private int a(byte[] bArr, int i2, int i3) throws IOException {
        int i4;
        if (this.o) {
            int min = Math.min(this.p, i3);
            if (min == 0) {
                return -1;
            }
            i4 = this.j.read(bArr, i2, min);
            if (i4 != -1) {
                this.p -= i4;
                a(i4);
            }
        } else if (this.l != null) {
            long c2 = this.l.c();
            i4 = this.l.read(bArr, i2, i3);
            if (i4 == -1) {
                this.l.close();
                this.l = null;
            } else {
                a(this.l.c() - c2);
            }
        } else {
            i4 = -1;
        }
        if (i4 > 0) {
            this.r.update(bArr, i2, i4);
        }
        return i4;
    }

    private void a() throws IOException {
        i();
        this.o = false;
        int h2 = h();
        if (h2 == -1) {
            this.n = true;
        } else if (h2 == 255) {
            this.j.unread(h2);
            b(1);
            g();
            a();
        } else if (h2 == 254 || (h2 > 127 && h2 <= h)) {
            f();
            a();
        } else if (h2 >= 2 && h2 <= 127) {
            throw new IOException("unskippable chunk with type " + h2 + " (hex " + Integer.toHexString(h2) + Operators.BRACKET_END_STR + " detected.");
        } else if (h2 == 1) {
            this.o = true;
            this.p = e() - 4;
            this.q = c(d());
        } else if (h2 == 0) {
            boolean usesChecksumWithCompressedChunks = this.k.usesChecksumWithCompressedChunks();
            long e2 = ((long) e()) - (usesChecksumWithCompressedChunks ? 4 : 0);
            if (usesChecksumWithCompressedChunks) {
                this.q = c(d());
            } else {
                this.q = -1;
            }
            this.l = new SnappyCompressorInputStream(new BoundedInputStream(this.j, e2));
            a(this.l.c());
        } else {
            throw new IOException("unknown chunk type " + h2 + " detected.");
        }
    }

    private long d() throws IOException {
        byte[] bArr = new byte[4];
        int a2 = IOUtils.a((InputStream) this.j, bArr);
        a(a2);
        if (a2 == 4) {
            long j2 = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                j2 |= (((long) bArr[i2]) & 255) << (i2 * 8);
            }
            return j2;
        }
        throw new IOException("premature end of stream");
    }

    private int e() throws IOException {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 3) {
            int h2 = h();
            if (h2 != -1) {
                i3 |= h2 << (i2 * 8);
                i2++;
            } else {
                throw new IOException("premature end of stream");
            }
        }
        return i3;
    }

    private void f() throws IOException {
        long e2 = (long) e();
        long a2 = IOUtils.a((InputStream) this.j, e2);
        a(a2);
        if (a2 != e2) {
            throw new IOException("premature end of stream");
        }
    }

    private void g() throws IOException {
        byte[] bArr = new byte[10];
        int a2 = IOUtils.a((InputStream) this.j, bArr);
        a(a2);
        if (10 != a2 || !a(bArr, 10)) {
            throw new IOException("Not a framed Snappy stream");
        }
    }

    private int h() throws IOException {
        int read = this.j.read();
        if (read == -1) {
            return -1;
        }
        a(1);
        return read & 255;
    }

    private void i() throws IOException {
        if (this.q < 0 || this.q == this.r.getValue()) {
            this.q = -1;
            this.r.reset();
            return;
        }
        throw new IOException("Checksum verification failed");
    }

    public static boolean a(byte[] bArr, int i2) {
        if (i2 < i.length) {
            return false;
        }
        if (bArr.length > i.length) {
            byte[] bArr2 = new byte[i.length];
            System.arraycopy(bArr, 0, bArr2, 0, i.length);
            bArr = bArr2;
        }
        return Arrays.equals(bArr, i);
    }
}
