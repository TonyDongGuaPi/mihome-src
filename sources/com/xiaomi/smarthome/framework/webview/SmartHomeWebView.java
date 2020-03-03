package com.xiaomi.smarthome.framework.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Locale;

public class SmartHomeWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17892a = "SmartHomeWebView";
    private SmartHomeWebViewClient b;
    private String c;

    public SmartHomeWebViewClient getWebViewClient() {
        return this.b;
    }

    public SmartHomeWebView(Context context) {
        super(context);
        a(context);
    }

    public SmartHomeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SmartHomeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @SuppressLint({"AddJavascriptInterface"})
    private void a(Context context) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        if (this.c == null) {
            this.c = settings.getUserAgentString() + " " + UserAgentUtil.a(context) + " XiaoMi/MiuiBrowser/4.3 XiaoMi/HybridView/";
        }
        settings.setUserAgentString(this.c);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(SHApplication.getAppContext().getDir("cache", 0).getPath());
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        settings.setSupportZoom(true);
        settings.setTextZoom(100);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
            removeJavascriptInterface("searchBoxJavaBridge_");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        setWebChromeClient(new SmartHomeWebChromeClient());
        this.b = new SmartHomeWebViewClient();
        setWebViewClient(this.b);
        setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    SmartHomeWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        initCookie();
        if (Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(GlobalSetting.q);
        }
        if (DarkModeCompat.a(context)) {
            setBackgroundColor(ContextCompat.getColor(context, 17170445));
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        if (webViewClient instanceof WebViewClientErrorPageWrapper) {
            super.setWebViewClient(webViewClient);
        }
        super.setWebViewClient(new WebViewClientErrorPageWrapper(webViewClient));
    }

    public void setExtraWebViewClient(WebViewClient webViewClient) {
        if (this.b != null) {
            this.b.a(webViewClient);
        }
    }

    public void initCookie() {
        CookieSyncManager.createInstance(getContext());
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(this, true);
        }
        if (CoreApi.a().q()) {
            CoreApi.a().s();
            try {
                MiServiceTokenInfo a2 = CoreApi.a().a("xiaomiio");
                if (a2 != null) {
                    setCookie(instance, "serviceToken", URLEncoder.encode(a2.c, "UTF-8"), ".io.mi.com");
                    setCookie(instance, "yetAnotherServiceToken", URLEncoder.encode(a2.c, "UTF-8"), ".home.mi.com");
                }
                MiServiceTokenInfo a3 = CoreApi.a().a("xiaomihome");
                if (a3 != null) {
                    setCookie(instance, "serviceToken", URLEncoder.encode(a3.c, "UTF-8"), ".home.mi.com");
                }
            } catch (Exception unused) {
            }
        }
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        setCookie(instance, "locale", LocaleUtil.a(I), ".io.mi.com");
        setCookie(instance, "locale", LocaleUtil.a(I), ".home.mi.com");
        setCookie(instance, "channel", GlobalSetting.v, ".home.mi.com");
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    /* access modifiers changed from: protected */
    public void setCookie(CookieManager cookieManager, String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null) {
            cookieManager.setCookie(str3, str + "=" + str2 + ";path=/;domain=" + str3);
            LogUtil.a(f17892a, "setCookie: domain: " + str3 + " ;name: " + str + " ;value: " + str2);
        }
    }

    public static class SmartHomeWebViewClient extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<WebViewClient> f17897a;
        private IUrlOperationListener b;

        public interface IUrlOperationListener {
            void a(WebView webView, String str, String str2, String str3);

            void a(String str);

            boolean a(WebView webView, String str);

            void b(String str);
        }

        public void a(IUrlOperationListener iUrlOperationListener) {
            this.b = iUrlOperationListener;
        }

        public IUrlOperationListener a() {
            return this.b;
        }

        public void a(WebViewClient webViewClient) {
            this.f17897a = new WeakReference<>(webViewClient);
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (this.b != null) {
                this.b.a(webView, str, str2, str3);
            }
        }

        @Nullable
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient;
            if (!(this.f17897a == null || (webViewClient = (WebViewClient) this.f17897a.get()) == null || Build.VERSION.SDK_INT < 24)) {
                webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (!TextUtils.isEmpty(str) && this.b != null) {
                this.b.a(str);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            WebViewClient webViewClient;
            if (!(this.f17897a == null || (webViewClient = (WebViewClient) this.f17897a.get()) == null)) {
                webViewClient.onPageFinished(webView, str);
            }
            if (!TextUtils.isEmpty(str) && this.b != null) {
                this.b.b(str);
            }
            super.onPageFinished(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            WebViewClient webViewClient;
            if (!(this.f17897a == null || (webViewClient = (WebViewClient) this.f17897a.get()) == null || Build.VERSION.SDK_INT < 24)) {
                webViewClient.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            WebViewClient webViewClient;
            if (!(this.f17897a == null || (webViewClient = (WebViewClient) this.f17897a.get()) == null || Build.VERSION.SDK_INT < 24)) {
                webViewClient.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Nullable
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            WebViewClient webViewClient;
            if (!(this.f17897a == null || (webViewClient = (WebViewClient) this.f17897a.get()) == null)) {
                webViewClient.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            WebViewClient webViewClient;
            Context context = webView.getContext();
            if (str.startsWith("tel") || str.startsWith("mailto")) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.contains("home.mi.com/shop/shexiangyun")) {
                LogUtil.a(SmartHomeWebView.f17892a, "buy url:" + str);
                UrlDispatchManger.a().c(str);
                return true;
            } else if (this.b != null && this.b.a(webView, str)) {
                return true;
            } else {
                if (!(this.f17897a == null || (webViewClient = (WebViewClient) this.f17897a.get()) == null)) {
                    webViewClient.shouldOverrideUrlLoading(webView, str);
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        }
    }

    public static class SmartHomeWebChromeClient extends WebChromeClient {
        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(webView.getContext());
            builder.b((CharSequence) str2);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.a(false);
            builder.b().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(webView.getContext());
            builder.b((CharSequence) str2);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            });
            builder.a(false);
            builder.b().show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            ToastUtil.a((CharSequence) str2);
            return true;
        }
    }
}
