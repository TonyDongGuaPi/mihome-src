package org.apache.commons.compress.archivers.tar;

import com.xiaomi.smarthome.framework.openapi.ApiConst;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

public class TarArchiveInputStream extends ArchiveInputStream {
    private static final int b = 256;

    /* renamed from: a  reason: collision with root package name */
    final String f3241a;
    private final byte[] c;
    private final int d;
    private final int e;
    private boolean f;
    private long g;
    private long h;
    private final InputStream i;
    private TarArchiveEntry j;
    private final ZipEncoding k;
    private Map<String, String> l;

    public void mark(int i2) {
    }

    public boolean markSupported() {
        return false;
    }

    public TarArchiveInputStream(InputStream inputStream) {
        this(inputStream, 10240, 512);
    }

    public TarArchiveInputStream(InputStream inputStream, String str) {
        this(inputStream, 10240, 512, str);
    }

    public TarArchiveInputStream(InputStream inputStream, int i2) {
        this(inputStream, i2, 512);
    }

    public TarArchiveInputStream(InputStream inputStream, int i2, String str) {
        this(inputStream, i2, 512, str);
    }

    public TarArchiveInputStream(InputStream inputStream, int i2, int i3) {
        this(inputStream, i2, i3, (String) null);
    }

    public TarArchiveInputStream(InputStream inputStream, int i2, int i3, String str) {
        this.c = new byte[256];
        this.l = new HashMap();
        this.i = inputStream;
        this.f = false;
        this.f3241a = str;
        this.k = ZipEncodingHelper.a(str);
        this.d = i3;
        this.e = i2;
    }

    public void close() throws IOException {
        this.i.close();
    }

    public int d() {
        return this.d;
    }

    public int available() throws IOException {
        if (o()) {
            return 0;
        }
        if (this.g - this.h > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) (this.g - this.h);
    }

    public long skip(long j2) throws IOException {
        if (j2 <= 0 || o()) {
            return 0;
        }
        long skip = this.i.skip(Math.min(j2, this.g - this.h));
        a(skip);
        this.h += skip;
        return skip;
    }

    public synchronized void reset() {
    }

    public TarArchiveEntry e() throws IOException {
        if (this.f) {
            return null;
        }
        if (this.j != null) {
            IOUtils.a((InputStream) this, Long.MAX_VALUE);
            j();
        }
        byte[] k2 = k();
        if (k2 == null) {
            this.j = null;
            return null;
        }
        try {
            this.j = new TarArchiveEntry(k2, this.k);
            this.h = 0;
            this.g = this.j.getSize();
            if (this.j.u()) {
                byte[] f2 = f();
                if (f2 == null) {
                    return null;
                }
                this.j.b(this.k.a(f2));
            }
            if (this.j.v()) {
                byte[] f3 = f();
                if (f3 == null) {
                    return null;
                }
                this.j.a(this.k.a(f3));
            }
            if (this.j.x()) {
                l();
            }
            if (this.j.w()) {
                m();
            } else if (!this.l.isEmpty()) {
                a(this.l);
            }
            if (this.j.r()) {
                n();
            }
            this.g = this.j.getSize();
            return this.j;
        } catch (IllegalArgumentException e2) {
            throw new IOException("Error detected parsing the header", e2);
        }
    }

    private void j() throws IOException {
        if (!o() && this.g > 0 && this.g % ((long) this.d) != 0) {
            a(IOUtils.a(this.i, (((this.g / ((long) this.d)) + 1) * ((long) this.d)) - this.g));
        }
    }

    /* access modifiers changed from: protected */
    public byte[] f() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = read(this.c);
            if (read < 0) {
                break;
            }
            byteArrayOutputStream.write(this.c, 0, read);
        }
        a();
        if (this.j == null) {
            return null;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        while (length > 0 && byteArray[length - 1] == 0) {
            length--;
        }
        if (length == byteArray.length) {
            return byteArray;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 0, bArr, 0, length);
        return bArr;
    }

    private byte[] k() throws IOException {
        byte[] g2 = g();
        this.f = a(g2);
        if (!this.f || g2 == null) {
            return g2;
        }
        p();
        q();
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean a(byte[] bArr) {
        return bArr == null || ArchiveUtils.a(bArr, this.d);
    }

    /* access modifiers changed from: protected */
    public byte[] g() throws IOException {
        byte[] bArr = new byte[this.d];
        int a2 = IOUtils.a(this.i, bArr);
        a(a2);
        if (a2 != this.d) {
            return null;
        }
        return bArr;
    }

    private void l() throws IOException {
        this.l = a((InputStream) this);
        a();
    }

    private void m() throws IOException {
        Map<String, String> a2 = a((InputStream) this);
        a();
        a(a2);
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> a(InputStream inputStream) throws IOException {
        int read;
        int read2;
        HashMap hashMap = new HashMap(this.l);
        do {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                read = inputStream.read();
                if (read == -1) {
                    break;
                }
                i2++;
                if (read == 10) {
                    continue;
                    break;
                } else if (read == 32) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        read2 = inputStream.read();
                        if (read2 == -1) {
                            break;
                        }
                        i2++;
                        if (read2 == 61) {
                            String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                            int i4 = i3 - i2;
                            if (i4 == 1) {
                                hashMap.remove(byteArrayOutputStream2);
                            } else {
                                byte[] bArr = new byte[i4];
                                int a2 = IOUtils.a(inputStream, bArr);
                                if (a2 == i4) {
                                    hashMap.put(byteArrayOutputStream2, new String(bArr, 0, i4 - 1, "UTF-8"));
                                } else {
                                    throw new IOException("Failed to read Paxheader. Expected " + i4 + " bytes, read " + a2);
                                }
                            }
                        } else {
                            byteArrayOutputStream.write((byte) read2);
                        }
                    }
                    read = read2;
                    continue;
                } else {
                    i3 = (i3 * 10) + (read - 48);
                }
            }
        } while (read != -1);
        return hashMap;
    }

    private void a(Map<String, String> map) {
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if ("path".equals(str)) {
                this.j.a(str2);
            } else if ("linkpath".equals(str)) {
                this.j.b(str2);
            } else if (ApiConst.j.equals(str)) {
                this.j.b(Long.parseLong(str2));
            } else if ("gname".equals(str)) {
                this.j.d(str2);
            } else if ("uid".equals(str)) {
                this.j.a(Long.parseLong(str2));
            } else if ("uname".equals(str)) {
                this.j.c(str2);
            } else if ("size".equals(str)) {
                this.j.d(Long.parseLong(str2));
            } else if ("mtime".equals(str)) {
                this.j.c((long) (Double.parseDouble(str2) * 1000.0d));
            } else if ("SCHILY.devminor".equals(str)) {
                this.j.e(Integer.parseInt(str2));
            } else if ("SCHILY.devmajor".equals(str)) {
                this.j.d(Integer.parseInt(str2));
            } else if ("GNU.sparse.size".equals(str)) {
                this.j.a(map);
            } else if ("GNU.sparse.realsize".equals(str)) {
                this.j.b(map);
            } else if ("SCHILY.filetype".equals(str) && "sparse".equals(str2)) {
                this.j.c(map);
            }
        }
    }

    private void n() throws IOException {
        byte[] k2;
        if (this.j.o()) {
            do {
                k2 = k();
                if (k2 == null) {
                    this.j = null;
                    return;
                }
            } while (new TarArchiveSparseEntry(k2).a());
        }
    }

    private boolean o() {
        return this.j != null && this.j.isDirectory();
    }

    public ArchiveEntry a() throws IOException {
        return e();
    }

    private void p() throws IOException {
        boolean markSupported = this.i.markSupported();
        if (markSupported) {
            this.i.mark(this.d);
        }
        try {
            if ((!a(g())) && markSupported) {
            }
        } finally {
            if (markSupported) {
                b((long) this.d);
                this.i.reset();
            }
        }
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        if (this.f || o() || this.h >= this.g) {
            return -1;
        }
        if (this.j != null) {
            int min = Math.min(i3, available());
            int read = this.i.read(bArr, i2, min);
            if (read != -1) {
                a(read);
                this.h += (long) read;
            } else if (min <= 0) {
                this.f = true;
            } else {
                throw new IOException("Truncated TAR archive");
            }
            return read;
        }
        throw new IllegalStateException("No current tar entry");
    }

    public boolean a(ArchiveEntry archiveEntry) {
        if (archiveEntry instanceof TarArchiveEntry) {
            return !((TarArchiveEntry) archiveEntry).E();
        }
        return false;
    }

    public TarArchiveEntry h() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public final void a(TarArchiveEntry tarArchiveEntry) {
        this.j = tarArchiveEntry;
    }

    /* access modifiers changed from: protected */
    public final boolean i() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z) {
        this.f = z;
    }

    private void q() throws IOException {
        long c2 = c() % ((long) this.e);
        if (c2 > 0) {
            a(IOUtils.a(this.i, ((long) this.e) - c2));
        }
    }

    public static boolean a(byte[] bArr, int i2) {
        if (i2 < 265) {
            return false;
        }
        if (ArchiveUtils.a("ustar\u0000", bArr, 257, 6) && ArchiveUtils.a("00", bArr, 263, 2)) {
            return true;
        }
        if (!ArchiveUtils.a(TarConstants.ac, bArr, 257, 6) || (!ArchiveUtils.a(TarConstants.ad, bArr, 263, 2) && !ArchiveUtils.a(TarConstants.ae, bArr, 263, 2))) {
            return ArchiveUtils.a("ustar\u0000", bArr, 257, 6) && ArchiveUtils.a(TarConstants.ag, bArr, 263, 2);
        }
        return true;
    }
}
