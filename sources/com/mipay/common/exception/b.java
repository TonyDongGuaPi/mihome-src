package com.mipay.common.exception;

public class b extends d {

    /* renamed from: a  reason: collision with root package name */
    private a f8135a;

    public enum a {
        NOT_YET_VALID,
        EXPIRED
    }

    public b(a aVar, Throwable th) {
        super(th);
        this.f8135a = aVar;
    }

    public String a() {
        return "CT";
    }

    public a b() {
        return this.f8135a;
    }
}
