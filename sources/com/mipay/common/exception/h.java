package com.mipay.common.exception;

import java.net.URL;

public class h extends f {

    /* renamed from: a  reason: collision with root package name */
    private int f8139a;
    private URL b;

    public h(int i, URL url) {
        super(Integer.toString(i));
        this.f8139a = i;
        this.b = url;
    }

    public h(Throwable th) {
        super(th);
    }

    public String a() {
        return "SR";
    }

    public int b() {
        return this.f8139a;
    }

    public URL h() {
        return this.b;
    }
}
