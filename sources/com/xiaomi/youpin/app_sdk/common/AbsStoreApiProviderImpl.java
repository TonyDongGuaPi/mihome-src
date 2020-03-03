package com.xiaomi.youpin.app_sdk.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xiaomi.youpin.share.config.YouPinShareApi;
import com.xiaomi.youpin.youpin_common.SharedDataManager;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.api.IAppStatApi;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsStoreApiProviderImpl implements StoreApiProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23202a = "StoreApiProviderImp";
    private static final String b = "com.xiaomi";
    SharedPreferences c;
    Handler d = new Handler(Looper.getMainLooper());

    public abstract String a(String str);

    public abstract void a(Activity activity);

    public abstract void a(WebView webView, String str, String str2, String str3, @Nullable StoreApiProvider.OnReceivedLoginRequestCallback onReceivedLoginRequestCallback);

    public void a(StoreApiManager storeApiManager) {
    }

    public abstract void a(IStoreCallback<Map<String, Object>> iStoreCallback);

    public abstract void a(String str, StoreBaseCallback<MiServiceTokenInfo> storeBaseCallback);

    public abstract IAppStatApi b();

    public abstract String c();

    public abstract String d();

    public Map<String, Object> e() {
        return null;
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return false;
    }

    public boolean h() {
        return false;
    }

    public abstract String i();

    public void j() {
    }

    public abstract boolean k();

    public boolean l() {
        return false;
    }

    public boolean m() {
        return false;
    }

    public abstract Context n();

    public void b(Activity activity, String str) {
        YouPinShareApi.a(activity, str);
    }

    public void a(String str, Object obj) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            if (this.c == null) {
                this.c = n().getSharedPreferences("shared_pref", 0);
            }
            if (obj instanceof String) {
                this.c.edit().putString(str, (String) obj).apply();
            } else if (obj instanceof Integer) {
                this.c.edit().putInt(str, ((Integer) obj).intValue()).apply();
            } else if (obj instanceof Boolean) {
                this.c.edit().putBoolean(str, ((Boolean) obj).booleanValue()).apply();
            } else if (obj instanceof Long) {
                this.c.edit().putLong(str, ((Long) obj).longValue()).apply();
            } else if (obj instanceof Float) {
                this.c.edit().putFloat(str, ((Float) obj).floatValue()).apply();
            } else {
                this.c.edit().putString(str, obj.toString()).apply();
            }
        }
    }

    public Object b(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.c == null) {
            this.c = n().getSharedPreferences("shared_pref", 0);
        }
        if (obj == null || (obj instanceof String)) {
            return this.c.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(this.c.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(this.c.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(this.c.getLong(str, ((Long) obj).longValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(this.c.getFloat(str, ((Float) obj).floatValue()));
        }
        return this.c.getString(str, obj.toString());
    }

    public HashMap<String, Object> o() {
        return WXAppStoreApiManager.b().a();
    }

    public void a(WebView webView, String str, String str2, String str3) {
        a(webView, str, str2, str3, (StoreApiProvider.OnReceivedLoginRequestCallback) null);
    }

    public void a(String str, IStoreCallback<String> iStoreCallback) {
        SharedDataManager.a().a(str, iStoreCallback);
    }

    public void a(String str, String str2, IStoreCallback<Void> iStoreCallback) {
        SharedDataManager.a().a(str, str2, iStoreCallback);
    }
}
