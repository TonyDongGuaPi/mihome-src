package com.youpin.weex.app.module.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.Map;

public class AccountModule extends WXModule implements IAccountAdapter {
    public static final String ACTION_ON_LOGIN = "com.xiaomi.youpin.action.on_login";
    public static final String ACTION_ON_LOGOUT = "com.xiaomi.youpin.action.on_logout";
    public static final String ACTION_ON_SERVICETOKEN_UPDATE = "com.xiaomi.youpin.action.on_servicetoken_update";
    public static final String MODULE_NAME = "yp-account";

    /* access modifiers changed from: package-private */
    public IAccountAdapter getAdapter() {
        return (IAccountAdapter) WXAppStoreApiManager.b().a(IAccountAdapter.class);
    }

    @JSMethod(uiThread = false)
    public void updateLoginInfo(String str, String str2, boolean z) {
        if (getAdapter() != null) {
            getAdapter().updateLoginInfo(str, str2, z);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xiaomi.youpin.action.on_login");
            intentFilter.addAction("com.xiaomi.youpin.action.on_logout");
            intentFilter.addAction(ACTION_ON_SERVICETOKEN_UPDATE);
            LocalBroadcastManager.getInstance(WXAppStoreApiManager.b().d()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    Map a2 = WXAppStoreApiManager.b().c().a();
                    if (a2 != null) {
                        AccountModule.this.mWXSDKInstance.fireGlobalEventCallback("updateAuth", a2);
                    }
                }
            }, intentFilter);
        }
    }

    @JSMethod(uiThread = true)
    public void openLoginPage() {
        if (getAdapter() != null) {
            getAdapter().openLoginPage();
        }
    }

    @JSMethod(uiThread = true)
    public void getUserInfo(JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().getUserInfo(jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    public void getUserAgent(JSCallback jSCallback) {
        if (getAdapter() != null) {
            getAdapter().getUserAgent(jSCallback);
        }
    }

    @JSMethod(uiThread = false)
    @Deprecated
    public void setUserAgent(String str) {
        if (getAdapter() != null) {
            getAdapter().setUserAgent(str);
        }
    }

    @JSMethod(uiThread = false)
    public void appendUserAgent(String str) {
        if (getAdapter() != null) {
            getAdapter().appendUserAgent(str);
        }
    }
}
