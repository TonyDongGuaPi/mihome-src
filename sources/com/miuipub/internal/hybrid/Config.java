package com.miuipub.internal.hybrid;

import java.util.HashMap;
import java.util.Map;

public class Config {

    /* renamed from: a  reason: collision with root package name */
    private Security f8252a;
    private String b;
    private String c;
    private Map<String, Feature> d = new HashMap();
    private Map<String, String> e = new HashMap();
    private Map<String, Permission> f = new HashMap();

    public Security a() {
        return this.f8252a;
    }

    public void a(Security security) {
        this.f8252a = security;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public Map<String, Feature> d() {
        return this.d;
    }

    public void a(Map<String, Feature> map) {
        this.d = map;
    }

    public void e() {
        this.d.clear();
    }

    public Feature c(String str) {
        return this.d.get(str);
    }

    public void a(Feature feature) {
        this.d.put(feature.a(), feature);
    }

    public Map<String, String> f() {
        return this.e;
    }

    public void b(Map<String, String> map) {
        this.e = map;
    }

    public void g() {
        this.e.clear();
    }

    public String d(String str) {
        return this.e.get(str);
    }

    public void a(String str, String str2) {
        this.e.put(str, str2);
    }

    public Map<String, Permission> h() {
        return this.f;
    }

    public void c(Map<String, Permission> map) {
        this.f = map;
    }

    public void i() {
        this.f.clear();
    }

    public Permission e(String str) {
        return this.f.get(str);
    }

    public void a(Permission permission) {
        this.f.put(permission.a(), permission);
    }
}
