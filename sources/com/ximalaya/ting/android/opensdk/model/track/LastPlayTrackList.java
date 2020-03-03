package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class LastPlayTrackList extends CommonTrackList<Track> {
    @SerializedName("category_id")

    /* renamed from: a  reason: collision with root package name */
    private int f2122a;
    @SerializedName("tag_name")
    private String b;
    @SerializedName("current_page")
    private int c;

    public int a() {
        return this.f2122a;
    }

    public void a(int i) {
        this.f2122a = i;
    }

    public String g() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int h() {
        return this.c;
    }

    public void d(int i) {
        this.c = i;
    }

    public String toString() {
        return "LastPlayTrackList [categoryId=" + this.f2122a + ", tagname=" + this.b + ", pageid=" + this.c + Operators.ARRAY_END_STR;
    }
}
