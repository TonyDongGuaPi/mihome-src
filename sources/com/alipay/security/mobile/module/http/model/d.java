package com.alipay.security.mobile.module.http.model;

import com.alipay.security.mobile.module.a.a;
import java.util.HashMap;
import java.util.Map;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private String f1175a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f;
    private String g;
    private String h;
    private String i;
    private String j;

    public String a() {
        return this.j;
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(Map<String, String> map) {
        this.f = map;
    }

    public String b() {
        return a.d(this.f1175a);
    }

    public void b(String str) {
        this.f1175a = str;
    }

    public String c() {
        return a.d(this.b);
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return a.d(this.c);
    }

    public void d(String str) {
        this.c = str;
    }

    public String e() {
        return a.d(this.d);
    }

    public void e(String str) {
        this.d = str;
    }

    public Map<String, String> f() {
        return this.f == null ? new HashMap() : this.f;
    }

    public void f(String str) {
        this.e = str;
    }

    public String g() {
        return this.e;
    }

    public void g(String str) {
        this.g = str;
    }

    public String h() {
        return this.g;
    }

    public void h(String str) {
        this.h = str;
    }

    public String i() {
        return this.h;
    }

    public void i(String str) {
        this.i = str;
    }

    public String j() {
        return this.i;
    }
}
