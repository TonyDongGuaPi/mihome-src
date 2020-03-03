package com.ximalaya.ting.android.opensdk.model.banner;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class BannerV2List extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<BannerV2> f2042a;

    public List<BannerV2> a() {
        return this.f2042a;
    }

    public void a(List<BannerV2> list) {
        this.f2042a = list;
    }
}
