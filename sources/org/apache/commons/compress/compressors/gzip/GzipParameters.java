package org.apache.commons.compress.compressors.gzip;

public class GzipParameters {

    /* renamed from: a  reason: collision with root package name */
    private int f3324a = -1;
    private long b;
    private String c;
    private String d;
    private int e = 255;

    public int a() {
        return this.f3324a;
    }

    public void a(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException("Invalid gzip compression level: " + i);
        }
        this.f3324a = i;
    }

    public long b() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public int e() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }
}
