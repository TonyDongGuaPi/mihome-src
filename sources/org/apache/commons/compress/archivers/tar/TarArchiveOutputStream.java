package org.apache.commons.compress.archivers.tar;

import com.google.common.base.Ascii;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CountingOutputStream;

public class TarArchiveOutputStream extends ArchiveOutputStream {
    private static final ZipEncoding A = ZipEncodingHelper.a("ASCII");
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    final String i;
    private long j;
    private String k;
    private long l;
    private final byte[] m;
    private int n;
    private final byte[] o;
    private int p;
    private int q;
    private int r;
    private final int s;
    private final int t;
    private boolean u;
    private boolean v;
    private boolean w;
    private final OutputStream x;
    private final ZipEncoding y;
    private boolean z;

    private boolean a(char c2) {
        return c2 == 0 || c2 == '/' || c2 == '\\';
    }

    public TarArchiveOutputStream(OutputStream outputStream) {
        this(outputStream, 10240, 512);
    }

    public TarArchiveOutputStream(OutputStream outputStream, String str) {
        this(outputStream, 10240, 512, str);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i2) {
        this(outputStream, i2, 512);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i2, String str) {
        this(outputStream, i2, 512, str);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i2, int i3) {
        this(outputStream, i2, i3, (String) null);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i2, int i3, String str) {
        this.p = 0;
        this.q = 0;
        this.u = false;
        this.v = false;
        this.w = false;
        this.z = false;
        this.x = new CountingOutputStream(outputStream);
        this.i = str;
        this.y = ZipEncodingHelper.a(str);
        this.n = 0;
        this.o = new byte[i3];
        this.m = new byte[i3];
        this.t = i3;
        this.s = i2 / i3;
    }

    public void b(int i2) {
        this.p = i2;
    }

    public void c(int i2) {
        this.q = i2;
    }

    public void a(boolean z2) {
        this.z = z2;
    }

    @Deprecated
    public int c() {
        return (int) d();
    }

    public long d() {
        return ((CountingOutputStream) this.x).a();
    }

    public void b() throws IOException {
        if (this.w) {
            throw new IOException("This archive has already been finished");
        } else if (!this.v) {
            f();
            f();
            g();
            this.x.flush();
            this.w = true;
        } else {
            throw new IOException("This archives contains unclosed entries.");
        }
    }

    public void close() throws IOException {
        if (!this.w) {
            b();
        }
        if (!this.u) {
            this.x.close();
            this.u = true;
        }
    }

    public int e() {
        return this.t;
    }

    public void a(ArchiveEntry archiveEntry) throws IOException {
        if (!this.w) {
            TarArchiveEntry tarArchiveEntry = (TarArchiveEntry) archiveEntry;
            HashMap hashMap = new HashMap();
            String name = tarArchiveEntry.getName();
            boolean a2 = a(tarArchiveEntry, name, hashMap, "path", (byte) 76, "file name");
            String b2 = tarArchiveEntry.b();
            boolean z2 = false;
            boolean z3 = b2 != null && b2.length() > 0 && a(tarArchiveEntry, b2, hashMap, "linkpath", TarConstants.U, "link name");
            if (this.q == 2) {
                a((Map<String, String>) hashMap, tarArchiveEntry);
            } else if (this.q != 1) {
                a(tarArchiveEntry);
            }
            if (this.z && !a2 && !A.a(name)) {
                hashMap.put("path", name);
            }
            if (this.z && !z3 && ((tarArchiveEntry.A() || tarArchiveEntry.z()) && !A.a(b2))) {
                hashMap.put("linkpath", b2);
            }
            if (hashMap.size() > 0) {
                a(tarArchiveEntry, name, (Map<String, String>) hashMap);
            }
            byte[] bArr = this.m;
            ZipEncoding zipEncoding = this.y;
            if (this.q == 1) {
                z2 = true;
            }
            tarArchiveEntry.a(bArr, zipEncoding, z2);
            a(this.m);
            this.l = 0;
            if (tarArchiveEntry.isDirectory()) {
                this.j = 0;
            } else {
                this.j = tarArchiveEntry.getSize();
            }
            this.k = name;
            this.v = true;
            return;
        }
        throw new IOException("Stream has already been finished");
    }

    public void a() throws IOException {
        if (this.w) {
            throw new IOException("Stream has already been finished");
        } else if (this.v) {
            if (this.n > 0) {
                for (int i2 = this.n; i2 < this.o.length; i2++) {
                    this.o[i2] = 0;
                }
                a(this.o);
                this.l += (long) this.n;
                this.n = 0;
            }
            if (this.l >= this.j) {
                this.v = false;
                return;
            }
            throw new IOException("entry '" + this.k + "' closed at '" + this.l + "' before the '" + this.j + "' bytes specified in the header were written");
        } else {
            throw new IOException("No current entry to close");
        }
    }

    public void write(byte[] bArr, int i2, int i3) throws IOException {
        if (!this.v) {
            throw new IllegalStateException("No current tar entry");
        } else if (this.l + ((long) i3) <= this.j) {
            if (this.n > 0) {
                if (this.n + i3 >= this.m.length) {
                    int length = this.m.length - this.n;
                    System.arraycopy(this.o, 0, this.m, 0, this.n);
                    System.arraycopy(bArr, i2, this.m, this.n, length);
                    a(this.m);
                    this.l += (long) this.m.length;
                    i2 += length;
                    i3 -= length;
                    this.n = 0;
                } else {
                    System.arraycopy(bArr, i2, this.o, this.n, i3);
                    i2 += i3;
                    this.n += i3;
                    i3 = 0;
                }
            }
            while (i3 > 0) {
                if (i3 < this.m.length) {
                    System.arraycopy(bArr, i2, this.o, this.n, i3);
                    this.n += i3;
                    return;
                }
                a(bArr, i2);
                int length2 = this.m.length;
                this.l += (long) length2;
                i3 -= length2;
                i2 += length2;
            }
        } else {
            throw new IOException("request to write '" + i3 + "' bytes exceeds size in header of '" + this.j + "' bytes for entry '" + this.k + "'");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(TarArchiveEntry tarArchiveEntry, String str, Map<String, String> map) throws IOException {
        String str2 = "./PaxHeaders.X/" + a(str);
        if (str2.length() >= 100) {
            str2 = str2.substring(0, 99);
        }
        TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(str2, (byte) 120);
        a(tarArchiveEntry, tarArchiveEntry2);
        StringWriter stringWriter = new StringWriter();
        for (Map.Entry next : map.entrySet()) {
            String str3 = (String) next.getKey();
            String str4 = (String) next.getValue();
            int length = str3.length() + str4.length() + 3 + 2;
            String str5 = length + " " + str3 + "=" + str4 + "\n";
            int length2 = str5.getBytes("UTF-8").length;
            while (length != length2) {
                str5 = length2 + " " + str3 + "=" + str4 + "\n";
                int i2 = length2;
                length2 = str5.getBytes("UTF-8").length;
                length = i2;
            }
            stringWriter.write(str5);
        }
        byte[] bytes = stringWriter.toString().getBytes("UTF-8");
        tarArchiveEntry2.d((long) bytes.length);
        a((ArchiveEntry) tarArchiveEntry2);
        write(bytes);
        a();
    }

    private String a(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = (char) (str.charAt(i2) & Ascii.MAX);
            if (a(charAt)) {
                sb.append(JSMethod.NOT_SET);
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private void f() throws IOException {
        Arrays.fill(this.m, (byte) 0);
        a(this.m);
    }

    public void flush() throws IOException {
        this.x.flush();
    }

    public ArchiveEntry a(File file, String str) throws IOException {
        if (!this.w) {
            return new TarArchiveEntry(file, str);
        }
        throw new IOException("Stream has already been finished");
    }

    private void a(byte[] bArr) throws IOException {
        if (bArr.length == this.t) {
            this.x.write(bArr);
            this.r++;
            return;
        }
        throw new IOException("record to write has length '" + bArr.length + "' which is not the record size of '" + this.t + "'");
    }

    private void a(byte[] bArr, int i2) throws IOException {
        if (this.t + i2 <= bArr.length) {
            this.x.write(bArr, i2, this.t);
            this.r++;
            return;
        }
        throw new IOException("record has length '" + bArr.length + "' with offset '" + i2 + "' which is less than the record size of '" + this.t + "'");
    }

    private void g() throws IOException {
        int i2 = this.r % this.s;
        if (i2 != 0) {
            while (i2 < this.s) {
                f();
                i2++;
            }
        }
    }

    private void a(Map<String, String> map, TarArchiveEntry tarArchiveEntry) {
        a(map, "size", tarArchiveEntry.getSize(), (long) TarConstants.r);
        Map<String, String> map2 = map;
        a(map2, ApiConst.j, tarArchiveEntry.f(), (long) TarConstants.n);
        Map<String, String> map3 = map;
        a(map3, "mtime", tarArchiveEntry.i().getTime() / 1000, (long) TarConstants.r);
        a(map2, "uid", tarArchiveEntry.d(), (long) TarConstants.n);
        a(map3, "SCHILY.devmajor", (long) tarArchiveEntry.m(), (long) TarConstants.n);
        a(map2, "SCHILY.devminor", (long) tarArchiveEntry.n(), (long) TarConstants.n);
        a("mode", (long) tarArchiveEntry.l(), (long) TarConstants.n);
    }

    private void a(Map<String, String> map, String str, long j2, long j3) {
        if (j2 < 0 || j2 > j3) {
            map.put(str, String.valueOf(j2));
        }
    }

    private void a(TarArchiveEntry tarArchiveEntry) {
        a("entry size", tarArchiveEntry.getSize(), (long) TarConstants.r);
        b("group id", tarArchiveEntry.f(), TarConstants.n);
        a("last modification time", tarArchiveEntry.i().getTime() / 1000, (long) TarConstants.r);
        a("user id", tarArchiveEntry.d(), (long) TarConstants.n);
        a("mode", (long) tarArchiveEntry.l(), (long) TarConstants.n);
        a("major device number", (long) tarArchiveEntry.m(), (long) TarConstants.n);
        a("minor device number", (long) tarArchiveEntry.n(), (long) TarConstants.n);
    }

    private void a(String str, long j2, long j3) {
        a(str, j2, j3, "");
    }

    private void b(String str, long j2, long j3) {
        a(str, j2, j3, " Use STAR or POSIX extensions to overcome this limit");
    }

    private void a(String str, long j2, long j3, String str2) {
        if (j2 < 0 || j2 > j3) {
            throw new RuntimeException(str + " '" + j2 + "' is too big ( > " + j3 + " )." + str2);
        }
    }

    private boolean a(TarArchiveEntry tarArchiveEntry, String str, Map<String, String> map, String str2, byte b2, String str3) throws IOException {
        ByteBuffer b3 = this.y.b(str);
        int limit = b3.limit() - b3.position();
        if (limit >= 100) {
            if (this.p == 3) {
                map.put(str2, str);
                return true;
            } else if (this.p == 2) {
                TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(TarConstants.ah, b2);
                tarArchiveEntry2.d(((long) limit) + 1);
                a(tarArchiveEntry, tarArchiveEntry2);
                a((ArchiveEntry) tarArchiveEntry2);
                write(b3.array(), b3.arrayOffset(), limit);
                write(0);
                a();
            } else if (this.p != 1) {
                throw new RuntimeException(str3 + " '" + str + "' is too long ( > " + 100 + " bytes)");
            }
        }
        return false;
    }

    private void a(TarArchiveEntry tarArchiveEntry, TarArchiveEntry tarArchiveEntry2) {
        Date i2 = tarArchiveEntry.i();
        long time = i2.getTime() / 1000;
        if (time < 0 || time > TarConstants.r) {
            i2 = new Date(0);
        }
        tarArchiveEntry2.a(i2);
    }
}
