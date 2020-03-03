package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class SearchAlbumList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2029a;
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
        return this.f2029a;
    }

    public void a(int i) {
        this.f2029a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int c() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public String d() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public List<Album> e() {
        return this.f;
    }

    public void a(List<Album> list) {
        this.f = list;
    }

    public int f() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public String toString() {
        return "SearchAlbumList [totalPage=" + this.f2029a + ", totalCount=" + this.b + ", albums=" + this.f + Operators.ARRAY_END_STR;
    }
}
