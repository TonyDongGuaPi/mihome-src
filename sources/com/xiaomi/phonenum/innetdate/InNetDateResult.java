package com.xiaomi.phonenum.innetdate;

import com.xiaomi.phonenum.bean.Error;

public class InNetDateResult {

    /* renamed from: a  reason: collision with root package name */
    private Error f12567a;
    private String b;
    private long c;
    private String d;

    InNetDateResult(long j, String str) {
        this.c = j;
        this.d = str;
    }

    InNetDateResult(Error error) {
        this(error, error.toString());
    }

    InNetDateResult(Error error, String str) {
        this.f12567a = error;
        this.b = str;
    }

    public boolean a() {
        return this.f12567a == null;
    }

    public boolean b() {
        return !a();
    }

    public long c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public Error e() {
        if (a()) {
            return Error.NONE;
        }
        return this.f12567a;
    }

    public String f() {
        return this.b;
    }

    public String toString() {
        return "IabResult: " + f() + " date:" + this.c;
    }
}
