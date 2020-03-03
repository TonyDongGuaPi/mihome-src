package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class AlbumList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2012a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("category_id")
    private int c;
    @SerializedName("tag_name")
    private String d;
    @SerializedName("current_page")
    private int e;
    @SerializedName(alternate = {"paid_albums"}, value = "albums")
    private List<Album> f;

    public int a() {
        return this.f2012a;
    }

    public void a(int i) {
        this.f2012a = i;
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

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public int e() {
        return this.e;
    }

    public void d(int i) {
        this.e = i;
    }

    public List<Album> f() {
        return this.f;
    }

    public void a(List<Album> list) {
        this.f = list;
    }

    public String toString() {
        return "AlbumList [totalPage=" + this.f2012a + ", totalCount=" + this.b + ", categoryId=" + this.c + ", tagName=" + this.d + ", currentPage=" + this.e + ", albums=" + this.f + Operators.ARRAY_END_STR;
    }
}
