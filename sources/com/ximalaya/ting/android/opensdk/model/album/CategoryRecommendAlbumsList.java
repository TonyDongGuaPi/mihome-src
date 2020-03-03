package com.ximalaya.ting.android.opensdk.model.album;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CategoryRecommendAlbumsList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<CategoryRecommendAlbums> f2017a;

    public List<CategoryRecommendAlbums> a() {
        return this.f2017a;
    }

    public void a(List<CategoryRecommendAlbums> list) {
        this.f2017a = list;
    }
}
