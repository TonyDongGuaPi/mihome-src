package com.xiaomi.infra.galaxy.fds.android.model;

import com.alipay.sdk.sys.a;

public class PutObjectResult {

    /* renamed from: a  reason: collision with root package name */
    private String f10141a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;

    public String a() {
        return this.f10141a;
    }

    public void a(String str) {
        this.f10141a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public long e() {
        return this.g;
    }

    public void a(long j) {
        this.g = j;
    }

    public void e(String str) {
        this.e = str;
    }

    public void f(String str) {
        this.f = str;
    }

    public String f() {
        return "/" + this.f10141a + "/" + this.b + "?" + "GalaxyAccessKeyId" + "=" + this.c + a.b + "Expires" + "=" + this.g + a.b + "Signature" + "=" + this.d;
    }

    public String g() {
        return this.e + f();
    }

    public String h() {
        return this.f + f();
    }
}
