package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;

public class SearchTrackList extends CommonTrackList<Track> {
    @SerializedName("category_id")

    /* renamed from: a  reason: collision with root package name */
    private int f2123a;
    @SerializedName("tag_name")
    private String b;

    public int a() {
        return this.f2123a;
    }

    public void a(int i) {
        this.f2123a = i;
    }

    public String g() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }
}
