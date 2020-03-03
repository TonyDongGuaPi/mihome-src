package com.ximalaya.ting.android.opensdk.model.banner;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RankBannerList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Banner> f2046a;

    public List<Banner> a() {
        return this.f2046a;
    }

    public void a(List<Banner> list) {
        this.f2046a = list;
    }

    public String toString() {
        return "BannerList [bannerList=" + this.f2046a + Operators.ARRAY_END_STR;
    }
}
