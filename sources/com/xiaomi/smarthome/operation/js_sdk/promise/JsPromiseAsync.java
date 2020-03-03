package com.xiaomi.smarthome.operation.js_sdk.promise;

import android.app.Activity;
import android.util.Log;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import java.lang.ref.WeakReference;

public abstract class JsPromiseAsync extends JsPromise {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21103a = "JsPromiseSync";

    public interface PromiseCallback {
        void a(String str);

        void b(String str);
    }

    /* access modifiers changed from: protected */
    public abstract void a(PromiseCallback promiseCallback);

    public JsPromiseAsync(WeakReference<CommonWebView> weakReference, WeakReference<Activity> weakReference2) {
        super(weakReference, weakReference2);
        a(new PromiseCallback() {
            public void a(String str) {
                Log.d(JsPromiseAsync.f21103a, "onSuccess: " + str);
                JsPromiseAsync.this.e.onNext(str);
            }

            public void b(String str) {
                Log.d(JsPromiseAsync.f21103a, "invoke: " + str);
                JsPromiseAsync.this.e.onError(new Exception(str));
            }
        });
    }
}
