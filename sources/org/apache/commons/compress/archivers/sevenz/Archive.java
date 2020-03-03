package org.apache.commons.compress.archivers.sevenz;

import java.util.BitSet;

class Archive {

    /* renamed from: a  reason: collision with root package name */
    long f3220a;
    long[] b;
    BitSet c;
    long[] d;
    Folder[] e;
    SubStreamsInfo f;
    SevenZArchiveEntry[] g;
    StreamMap h;

    Archive() {
    }

    public String toString() {
        return "Archive with packed streams starting at offset " + this.f3220a + ", " + a(this.b) + " pack sizes, " + a(this.d) + " CRCs, " + a((Object[]) this.e) + " folders, " + a((Object[]) this.g) + " files and " + this.h;
    }

    private static String a(long[] jArr) {
        return jArr == null ? "(null)" : String.valueOf(jArr.length);
    }

    private static String a(Object[] objArr) {
        return objArr == null ? "(null)" : String.valueOf(objArr.length);
    }
}
