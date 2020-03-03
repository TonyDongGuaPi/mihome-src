package com.xiaomi.miot.store.module;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.xiaomi.youpin.cookie.YouPinCookieManager;

public class CookieManagerModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "CookieManager";
    }

    public CookieManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void setCookie(@NonNull String str, String str2) {
        YouPinCookieManager.a().a(str, str2);
    }

    @ReactMethod
    public void removeCookie(@NonNull String str) {
        YouPinCookieManager.a().a(str);
    }

    @ReactMethod
    public void getCookie(@NonNull final String str, @NonNull final Callback callback) {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                String b = YouPinCookieManager.a().b(str);
                if (callback == null) {
                    return null;
                }
                callback.invoke(b);
                return null;
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void removeAllCookies() {
        YouPinCookieManager.a().b();
    }
}
