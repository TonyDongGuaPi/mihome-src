package com.mi.global.shop.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.user.FeedbackActivity;
import com.mi.global.shop.util.LocationUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.ApkUtils;
import com.mi.util.Device;
import com.mi.util.permission.Permission;
import com.mi.util.permission.PermissionCallback;
import com.mi.util.permission.PermissionUtil;
import com.mi.util.permission.SamplePermissionCallback;
import java.io.File;

public class WebViewHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7142a = "WebViewHelper";
    public static final String b = "com.google.android.apps.maps";
    public static String c;

    public static void a(WebView webView) {
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            settings.setJavaScriptEnabled(true);
            if (Build.VERSION.SDK_INT >= 21) {
                settings.setMixedContentMode(0);
            }
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setTextZoom(100);
            settings.setCacheMode(-1);
            String userAgentString = settings.getUserAgentString();
            settings.setUserAgentString(userAgentString + " XiaoMi/GlobalShop/" + Device.s);
            settings.setUseWideViewPort(true);
            settings.setTextZoom(100);
            if (LocaleHelper.n()) {
                settings.setUseWideViewPort(false);
            }
            d(webView);
            e(webView);
            settings.setBuiltInZoomControls(false);
            webView.addJavascriptInterface(new WebEvent(webView), "WE");
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
            }
        }
    }

    public static void a(String str) {
        c = str;
    }

    public static void b(WebView webView) {
        webView.clearHistory();
    }

    public static void c(WebView webView) {
        webView.clearCache(true);
    }

    private static class WebEvent {
        WebView mWebView;

        WebEvent(WebView webView) {
            this.mWebView = webView;
        }

        @JavascriptInterface
        public void logHTML(String str) {
            LogUtil.b(WebActivity.TAG, "html:" + str);
        }

        @JavascriptInterface
        public void reload() {
            this.mWebView.post(new Runnable() {
                public void run() {
                    WebEvent.this.mWebView.goBack();
                    WebEvent.this.mWebView.reload();
                }
            });
        }

        @JavascriptInterface
        public void reload2() {
            this.mWebView.post(new Runnable() {
                public void run() {
                    WebEvent.this.mWebView.reload();
                }
            });
        }

        @JavascriptInterface
        public void logout() {
            LoginManager.u().logout();
        }

        @JavascriptInterface
        public void login() {
            if (ShopApp.h.f.equals("community_sdk")) {
                ((BaseActivity) this.mWebView.getContext()).gotoAccount();
            } else {
                ShopApp.d();
            }
        }

        @JavascriptInterface
        public void updateCart(final int i) {
            LogUtil.b("WebViewHelper", "update cart from js:" + i);
            if (!this.mWebView.getContext().getClass().equals(WebActivity.class)) {
                LogUtil.b("WebViewHelper", "ignore update cart from " + this.mWebView.getContext().getClass().getName());
                return;
            }
            ((BaseActivity) this.mWebView.getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    ((BaseActivity) WebEvent.this.mWebView.getContext()).updateShoppingCart(i);
                }
            });
        }

        @JavascriptInterface
        public void goToPay(final String str, final String str2) {
            ((WebActivity) this.mWebView.getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    ((WebActivity) WebEvent.this.mWebView.getContext()).jumpToPayment(str, str2);
                }
            });
        }

        @JavascriptInterface
        public void updatePinCode(String str, String str2, String str3, String str4) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str)) {
                Utils.Preference.setStringPref(this.mWebView.getContext(), "pref_key_zip_code", str);
                Utils.Preference.setStringPref(this.mWebView.getContext(), "pref_key_city_name", str2);
                Utils.Preference.setStringPref(this.mWebView.getContext(), "pref_key_state_id", str3);
                Utils.Preference.setStringPref(this.mWebView.getContext(), "pref_key_warehouse_id", str4);
            }
        }

        @JavascriptInterface
        public void shareToFacebook(String str, String str2, String str3, String str4) {
            ShareLinkContent shareLinkContent;
            if (str4 != null && !str4.equals("") && str3 != null && !str3.equals("")) {
                shareLinkContent = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str4))).setImageUrl(Uri.parse(str3)).setContentTitle(str).setContentDescription(str2).build();
            } else if (str4 == null || str4.equals("")) {
                shareLinkContent = new ShareLinkContent.Builder().setContentTitle(str).setContentDescription(str2).build();
            } else {
                shareLinkContent = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(str4))).setContentTitle(str).setContentDescription(str2).build();
            }
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ((WebActivity) this.mWebView.getContext()).shareDialog.show(shareLinkContent);
            }
        }

        @JavascriptInterface
        public int getVersionCode() {
            return Device.r;
        }

        @JavascriptInterface
        public void goToFeedback() {
            this.mWebView.getContext().startActivity(new Intent(this.mWebView.getContext(), FeedbackActivity.class));
        }

        @JavascriptInterface
        public boolean isGoogleMapInstalled() {
            return ApkUtils.b(this.mWebView.getContext(), WebViewHelper.b);
        }

        @JavascriptInterface
        public void openGoogleMap(String str) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (isGoogleMapInstalled()) {
                    intent.setPackage(WebViewHelper.b);
                }
                if (intent.resolveActivity(this.mWebView.getContext().getPackageManager()) != null) {
                    this.mWebView.getContext().startActivity(intent);
                }
            } catch (Exception unused) {
            }
        }

        @JavascriptInterface
        public void requestLocationPermission() {
            final WebActivity webActivity = this.mWebView.getContext() instanceof WebActivity ? (WebActivity) this.mWebView.getContext() : null;
            if (webActivity != null) {
                if (!PermissionUtil.a((Context) webActivity, "android.permission.ACCESS_COARSE_LOCATION")) {
                    PermissionUtil.a((Activity) webActivity, (PermissionCallback) new SamplePermissionCallback() {
                        public void onResult() {
                            LocationUtil.a(webActivity);
                            String url = WebEvent.this.mWebView.getUrl();
                            String a2 = LocationUtil.a();
                            if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(a2)) {
                                WebView webView = WebEvent.this.mWebView;
                                webView.loadUrl(url + "&location=" + LocationUtil.a());
                            }
                        }
                    }, Permission.Group.d);
                }
            }
        }
    }

    protected static void d(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setAppCacheEnabled(true);
        String path = webView.getContext().getApplicationContext().getDir("cache", 0).getPath();
        LogUtil.b("app cacheDir:" + path);
        b(path);
        settings.setAppCachePath(path);
    }

    protected static void e(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        String path = webView.getContext().getApplicationContext().getDir("database", 0).getPath();
        LogUtil.b("dom storageDir:" + path);
        settings.setDatabasePath(path);
    }

    private static void b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
