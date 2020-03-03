package com.mi.global.bbs.ui;

import android.content.Intent;
import android.os.Bundle;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.webview.WebViewCookieManager;
import com.mi.log.LogUtil;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;

public class FacebookLoginActivity extends BaseActivity {
    private static final String TAG = "FacebookLoginActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.b(TAG, "onCreate");
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra("FacebookLogin", false)) {
            LogUtil.b(TAG, "get null fb info");
        } else {
            LogUtil.b(TAG, "get userId:" + intent.getStringExtra("FacebookUid"));
            LogUtil.b(TAG, "get serviceToken:" + intent.getStringExtra("FacebookServiceToken"));
            String stringExtra = intent.getStringExtra("FacebookUid");
            String stringExtra2 = intent.getStringExtra("FacebookServiceToken");
            Utils.Preference.setStringPref(this, "pref_uid", stringExtra);
            Utils.Preference.setStringPref(this, "pref_extended_token", ExtendedAuthToken.build(stringExtra2, "Security").toPlain());
            Utils.Preference.setBooleanPref(this, "pref_login_system", false);
            LoginManager.getInstance().onAccountLoginSucceed(stringExtra, stringExtra2, "Security");
            WebViewCookieManager.addAppCookie();
            WebViewCookieManager.setLoginCookies(BBSApplication.getInstance());
            WebViewCookieManager.updateCustomCookies(BBSApplication.getInstance());
        }
        finish();
    }
}
