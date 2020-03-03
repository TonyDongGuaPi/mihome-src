package com.xiaomi.youpin.login;

import android.content.Intent;
import android.util.Pair;
import android.webkit.WebView;

public interface LoginDependencyApi {

    public interface OnReceivedLoginRequestCallback {
        void a(Intent intent);
    }

    String a(String str);

    void a(WebView webView, String str, String str2, String str3, OnReceivedLoginRequestCallback onReceivedLoginRequestCallback);

    boolean a();

    boolean b();

    Pair<Long, Boolean> c();
}
