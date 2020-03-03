package com.ximalaya.ting.android.opensdk.model.album;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

@Deprecated
public class HumanRecommendAlbumList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<HumanRecommendAlbum> f2024a;

    public List<HumanRecommendAlbum> a() {
        return this.f2024a;
    }

    public void a(List<HumanRecommendAlbum> list) {
        this.f2024a = list;
    }

    public String toString() {
        return "HumanRecommendAlbumList [albumList=" + this.f2024a + Operators.ARRAY_END_STR;
    }
}
