package com.xiaomi.jr.http;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.xiaomi.jr.common.utils.MifiLog;
import java.lang.ref.WeakReference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpCallback<T> implements Callback<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10812a = "HttpCallback";
    protected WeakReference<Fragment> d;
    protected WeakReference<Activity> e;

    public abstract void a(int i, String str, T t, Throwable th);

    public abstract void a(T t);

    /* access modifiers changed from: protected */
    public abstract void a(Call<T> call, T t);

    public HttpCallback(Fragment fragment) {
        if (fragment != null) {
            this.d = new WeakReference<>(fragment);
        }
    }

    public HttpCallback(Activity activity) {
        if (activity != null) {
            this.e = new WeakReference<>(activity);
        }
    }

    public final void onResponse(Call<T> call, Response<T> response) {
        if (!a()) {
            MifiLog.d(f10812a, "binding view invalid: " + call.request().url());
        } else if (!response.isSuccessful() || response.body() == null) {
            a(-1, "http code " + response.code(), response.body(), new Throwable());
        } else {
            a(call, response.body());
        }
    }

    public final void onFailure(Call<T> call, Throwable th) {
        if (!a()) {
            MifiLog.d(f10812a, "binding view invalid: " + call.request().url());
            return;
        }
        a(-2, th.getMessage(), (Object) null, th);
        th.printStackTrace();
        MifiLog.e(f10812a, "error occurs for " + call.request().toString(), th);
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x001d A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a() {
        /*
            r3 = this;
            java.lang.ref.WeakReference<android.support.v4.app.Fragment> r0 = r3.d
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x001f
            java.lang.ref.WeakReference<android.support.v4.app.Fragment> r0 = r3.d
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x001d
            java.lang.ref.WeakReference<android.support.v4.app.Fragment> r0 = r3.d
            java.lang.Object r0 = r0.get()
            android.support.v4.app.Fragment r0 = (android.support.v4.app.Fragment) r0
            boolean r0 = r0.isAdded()
            if (r0 == 0) goto L_0x001d
            goto L_0x0039
        L_0x001d:
            r2 = 0
            goto L_0x0039
        L_0x001f:
            java.lang.ref.WeakReference<android.app.Activity> r0 = r3.e
            if (r0 == 0) goto L_0x0039
            java.lang.ref.WeakReference<android.app.Activity> r0 = r3.e
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x001d
            java.lang.ref.WeakReference<android.app.Activity> r0 = r3.e
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = com.xiaomi.jr.common.app.ActivityChecker.a((android.app.Activity) r0)
            if (r0 == 0) goto L_0x001d
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.http.HttpCallback.a():boolean");
    }
}
