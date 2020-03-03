package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RelativeAlbums extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2028a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("reletive_albums")
    private List<Album> d;

    public int a() {
        return this.f2028a;
    }

    public void a(int i) {
        this.f2028a = i;
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

    public List<Album> d() {
        return this.d;
    }

    public void a(List<Album> list) {
        this.d = list;
    }

    public String toString() {
        return "RelativeAlbums [totalPage=" + this.f2028a + ", totalCount=" + this.b + ", currentPage=" + this.c + ", relativeAlbumList=" + this.d + Operators.ARRAY_END_STR;
    }
}
