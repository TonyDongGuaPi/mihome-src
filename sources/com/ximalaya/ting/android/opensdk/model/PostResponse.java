package com.ximalaya.ting.android.opensdk.model;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class PostResponse extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private String f2006a;
    private String b;
    private int c;

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int b() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String c() {
        return this.f2006a;
    }

    public void b(String str) {
        this.f2006a = str;
    }

    public String toString() {
        return "PostResponse [response=" + this.f2006a + ", message=" + this.b + ", code=" + this.c + Operators.ARRAY_END_STR;
    }
}
