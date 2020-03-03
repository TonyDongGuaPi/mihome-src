package com.ximalaya.ting.android.opensdk.model.column;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class ColumnEditor {

    /* renamed from: a  reason: collision with root package name */
    private long f2061a;
    @SerializedName("nickname")
    private String b;
    @SerializedName("avatar_url")
    private String c;
    @SerializedName("personal_signature")
    private String d;

    public long a() {
        return this.f2061a;
    }

    public void a(long j) {
        this.f2061a = j;
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

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String toString() {
        return "ColumnEditor [uid=" + this.f2061a + ", nickName=" + this.b + ", avatarUrl=" + this.c + ", personalsignature=" + this.d + Operators.ARRAY_END_STR;
    }
}
