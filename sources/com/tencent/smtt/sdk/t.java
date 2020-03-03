package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

class t implements ValueCallback<Uri[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9190a;
    final /* synthetic */ q b;

    t(q qVar, ValueCallback valueCallback) {
        this.b = qVar;
        this.f9190a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri[] uriArr) {
        this.f9190a.onReceiveValue(uriArr);
    }
}
