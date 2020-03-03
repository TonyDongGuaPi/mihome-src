package com.ximalaya.ting.android.opensdk.model.banner;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CategoryBannerList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Banner> f2044a;

    public List<Banner> a() {
        return this.f2044a;
    }

    public void a(List<Banner> list) {
        this.f2044a = list;
    }

    public String toString() {
        return "BannerList [bannerList=" + this.f2044a + Operators.ARRAY_END_STR;
    }
}
