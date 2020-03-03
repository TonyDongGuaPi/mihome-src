package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

@Deprecated
public class HumanRecommendAlbum {
    @SerializedName("category_id")

    /* renamed from: a  reason: collision with root package name */
    private long f2023a;
    @SerializedName("category_name")
    private String b;
    @SerializedName("human_recommend_category_name")
    private String c;
    @SerializedName("albums")
    private List<Album> d;

    public long a() {
        return this.f2023a;
    }

    public void a(long j) {
        this.f2023a = j;
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

    public List<Album> d() {
        return this.d;
    }

    public void a(List<Album> list) {
        this.d = list;
    }

    public String toString() {
        return "HumanRecommendAlbum [categoryId=" + this.f2023a + ", categoryName=" + this.b + ", humanRecommendCategoryName=" + this.c + ", albumList=" + this.d + Operators.ARRAY_END_STR;
    }
}
