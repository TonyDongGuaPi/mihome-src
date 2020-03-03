package com.xiaomi.youpin.youpin_common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import com.xiaomi.youpin.youpin_common.api.IAppStatApi;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import com.xiaomi.youpin.youpin_common.api.StoreBaseCallback;
import com.xiaomi.youpin.youpin_common.login.MiServiceTokenInfo;
import java.util.HashMap;
import java.util.Map;

public interface StoreApiProvider {

    public interface OnReceivedLoginRequestCallback {
        void a(Intent intent);
    }

    String a(String str);

    void a(Activity activity);

    void a(Activity activity, String str);

    void a(WebView webView, String str, String str2, String str3);

    void a(WebView webView, String str, String str2, String str3, OnReceivedLoginRequestCallback onReceivedLoginRequestCallback);

    void a(StoreApiManager storeApiManager);

    void a(IStoreCallback<Map<String, Object>> iStoreCallback);

    void a(String str, IStoreCallback<String> iStoreCallback);

    void a(String str, StoreBaseCallback<MiServiceTokenInfo> storeBaseCallback);

    void a(String str, Object obj);

    void a(String str, String str2, IStoreCallback<Void> iStoreCallback);

    IAppStatApi b();

    Object b(String str, Object obj);

    void b(Activity activity, String str);

    String c();

    String d();

    Map<String, Object> e();

    boolean f();

    boolean g();

    boolean h();

    String i();

    void j();

    boolean k();

    boolean l();

    boolean m();

    Context n();

    HashMap<String, Object> o();
}
