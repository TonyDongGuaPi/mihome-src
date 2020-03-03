package com.ximalaya.ting.android.opensdk.model.album;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RecommendCollectAlbumList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Album> f2026a;

    public List<Album> a() {
        return this.f2026a;
    }

    public void a(List<Album> list) {
        this.f2026a = list;
    }
}
