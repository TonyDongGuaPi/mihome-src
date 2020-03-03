package org.apache.commons.compress.archivers.dump;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

public class DumpArchiveInputStream extends ArchiveInputStream {

    /* renamed from: a  reason: collision with root package name */
    protected TapeInputStream f3214a;
    final String b;
    private DumpArchiveSummary c;
    private DumpArchiveEntry d;
    private boolean e;
    private boolean f;
    private long g;
    private long h;
    private int i;
    private final byte[] j;
    private byte[] k;
    private int l;
    private long m;
    private final Map<Integer, Dirent> n;
    private final Map<Integer, DumpArchiveEntry> o;
    private Queue<DumpArchiveEntry> p;
    private final ZipEncoding q;

    public DumpArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, (String) null);
    }

    public DumpArchiveInputStream(InputStream inputStream, String str) throws ArchiveException {
        this.j = new byte[1024];
        this.n = new HashMap();
        this.o = new HashMap();
        this.f3214a = new TapeInputStream(inputStream);
        this.f = false;
        this.b = str;
        this.q = ZipEncodingHelper.a(str);
        try {
            byte[] b2 = this.f3214a.b();
            if (DumpArchiveUtil.b(b2)) {
                this.c = new DumpArchiveSummary(b2, this.q);
                this.f3214a.a(this.c.k(), this.c.n());
                this.k = new byte[4096];
                g();
                h();
                this.n.put(2, new Dirent(2, 2, 4, "."));
                this.p = new PriorityQueue(10, new Comparator<DumpArchiveEntry>() {
                    /* renamed from: a */
                    public int compare(DumpArchiveEntry dumpArchiveEntry, DumpArchiveEntry dumpArchiveEntry2) {
                        if (dumpArchiveEntry.m() == null || dumpArchiveEntry2.m() == null) {
                            return Integer.MAX_VALUE;
                        }
                        return dumpArchiveEntry.m().compareTo(dumpArchiveEntry2.m());
                    }
                });
                return;
            }
            throw new UnrecognizedFormatException();
        } catch (IOException e2) {
            throw new ArchiveException(e2.getMessage(), e2);
        }
    }

    @Deprecated
    public int b() {
        return (int) c();
    }

    public long c() {
        return this.f3214a.c();
    }

    public DumpArchiveSummary d() {
        return this.c;
    }

    private void g() throws IOException {
        byte[] b2 = this.f3214a.b();
        if (DumpArchiveUtil.b(b2)) {
            this.d = DumpArchiveEntry.a(b2);
            if (DumpArchiveConstants.SEGMENT_TYPE.CLRI != this.d.j()) {
                throw new InvalidFormatException();
            } else if (this.f3214a.skip(((long) this.d.k()) * 1024) != -1) {
                this.i = this.d.k();
            } else {
                throw new EOFException();
            }
        } else {
            throw new InvalidFormatException();
        }
    }

    private void h() throws IOException {
        byte[] b2 = this.f3214a.b();
        if (DumpArchiveUtil.b(b2)) {
            this.d = DumpArchiveEntry.a(b2);
            if (DumpArchiveConstants.SEGMENT_TYPE.BITS != this.d.j()) {
                throw new InvalidFormatException();
            } else if (this.f3214a.skip(((long) this.d.k()) * 1024) != -1) {
                this.i = this.d.k();
            } else {
                throw new EOFException();
            }
        } else {
            throw new InvalidFormatException();
        }
    }

    public DumpArchiveEntry e() throws IOException {
        return a();
    }

    /* renamed from: f */
    public DumpArchiveEntry a() throws IOException {
        if (!this.p.isEmpty()) {
            return this.p.remove();
        }
        DumpArchiveEntry dumpArchiveEntry = null;
        String str = null;
        while (dumpArchiveEntry == null) {
            if (this.f) {
                return null;
            }
            while (this.i < this.d.k()) {
                DumpArchiveEntry dumpArchiveEntry2 = this.d;
                int i2 = this.i;
                this.i = i2 + 1;
                if (!dumpArchiveEntry2.d(i2) && this.f3214a.skip(1024) == -1) {
                    throw new EOFException();
                }
            }
            this.i = 0;
            this.m = this.f3214a.c();
            byte[] b2 = this.f3214a.b();
            if (DumpArchiveUtil.b(b2)) {
                this.d = DumpArchiveEntry.a(b2);
                while (DumpArchiveConstants.SEGMENT_TYPE.ADDR == this.d.j()) {
                    if (this.f3214a.skip(((long) (this.d.k() - this.d.l())) * 1024) != -1) {
                        this.m = this.f3214a.c();
                        byte[] b3 = this.f3214a.b();
                        if (DumpArchiveUtil.b(b3)) {
                            this.d = DumpArchiveEntry.a(b3);
                        } else {
                            throw new InvalidFormatException();
                        }
                    } else {
                        throw new EOFException();
                    }
                }
                if (DumpArchiveConstants.SEGMENT_TYPE.END == this.d.j()) {
                    this.f = true;
                    return null;
                }
                DumpArchiveEntry dumpArchiveEntry3 = this.d;
                if (dumpArchiveEntry3.isDirectory()) {
                    a(this.d);
                    this.h = 0;
                    this.g = 0;
                    this.i = this.d.k();
                } else {
                    this.h = 0;
                    this.g = this.d.v();
                    this.i = 0;
                }
                this.l = this.j.length;
                String b4 = b(dumpArchiveEntry3);
                if (b4 == null) {
                    dumpArchiveEntry3 = null;
                }
                DumpArchiveEntry dumpArchiveEntry4 = dumpArchiveEntry3;
                str = b4;
                dumpArchiveEntry = dumpArchiveEntry4;
            } else {
                throw new InvalidFormatException();
            }
        }
        dumpArchiveEntry.b(str);
        dumpArchiveEntry.a(this.n.get(Integer.valueOf(dumpArchiveEntry.c())).d());
        dumpArchiveEntry.a(this.m);
        return dumpArchiveEntry;
    }

    private void a(DumpArchiveEntry dumpArchiveEntry) throws IOException {
        long v = dumpArchiveEntry.v();
        boolean z = true;
        while (true) {
            if (z || DumpArchiveConstants.SEGMENT_TYPE.ADDR == dumpArchiveEntry.j()) {
                if (!z) {
                    this.f3214a.b();
                }
                if (!this.n.containsKey(Integer.valueOf(dumpArchiveEntry.c())) && DumpArchiveConstants.SEGMENT_TYPE.INODE == dumpArchiveEntry.j()) {
                    this.o.put(Integer.valueOf(dumpArchiveEntry.c()), dumpArchiveEntry);
                }
                int k2 = dumpArchiveEntry.k() * 1024;
                if (this.k.length < k2) {
                    this.k = new byte[k2];
                }
                if (this.f3214a.read(this.k, 0, k2) == k2) {
                    int i2 = 0;
                    while (i2 < k2 - 8 && ((long) i2) < v - 8) {
                        int b2 = DumpArchiveUtil.b(this.k, i2);
                        int c2 = DumpArchiveUtil.c(this.k, i2 + 4);
                        byte b3 = this.k[i2 + 6];
                        String a2 = DumpArchiveUtil.a(this.q, this.k, i2 + 8, this.k[i2 + 7]);
                        if (!".".equals(a2) && !"..".equals(a2)) {
                            this.n.put(Integer.valueOf(b2), new Dirent(b2, dumpArchiveEntry.c(), b3, a2));
                            for (Map.Entry next : this.o.entrySet()) {
                                String b4 = b((DumpArchiveEntry) next.getValue());
                                if (b4 != null) {
                                    ((DumpArchiveEntry) next.getValue()).b(b4);
                                    ((DumpArchiveEntry) next.getValue()).a(this.n.get(next.getKey()).d());
                                    this.p.add(next.getValue());
                                }
                            }
                            for (DumpArchiveEntry c3 : this.p) {
                                this.o.remove(Integer.valueOf(c3.c()));
                            }
                        }
                        i2 += c2;
                    }
                    byte[] a3 = this.f3214a.a();
                    if (DumpArchiveUtil.b(a3)) {
                        dumpArchiveEntry = DumpArchiveEntry.a(a3);
                        v -= 1024;
                        z = false;
                    } else {
                        throw new InvalidFormatException();
                    }
                } else {
                    throw new EOFException();
                }
            } else {
                return;
            }
        }
    }

    private String b(DumpArchiveEntry dumpArchiveEntry) {
        Stack stack = new Stack();
        int c2 = dumpArchiveEntry.c();
        while (true) {
            if (!this.n.containsKey(Integer.valueOf(c2))) {
                stack.clear();
                break;
            }
            Dirent dirent = this.n.get(Integer.valueOf(c2));
            stack.push(dirent.d());
            if (dirent.a() == dirent.b()) {
                break;
            }
            c2 = dirent.b();
        }
        if (stack.isEmpty()) {
            this.o.put(Integer.valueOf(dumpArchiveEntry.c()), dumpArchiveEntry);
            return null;
        }
        StringBuilder sb = new StringBuilder((String) stack.pop());
        while (!stack.isEmpty()) {
            sb.append(IOUtils.f15883a);
            sb.append((String) stack.pop());
        }
        return sb.toString();
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.f || this.e || this.h >= this.g) {
            return -1;
        }
        if (this.d != null) {
            if (((long) i3) + this.h > this.g) {
                i3 = (int) (this.g - this.h);
            }
            int i4 = i2;
            int i5 = 0;
            while (i3 > 0) {
                int length = i3 > this.j.length - this.l ? this.j.length - this.l : i3;
                if (this.l + length <= this.j.length) {
                    System.arraycopy(this.j, this.l, bArr, i4, length);
                    i5 += length;
                    this.l += length;
                    i3 -= length;
                    i4 += length;
                }
                if (i3 > 0) {
                    if (this.i >= 512) {
                        byte[] b2 = this.f3214a.b();
                        if (DumpArchiveUtil.b(b2)) {
                            this.d = DumpArchiveEntry.a(b2);
                            this.i = 0;
                        } else {
                            throw new InvalidFormatException();
                        }
                    }
                    DumpArchiveEntry dumpArchiveEntry = this.d;
                    int i6 = this.i;
                    this.i = i6 + 1;
                    if (dumpArchiveEntry.d(i6)) {
                        Arrays.fill(this.j, (byte) 0);
                    } else if (this.f3214a.read(this.j, 0, this.j.length) != this.j.length) {
                        throw new EOFException();
                    }
                    this.l = 0;
                }
            }
            this.h += (long) i5;
            return i5;
        }
        throw new IllegalStateException("No current dump entry");
    }

    public void close() throws IOException {
        if (!this.e) {
            this.e = true;
            this.f3214a.close();
        }
    }

    public static boolean a(byte[] bArr, int i2) {
        if (i2 < 32) {
            return false;
        }
        if (i2 >= 1024) {
            return DumpArchiveUtil.b(bArr);
        }
        if (60012 == DumpArchiveUtil.b(bArr, 24)) {
            return true;
        }
        return false;
    }
}
