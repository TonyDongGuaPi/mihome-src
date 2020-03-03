package com.amap.location;

import com.amap.location.common.HeaderConfig;
import com.amap.location.common.a;
import com.amap.location.common.network.IHttpClient;

public class BasicLocateConfig {

    /* renamed from: a  reason: collision with root package name */
    private String f4557a = "";
    private IHttpClient b;

    public byte a() {
        return HeaderConfig.a();
    }

    public void a(byte b2) {
        HeaderConfig.a(b2);
    }

    public void a(IHttpClient iHttpClient) {
        this.b = iHttpClient;
    }

    public void a(String str) {
        HeaderConfig.a(str);
    }

    public void a(boolean z) {
        a.a(z);
    }

    public String b() {
        return HeaderConfig.b();
    }

    public void b(String str) {
        HeaderConfig.c(str);
    }

    public String c() {
        return HeaderConfig.d();
    }

    public void c(String str) {
        HeaderConfig.b(str);
    }

    public String d() {
        return HeaderConfig.c();
    }

    public void d(String str) {
        a.a(str);
    }

    public String e() {
        return a.a();
    }

    public void e(String str) {
        this.f4557a = str;
    }

    public String f() {
        return this.f4557a;
    }

    public IHttpClient g() {
        return this.b;
    }
}
