package com.ximalaya.ting.android.opensdk.model.category;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class HumanRecommendCategoryList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<HumanRecommendCategory> f2052a;

    public List<HumanRecommendCategory> a() {
        return this.f2052a;
    }

    public void a(List<HumanRecommendCategory> list) {
        this.f2052a = list;
    }
}
