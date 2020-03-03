package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class BatchTrackList extends XimalayaResponse {
    @SerializedName("tracks")

    /* renamed from: a  reason: collision with root package name */
    private List<Track> f2120a;

    public List<Track> a() {
        return this.f2120a;
    }

    public void a(List<Track> list) {
        this.f2120a = list;
    }
}
