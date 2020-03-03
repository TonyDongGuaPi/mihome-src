package com.xiaomi.smarthome.operation.js_sdk.base;

import android.app.Activity;
import java.lang.ref.WeakReference;

public class BaseJSInterface {

    /* renamed from: a  reason: collision with root package name */
    protected WeakReference<Activity> f21058a;
    protected WeakReference<CommonWebView> b;

    public BaseJSInterface(Activity activity, CommonWebView commonWebView) {
        this.f21058a = new WeakReference<>(activity);
        this.b = new WeakReference<>(commonWebView);
    }
}
