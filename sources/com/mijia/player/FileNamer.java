package com.mijia.player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNamer {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8101a = "'IMG'_yyyyMMdd_HHmmssSSS";
    public static final String b = "'VID'_yyyyMMdd_HHmmss";
    private static FileNamer g;
    private SimpleDateFormat c = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmssSSS");
    private SimpleDateFormat d = new SimpleDateFormat("'VID'_yyyyMMdd_HHmmss");
    private long e;
    private int f;

    private FileNamer() {
    }

    public static synchronized FileNamer a() {
        FileNamer fileNamer;
        synchronized (FileNamer.class) {
            if (g == null) {
                g = new FileNamer();
            }
            fileNamer = g;
        }
        return fileNamer;
    }

    public synchronized String a(long j, boolean z) {
        String str;
        Date date = new Date(j);
        if (z) {
            str = this.d.format(date);
        } else {
            str = this.c.format(date);
        }
        return str;
    }

    public long a(String str, boolean z) {
        if (!z) {
            return this.c.parse(str).getTime();
        }
        try {
            return this.d.parse(str).getTime();
        } catch (Exception unused) {
            return 0;
        }
    }
}
