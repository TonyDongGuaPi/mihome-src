package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;
import com.tencent.smtt.sdk.JsVirtualMachine;

class e implements ValueCallback<String> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ValueCallback f9176a;
    final /* synthetic */ JsVirtualMachine.a b;

    e(JsVirtualMachine.a aVar, ValueCallback valueCallback) {
        this.b = aVar;
        this.f9176a = valueCallback;
    }

    /* renamed from: a */
    public void onReceiveValue(String str) {
        this.f9176a.onReceiveValue(str);
    }
}
