package com.ximalaya.ting.android.opensdk.model.word;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class HotWord {
    @SerializedName("search_word")

    /* renamed from: a  reason: collision with root package name */
    private String f2133a;
    private int b;
    private int c;

    public String a() {
        return this.f2133a;
    }

    public void a(String str) {
        this.f2133a = str;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String toString() {
        return "HotWord [searchword=" + this.f2133a + ", degree=" + this.b + ", count=" + this.c + Operators.ARRAY_END_STR;
    }
}
