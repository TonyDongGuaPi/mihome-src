package com.ximalaya.ting.android.opensdk.model.album;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class UpdateBatchList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<UpdateBatch> f2033a;

    public List<UpdateBatch> a() {
        return this.f2033a;
    }

    public void a(List<UpdateBatch> list) {
        this.f2033a = list;
    }
}
