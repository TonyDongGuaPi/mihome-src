package org.apache.commons.compress.changes;

import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;

class Change {

    /* renamed from: a  reason: collision with root package name */
    static final int f3302a = 1;
    static final int b = 2;
    static final int c = 3;
    static final int d = 4;
    private final String e;
    private final ArchiveEntry f;
    private final InputStream g;
    private final boolean h;
    private final int i;

    Change(String str, int i2) {
        if (str != null) {
            this.e = str;
            this.i = i2;
            this.g = null;
            this.f = null;
            this.h = true;
            return;
        }
        throw new NullPointerException();
    }

    Change(ArchiveEntry archiveEntry, InputStream inputStream, boolean z) {
        if (archiveEntry == null || inputStream == null) {
            throw new NullPointerException();
        }
        this.f = archiveEntry;
        this.g = inputStream;
        this.i = 2;
        this.e = null;
        this.h = z;
    }

    /* access modifiers changed from: package-private */
    public ArchiveEntry a() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public InputStream b() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.h;
    }
}
