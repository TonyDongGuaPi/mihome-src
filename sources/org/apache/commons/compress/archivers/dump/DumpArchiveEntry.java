package org.apache.commons.compress.archivers.dump;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.archivers.zip.UnixStat;

public class DumpArchiveEntry implements ArchiveEntry {
    private String b;
    private TYPE c = TYPE.UNKNOWN;
    private int d;
    private Set<PERMISSION> e = Collections.emptySet();
    private long f;
    private long g;
    private long h;
    private int i;
    private int j;
    private final DumpArchiveSummary k = null;
    private final TapeSegmentHeader l = new TapeSegmentHeader();
    private String m;
    private String n;
    private int o;
    private long p;
    private int q;
    private int r;
    private long s;
    private int t;
    private boolean u;

    public DumpArchiveEntry() {
    }

    public DumpArchiveEntry(String str, String str2) {
        b(str);
        this.m = str2;
    }

    protected DumpArchiveEntry(String str, String str2, int i2, TYPE type) {
        a(type);
        b(str);
        this.m = str2;
        this.q = i2;
        this.p = 0;
    }

    public String b() {
        return this.m;
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.m = str;
    }

    public int c() {
        return this.l.c();
    }

    public int d() {
        return this.r;
    }

    public void a(int i2) {
        this.r = i2;
    }

    public Date e() {
        return new Date(this.s);
    }

    public void a(Date date) {
        this.s = date.getTime();
    }

    public int f() {
        return this.t;
    }

    public void b(int i2) {
        this.t = i2;
    }

    public boolean g() {
        return this.u;
    }

    public void a(boolean z) {
        this.u = z;
    }

    public long h() {
        return this.p;
    }

    public void a(long j2) {
        this.p = j2;
    }

    public int i() {
        return this.o;
    }

    public void c(int i2) {
        this.o = i2;
    }

    public DumpArchiveConstants.SEGMENT_TYPE j() {
        return this.l.a();
    }

    public int k() {
        return this.l.d();
    }

    public int l() {
        return this.l.e();
    }

    public boolean d(int i2) {
        return (this.l.b(i2) & 1) == 0;
    }

    public int hashCode() {
        return this.q;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        DumpArchiveEntry dumpArchiveEntry = (DumpArchiveEntry) obj;
        if (this.l == null || dumpArchiveEntry.l == null || this.q != dumpArchiveEntry.q) {
            return false;
        }
        return (this.k != null || dumpArchiveEntry.k == null) && (this.k == null || this.k.equals(dumpArchiveEntry.k));
    }

    public String toString() {
        return getName();
    }

    static DumpArchiveEntry a(byte[] bArr) {
        DumpArchiveEntry dumpArchiveEntry = new DumpArchiveEntry();
        TapeSegmentHeader tapeSegmentHeader = dumpArchiveEntry.l;
        DumpArchiveConstants.SEGMENT_TYPE unused = tapeSegmentHeader.f3213a = DumpArchiveConstants.SEGMENT_TYPE.find(DumpArchiveUtil.b(bArr, 0));
        int unused2 = tapeSegmentHeader.b = DumpArchiveUtil.b(bArr, 12);
        dumpArchiveEntry.q = tapeSegmentHeader.c = DumpArchiveUtil.b(bArr, 20);
        int c2 = DumpArchiveUtil.c(bArr, 32);
        dumpArchiveEntry.a(TYPE.find((c2 >> 12) & 15));
        dumpArchiveEntry.e(c2);
        dumpArchiveEntry.r = DumpArchiveUtil.c(bArr, 34);
        dumpArchiveEntry.b(DumpArchiveUtil.a(bArr, 40));
        dumpArchiveEntry.c(new Date((((long) DumpArchiveUtil.b(bArr, 48)) * 1000) + ((long) (DumpArchiveUtil.b(bArr, 52) / 1000))));
        dumpArchiveEntry.b(new Date((((long) DumpArchiveUtil.b(bArr, 56)) * 1000) + ((long) (DumpArchiveUtil.b(bArr, 60) / 1000))));
        dumpArchiveEntry.s = (((long) DumpArchiveUtil.b(bArr, 64)) * 1000) + ((long) (DumpArchiveUtil.b(bArr, 68) / 1000));
        dumpArchiveEntry.t = DumpArchiveUtil.b(bArr, 140);
        dumpArchiveEntry.f(DumpArchiveUtil.b(bArr, 144));
        dumpArchiveEntry.g(DumpArchiveUtil.b(bArr, 148));
        int unused3 = tapeSegmentHeader.d = DumpArchiveUtil.b(bArr, 160);
        int unused4 = tapeSegmentHeader.e = 0;
        int i2 = 0;
        while (i2 < 512 && i2 < tapeSegmentHeader.d) {
            if (bArr[i2 + 164] == 0) {
                TapeSegmentHeader.b(tapeSegmentHeader);
            }
            i2++;
        }
        System.arraycopy(bArr, 164, tapeSegmentHeader.f, 0, 512);
        dumpArchiveEntry.o = tapeSegmentHeader.b();
        return dumpArchiveEntry;
    }

    /* access modifiers changed from: package-private */
    public void b(byte[] bArr) {
        int unused = this.l.b = DumpArchiveUtil.b(bArr, 16);
        int unused2 = this.l.d = DumpArchiveUtil.b(bArr, 160);
        int unused3 = this.l.e = 0;
        int i2 = 0;
        while (i2 < 512 && i2 < this.l.d) {
            if (bArr[i2 + 164] == 0) {
                TapeSegmentHeader.b(this.l);
            }
            i2++;
        }
        System.arraycopy(bArr, 164, this.l.f, 0, 512);
    }

    static class TapeSegmentHeader {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public DumpArchiveConstants.SEGMENT_TYPE f3213a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public int d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public final byte[] f = new byte[512];

        TapeSegmentHeader() {
        }

        static /* synthetic */ int b(TapeSegmentHeader tapeSegmentHeader) {
            int i = tapeSegmentHeader.e;
            tapeSegmentHeader.e = i + 1;
            return i;
        }

        public DumpArchiveConstants.SEGMENT_TYPE a() {
            return this.f3213a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.c = i;
        }

        public int d() {
            return this.d;
        }

        public int e() {
            return this.e;
        }

        public int b(int i) {
            return this.f[i];
        }
    }

    public String getName() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public String m() {
        return this.n;
    }

    public final void b(String str) {
        this.n = str;
        if (str != null) {
            if (isDirectory() && !str.endsWith("/")) {
                str = str + "/";
            }
            if (str.startsWith("./")) {
                str = str.substring(2);
            }
        }
        this.b = str;
    }

    public Date a() {
        return new Date(this.h);
    }

    public boolean isDirectory() {
        return this.c == TYPE.DIRECTORY;
    }

    public boolean n() {
        return this.c == TYPE.FILE;
    }

    public boolean o() {
        return this.c == TYPE.SOCKET;
    }

    public boolean p() {
        return this.c == TYPE.CHRDEV;
    }

    public boolean q() {
        return this.c == TYPE.BLKDEV;
    }

    public boolean r() {
        return this.c == TYPE.FIFO;
    }

    public TYPE s() {
        return this.c;
    }

    public void a(TYPE type) {
        this.c = type;
    }

    public int t() {
        return this.d;
    }

    public void e(int i2) {
        this.d = i2 & UnixStat.f3272a;
        this.e = PERMISSION.find(i2);
    }

    public Set<PERMISSION> u() {
        return this.e;
    }

    public long getSize() {
        if (isDirectory()) {
            return -1;
        }
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public long v() {
        return this.f;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public void b(Date date) {
        this.h = date.getTime();
    }

    public Date w() {
        return new Date(this.g);
    }

    public void c(Date date) {
        this.g = date.getTime();
    }

    public int x() {
        return this.i;
    }

    public void f(int i2) {
        this.i = i2;
    }

    public int y() {
        return this.j;
    }

    public void g(int i2) {
        this.j = i2;
    }

    public enum TYPE {
        WHITEOUT(14),
        SOCKET(12),
        LINK(10),
        FILE(8),
        BLKDEV(6),
        DIRECTORY(4),
        CHRDEV(2),
        FIFO(1),
        UNKNOWN(15);
        
        private int code;

        private TYPE(int i) {
            this.code = i;
        }

        public static TYPE find(int i) {
            TYPE type = UNKNOWN;
            for (TYPE type2 : values()) {
                if (i == type2.code) {
                    type = type2;
                }
            }
            return type;
        }
    }

    public enum PERMISSION {
        SETUID(2048),
        SETGUI(1024),
        STICKY(512),
        USER_READ(256),
        USER_WRITE(128),
        USER_EXEC(64),
        GROUP_READ(32),
        GROUP_WRITE(16),
        GROUP_EXEC(8),
        WORLD_READ(4),
        WORLD_WRITE(2),
        WORLD_EXEC(1);
        
        private int code;

        private PERMISSION(int i) {
            this.code = i;
        }

        public static Set<PERMISSION> find(int i) {
            HashSet hashSet = new HashSet();
            for (PERMISSION permission : values()) {
                if ((permission.code & i) == permission.code) {
                    hashSet.add(permission);
                }
            }
            if (hashSet.isEmpty()) {
                return Collections.emptySet();
            }
            return EnumSet.copyOf(hashSet);
        }
    }
}
