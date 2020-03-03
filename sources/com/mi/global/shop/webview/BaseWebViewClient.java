package com.mi.global.shop.webview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mi.account.LoginManager;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class BaseWebViewClient extends WebViewClient {
    public InputStream a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return new FileInputStream(file);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebResourceResponse a(String str, WebView webView, String str2, String[] strArr) {
        LogUtil.b(str, "Resource Request PATH:" + strArr[1]);
        InputStream a2 = a(strArr[1]);
        if (a2 == null) {
            return super.shouldInterceptRequest(webView, str2);
        }
        LogUtil.b(str, "Resource Request file exists!");
        return new WebResourceResponse(strArr[0], "UTF-8", a2);
    }

    public boolean a(Activity activity, String str) {
        if (!str.startsWith("mailto:") && !str.startsWith("tel:")) {
            return false;
        }
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (ActivityNotFoundException unused) {
            if (str.startsWith("mailto:")) {
                MiToast.a((Context) ShopApp.g(), R.string.error_mailservice, 1);
            }
            if (str.startsWith("tel:")) {
                MiToast.a((Context) ShopApp.g(), R.string.error_phoneservice, 1);
            }
        }
        return true;
    }

    public boolean b(Activity activity, String str) {
        if (!LocaleHelper.g() || !ConnectionHelper.g(str).booleanValue()) {
            return false;
        }
        activity.startActivityForResult(new Intent(activity, ShoppingCartActivity.class), 22);
        return true;
    }

    public boolean c(final Activity activity, String str) {
        if (!str.toLowerCase().contains("account.xiaomi.com/pass/serviceLogin".toLowerCase())) {
            return false;
        }
        if (LoginManager.u().x()) {
            LoginManager.u().logout((LoginManager.LogoutCallback) null);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!"community_sdk".equals(ShopApp.h.f)) {
                        ShopApp.d();
                    } else if (BaseActivity.isActivityAlive(activity)) {
                        ((BaseActivity) activity).gotoAccount();
                    }
                }
            }, 1000);
            return true;
        } else if ("community_sdk".equals(ShopApp.h.f)) {
            ((BaseActivity) activity).gotoAccount();
            return true;
        } else {
            ShopApp.d();
            return true;
        }
    }
}
