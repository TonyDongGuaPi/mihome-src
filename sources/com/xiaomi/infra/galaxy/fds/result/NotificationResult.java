package com.xiaomi.infra.galaxy.fds.result;

public class NotificationResult {

    /* renamed from: a  reason: collision with root package name */
    private String f10168a;
    private String b;
    private long c;
    private long d;
    private String e;

    public enum NotificationMethod {
        PUT,
        POST,
        DELETE
    }

    public String a() {
        return this.f10168a;
    }

    public void a(String str) {
        this.f10168a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public long d() {
        return this.d;
    }

    public void b(long j) {
        this.d = j;
    }

    public String e() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }
}
