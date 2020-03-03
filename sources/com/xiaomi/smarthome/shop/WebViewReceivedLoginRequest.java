package com.xiaomi.smarthome.shop;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebView;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import java.lang.ref.WeakReference;

public class WebViewReceivedLoginRequest {
    public static void a(WebView webView, String str, String str2, String str3, @Nullable StoreApiProvider.OnReceivedLoginRequestCallback onReceivedLoginRequestCallback) {
        MiotAccountProvider a2 = MiotAccountProvider.a(SHApplication.getAppContext());
        Account[] accountsByType = a2.getAccountsByType(str);
        if (accountsByType != null && accountsByType.length != 0) {
            final WeakReference weakReference = new WeakReference(webView);
            String str4 = "weblogin:" + str3;
            if (!CoreApi.a().v()) {
                a2.getAuthToken(accountsByType[0], str4, (Bundle) null, false, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
                    public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                        try {
                            String string = accountManagerFuture.getResult().getString("authtoken");
                            if (!TextUtils.isEmpty(string) && weakReference.get() != null) {
                                ((WebView) weakReference.get()).loadUrl(string);
                                ((WebView) weakReference.get()).postDelayed(new Runnable() {
                                    public void run() {
                                        if (weakReference.get() != null) {
                                            ((WebView) weakReference.get()).clearHistory();
                                        }
                                    }
                                }, 1000);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }, (Handler) null);
                return;
            }
            AccountManager.get(SHApplication.getAppContext()).getAuthToken(accountsByType[0], str4, (Bundle) null, false, new AccountManagerCallback<Bundle>() {
                public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                    try {
                        String string = accountManagerFuture.getResult().getString("authtoken");
                        if (!TextUtils.isEmpty(string) && weakReference.get() != null) {
                            ((WebView) weakReference.get()).loadUrl(string);
                            ((WebView) weakReference.get()).postDelayed(new Runnable() {
                                public void run() {
                                    if (weakReference.get() != null) {
                                        ((WebView) weakReference.get()).clearHistory();
                                    }
                                }
                            }, 1000);
                        }
                    } catch (Exception unused) {
                    }
                }
            }, (Handler) null);
        }
    }
}
