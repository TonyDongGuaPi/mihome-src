package com.ximalaya.ting.android.opensdk.model.pay;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;

public class BoughtStatu extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private int f2100a;
    @SerializedName("has_bought")
    private boolean b;

    public int a() {
        return this.f2100a;
    }

    public void a(int i) {
        this.f2100a = i;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }
}
