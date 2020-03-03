package com.ximalaya.ting.android.opensdk.model.category;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class HumanRecommendCategory extends XimalayaResponse {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private long f2051a;
    @SerializedName("category_name")
    private String b;
    @SerializedName("cover_url_small")
    private String c;
    @SerializedName("cover_url_middle")
    private String d;
    @SerializedName("cover_url_large")
    private String e;
    @SerializedName("order_num")
    private int f;
    @SerializedName("human_recommend_category_name")
    private String g;

    public long a() {
        return this.f2051a;
    }

    public void a(long j) {
        this.f2051a = j;
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

    public int f() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public String toString() {
        return "HumanRecommendCategory [categoryId=" + this.f2051a + ", categoryName=" + this.b + ", coverUrlSmall=" + this.c + ", coverUrlMiddle=" + this.d + ", coverUrlLarge=" + this.e + ", orderNum=" + this.f + ", humanRecommendCategoryName=" + this.g + Operators.ARRAY_END_STR;
    }

    public String g() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }
}
