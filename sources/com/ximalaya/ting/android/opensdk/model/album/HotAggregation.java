package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class HotAggregation {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2021a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("category_id")
    private int d;
    @SerializedName("tag_name")
    private String e;
    private List<Album> f;

    public int a() {
        return this.f2021a;
    }

    public void a(int i) {
        this.f2021a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int d() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public String e() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public List<Album> f() {
        return this.f;
    }

    public void a(List<Album> list) {
        this.f = list;
    }

    public String toString() {
        return "HotAggregation [totalPage=" + this.f2021a + ", totalCount=" + this.b + ", currentPage=" + this.c + ", categoryId=" + this.d + ", tagName=" + this.e + ", albums=" + this.f + Operators.ARRAY_END_STR;
    }
}
