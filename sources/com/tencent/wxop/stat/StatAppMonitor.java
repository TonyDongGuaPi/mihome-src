package com.tencent.wxop.stat;

public class StatAppMonitor implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9264a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private String d = null;
    private long e = 0;
    private long f = 0;
    private int g = 0;
    private long h = 0;
    private int i = 0;
    private int j = 1;

    public StatAppMonitor(String str) {
        this.d = str;
    }

    public StatAppMonitor(String str, int i2, int i3, long j2, long j3, long j4, int i4) {
        this.d = str;
        this.e = j2;
        this.f = j3;
        this.g = i2;
        this.h = j4;
        this.i = i3;
        this.j = i4;
    }

    public String a() {
        return this.d;
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a(long j2) {
        this.e = j2;
    }

    public void a(String str) {
        this.d = str;
    }

    public long b() {
        return this.e;
    }

    public void b(int i2) {
        this.i = i2;
    }

    public void b(long j2) {
        this.f = j2;
    }

    public long c() {
        return this.f;
    }

    public void c(int i2) {
        if (i2 <= 0) {
            i2 = 1;
        }
        this.j = i2;
    }

    public void c(long j2) {
        this.h = j2;
    }

    public int d() {
        return this.g;
    }

    public long e() {
        return this.h;
    }

    public int f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    /* renamed from: h */
    public StatAppMonitor clone() {
        try {
            return (StatAppMonitor) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
