package org.apache.commons.compress.archivers.cpio;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.File;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class CpioArchiveEntry implements ArchiveEntry, CpioConstants {
    private final int J;
    private final int K;
    private long L;
    private long M;
    private long N;
    private long O;
    private long P;
    private long Q;
    private long R;
    private long S;
    private String T;
    private long U;
    private long V;
    private long W;
    private long X;
    private final short b;

    public CpioArchiveEntry(short s) {
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.S = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = 0;
        if (s == 4) {
            this.J = 76;
            this.K = 0;
        } else if (s != 8) {
            switch (s) {
                case 1:
                    this.J = 110;
                    this.K = 4;
                    break;
                case 2:
                    this.J = 110;
                    this.K = 4;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown header type");
            }
        } else {
            this.J = 26;
            this.K = 2;
        }
        this.b = s;
    }

    public CpioArchiveEntry(String str) {
        this(1, str);
    }

    public CpioArchiveEntry(short s, String str) {
        this(s);
        this.T = str;
    }

    public CpioArchiveEntry(String str, long j) {
        this(str);
        e(j);
    }

    public CpioArchiveEntry(short s, String str, long j) {
        this(s, str);
        e(j);
    }

    public CpioArchiveEntry(File file, String str) {
        this(1, file, str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CpioArchiveEntry(short s, File file, String str) {
        this(s, str, file.isFile() ? file.length() : 0);
        if (file.isDirectory()) {
            h(16384);
        } else if (file.isFile()) {
            h(32768);
        } else {
            throw new IllegalArgumentException("Cannot determine type of file " + file.getName());
        }
        m(file.lastModified() / 1000);
    }

    private void A() {
        if ((this.b & 3) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    private void B() {
        if ((this.b & 12) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public long b() {
        A();
        return this.L;
    }

    public long c() {
        B();
        return this.Q;
    }

    public long d() {
        A();
        return this.P;
    }

    public long e() {
        A();
        return this.Q;
    }

    public long getSize() {
        return this.M;
    }

    public short f() {
        return this.b;
    }

    public long g() {
        return this.N;
    }

    public int h() {
        return this.J;
    }

    public int i() {
        return this.K;
    }

    public int j() {
        if (this.K == 0) {
            return 0;
        }
        int i = this.J + 1;
        if (this.T != null) {
            i += this.T.length();
        }
        int i2 = i % this.K;
        if (i2 > 0) {
            return this.K - i2;
        }
        return 0;
    }

    public int k() {
        int i;
        if (this.K != 0 && (i = (int) (this.M % ((long) this.K))) > 0) {
            return this.K - i;
        }
        return 0;
    }

    public long l() {
        return this.O;
    }

    public long m() {
        if (this.R != 0 || CpioConstants.H.equals(this.T)) {
            return this.R;
        }
        return 32768;
    }

    public String getName() {
        return this.T;
    }

    public long n() {
        if (this.U == 0) {
            return isDirectory() ? 2 : 1;
        }
        return this.U;
    }

    public long o() {
        B();
        return this.W;
    }

    public long p() {
        A();
        return this.V;
    }

    public long q() {
        A();
        return this.W;
    }

    public long r() {
        return this.S;
    }

    public Date a() {
        return new Date(r() * 1000);
    }

    public long s() {
        return this.X;
    }

    public boolean t() {
        return CpioUtil.a(this.R) == 24576;
    }

    public boolean u() {
        return CpioUtil.a(this.R) == 8192;
    }

    public boolean isDirectory() {
        return CpioUtil.a(this.R) == 16384;
    }

    public boolean v() {
        return CpioUtil.a(this.R) == 36864;
    }

    public boolean w() {
        return CpioUtil.a(this.R) == 4096;
    }

    public boolean x() {
        return CpioUtil.a(this.R) == 32768;
    }

    public boolean y() {
        return CpioUtil.a(this.R) == 49152;
    }

    public boolean z() {
        return CpioUtil.a(this.R) == 40960;
    }

    public void a(long j) {
        A();
        this.L = j;
    }

    public void b(long j) {
        B();
        this.Q = j;
    }

    public void c(long j) {
        A();
        this.P = j;
    }

    public void d(long j) {
        A();
        this.Q = j;
    }

    public void e(long j) {
        if (j < 0 || j > MessageHead.SERIAL_MAK) {
            throw new IllegalArgumentException("invalid entry size <" + j + ">");
        }
        this.M = j;
    }

    public void f(long j) {
        this.N = j;
    }

    public void g(long j) {
        this.O = j;
    }

    public void h(long j) {
        long j2 = 61440 & j;
        int i = (int) j2;
        if (i == 4096 || i == 8192 || i == 16384 || i == 24576 || i == 32768 || i == 36864 || i == 40960 || i == 49152) {
            this.R = j;
            return;
        }
        throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(j) + " Masked: " + Long.toHexString(j2));
    }

    public void a(String str) {
        this.T = str;
    }

    public void i(long j) {
        this.U = j;
    }

    public void j(long j) {
        B();
        this.W = j;
    }

    public void k(long j) {
        A();
        this.V = j;
    }

    public void l(long j) {
        A();
        this.W = j;
    }

    public void m(long j) {
        this.S = j;
    }

    public void n(long j) {
        this.X = j;
    }

    public int hashCode() {
        return 31 + (this.T == null ? 0 : this.T.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CpioArchiveEntry cpioArchiveEntry = (CpioArchiveEntry) obj;
        if (this.T == null) {
            if (cpioArchiveEntry.T != null) {
                return false;
            }
        } else if (!this.T.equals(cpioArchiveEntry.T)) {
            return false;
        }
        return true;
    }
}
