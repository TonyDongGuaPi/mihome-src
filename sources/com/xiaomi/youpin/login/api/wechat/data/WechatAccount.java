package com.xiaomi.youpin.login.api.wechat.data;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class WechatAccount {
    @SerializedName("province")

    /* renamed from: a  reason: collision with root package name */
    private String f23509a;
    @SerializedName("city")
    private String b;
    @SerializedName("avatarUrl")
    private String c;
    @SerializedName("openId")
    private String d;
    @SerializedName("unionIdSign")
    private String e;
    @SerializedName("sex")
    private String f;
    @SerializedName("nickname")
    private String g;

    public String a() {
        return this.f23509a;
    }

    public void a(String str) {
        this.f23509a = str;
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

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void g(String str) {
        this.g = str;
    }

    public String toString() {
        return "WechatAccount{province='" + this.f23509a + Operators.SINGLE_QUOTE + ", city='" + this.b + Operators.SINGLE_QUOTE + ", avatarUrl='" + this.c + Operators.SINGLE_QUOTE + ", openId='" + this.d + Operators.SINGLE_QUOTE + ", unionIdSign='" + this.e + Operators.SINGLE_QUOTE + ", sex='" + this.f + Operators.SINGLE_QUOTE + ", nickname='" + this.g + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
