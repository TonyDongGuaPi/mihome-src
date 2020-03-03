package com.mi.global.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;

public class FacebookLoginActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5377a = "FacebookLoginActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.b(f5377a, "onCreate");
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra("FacebookLogin", false)) {
            LogUtil.b(f5377a, "get null fb info");
        } else {
            LogUtil.b(f5377a, "get userId:" + intent.getStringExtra("FacebookUid"));
            LogUtil.b(f5377a, "get serviceToken:" + intent.getStringExtra("FacebookServiceToken"));
            String stringExtra = intent.getStringExtra("FacebookUid");
            String stringExtra2 = intent.getStringExtra("FacebookServiceToken");
            Utils.Preference.setStringPref(this, "pref_uid", stringExtra);
            Utils.Preference.setStringPref(this, "pref_extended_token", ExtendedAuthToken.build(stringExtra2, "Security").toPlain());
            Utils.Preference.setBooleanPref(this, "pref_login_system", false);
            LoginManager.u().a(stringExtra, stringExtra2, "Security");
            WebViewCookieManager.a();
            WebViewCookieManager.e(ShopApp.g());
            WebViewCookieManager.d(ShopApp.g());
        }
        finish();
    }
}
