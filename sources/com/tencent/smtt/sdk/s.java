package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

class s implements ValueCallback<Uri> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9189a;
    final /* synthetic */ q b;

    s(q qVar, ValueCallback valueCallback) {
        this.b = qVar;
        this.f9189a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(Uri uri) {
        this.f9189a.onReceiveValue(new Uri[]{uri});
    }
}
