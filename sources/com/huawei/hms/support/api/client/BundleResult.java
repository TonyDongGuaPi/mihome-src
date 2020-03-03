package com.huawei.hms.support.api.client;

import android.os.Bundle;

public class BundleResult {

    /* renamed from: a  reason: collision with root package name */
    private int f5877a;
    private Bundle b;

    public BundleResult(int i, Bundle bundle) {
        this.f5877a = i;
        this.b = bundle;
    }

    public int getResultCode() {
        return this.f5877a;
    }

    public Bundle getRspBody() {
        return this.b;
    }

    public void setResultCode(int i) {
        this.f5877a = i;
    }

    public void setRspBody(Bundle bundle) {
        this.b = bundle;
    }
}
