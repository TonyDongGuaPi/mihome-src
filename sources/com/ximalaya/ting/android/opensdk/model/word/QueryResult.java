package com.ximalaya.ting.android.opensdk.model.word;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class QueryResult {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2135a;
    private String b;
    @SerializedName("highlight_keyword")
    private String c;

    public long a() {
        return this.f2135a;
    }

    public void a(long j) {
        this.f2135a = j;
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
        return "QueryResult [queryId=" + this.f2135a + ", keyword=" + this.b + ", highlightKeyword=" + this.c + Operators.ARRAY_END_STR;
    }
}
