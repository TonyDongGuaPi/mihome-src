package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;
import com.tencent.smtt.sdk.JsVirtualMachine;

class f implements ValueCallback<String> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9177a;
    final /* synthetic */ JsVirtualMachine.a b;

    f(JsVirtualMachine.a aVar, ValueCallback valueCallback) {
        this.b = aVar;
        this.f9177a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(String str) {
        this.f9177a.onReceiveValue((Object) null);
    }
}
