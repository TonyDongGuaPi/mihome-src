package com.tencent.smtt.sdk;

import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;

class d implements ValueCallback<IX5JsError> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ JsContext f9175a;

    d(JsContext jsContext) {
        this.f9175a = jsContext;
    }

    /* renamed from: a */
    public void onReceiveValue(IX5JsError iX5JsError) {
        this.f9175a.c.handleException(this.f9175a, new JsError(iX5JsError));
    }
}
