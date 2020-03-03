package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class TrackHotList extends CommonTrackList<Track> {
    @SerializedName("current_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2125a;
    @SerializedName("category_id")
    private int b;
    @SerializedName("tag_name")
    private String c;

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String g() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public int h() {
        return this.f2125a;
    }

    public void d(int i) {
        this.f2125a = i;
    }

    public String toString() {
        return "TrackHotList [currentPage=" + this.f2125a + ", categoryId=" + this.b + ", tagName=" + this.c + Operators.ARRAY_END_STR;
    }
}
