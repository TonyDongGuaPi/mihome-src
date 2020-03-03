package com.ximalaya.ting.android.opensdk.model.album;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class HotAggregationList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<HotAggregation> f2022a;

    public List<HotAggregation> a() {
        return this.f2022a;
    }

    public void a(List<HotAggregation> list) {
        this.f2022a = list;
    }

    public String toString() {
        return "HotAggregationList [list=" + this.f2022a + Operators.ARRAY_END_STR;
    }
}
