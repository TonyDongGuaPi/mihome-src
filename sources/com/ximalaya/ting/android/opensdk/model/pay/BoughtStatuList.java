package com.ximalaya.ting.android.opensdk.model.pay;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class BoughtStatuList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<BoughtStatu> f2101a;

    public List<BoughtStatu> a() {
        return this.f2101a;
    }

    public void a(List<BoughtStatu> list) {
        this.f2101a = list;
    }
}
