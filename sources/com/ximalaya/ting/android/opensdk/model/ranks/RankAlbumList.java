package com.ximalaya.ting.android.opensdk.model.ranks;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import java.util.List;

public class RankAlbumList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2110a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName(alternate = {"paid_albums"}, value = "albums")
    private List<Album> d;

    public int a() {
        return this.f2110a;
    }

    public void a(int i) {
        this.f2110a = i;
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
        return "RankAlbumList [totalPage=" + this.f2110a + ", totalCount=" + this.b + ", currentPage=" + this.c + ", rankAlbumList=" + this.d + Operators.ARRAY_END_STR;
    }
}
