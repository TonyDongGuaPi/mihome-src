package org.apache.commons.compress.archivers.cpio;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class CpioArchiveOutputStream extends ArchiveOutputStream implements CpioConstants {
    private CpioArchiveEntry J;
    private boolean K;
    private boolean L;
    private final short M;
    private final HashMap<String, CpioArchiveEntry> N;
    private long O;
    private long P;
    private final OutputStream Q;
    private final int R;
    private long S;
    private final ZipEncoding T;
    final String b;

    public CpioArchiveOutputStream(OutputStream outputStream, short s) {
        this(outputStream, s, 512, "US-ASCII");
    }

    public CpioArchiveOutputStream(OutputStream outputStream, short s, int i) {
        this(outputStream, s, i, "US-ASCII");
    }

    public CpioArchiveOutputStream(OutputStream outputStream, short s, int i, String str) {
        this.K = false;
        this.N = new HashMap<>();
        this.O = 0;
        this.S = 1;
        this.Q = outputStream;
        if (!(s == 4 || s == 8)) {
            switch (s) {
                case 1:
                case 2:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown format: " + s);
            }
        }
        this.M = s;
        this.R = i;
        this.b = str;
        this.T = ZipEncodingHelper.a(str);
    }

    public CpioArchiveOutputStream(OutputStream outputStream) {
        this(outputStream, 1);
    }

    public CpioArchiveOutputStream(OutputStream outputStream, String str) {
        this(outputStream, 1, 512, str);
    }

    private void e() throws IOException {
        if (this.K) {
            throw new IOException("Stream closed");
        }
    }

    public void a(ArchiveEntry archiveEntry) throws IOException {
        if (!this.L) {
            CpioArchiveEntry cpioArchiveEntry = (CpioArchiveEntry) archiveEntry;
            e();
            if (this.J != null) {
                a();
            }
            if (cpioArchiveEntry.r() == -1) {
                cpioArchiveEntry.m(System.currentTimeMillis() / 1000);
            }
            short f = cpioArchiveEntry.f();
            if (f != this.M) {
                throw new IOException("Header format: " + f + " does not match existing format: " + this.M);
            } else if (this.N.put(cpioArchiveEntry.getName(), cpioArchiveEntry) == null) {
                a(cpioArchiveEntry);
                this.J = cpioArchiveEntry;
                this.P = 0;
            } else {
                throw new IOException("duplicate entry: " + cpioArchiveEntry.getName());
            }
        } else {
            throw new IOException("Stream has already been finished");
        }
    }

    private void a(CpioArchiveEntry cpioArchiveEntry) throws IOException {
        short f = cpioArchiveEntry.f();
        if (f == 4) {
            this.Q.write(ArchiveUtils.a(CpioConstants.e));
            a(6);
            c(cpioArchiveEntry);
        } else if (f != 8) {
            switch (f) {
                case 1:
                    this.Q.write(ArchiveUtils.a(CpioConstants.c));
                    a(6);
                    b(cpioArchiveEntry);
                    return;
                case 2:
                    this.Q.write(ArchiveUtils.a(CpioConstants.d));
                    a(6);
                    b(cpioArchiveEntry);
                    return;
                default:
                    throw new IOException("unknown format " + cpioArchiveEntry.f());
            }
        } else {
            a(29127, 2, true);
            a(cpioArchiveEntry, true);
        }
    }

    private void b(CpioArchiveEntry cpioArchiveEntry) throws IOException {
        long l = cpioArchiveEntry.l();
        long e = cpioArchiveEntry.e();
        if (CpioConstants.H.equals(cpioArchiveEntry.getName())) {
            l = 0;
            e = 0;
        } else if (l == 0 && e == 0) {
            l = this.S & -1;
            long j = this.S;
            this.S = j + 1;
            e = -1 & (j >> 32);
        } else {
            this.S = Math.max(this.S, (IjkMediaMeta.AV_CH_WIDE_RIGHT * e) + l) + 1;
        }
        a(l, 8, 16);
        a(cpioArchiveEntry.m(), 8, 16);
        a(cpioArchiveEntry.s(), 8, 16);
        a(cpioArchiveEntry.g(), 8, 16);
        a(cpioArchiveEntry.n(), 8, 16);
        a(cpioArchiveEntry.r(), 8, 16);
        a(cpioArchiveEntry.getSize(), 8, 16);
        a(cpioArchiveEntry.d(), 8, 16);
        a(e, 8, 16);
        a(cpioArchiveEntry.p(), 8, 16);
        a(cpioArchiveEntry.q(), 8, 16);
        a(((long) cpioArchiveEntry.getName().length()) + 1, 8, 16);
        a(cpioArchiveEntry.b(), 8, 16);
        a(cpioArchiveEntry.getName());
        b(cpioArchiveEntry.j());
    }

    private void c(CpioArchiveEntry cpioArchiveEntry) throws IOException {
        long l = cpioArchiveEntry.l();
        long c = cpioArchiveEntry.c();
        if (CpioConstants.H.equals(cpioArchiveEntry.getName())) {
            l = 0;
            c = 0;
        } else if (l == 0 && c == 0) {
            l = this.S & 262143;
            long j = this.S;
            this.S = j + 1;
            c = 262143 & (j >> 18);
        } else {
            this.S = Math.max(this.S, (262144 * c) + l) + 1;
        }
        a(c, 6, 8);
        a(l, 6, 8);
        a(cpioArchiveEntry.m(), 6, 8);
        a(cpioArchiveEntry.s(), 6, 8);
        a(cpioArchiveEntry.g(), 6, 8);
        a(cpioArchiveEntry.n(), 6, 8);
        a(cpioArchiveEntry.o(), 6, 8);
        a(cpioArchiveEntry.r(), 11, 8);
        a(((long) cpioArchiveEntry.getName().length()) + 1, 6, 8);
        a(cpioArchiveEntry.getSize(), 11, 8);
        a(cpioArchiveEntry.getName());
    }

    private void a(CpioArchiveEntry cpioArchiveEntry, boolean z) throws IOException {
        long l = cpioArchiveEntry.l();
        long c = cpioArchiveEntry.c();
        if (CpioConstants.H.equals(cpioArchiveEntry.getName())) {
            l = 0;
            c = 0;
        } else if (l == 0 && c == 0) {
            l = this.S & 65535;
            long j = this.S;
            this.S = j + 1;
            c = 65535 & (j >> 16);
        } else {
            this.S = Math.max(this.S, (65536 * c) + l) + 1;
        }
        a(c, 2, z);
        a(l, 2, z);
        a(cpioArchiveEntry.m(), 2, z);
        a(cpioArchiveEntry.s(), 2, z);
        a(cpioArchiveEntry.g(), 2, z);
        a(cpioArchiveEntry.n(), 2, z);
        a(cpioArchiveEntry.o(), 2, z);
        a(cpioArchiveEntry.r(), 4, z);
        a(((long) cpioArchiveEntry.getName().length()) + 1, 2, z);
        a(cpioArchiveEntry.getSize(), 4, z);
        a(cpioArchiveEntry.getName());
        b(cpioArchiveEntry.j());
    }

    public void a() throws IOException {
        if (!this.L) {
            e();
            if (this.J == null) {
                throw new IOException("Trying to close non-existent entry");
            } else if (this.J.getSize() == this.P) {
                b(this.J.k());
                if (this.J.f() != 2 || this.O == this.J.b()) {
                    this.J = null;
                    this.O = 0;
                    this.P = 0;
                    return;
                }
                throw new IOException("CRC Error");
            } else {
                throw new IOException("invalid entry size (expected " + this.J.getSize() + " but got " + this.P + " bytes)");
            }
        } else {
            throw new IOException("Stream has already been finished");
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        e();
        if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            if (this.J != null) {
                long j = (long) i2;
                if (this.P + j <= this.J.getSize()) {
                    this.Q.write(bArr, i, i2);
                    this.P += j;
                    if (this.J.f() == 2) {
                        for (int i3 = 0; i3 < i2; i3++) {
                            this.O += (long) (bArr[i3] & 255);
                        }
                    }
                    a(i2);
                    return;
                }
                throw new IOException("attempt to write past end of STORED entry");
            }
            throw new IOException("no current CPIO entry");
        }
    }

    public void b() throws IOException {
        e();
        if (this.L) {
            throw new IOException("This archive has already been finished");
        } else if (this.J == null) {
            this.J = new CpioArchiveEntry(this.M);
            this.J.a(CpioConstants.H);
            this.J.i(1);
            a(this.J);
            a();
            int d = (int) (d() % ((long) this.R));
            if (d != 0) {
                b(this.R - d);
            }
            this.L = true;
        } else {
            throw new IOException("This archive contains unclosed entries.");
        }
    }

    public void close() throws IOException {
        if (!this.L) {
            b();
        }
        if (!this.K) {
            this.Q.close();
            this.K = true;
        }
    }

    private void b(int i) throws IOException {
        if (i > 0) {
            this.Q.write(new byte[i]);
            a(i);
        }
    }

    private void a(long j, int i, boolean z) throws IOException {
        byte[] a2 = CpioUtil.a(j, i, z);
        this.Q.write(a2);
        a(a2.length);
    }

    private void a(long j, int i, int i2) throws IOException {
        String str;
        StringBuilder sb = new StringBuilder();
        if (i2 == 16) {
            sb.append(Long.toHexString(j));
        } else if (i2 == 8) {
            sb.append(Long.toOctalString(j));
        } else {
            sb.append(Long.toString(j));
        }
        if (sb.length() <= i) {
            int length = i - sb.length();
            for (int i3 = 0; i3 < length; i3++) {
                sb.insert(0, "0");
            }
            str = sb.toString();
        } else {
            str = sb.substring(sb.length() - i);
        }
        byte[] a2 = ArchiveUtils.a(str);
        this.Q.write(a2);
        a(a2.length);
    }

    private void a(String str) throws IOException {
        ByteBuffer b2 = this.T.b(str);
        int limit = b2.limit() - b2.position();
        this.Q.write(b2.array(), b2.arrayOffset(), limit);
        this.Q.write(0);
        a(limit + 1);
    }

    public ArchiveEntry a(File file, String str) throws IOException {
        if (!this.L) {
            return new CpioArchiveEntry(file, str);
        }
        throw new IOException("Stream has already been finished");
    }
}
