package org.apache.commons.compress.archivers.ar;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

public class ArArchiveInputStream extends ArchiveInputStream {

    /* renamed from: a  reason: collision with root package name */
    static final String f3201a = "#1/";
    private static final int m = f3201a.length();
    private static final String n = "^#1/\\d+";
    private static final String o = "//";
    private static final String p = "^/\\d+";
    private final InputStream b;
    private long c = 0;
    private boolean d;
    private ArArchiveEntry e = null;
    private byte[] f = null;
    private long g = -1;
    private final byte[] h = new byte[16];
    private final byte[] i = new byte[12];
    private final byte[] j = new byte[6];
    private final byte[] k = new byte[8];
    private final byte[] l = new byte[10];

    public ArArchiveInputStream(InputStream inputStream) {
        this.b = inputStream;
        this.d = false;
    }

    public ArArchiveEntry d() throws IOException {
        if (this.e != null) {
            IOUtils.a((InputStream) this, (this.g + this.e.f()) - this.c);
            this.e = null;
        }
        if (this.c == 0) {
            byte[] a2 = ArchiveUtils.a(ArArchiveEntry.b);
            byte[] bArr = new byte[a2.length];
            if (IOUtils.a((InputStream) this, bArr) == a2.length) {
                int i2 = 0;
                while (i2 < a2.length) {
                    if (a2[i2] == bArr[i2]) {
                        i2++;
                    } else {
                        throw new IOException("invalid header " + ArchiveUtils.a(bArr));
                    }
                }
            } else {
                throw new IOException("failed to read header. Occured at byte: " + c());
            }
        }
        if ((this.c % 2 != 0 && read() < 0) || this.b.available() == 0) {
            return null;
        }
        IOUtils.a((InputStream) this, this.h);
        IOUtils.a((InputStream) this, this.i);
        IOUtils.a((InputStream) this, this.j);
        int a3 = a(this.j, true);
        IOUtils.a((InputStream) this, this.j);
        IOUtils.a((InputStream) this, this.k);
        IOUtils.a((InputStream) this, this.l);
        byte[] a4 = ArchiveUtils.a(ArArchiveEntry.c);
        byte[] bArr2 = new byte[a4.length];
        if (IOUtils.a((InputStream) this, bArr2) == a4.length) {
            int i3 = 0;
            while (i3 < a4.length) {
                if (a4[i3] == bArr2[i3]) {
                    i3++;
                } else {
                    throw new IOException("invalid entry trailer. not read the content? Occured at byte: " + c());
                }
            }
            this.g = this.c;
            String trim = ArchiveUtils.a(this.h).trim();
            if (c(trim)) {
                this.e = c(this.l);
                return d();
            }
            long a5 = a(this.l);
            if (trim.endsWith("/")) {
                trim = trim.substring(0, trim.length() - 1);
            } else if (d(trim)) {
                trim = b(Integer.parseInt(trim.substring(1)));
            } else if (a(trim)) {
                trim = b(trim);
                long length = (long) trim.length();
                a5 -= length;
                this.g += length;
            }
            this.e = new ArArchiveEntry(trim, a5, a3, a(this.j, true), b(this.k, 8), a(this.i));
            return this.e;
        }
        throw new IOException("failed to read entry trailer. Occured at byte: " + c());
    }

    private String b(int i2) throws IOException {
        if (this.f != null) {
            int i3 = i2;
            while (i3 < this.f.length) {
                if (this.f[i3] == 10 || this.f[i3] == 0) {
                    if (this.f[i3 - 1] == 47) {
                        i3--;
                    }
                    return ArchiveUtils.a(this.f, i2, i3 - i2);
                }
                i3++;
            }
            throw new IOException("Failed to read entry: " + i2);
        }
        throw new IOException("Cannot process GNU long filename as no // record was found");
    }

    private long a(byte[] bArr) {
        return Long.parseLong(ArchiveUtils.a(bArr).trim());
    }

    private int b(byte[] bArr) {
        return a(bArr, 10, false);
    }

    private int a(byte[] bArr, boolean z) {
        return a(bArr, 10, z);
    }

    private int b(byte[] bArr, int i2) {
        return a(bArr, i2, false);
    }

    private int a(byte[] bArr, int i2, boolean z) {
        String trim = ArchiveUtils.a(bArr).trim();
        if (trim.length() != 0 || !z) {
            return Integer.parseInt(trim, i2);
        }
        return 0;
    }

    public ArchiveEntry a() throws IOException {
        return d();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.d = true;
            this.b.close();
        }
        this.e = null;
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.e != null) {
            long f2 = this.g + this.e.f();
            if (i3 <= 0 || f2 <= this.c) {
                return -1;
            }
            i3 = (int) Math.min((long) i3, f2 - this.c);
        }
        int read = this.b.read(bArr, i2, i3);
        a(read);
        this.c += read > 0 ? (long) read : 0;
        return read;
    }

    public static boolean a(byte[] bArr, int i2) {
        return i2 >= 8 && bArr[0] == 33 && bArr[1] == 60 && bArr[2] == 97 && bArr[3] == 114 && bArr[4] == 99 && bArr[5] == 104 && bArr[6] == 62 && bArr[7] == 10;
    }

    private static boolean a(String str) {
        return str != null && str.matches(n);
    }

    private String b(String str) throws IOException {
        int parseInt = Integer.parseInt(str.substring(m));
        byte[] bArr = new byte[parseInt];
        if (IOUtils.a((InputStream) this, bArr) == parseInt) {
            return ArchiveUtils.a(bArr);
        }
        throw new EOFException();
    }

    private static boolean c(String str) {
        return o.equals(str);
    }

    private ArArchiveEntry c(byte[] bArr) throws IOException {
        int b2 = b(bArr);
        this.f = new byte[b2];
        int a2 = IOUtils.a(this, this.f, 0, b2);
        if (a2 == b2) {
            return new ArArchiveEntry(o, (long) b2);
        }
        throw new IOException("Failed to read complete // record: expected=" + b2 + " read=" + a2);
    }

    private boolean d(String str) {
        return str != null && str.matches(p);
    }
}
