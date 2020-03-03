package com.ximalaya.ting.android.opensdk.model.banner;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class DiscoveryBannerList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Banner> f2045a;

    public List<Banner> a() {
        return this.f2045a;
    }

    public void a(List<Banner> list) {
        this.f2045a = list;
    }

    public String toString() {
        return "BannerList [bannerList=" + this.f2045a + Operators.ARRAY_END_STR;
    }
}
