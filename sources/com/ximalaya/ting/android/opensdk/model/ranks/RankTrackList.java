package com.ximalaya.ting.android.opensdk.model.ranks;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.List;

public class RankTrackList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2113a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("tracks")
    private List<Track> d;

    public int a() {
        return this.f2113a;
    }

    public void a(int i) {
        this.f2113a = i;
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

    public List<Track> d() {
        return this.d;
    }

    public void a(List<Track> list) {
        this.d = list;
    }

    public String toString() {
        return "RankTrackList [totalPage=" + this.f2113a + ", totalCount=" + this.b + ", currentPage=" + this.c + ", trackList=" + this.d + Operators.ARRAY_END_STR;
    }
}
