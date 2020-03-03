package com.ximalaya.ting.android.opensdk.model.metadata;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class MetaDataList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<MetaData> f2098a;

    public List<MetaData> a() {
        return this.f2098a;
    }

    public void a(List<MetaData> list) {
        this.f2098a = list;
    }
}
