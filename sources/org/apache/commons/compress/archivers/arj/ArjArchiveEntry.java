package org.apache.commons.compress.archivers.arj;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipUtil;

public class ArjArchiveEntry implements ArchiveEntry {
    private final LocalFileHeader b;

    public static class HostOs {

        /* renamed from: a  reason: collision with root package name */
        public static final int f3202a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final int h = 7;
        public static final int i = 8;
        public static final int j = 9;
        public static final int k = 10;
        public static final int l = 11;
    }

    public ArjArchiveEntry() {
        this.b = new LocalFileHeader();
    }

    ArjArchiveEntry(LocalFileHeader localFileHeader) {
        this.b = localFileHeader;
    }

    public String getName() {
        if ((this.b.d & 16) != 0) {
            return this.b.t.replaceAll("/", Matcher.quoteReplacement(File.separator));
        }
        return this.b.t;
    }

    public long getSize() {
        return this.b.j;
    }

    public boolean isDirectory() {
        return this.b.f == 3;
    }

    public Date a() {
        return new Date(e() ? ((long) this.b.h) * 1000 : ZipUtil.c(MessageHead.SERIAL_MAK & ((long) this.b.h)));
    }

    public int b() {
        return this.b.m;
    }

    public int c() {
        if (e()) {
            return b();
        }
        return 0;
    }

    public int d() {
        return this.b.c;
    }

    public boolean e() {
        return d() == 2 || d() == 8;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.b.e;
    }
}
