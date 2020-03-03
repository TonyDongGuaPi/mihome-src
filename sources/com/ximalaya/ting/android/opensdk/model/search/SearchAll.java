package com.ximalaya.ting.android.opensdk.model.search;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.album.SearchAlbumList;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioList;
import com.ximalaya.ting.android.opensdk.model.track.SearchTrackList;

public class SearchAll extends XimalayaResponse {
    @SerializedName("track_list")

    /* renamed from: a  reason: collision with root package name */
    private SearchTrackList f2114a;
    @SerializedName("album_list")
    private SearchAlbumList b;
    @SerializedName("radio_list")
    private RadioList c;

    public SearchTrackList a() {
        return this.f2114a;
    }

    public void a(SearchTrackList searchTrackList) {
        this.f2114a = searchTrackList;
    }

    public SearchAlbumList b() {
        return this.b;
    }

    public void a(SearchAlbumList searchAlbumList) {
        this.b = searchAlbumList;
    }

    public RadioList c() {
        return this.c;
    }

    public void a(RadioList radioList) {
        this.c = radioList;
    }
}
