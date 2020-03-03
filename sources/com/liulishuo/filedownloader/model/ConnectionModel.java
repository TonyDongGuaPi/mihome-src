package com.liulishuo.filedownloader.model;

import android.content.ContentValues;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.List;

public class ConnectionModel {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6440a = "id";
    public static final String b = "connectionIndex";
    public static final String c = "startOffset";
    public static final String d = "currentOffset";
    public static final String e = "endOffset";
    private int f;
    private int g;
    private long h;
    private long i;
    private long j;

    public int a() {
        return this.f;
    }

    public void a(int i2) {
        this.f = i2;
    }

    public int b() {
        return this.g;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public long c() {
        return this.h;
    }

    public void a(long j2) {
        this.h = j2;
    }

    public long d() {
        return this.i;
    }

    public void b(long j2) {
        this.i = j2;
    }

    public long e() {
        return this.j;
    }

    public void c(long j2) {
        this.j = j2;
    }

    public ContentValues f() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(this.f));
        contentValues.put(b, Integer.valueOf(this.g));
        contentValues.put(c, Long.valueOf(this.h));
        contentValues.put(d, Long.valueOf(this.i));
        contentValues.put(e, Long.valueOf(this.j));
        return contentValues;
    }

    public static long a(List<ConnectionModel> list) {
        long j2 = 0;
        for (ConnectionModel next : list) {
            j2 += next.d() - next.c();
        }
        return j2;
    }

    public String toString() {
        return FileDownloadUtils.a("id[%d] index[%d] range[%d, %d) current offset(%d)", Integer.valueOf(this.f), Integer.valueOf(this.g), Long.valueOf(this.h), Long.valueOf(this.j), Long.valueOf(this.i));
    }
}
