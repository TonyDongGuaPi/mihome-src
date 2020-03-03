package org.apache.commons.compress.archivers.cpio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

public class CpioArchiveInputStream extends ArchiveInputStream implements CpioConstants {
    private CpioArchiveEntry J;
    private long K;
    private boolean L;
    private final byte[] M;
    private long N;
    private final InputStream O;
    private final byte[] P;
    private final byte[] Q;
    private final byte[] R;
    private final int S;
    private final ZipEncoding T;

    /* renamed from: a  reason: collision with root package name */
    final String f3210a;
    private boolean b;

    public CpioArchiveInputStream(InputStream inputStream) {
        this(inputStream, 512, "US-ASCII");
    }

    public CpioArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, 512, str);
    }

    public CpioArchiveInputStream(InputStream inputStream, int i) {
        this(inputStream, i, "US-ASCII");
    }

    public CpioArchiveInputStream(InputStream inputStream, int i, String str) {
        this.b = false;
        this.K = 0;
        this.L = false;
        this.M = new byte[4096];
        this.N = 0;
        this.P = new byte[2];
        this.Q = new byte[4];
        this.R = new byte[6];
        this.O = inputStream;
        this.S = i;
        this.f3210a = str;
        this.T = ZipEncodingHelper.a(str);
    }

    public int available() throws IOException {
        f();
        return this.L ? 0 : 1;
    }

    public void close() throws IOException {
        if (!this.b) {
            this.O.close();
            this.b = true;
        }
    }

    private void e() throws IOException {
        do {
        } while (skip(2147483647L) == 2147483647L);
    }

    private void f() throws IOException {
        if (this.b) {
            throw new IOException("Stream closed");
        }
    }

    public CpioArchiveEntry d() throws IOException {
        f();
        if (this.J != null) {
            e();
        }
        a(this.P, 0, this.P.length);
        if (CpioUtil.a(this.P, false) == 29127) {
            this.J = b(false);
        } else if (CpioUtil.a(this.P, true) == 29127) {
            this.J = b(true);
        } else {
            System.arraycopy(this.P, 0, this.R, 0, this.P.length);
            a(this.R, this.P.length, this.Q.length);
            String a2 = ArchiveUtils.a(this.R);
            char c = 65535;
            int hashCode = a2.hashCode();
            if (hashCode != 1426477269) {
                switch (hashCode) {
                    case 1426477263:
                        if (a2.equals(CpioConstants.c)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1426477264:
                        if (a2.equals(CpioConstants.d)) {
                            c = 1;
                            break;
                        }
                        break;
                }
            } else if (a2.equals(CpioConstants.e)) {
                c = 2;
            }
            switch (c) {
                case 0:
                    this.J = a(false);
                    break;
                case 1:
                    this.J = a(true);
                    break;
                case 2:
                    this.J = g();
                    break;
                default:
                    throw new IOException("Unknown magic [" + a2 + "]. Occured at byte: " + c());
            }
        }
        this.K = 0;
        this.L = false;
        this.N = 0;
        if (!this.J.getName().equals(CpioConstants.H)) {
            return this.J;
        }
        this.L = true;
        h();
        return null;
    }

    private void b(int i) throws IOException {
        if (i > 0) {
            a(this.Q, 0, i);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        f();
        if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        if (this.J == null || this.L) {
            return -1;
        }
        if (this.K == this.J.getSize()) {
            b(this.J.k());
            this.L = true;
            if (this.J.f() != 2 || this.N == this.J.b()) {
                return -1;
            }
            throw new IOException("CRC Error. Occured at byte: " + c());
        }
        int min = (int) Math.min((long) i2, this.J.getSize() - this.K);
        if (min < 0) {
            return -1;
        }
        int a2 = a(bArr, i, min);
        if (this.J.f() == 2) {
            for (int i3 = 0; i3 < a2; i3++) {
                this.N += (long) (bArr[i3] & 255);
            }
        }
        this.K += (long) a2;
        return a2;
    }

    private final int a(byte[] bArr, int i, int i2) throws IOException {
        int a2 = IOUtils.a(this.O, bArr, i, i2);
        a(a2);
        if (a2 >= i2) {
            return a2;
        }
        throw new EOFException();
    }

    private long a(int i, boolean z) throws IOException {
        byte[] bArr = new byte[i];
        a(bArr, 0, bArr.length);
        return CpioUtil.a(bArr, z);
    }

    private long a(int i, int i2) throws IOException {
        byte[] bArr = new byte[i];
        a(bArr, 0, bArr.length);
        return Long.parseLong(ArchiveUtils.a(bArr), i2);
    }

    private CpioArchiveEntry a(boolean z) throws IOException {
        CpioArchiveEntry cpioArchiveEntry;
        if (z) {
            cpioArchiveEntry = new CpioArchiveEntry(2);
        } else {
            cpioArchiveEntry = new CpioArchiveEntry(1);
        }
        cpioArchiveEntry.g(a(8, 16));
        long a2 = a(8, 16);
        if (CpioUtil.a(a2) != 0) {
            cpioArchiveEntry.h(a2);
        }
        cpioArchiveEntry.n(a(8, 16));
        cpioArchiveEntry.f(a(8, 16));
        cpioArchiveEntry.i(a(8, 16));
        cpioArchiveEntry.m(a(8, 16));
        cpioArchiveEntry.e(a(8, 16));
        cpioArchiveEntry.c(a(8, 16));
        cpioArchiveEntry.d(a(8, 16));
        cpioArchiveEntry.k(a(8, 16));
        cpioArchiveEntry.l(a(8, 16));
        long a3 = a(8, 16);
        cpioArchiveEntry.a(a(8, 16));
        String c = c((int) a3);
        cpioArchiveEntry.a(c);
        if (CpioUtil.a(a2) != 0 || c.equals(CpioConstants.H)) {
            b(cpioArchiveEntry.j());
            return cpioArchiveEntry;
        }
        throw new IOException("Mode 0 only allowed in the trailer. Found entry name: " + ArchiveUtils.b(c) + " Occured at byte: " + c());
    }

    private CpioArchiveEntry g() throws IOException {
        CpioArchiveEntry cpioArchiveEntry = new CpioArchiveEntry(4);
        cpioArchiveEntry.b(a(6, 8));
        cpioArchiveEntry.g(a(6, 8));
        long a2 = a(6, 8);
        if (CpioUtil.a(a2) != 0) {
            cpioArchiveEntry.h(a2);
        }
        cpioArchiveEntry.n(a(6, 8));
        cpioArchiveEntry.f(a(6, 8));
        cpioArchiveEntry.i(a(6, 8));
        cpioArchiveEntry.j(a(6, 8));
        cpioArchiveEntry.m(a(11, 8));
        long a3 = a(6, 8);
        cpioArchiveEntry.e(a(11, 8));
        String c = c((int) a3);
        cpioArchiveEntry.a(c);
        if (CpioUtil.a(a2) != 0 || c.equals(CpioConstants.H)) {
            return cpioArchiveEntry;
        }
        throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + ArchiveUtils.b(c) + " Occured at byte: " + c());
    }

    private CpioArchiveEntry b(boolean z) throws IOException {
        CpioArchiveEntry cpioArchiveEntry = new CpioArchiveEntry(8);
        cpioArchiveEntry.b(a(2, z));
        cpioArchiveEntry.g(a(2, z));
        long a2 = a(2, z);
        if (CpioUtil.a(a2) != 0) {
            cpioArchiveEntry.h(a2);
        }
        cpioArchiveEntry.n(a(2, z));
        cpioArchiveEntry.f(a(2, z));
        cpioArchiveEntry.i(a(2, z));
        cpioArchiveEntry.j(a(2, z));
        cpioArchiveEntry.m(a(4, z));
        long a3 = a(2, z);
        cpioArchiveEntry.e(a(4, z));
        String c = c((int) a3);
        cpioArchiveEntry.a(c);
        if (CpioUtil.a(a2) != 0 || c.equals(CpioConstants.H)) {
            b(cpioArchiveEntry.j());
            return cpioArchiveEntry;
        }
        throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + ArchiveUtils.b(c) + "Occured at byte: " + c());
    }

    private String c(int i) throws IOException {
        byte[] bArr = new byte[(i - 1)];
        a(bArr, 0, bArr.length);
        this.O.read();
        return this.T.a(bArr);
    }

    public long skip(long j) throws IOException {
        if (j >= 0) {
            f();
            int min = (int) Math.min(j, 2147483647L);
            int i = 0;
            while (true) {
                if (i >= min) {
                    break;
                }
                int i2 = min - i;
                if (i2 > this.M.length) {
                    i2 = this.M.length;
                }
                int read = read(this.M, 0, i2);
                if (read == -1) {
                    this.L = true;
                    break;
                }
                i += read;
            }
            return (long) i;
        }
        throw new IllegalArgumentException("negative skip length");
    }

    public ArchiveEntry a() throws IOException {
        return d();
    }

    private void h() throws IOException {
        long j;
        long c = c() % ((long) this.S);
        if (c == 0) {
            j = 0;
        } else {
            j = ((long) this.S) - c;
        }
        while (j > 0) {
            long skip = skip(((long) this.S) - c);
            if (skip > 0) {
                j -= skip;
            } else {
                return;
            }
        }
    }

    public static boolean a(byte[] bArr, int i) {
        if (i < 6) {
            return false;
        }
        if (bArr[0] == 113 && (bArr[1] & 255) == 199) {
            return true;
        }
        if (bArr[1] == 113 && (bArr[0] & 255) == 199) {
            return true;
        }
        if (bArr[0] == 48 && bArr[1] == 55 && bArr[2] == 48 && bArr[3] == 55 && bArr[4] == 48) {
            return bArr[5] == 49 || bArr[5] == 50 || bArr[5] == 55;
        }
        return false;
    }
}
