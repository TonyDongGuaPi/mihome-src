package com.ximalaya.ting.android.opensdk.model.live.radio;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RadioCategoryList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<RadioCategory> f2088a;

    public List<RadioCategory> a() {
        return this.f2088a;
    }

    public void a(List<RadioCategory> list) {
        this.f2088a = list;
    }
}
