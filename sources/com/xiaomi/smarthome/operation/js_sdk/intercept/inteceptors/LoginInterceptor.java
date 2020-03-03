package com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;

public class LoginInterceptor extends IUrlInterceptorAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21074a = "LoginInterceptor";
    private final Context b;

    public LoginInterceptor(Context context) {
        this.b = context;
    }

    public void a(final WebView webView, String str, String str2, String str3) {
        if ("com.xiaomi".equals(str)) {
            try {
                MiAccountManager miAccountManager = MiAccountManager.get(this.b.getApplicationContext());
                Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                if (accountsByType.length != 0) {
                    miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) this.b, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
                        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                            String str = null;
                            if (accountManagerFuture != null) {
                                try {
                                    Bundle result = accountManagerFuture.getResult();
                                    if (result != null) {
                                        str = result.getString("authtoken");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (str == null) {
                                Log.d(LoginInterceptor.f21074a, "web sso failed.");
                                return;
                            }
                            webView.loadUrl(str);
                            Log.d(LoginInterceptor.f21074a, "web sso succeed.");
                        }
                    }, (Handler) null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
