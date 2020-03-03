package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;

class y implements ValueCallback<String[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9195a;
    final /* synthetic */ SystemWebChromeClient b;

    y(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.f9195a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(String[] strArr) {
        this.f9195a.onReceiveValue(strArr);
    }
}
