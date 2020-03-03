package org.apache.commons.compress.archivers.zip;

import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ExtraFieldUtils;

public class ZipArchiveEntry extends ZipEntry implements ArchiveEntry {
    public static final int b = 3;
    public static final int c = 0;
    public static final int d = -1;
    private static final int e = 65535;
    private static final int f = 16;
    private static final byte[] g = new byte[0];
    private static final ZipExtraField[] u = new ZipExtraField[0];
    private int h;
    private long i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private long o;
    private ZipExtraField[] p;
    private UnparseableExtraFieldData q;
    private String r;
    private byte[] s;
    private GeneralPurposeBit t;

    public ZipArchiveEntry(String str) {
        super(str);
        this.h = -1;
        this.i = -1;
        this.j = 0;
        this.m = 0;
        this.o = 0;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = new GeneralPurposeBit();
        a(str);
    }

    public ZipArchiveEntry(ZipEntry zipEntry) throws ZipException {
        super(zipEntry);
        this.h = -1;
        this.i = -1;
        this.j = 0;
        this.m = 0;
        this.o = 0;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = new GeneralPurposeBit();
        a(zipEntry.getName());
        byte[] extra = zipEntry.getExtra();
        if (extra != null) {
            a(ExtraFieldUtils.a(extra, true, ExtraFieldUtils.UnparseableExtraField.f));
        } else {
            l();
        }
        setMethod(zipEntry.getMethod());
        this.i = zipEntry.getSize();
    }

    public ZipArchiveEntry(ZipArchiveEntry zipArchiveEntry) throws ZipException {
        this((ZipEntry) zipArchiveEntry);
        GeneralPurposeBit generalPurposeBit;
        a(zipArchiveEntry.d());
        a(zipArchiveEntry.e());
        a(t());
        c(zipArchiveEntry.h());
        GeneralPurposeBit p2 = zipArchiveEntry.p();
        if (p2 == null) {
            generalPurposeBit = null;
        } else {
            generalPurposeBit = (GeneralPurposeBit) p2.clone();
        }
        a(generalPurposeBit);
    }

    protected ZipArchiveEntry() {
        this("");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ZipArchiveEntry(java.io.File r3, java.lang.String r4) {
        /*
            r2 = this;
            boolean r0 = r3.isDirectory()
            if (r0 == 0) goto L_0x001f
            java.lang.String r0 = "/"
            boolean r0 = r4.endsWith(r0)
            if (r0 != 0) goto L_0x001f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r4)
            java.lang.String r4 = "/"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
        L_0x001f:
            r2.<init>((java.lang.String) r4)
            boolean r4 = r3.isFile()
            if (r4 == 0) goto L_0x002f
            long r0 = r3.length()
            r2.setSize(r0)
        L_0x002f:
            long r3 = r3.lastModified()
            r2.setTime(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipArchiveEntry.<init>(java.io.File, java.lang.String):void");
    }

    public Object clone() {
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) super.clone();
        zipArchiveEntry.a(d());
        zipArchiveEntry.a(e());
        zipArchiveEntry.a(t());
        return zipArchiveEntry;
    }

    public int getMethod() {
        return this.h;
    }

    public void setMethod(int i2) {
        if (i2 >= 0) {
            this.h = i2;
            return;
        }
        throw new IllegalArgumentException("ZIP compression method can not be negative: " + i2);
    }

    public int d() {
        return this.j;
    }

    public void a(int i2) {
        this.j = i2;
    }

    public long e() {
        return this.o;
    }

    public void a(long j2) {
        this.o = j2;
    }

    public void b(int i2) {
        int i3 = 0;
        int i4 = ((i2 & 128) == 0 ? 1 : 0) | (i2 << 16);
        if (isDirectory()) {
            i3 = 16;
        }
        a((long) (i4 | i3));
        this.m = 3;
    }

    public int f() {
        if (this.m != 3) {
            return 0;
        }
        return (int) ((e() >> 16) & 65535);
    }

    public boolean g() {
        return (f() & 40960) == 40960;
    }

    public int h() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void c(int i2) {
        this.m = i2;
    }

    public void a(ZipExtraField[] zipExtraFieldArr) {
        ArrayList arrayList = new ArrayList();
        for (UnparseableExtraFieldData unparseableExtraFieldData : zipExtraFieldArr) {
            if (unparseableExtraFieldData instanceof UnparseableExtraFieldData) {
                this.q = unparseableExtraFieldData;
            } else {
                arrayList.add(unparseableExtraFieldData);
            }
        }
        this.p = (ZipExtraField[]) arrayList.toArray(new ZipExtraField[arrayList.size()]);
        l();
    }

    public ZipExtraField[] i() {
        return c();
    }

    public ZipExtraField[] a(boolean z) {
        return z ? w() : c();
    }

    private ZipExtraField[] b() {
        if (this.p == null) {
            return u;
        }
        return this.p;
    }

    private ZipExtraField[] c() {
        ZipExtraField[] b2 = b();
        return b2 == this.p ? b(b2) : b2;
    }

    private ZipExtraField[] t() {
        if (this.p == null) {
            return v();
        }
        return this.q != null ? u() : this.p;
    }

    private ZipExtraField[] b(ZipExtraField[] zipExtraFieldArr) {
        return a(zipExtraFieldArr, zipExtraFieldArr.length);
    }

    private ZipExtraField[] a(ZipExtraField[] zipExtraFieldArr, int i2) {
        ZipExtraField[] zipExtraFieldArr2 = new ZipExtraField[i2];
        System.arraycopy(zipExtraFieldArr, 0, zipExtraFieldArr2, 0, Math.min(zipExtraFieldArr.length, i2));
        return zipExtraFieldArr2;
    }

    private ZipExtraField[] u() {
        ZipExtraField[] a2 = a(this.p, this.p.length + 1);
        a2[this.p.length] = this.q;
        return a2;
    }

    private ZipExtraField[] v() {
        if (this.q == null) {
            return u;
        }
        return new ZipExtraField[]{this.q};
    }

    private ZipExtraField[] w() {
        ZipExtraField[] t2 = t();
        return t2 == this.p ? b(t2) : t2;
    }

    public void a(ZipExtraField zipExtraField) {
        if (zipExtraField instanceof UnparseableExtraFieldData) {
            this.q = (UnparseableExtraFieldData) zipExtraField;
        } else if (this.p == null) {
            this.p = new ZipExtraField[]{zipExtraField};
        } else {
            if (b(zipExtraField.getHeaderId()) != null) {
                a(zipExtraField.getHeaderId());
            }
            ZipExtraField[] a2 = a(this.p, this.p.length + 1);
            a2[a2.length - 1] = zipExtraField;
            this.p = a2;
        }
        l();
    }

    public void b(ZipExtraField zipExtraField) {
        if (zipExtraField instanceof UnparseableExtraFieldData) {
            this.q = (UnparseableExtraFieldData) zipExtraField;
        } else {
            if (b(zipExtraField.getHeaderId()) != null) {
                a(zipExtraField.getHeaderId());
            }
            ZipExtraField[] zipExtraFieldArr = this.p;
            this.p = new ZipExtraField[(this.p != null ? this.p.length + 1 : 1)];
            this.p[0] = zipExtraField;
            if (zipExtraFieldArr != null) {
                System.arraycopy(zipExtraFieldArr, 0, this.p, 1, this.p.length - 1);
            }
        }
        l();
    }

    public void a(ZipShort zipShort) {
        if (this.p != null) {
            ArrayList arrayList = new ArrayList();
            for (ZipExtraField zipExtraField : this.p) {
                if (!zipShort.equals(zipExtraField.getHeaderId())) {
                    arrayList.add(zipExtraField);
                }
            }
            if (this.p.length != arrayList.size()) {
                this.p = (ZipExtraField[]) arrayList.toArray(new ZipExtraField[arrayList.size()]);
                l();
                return;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public void j() {
        if (this.q != null) {
            this.q = null;
            l();
            return;
        }
        throw new NoSuchElementException();
    }

    public ZipExtraField b(ZipShort zipShort) {
        if (this.p == null) {
            return null;
        }
        for (ZipExtraField zipExtraField : this.p) {
            if (zipShort.equals(zipExtraField.getHeaderId())) {
                return zipExtraField;
            }
        }
        return null;
    }

    public UnparseableExtraFieldData k() {
        return this.q;
    }

    public void setExtra(byte[] bArr) throws RuntimeException {
        try {
            a(ExtraFieldUtils.a(bArr, true, ExtraFieldUtils.UnparseableExtraField.f), true);
        } catch (ZipException e2) {
            throw new RuntimeException("Error parsing extra fields for entry: " + getName() + " - " + e2.getMessage(), e2);
        }
    }

    /* access modifiers changed from: protected */
    public void l() {
        super.setExtra(ExtraFieldUtils.a(t()));
    }

    public void a(byte[] bArr) {
        try {
            a(ExtraFieldUtils.a(bArr, false, ExtraFieldUtils.UnparseableExtraField.f), false);
        } catch (ZipException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public byte[] m() {
        byte[] extra = getExtra();
        return extra != null ? extra : g;
    }

    public byte[] n() {
        return ExtraFieldUtils.b(t());
    }

    public String getName() {
        return this.r == null ? super.getName() : this.r;
    }

    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        if (str != null && h() == 0 && !str.contains("/")) {
            str = str.replace(IOUtils.b, IOUtils.f15883a);
        }
        this.r = str;
    }

    public long getSize() {
        return this.i;
    }

    public void setSize(long j2) {
        if (j2 >= 0) {
            this.i = j2;
            return;
        }
        throw new IllegalArgumentException("invalid entry size");
    }

    /* access modifiers changed from: protected */
    public void a(String str, byte[] bArr) {
        a(str);
        this.s = bArr;
    }

    public byte[] o() {
        if (this.s == null) {
            return null;
        }
        byte[] bArr = new byte[this.s.length];
        System.arraycopy(this.s, 0, bArr, 0, this.s.length);
        return bArr;
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public GeneralPurposeBit p() {
        return this.t;
    }

    public void a(GeneralPurposeBit generalPurposeBit) {
        this.t = generalPurposeBit;
    }

    private void a(ZipExtraField[] zipExtraFieldArr, boolean z) throws ZipException {
        ZipExtraField zipExtraField;
        if (this.p == null) {
            a(zipExtraFieldArr);
            return;
        }
        for (ZipExtraField zipExtraField2 : zipExtraFieldArr) {
            if (zipExtraField2 instanceof UnparseableExtraFieldData) {
                zipExtraField = this.q;
            } else {
                zipExtraField = b(zipExtraField2.getHeaderId());
            }
            if (zipExtraField == null) {
                a(zipExtraField2);
            } else if (z) {
                byte[] localFileDataData = zipExtraField2.getLocalFileDataData();
                zipExtraField.parseFromLocalFileData(localFileDataData, 0, localFileDataData.length);
            } else {
                byte[] centralDirectoryData = zipExtraField2.getCentralDirectoryData();
                zipExtraField.parseFromCentralDirectoryData(centralDirectoryData, 0, centralDirectoryData.length);
            }
        }
        l();
    }

    public Date a() {
        return new Date(getTime());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) obj;
        String name = getName();
        String name2 = zipArchiveEntry.getName();
        if (name == null) {
            if (name2 != null) {
                return false;
            }
        } else if (!name.equals(name2)) {
            return false;
        }
        String comment = getComment();
        String comment2 = zipArchiveEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        if (comment2 == null) {
            comment2 = "";
        }
        if (getTime() == zipArchiveEntry.getTime() && comment.equals(comment2) && d() == zipArchiveEntry.d() && h() == zipArchiveEntry.h() && e() == zipArchiveEntry.e() && getMethod() == zipArchiveEntry.getMethod() && getSize() == zipArchiveEntry.getSize() && getCrc() == zipArchiveEntry.getCrc() && getCompressedSize() == zipArchiveEntry.getCompressedSize() && Arrays.equals(n(), zipArchiveEntry.n()) && Arrays.equals(m(), zipArchiveEntry.m()) && this.t.equals(zipArchiveEntry.t)) {
            return true;
        }
        return false;
    }

    public void d(int i2) {
        this.l = i2;
    }

    public void e(int i2) {
        this.k = i2;
    }

    public int q() {
        return this.k;
    }

    public int r() {
        return this.l;
    }

    public int s() {
        return this.n;
    }

    public void f(int i2) {
        this.n = i2;
    }
}
