package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class XmAlbumTracksStatue extends XimalayaResponse {
    @SerializedName("total_count")

    /* renamed from: a  reason: collision with root package name */
    private int f2034a;
    @SerializedName("track_ids")
    private List<XmTrackStatue> b;

    public int a() {
        return this.f2034a;
    }

    public void a(int i) {
        this.f2034a = i;
    }

    public List<XmTrackStatue> b() {
        return this.b;
    }

    public void a(List<XmTrackStatue> list) {
        this.b = list;
    }
}
