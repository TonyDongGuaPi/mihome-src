package com.ximalaya.ting.android.opensdk.model.ranks;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class RankItem {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2111a;
    private String b;
    @SerializedName("content_type")
    private String c;

    public long a() {
        return this.f2111a;
    }

    public void a(long j) {
        this.f2111a = j;
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

    public String toString() {
        return "RankItem [dataId=" + this.f2111a + ", title=" + this.b + ", contentType=" + this.c + Operators.ARRAY_END_STR;
    }
}
