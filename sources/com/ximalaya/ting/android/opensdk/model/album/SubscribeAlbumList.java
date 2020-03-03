package com.ximalaya.ting.android.opensdk.model.album;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class SubscribeAlbumList extends XimalayaResponse {
    @SerializedName("total_count")

    /* renamed from: a  reason: collision with root package name */
    private int f2031a;
    @SerializedName("subscribe_albums")
    private List<Album> b;

    public int a() {
        return this.f2031a;
    }

    public void a(int i) {
        this.f2031a = i;
    }

    public List<Album> b() {
        return this.b;
    }

    public void a(List<Album> list) {
        this.b = list;
    }
}
