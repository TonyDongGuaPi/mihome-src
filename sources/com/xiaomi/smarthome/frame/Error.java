package com.xiaomi.smarthome.frame;

import com.taobao.weex.el.parse.Operators;

public class Error {

    /* renamed from: a  reason: collision with root package name */
    private int f15993a;
    private String b;
    private String c;

    public Error(int i, String str) {
        this.f15993a = i;
        this.b = str;
    }

    public Error(int i, String str, String str2) {
        this.f15993a = i;
        this.b = str;
        this.c = str2;
    }

    public final int a() {
        return this.f15993a;
    }

    public final String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String toString() {
        return "Error{mCode=" + this.f15993a + ", mDetail='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
