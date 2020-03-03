package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

class ab implements ValueCallback<Uri[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9125a;
    final /* synthetic */ SystemWebChromeClient b;

    ab(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.f9125a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri[] uriArr) {
        this.f9125a.onReceiveValue(uriArr);
    }
}
