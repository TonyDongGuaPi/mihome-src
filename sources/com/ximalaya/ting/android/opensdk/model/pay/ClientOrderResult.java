package com.ximalaya.ting.android.opensdk.model.pay;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class ClientOrderResult extends XimalayaResponse {
    @SerializedName("order_settlement_url")

    /* renamed from: a  reason: collision with root package name */
    private String f2102a;

    public String a() {
        return this.f2102a;
    }

    public void a(String str) {
        this.f2102a = str;
    }
}
