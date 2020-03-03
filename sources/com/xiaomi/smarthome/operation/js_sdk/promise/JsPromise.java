package com.xiaomi.smarthome.operation.js_sdk.promise;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;
import com.xiaomi.smarthome.operation.js_sdk.utils.JsSdkUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class JsPromise {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21099a = "JsPromise";
    /* access modifiers changed from: protected */
    public WeakReference<CommonWebView> c;
    protected WeakReference<Activity> d;
    protected final Subject<String> e = BehaviorSubject.create();

    public List<String> a() {
        return null;
    }

    public JsPromise(WeakReference<CommonWebView> weakReference, WeakReference<Activity> weakReference2) {
        this.c = weakReference;
        this.d = weakReference2;
    }

    @JavascriptInterface
    @SuppressLint({"CheckResult"})
    public final void then(final String str, final String str2) {
        this.e.observeOn(AndroidSchedulers.mainThread()).filter(new Predicate<String>() {
            /* renamed from: a */
            public boolean test(String str) throws Exception {
                Activity activity = (Activity) JsPromise.this.d.get();
                return (activity == null || ((CommonWebView) JsPromise.this.c.get()) == null || activity.isDestroyed()) ? false : true;
            }
        }).subscribe(new Consumer<String>() {
            /* renamed from: a */
            public void accept(String str) throws Exception {
                LogUtil.a(JsPromise.f21099a, "accept then : result: " + str);
                JsSdkUtils.b((WebView) JsPromise.this.c.get(), str, JsPromise.this.a(str));
            }
        }, new Consumer<Throwable>() {
            /* renamed from: a */
            public void accept(Throwable th) throws Exception {
                LogUtil.a(JsPromise.f21099a, "accept then : error: " + th.getLocalizedMessage());
                JsSdkUtils.b((WebView) JsPromise.this.c.get(), str2, JsPromise.this.a(th.getLocalizedMessage()));
            }
        });
    }

    /* access modifiers changed from: private */
    public String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        List<String> a2 = a();
        if (a2 != null) {
            arrayList.addAll(a2);
        }
        arrayList.add(str);
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }
}
