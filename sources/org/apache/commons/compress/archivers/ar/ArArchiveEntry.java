package org.apache.commons.compress.archivers.ar;

import java.io.File;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class ArArchiveEntry implements ArchiveEntry {
    public static final String b = "!<arch>\n";
    public static final String c = "`\n";
    private static final int h = 33188;
    private final String d;
    private final int e;
    private final int f;
    private final int g;
    private final long i;
    private final long j;

    public boolean isDirectory() {
        return false;
    }

    public ArArchiveEntry(String str, long j2) {
        this(str, j2, 0, 0, 33188, System.currentTimeMillis() / 1000);
    }

    public ArArchiveEntry(String str, long j2, int i2, int i3, int i4, long j3) {
        this.d = str;
        this.j = j2;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        this.i = j3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ArArchiveEntry(File file, String str) {
        this(str, file.isFile() ? file.length() : 0, 0, 0, 33188, file.lastModified() / 1000);
    }

    public long getSize() {
        return f();
    }

    public String getName() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    public long e() {
        return this.i;
    }

    public Date a() {
        return new Date(e() * 1000);
    }

    public long f() {
        return this.j;
    }

    public int hashCode() {
        return 31 + (this.d == null ? 0 : this.d.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArArchiveEntry arArchiveEntry = (ArArchiveEntry) obj;
        if (this.d == null) {
            if (arArchiveEntry.d != null) {
                return false;
            }
        } else if (!this.d.equals(arArchiveEntry.d)) {
            return false;
        }
        return true;
    }
}
