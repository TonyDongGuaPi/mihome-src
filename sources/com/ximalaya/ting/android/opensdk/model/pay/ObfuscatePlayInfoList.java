package com.ximalaya.ting.android.opensdk.model.pay;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class ObfuscatePlayInfoList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<ObfuscatePlayInfo> f2104a;

    public List<ObfuscatePlayInfo> a() {
        return this.f2104a;
    }

    public void a(List<ObfuscatePlayInfo> list) {
        this.f2104a = list;
    }
}
