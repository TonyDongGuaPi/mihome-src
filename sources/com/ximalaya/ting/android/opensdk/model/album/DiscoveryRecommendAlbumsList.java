package com.ximalaya.ting.android.opensdk.model.album;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class DiscoveryRecommendAlbumsList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<DiscoveryRecommendAlbums> f2019a;

    public List<DiscoveryRecommendAlbums> a() {
        return this.f2019a;
    }

    public void a(List<DiscoveryRecommendAlbums> list) {
        this.f2019a = list;
    }
}
