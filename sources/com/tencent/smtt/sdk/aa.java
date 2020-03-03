package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

class aa implements ValueCallback<Uri> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9124a;
    final /* synthetic */ SystemWebChromeClient b;

    aa(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.f9124a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri uri) {
        this.f9124a.onReceiveValue(uri);
    }
}
