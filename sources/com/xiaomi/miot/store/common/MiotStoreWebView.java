package com.xiaomi.miot.store.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomiyoupin.toast.YPDToast;
import java.util.List;

public class MiotStoreWebView extends WebView {
    public static final String[] VIDEO_TYPE = {".mp4", ".3gp", ".avi", ".mkv", ".wmv", ".mpg", ".vob", ".flv", ".swf", ".mov"};

    /* renamed from: a  reason: collision with root package name */
    private DeviceShopWebViewClient f11379a;
    private DeviceShopWebChromeClient b;

    private void a() {
    }

    private void a(CookieManager cookieManager, String str, String str2, String str3) {
    }

    public MiotStoreWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    @SuppressLint({"AddJavascriptInterface"})
    private void a(Context context) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getContext().getDir("cache", 0).getPath());
        settings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        settings.setSupportZoom(true);
        settings.setTextZoom(100);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        String f = UserAgent.f();
        if (!TextUtils.isEmpty(f)) {
            settings.setUserAgentString(settings.getUserAgentString() + f);
        }
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
            removeJavascriptInterface("searchBoxJavaBridge_");
        }
        setWebChromeClient(new DeviceShopWebChromeClient());
        setWebViewClient(new DeviceShopWebViewClient());
        setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    MiotStoreWebView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        a();
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        if (webViewClient instanceof DeviceShopWebViewClient) {
            this.f11379a = (DeviceShopWebViewClient) webViewClient;
        }
        super.setWebViewClient(webViewClient);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        if (webChromeClient instanceof DeviceShopWebChromeClient) {
            this.b = (DeviceShopWebChromeClient) webChromeClient;
        }
        super.setWebChromeClient(webChromeClient);
    }

    public static class DeviceShopWebViewClient extends WebViewClient {
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            StoreApiManager.a().b().a(webView, str, str2, str3);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Context context = webView.getContext();
            if (str.startsWith("tel") || str.startsWith("mailto")) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
            if (str.startsWith("tmall") || str.startsWith("taobao")) {
                Uri parse = Uri.parse(str);
                Intent intent = new Intent();
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(parse);
                List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
                if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(parse);
                    context.startActivity(intent2);
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return super.shouldInterceptRequest(webView, str);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (sslErrorHandler == null) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                sslErrorHandler.proceed();
            }
        }

        private boolean a(String str) {
            int lastIndexOf;
            if (str == null || (lastIndexOf = str.lastIndexOf(".")) == -1) {
                return false;
            }
            String substring = str.substring(lastIndexOf);
            for (String equalsIgnoreCase : MiotStoreWebView.VIDEO_TYPE) {
                if (equalsIgnoreCase.equalsIgnoreCase(substring)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class DeviceShopWebChromeClient extends WebChromeClient {
        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            builder.setMessage((CharSequence) str2);
            builder.setPositiveButton((CharSequence) "确定", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.setCancelable(false);
            builder.create().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            builder.setMessage((CharSequence) str2);
            builder.setPositiveButton((CharSequence) "确定", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.confirm();
                }
            });
            builder.setNegativeButton((CharSequence) "取消", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    jsResult.cancel();
                }
            });
            builder.setCancelable(false);
            builder.create().show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            YPDToast.getInstance().toast(webView.getContext(), str2);
            return true;
        }
    }
}
