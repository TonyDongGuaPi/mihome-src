package com.xiaomi.smarthome.newui.onekey_delete;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;
import com.xiaomi.smarthome.shop.utils.LogUtil;

public class AuthFragment extends CommonWebViewFragment {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20693a = "AuthFragment";

    public static AuthFragment a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("arg_url", str);
        bundle.putString("arg_title", str2);
        bundle.putBoolean("arg_use_title_bar", true);
        AuthFragment authFragment = new AuthFragment();
        authFragment.setArguments(bundle);
        return authFragment;
    }

    public void init() {
        getWebView().addUrlInterceptor(AuthUrlInterceptor.class.getSimpleName(), new AuthUrlInterceptor());
        super.init();
    }

    /* access modifiers changed from: private */
    public AuthActivityBridge a() {
        FragmentActivity activity = getActivity();
        if (activity instanceof AuthActivityBridge) {
            return (AuthActivityBridge) activity;
        }
        return null;
    }

    class AuthUrlInterceptor extends IUrlInterceptorAdapter {
        AuthUrlInterceptor() {
        }

        public boolean a(WebView webView, String str) {
            if (!str.startsWith("https://api.io.mi.com/v2/idverify")) {
                return false;
            }
            LogUtil.c(AuthFragment.f20693a, "shouldOverrideUrlLoading: " + str);
            if (AuthFragment.this.a() == null) {
                return true;
            }
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("code");
            String queryParameter2 = parse.getQueryParameter("message");
            if (!TextUtils.equals(queryParameter, "0") || TextUtils.isEmpty(queryParameter2)) {
                AuthFragment.this.a().onAuthFailed(queryParameter2);
                return true;
            }
            AuthFragment.this.a().onAuthSuccess(queryParameter2);
            return true;
        }
    }
}
