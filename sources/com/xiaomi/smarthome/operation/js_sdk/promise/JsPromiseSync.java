package com.xiaomi.smarthome.operation.js_sdk.promise;

import android.app.Activity;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public abstract class JsPromiseSync extends JsPromise implements Callable<String> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21105a = "JsPromiseSync";

    public JsPromiseSync(WeakReference<CommonWebView> weakReference, WeakReference<Activity> weakReference2) {
        super(weakReference, weakReference2);
        this.c = weakReference;
        this.d = weakReference2;
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public final void run() {
                JsPromiseSync.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        try {
            String str = (String) call();
            Log.d(f21105a, "invoke: " + str);
            this.e.onNext(str);
        } catch (Exception e) {
            Log.d(f21105a, "invoke: " + Log.getStackTraceString(e));
            this.e.onError(e);
        }
    }
}
