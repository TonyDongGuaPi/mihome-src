package com.ximalaya.ting.android.opensdk.model.banner;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.List;

public class BannersContentList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Track> f2043a;
    private List<Album> b;

    public List<Track> a() {
        return this.f2043a;
    }

    public void a(List<Track> list) {
        this.f2043a = list;
    }

    public List<Album> b() {
        return this.b;
    }

    public void b(List<Album> list) {
        this.b = list;
    }
}
