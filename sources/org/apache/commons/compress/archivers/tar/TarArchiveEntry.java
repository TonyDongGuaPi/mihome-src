package org.apache.commons.compress.archivers.tar;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ArchiveUtils;

public class TarArchiveEntry implements ArchiveEntry, TarConstants {
    private static final TarArchiveEntry[] ao = new TarArchiveEntry[0];
    public static final int b = 31;
    public static final int c = 16877;
    public static final int d = 33188;
    public static final int e = 1000;
    private String aA;
    private String aB;
    private String aC;
    private int aD;
    private int aE;
    private boolean aF;
    private long aG;
    private boolean aH;
    private boolean aI;
    private final File aJ;
    private String ap;
    private boolean aq;
    private int ar;
    private long as;
    private long at;
    private long au;
    private long av;
    private boolean aw;
    private byte ax;
    private String ay;
    private String az;

    private TarArchiveEntry() {
        this.ap = "";
        this.as = 0;
        this.at = 0;
        this.au = 0;
        this.ay = "";
        this.az = "ustar\u0000";
        this.aA = "00";
        this.aC = "";
        this.aD = 0;
        this.aE = 0;
        String property = System.getProperty("user.name", "");
        this.aB = property.length() > 31 ? property.substring(0, 31) : property;
        this.aJ = null;
    }

    public TarArchiveEntry(String str) {
        this(str, false);
    }

    public TarArchiveEntry(String str, boolean z) {
        this();
        this.aq = z;
        String a2 = a(str, z);
        boolean endsWith = a2.endsWith("/");
        this.ap = a2;
        this.ar = endsWith ? c : d;
        this.ax = endsWith ? TarConstants.R : 48;
        this.av = new Date().getTime() / 1000;
        this.aB = "";
    }

    public TarArchiveEntry(String str, byte b2) {
        this(str, b2, false);
    }

    public TarArchiveEntry(String str, byte b2, boolean z) {
        this(str, z);
        this.ax = b2;
        if (b2 == 76) {
            this.az = TarConstants.ac;
            this.aA = TarConstants.ad;
        }
    }

    public TarArchiveEntry(File file) {
        this(file, file.getPath());
    }

    public TarArchiveEntry(File file, String str) {
        this.ap = "";
        this.as = 0;
        this.at = 0;
        this.au = 0;
        this.ay = "";
        this.az = "ustar\u0000";
        this.aA = "00";
        this.aC = "";
        this.aD = 0;
        this.aE = 0;
        String a2 = a(str, false);
        this.aJ = file;
        if (file.isDirectory()) {
            this.ar = c;
            this.ax = TarConstants.R;
            int length = a2.length();
            if (length == 0 || a2.charAt(length - 1) != '/') {
                this.ap = a2 + "/";
            } else {
                this.ap = a2;
            }
        } else {
            this.ar = d;
            this.ax = 48;
            this.au = file.length();
            this.ap = a2;
        }
        this.av = file.lastModified() / 1000;
        this.aB = "";
    }

    public TarArchiveEntry(byte[] bArr) {
        this();
        b(bArr);
    }

    public TarArchiveEntry(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        this();
        a(bArr, zipEncoding);
    }

    public boolean a(TarArchiveEntry tarArchiveEntry) {
        return tarArchiveEntry != null && getName().equals(tarArchiveEntry.getName());
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return a((TarArchiveEntry) obj);
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean b(TarArchiveEntry tarArchiveEntry) {
        return tarArchiveEntry.getName().startsWith(getName());
    }

    public String getName() {
        return this.ap;
    }

    public void a(String str) {
        this.ap = a(str, this.aq);
    }

    public void a(int i) {
        this.ar = i;
    }

    public String b() {
        return this.ay;
    }

    public void b(String str) {
        this.ay = str;
    }

    @Deprecated
    public int c() {
        return (int) (this.as & -1);
    }

    public void b(int i) {
        a((long) i);
    }

    public long d() {
        return this.as;
    }

    public void a(long j) {
        this.as = j;
    }

    @Deprecated
    public int e() {
        return (int) (this.at & -1);
    }

    public void c(int i) {
        b((long) i);
    }

    public long f() {
        return this.at;
    }

    public void b(long j) {
        this.at = j;
    }

    public String g() {
        return this.aB;
    }

    public void c(String str) {
        this.aB = str;
    }

    public String h() {
        return this.aC;
    }

    public void d(String str) {
        this.aC = str;
    }

    public void a(int i, int i2) {
        b(i);
        c(i2);
    }

    public void a(String str, String str2) {
        c(str);
        d(str2);
    }

    public void c(long j) {
        this.av = j / 1000;
    }

    public void a(Date date) {
        this.av = date.getTime() / 1000;
    }

    public Date i() {
        return new Date(this.av * 1000);
    }

    public Date a() {
        return i();
    }

    public boolean j() {
        return this.aw;
    }

    public File k() {
        return this.aJ;
    }

    public int l() {
        return this.ar;
    }

    public long getSize() {
        return this.au;
    }

    public void d(long j) {
        if (j >= 0) {
            this.au = j;
            return;
        }
        throw new IllegalArgumentException("Size is out of range: " + j);
    }

    public int m() {
        return this.aD;
    }

    public void d(int i) {
        if (i >= 0) {
            this.aD = i;
            return;
        }
        throw new IllegalArgumentException("Major device number is out of range: " + i);
    }

    public int n() {
        return this.aE;
    }

    public void e(int i) {
        if (i >= 0) {
            this.aE = i;
            return;
        }
        throw new IllegalArgumentException("Minor device number is out of range: " + i);
    }

    public boolean o() {
        return this.aF;
    }

    public long p() {
        return this.aG;
    }

    public boolean q() {
        return r() || s();
    }

    public boolean r() {
        return this.ax == 83;
    }

    public boolean s() {
        return this.aH;
    }

    public boolean t() {
        return this.aI;
    }

    public boolean u() {
        return this.ax == 75;
    }

    public boolean v() {
        return this.ax == 76;
    }

    public boolean w() {
        return this.ax == 120 || this.ax == 88;
    }

    public boolean x() {
        return this.ax == 103;
    }

    public boolean isDirectory() {
        if (this.aJ != null) {
            return this.aJ.isDirectory();
        }
        if (this.ax == 53) {
            return true;
        }
        if (w() || x() || !getName().endsWith("/")) {
            return false;
        }
        return true;
    }

    public boolean y() {
        if (this.aJ != null) {
            return this.aJ.isFile();
        }
        if (this.ax == 0 || this.ax == 48) {
            return true;
        }
        return !getName().endsWith("/");
    }

    public boolean z() {
        return this.ax == 50;
    }

    public boolean A() {
        return this.ax == 49;
    }

    public boolean B() {
        return this.ax == 51;
    }

    public boolean C() {
        return this.ax == 52;
    }

    public boolean D() {
        return this.ax == 54;
    }

    public boolean E() {
        return q() || t();
    }

    public TarArchiveEntry[] F() {
        if (this.aJ == null || !this.aJ.isDirectory()) {
            return ao;
        }
        String[] list = this.aJ.list();
        if (list == null) {
            return ao;
        }
        TarArchiveEntry[] tarArchiveEntryArr = new TarArchiveEntry[list.length];
        for (int i = 0; i < tarArchiveEntryArr.length; i++) {
            tarArchiveEntryArr[i] = new TarArchiveEntry(new File(this.aJ, list[i]));
        }
        return tarArchiveEntryArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(byte[] r3) {
        /*
            r2 = this;
            r0 = 0
            org.apache.commons.compress.archivers.zip.ZipEncoding r1 = org.apache.commons.compress.archivers.tar.TarUtils.f3243a     // Catch:{ IOException -> 0x0007 }
            r2.a(r3, r1, r0)     // Catch:{ IOException -> 0x0007 }
            goto L_0x000c
        L_0x0007:
            org.apache.commons.compress.archivers.zip.ZipEncoding r1 = org.apache.commons.compress.archivers.tar.TarUtils.b     // Catch:{ IOException -> 0x000d }
            r2.a(r3, r1, r0)     // Catch:{ IOException -> 0x000d }
        L_0x000c:
            return
        L_0x000d:
            r3 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveEntry.a(byte[]):void");
    }

    public void a(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        byte[] bArr2 = bArr;
        ZipEncoding zipEncoding2 = zipEncoding;
        int a2 = TarUtils.a(this.ap, bArr, 0, 100, zipEncoding2);
        byte[] bArr3 = bArr;
        boolean z2 = z;
        int a3 = a(this.av, bArr3, a(this.au, bArr3, a(this.at, bArr3, a(this.as, bArr3, a((long) this.ar, bArr3, a2, 8, z2), 8, z2), 8, z2), 12, z2), 12, z2);
        int i = a3;
        int i2 = 0;
        while (i2 < 8) {
            bArr2[i] = 32;
            i2++;
            i++;
        }
        bArr2[i] = this.ax;
        int a4 = TarUtils.a(this.aC, bArr, TarUtils.a(this.aB, bArr, TarUtils.a(this.aA, bArr, TarUtils.a(this.az, bArr, TarUtils.a(this.ay, bArr, i + 1, 100, zipEncoding2), 6), 2), 32, zipEncoding2), 32, zipEncoding2);
        byte[] bArr4 = bArr;
        boolean z3 = z;
        int a5 = a((long) this.aD, bArr4, a4, 8, z3);
        for (int a6 = a((long) this.aE, bArr4, a5, 8, z3); a6 < bArr2.length; a6++) {
            bArr2[a6] = 0;
        }
        TarUtils.e(TarUtils.a(bArr), bArr, a3, 8);
    }

    private int a(long j, byte[] bArr, int i, int i2, boolean z) {
        if (z || (j >= 0 && j < (1 << ((i2 - 1) * 3)))) {
            return TarUtils.d(j, bArr, i, i2);
        }
        return TarUtils.c(0, bArr, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0006 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(byte[] r3) {
        /*
            r2 = this;
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = org.apache.commons.compress.archivers.tar.TarUtils.f3243a     // Catch:{ IOException -> 0x0006 }
            r2.a((byte[]) r3, (org.apache.commons.compress.archivers.zip.ZipEncoding) r0)     // Catch:{ IOException -> 0x0006 }
            goto L_0x000c
        L_0x0006:
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = org.apache.commons.compress.archivers.tar.TarUtils.f3243a     // Catch:{ IOException -> 0x000d }
            r1 = 1
            r2.b(r3, r0, r1)     // Catch:{ IOException -> 0x000d }
        L_0x000c:
            return
        L_0x000d:
            r3 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveEntry.b(byte[]):void");
    }

    public void a(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        b(bArr, zipEncoding, false);
    }

    private void b(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        this.ap = z ? TarUtils.c(bArr, 0, 100) : TarUtils.a(bArr, 0, 100, zipEncoding);
        this.ar = (int) TarUtils.b(bArr, 100, 8);
        this.as = (long) ((int) TarUtils.b(bArr, 108, 8));
        this.at = (long) ((int) TarUtils.b(bArr, 116, 8));
        this.au = TarUtils.b(bArr, 124, 12);
        this.av = TarUtils.b(bArr, 136, 12);
        this.aw = TarUtils.b(bArr);
        this.ax = bArr[156];
        this.ay = z ? TarUtils.c(bArr, 157, 100) : TarUtils.a(bArr, 157, 100, zipEncoding);
        this.az = TarUtils.c(bArr, 257, 6);
        this.aA = TarUtils.c(bArr, 263, 2);
        this.aB = z ? TarUtils.c(bArr, 265, 32) : TarUtils.a(bArr, 265, 32, zipEncoding);
        this.aC = z ? TarUtils.c(bArr, ExifDirectoryBase.F, 32) : TarUtils.a(bArr, (int) ExifDirectoryBase.F, 32, zipEncoding);
        this.aD = (int) TarUtils.b(bArr, TbsListener.ErrorCode.ERROR_GETSTRINGARRAY_JARFILE, 8);
        this.aE = (int) TarUtils.b(bArr, 337, 8);
        int c2 = c(bArr);
        if (c2 == 2) {
            this.aF = TarUtils.a(bArr, 482);
            this.aG = TarUtils.a(bArr, 483, 12);
        } else if (c2 != 4) {
            String c3 = z ? TarUtils.c(bArr, 345, 155) : TarUtils.a(bArr, 345, 155, zipEncoding);
            if (isDirectory() && !this.ap.endsWith("/")) {
                this.ap += "/";
            }
            if (c3.length() > 0) {
                this.ap = c3 + "/" + this.ap;
            }
        } else {
            String c4 = z ? TarUtils.c(bArr, 345, 131) : TarUtils.a(bArr, 345, 131, zipEncoding);
            if (c4.length() > 0) {
                this.ap = c4 + "/" + this.ap;
            }
        }
    }

    private static String a(String str, boolean z) {
        int indexOf;
        String lowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        if (lowerCase != null) {
            if (lowerCase.startsWith("windows")) {
                if (str.length() > 2) {
                    char charAt = str.charAt(0);
                    if (str.charAt(1) == ':' && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                        str = str.substring(2);
                    }
                }
            } else if (lowerCase.contains("netware") && (indexOf = str.indexOf(58)) != -1) {
                str = str.substring(indexOf + 1);
            }
        }
        String replace = str.replace(File.separatorChar, IOUtils.f15883a);
        while (!z && replace.startsWith("/")) {
            replace = replace.substring(1);
        }
        return replace;
    }

    private int c(byte[] bArr) {
        if (ArchiveUtils.a(TarConstants.ac, bArr, 257, 6)) {
            return 2;
        }
        if (!ArchiveUtils.a("ustar\u0000", bArr, 257, 6)) {
            return 0;
        }
        if (ArchiveUtils.a(TarConstants.ai, bArr, 508, 4)) {
            return 4;
        }
        return 3;
    }

    /* access modifiers changed from: package-private */
    public void a(Map<String, String> map) {
        this.aH = true;
        this.aG = (long) Integer.parseInt(map.get("GNU.sparse.size"));
        if (map.containsKey("GNU.sparse.name")) {
            this.ap = map.get("GNU.sparse.name");
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Map<String, String> map) {
        this.aH = true;
        this.aG = (long) Integer.parseInt(map.get("GNU.sparse.realsize"));
        this.ap = map.get("GNU.sparse.name");
    }

    /* access modifiers changed from: package-private */
    public void c(Map<String, String> map) {
        this.aI = true;
        if (map.containsKey("SCHILY.realsize")) {
            this.aG = Long.parseLong(map.get("SCHILY.realsize"));
        }
    }
}
