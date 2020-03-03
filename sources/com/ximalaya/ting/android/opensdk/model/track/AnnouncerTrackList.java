package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;

public class AnnouncerTrackList extends CommonTrackList<Track> {
    @SerializedName("current_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2119a;

    public int a() {
        return this.f2119a;
    }

    public void a(int i) {
        this.f2119a = i;
    }
}
