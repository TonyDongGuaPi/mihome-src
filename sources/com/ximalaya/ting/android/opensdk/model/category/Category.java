package com.ximalaya.ting.android.opensdk.model.category;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class Category {

    /* renamed from: a  reason: collision with root package name */
    private long f2048a;
    private String b;
    @SerializedName("category_name")
    private String c;
    @SerializedName("cover_url_small")
    private String d;
    @SerializedName("cover_url_middle")
    private String e;
    @SerializedName("cover_url_large")
    private String f;
    @SerializedName("need_show_high_quality_tag")
    private boolean g;

    public long a() {
        return this.f2048a;
    }

    public void a(long j) {
        this.f2048a = j;
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

    public String e() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public String toString() {
        return "Category [id=" + this.f2048a + ", kind=" + this.b + ", categoryName=" + this.c + ", coverUrlSmall=" + this.d + ", coverUrlMiddle=" + this.e + ", coverUrlLarge=" + this.f + Operators.ARRAY_END_STR;
    }

    public boolean g() {
        return this.g;
    }
}
