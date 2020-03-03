package com.xiaomi.jr.hybrid;

public class Request<T> {

    /* renamed from: a  reason: collision with root package name */
    private HybridContext f1444a;
    private String b;
    private T c;
    private String d;
    private Object e;

    public Request(HybridContext hybridContext) {
        this.f1444a = hybridContext;
    }

    public HybridContext a() {
        return this.f1444a;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public T c() {
        return this.c;
    }

    public void a(T t, String str) {
        this.c = t;
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public Object e() {
        return this.e;
    }

    public void a(Object obj) {
        this.e = obj;
    }
}
