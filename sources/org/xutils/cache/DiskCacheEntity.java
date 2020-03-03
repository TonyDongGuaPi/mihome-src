package org.xutils.cache;

import java.util.Date;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "disk_cache")
public final class DiskCacheEntity {
    @Column(isId = true, name = "id")

    /* renamed from: a  reason: collision with root package name */
    private long f4209a;
    @Column(name = "key", property = "UNIQUE")
    private String b;
    @Column(name = "path")
    private String c;
    @Column(name = "textContent")
    private String d;
    @Column(name = "expires")
    private long e = Long.MAX_VALUE;
    @Column(name = "etag")
    private String f;
    @Column(name = "hits")
    private long g;
    @Column(name = "lastModify")
    private Date h;
    @Column(name = "lastAccess")
    private long i;

    public long a() {
        return this.f4209a;
    }

    public void a(long j) {
        this.f4209a = j;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public long e() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public long g() {
        return this.g;
    }

    public void c(long j) {
        this.g = j;
    }

    public Date h() {
        return this.h;
    }

    public void a(Date date) {
        this.h = date;
    }

    public long i() {
        return this.i == 0 ? System.currentTimeMillis() : this.i;
    }

    public void d(long j) {
        this.i = j;
    }
}
