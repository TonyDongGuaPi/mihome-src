package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class TrackIdList extends XimalayaResponse {
    @SerializedName("total_count")

    /* renamed from: a  reason: collision with root package name */
    private int f2127a;
    @SerializedName("track_ids")
    private List<TrackId> b;

    public int a() {
        return this.f2127a;
    }

    public List<TrackId> b() {
        return this.b;
    }
}
