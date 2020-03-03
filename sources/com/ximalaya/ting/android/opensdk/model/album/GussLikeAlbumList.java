package com.ximalaya.ting.android.opensdk.model.album;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.ArrayList;
import java.util.List;

public class GussLikeAlbumList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Album> f2020a = new ArrayList();

    public List<Album> a() {
        return this.f2020a;
    }

    public void a(List<Album> list) {
        this.f2020a = list;
    }
}
