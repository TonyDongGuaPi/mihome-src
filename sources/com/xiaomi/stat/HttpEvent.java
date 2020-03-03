package com.xiaomi.stat;

public class HttpEvent {

    /* renamed from: a  reason: collision with root package name */
    private int f22989a;
    private long b;
    private long c;
    private String d;
    private String e;

    public HttpEvent(String str, long j) {
        this(str, j, -1, (String) null);
    }

    public HttpEvent(String str, long j, long j2) {
        this(str, j, j2, -1, (String) null);
    }

    public HttpEvent(String str, long j, long j2, int i) {
        this(str, j, j2, i, (String) null);
    }

    public HttpEvent(String str, long j, String str2) {
        this(str, j, -1, str2);
    }

    public HttpEvent(String str, long j, int i, String str2) {
        this(str, j, 0, i, str2);
    }

    public HttpEvent(String str, long j, long j2, int i, String str2) {
        this.c = 0;
        this.d = str;
        this.b = j;
        this.f22989a = i;
        this.e = str2;
        this.c = j2;
    }

    public int getResponseCode() {
        return this.f22989a;
    }

    public long getTimeCost() {
        return this.b;
    }

    public long getNetFlow() {
        return this.c;
    }

    public String getUrl() {
        return this.d;
    }

    public String getExceptionName() {
        return this.e;
    }
}
