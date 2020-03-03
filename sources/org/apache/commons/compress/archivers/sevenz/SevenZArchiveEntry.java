package org.apache.commons.compress.archivers.sevenz;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class SevenZArchiveEntry implements ArchiveEntry {
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private long i;
    private long j;
    private long k;
    private boolean l;
    private int m;
    private boolean n;
    private long o;
    private long p;
    private long q;
    private long r;
    private Iterable<? extends SevenZMethodConfiguration> s;

    public String getName() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean b() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean isDirectory() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean c() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public boolean d() {
        return this.f;
    }

    public void d(boolean z) {
        this.f = z;
    }

    public Date e() {
        if (this.f) {
            return h(this.i);
        }
        throw new UnsupportedOperationException("The entry doesn't have this timestamp");
    }

    public void a(long j2) {
        this.i = j2;
    }

    public void a(Date date) {
        this.f = date != null;
        if (this.f) {
            this.i = d(date);
        }
    }

    public boolean f() {
        return this.g;
    }

    public void e(boolean z) {
        this.g = z;
    }

    public Date a() {
        if (this.g) {
            return h(this.j);
        }
        throw new UnsupportedOperationException("The entry doesn't have this timestamp");
    }

    public void b(long j2) {
        this.j = j2;
    }

    public void b(Date date) {
        this.g = date != null;
        if (this.g) {
            this.j = d(date);
        }
    }

    public boolean g() {
        return this.h;
    }

    public void f(boolean z) {
        this.h = z;
    }

    public Date h() {
        if (this.h) {
            return h(this.k);
        }
        throw new UnsupportedOperationException("The entry doesn't have this timestamp");
    }

    public void c(long j2) {
        this.k = j2;
    }

    public void c(Date date) {
        this.h = date != null;
        if (this.h) {
            this.k = d(date);
        }
    }

    public boolean i() {
        return this.l;
    }

    public void g(boolean z) {
        this.l = z;
    }

    public int j() {
        return this.m;
    }

    public void a(int i2) {
        this.m = i2;
    }

    public boolean k() {
        return this.n;
    }

    public void h(boolean z) {
        this.n = z;
    }

    @Deprecated
    public int l() {
        return (int) this.o;
    }

    @Deprecated
    public void b(int i2) {
        this.o = (long) i2;
    }

    public long m() {
        return this.o;
    }

    public void d(long j2) {
        this.o = j2;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public int n() {
        return (int) this.p;
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void c(int i2) {
        this.p = (long) i2;
    }

    /* access modifiers changed from: package-private */
    public long o() {
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public void e(long j2) {
        this.p = j2;
    }

    public long getSize() {
        return this.q;
    }

    public void f(long j2) {
        this.q = j2;
    }

    /* access modifiers changed from: package-private */
    public long p() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public void g(long j2) {
        this.r = j2;
    }

    public void a(Iterable<? extends SevenZMethodConfiguration> iterable) {
        if (iterable != null) {
            LinkedList linkedList = new LinkedList();
            for (SevenZMethodConfiguration addLast : iterable) {
                linkedList.addLast(addLast);
            }
            this.s = Collections.unmodifiableList(linkedList);
            return;
        }
        this.s = null;
    }

    public Iterable<? extends SevenZMethodConfiguration> q() {
        return this.s;
    }

    public static Date h(long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        instance.set(1601, 0, 1, 0, 0, 0);
        instance.set(14, 0);
        return new Date(instance.getTimeInMillis() + (j2 / 10000));
    }

    public static long d(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        instance.set(1601, 0, 1, 0, 0, 0);
        instance.set(14, 0);
        return (date.getTime() - instance.getTimeInMillis()) * 1000 * 10;
    }
}
