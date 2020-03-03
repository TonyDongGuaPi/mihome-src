package com.mi.global.bbs.view.webview;

import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.mi.global.shop.activity.WebActivity;
import com.mi.log.LogUtil;
import com.mi.util.Device;
import java.io.File;
import java.util.ArrayList;

public class WebViewHelper {
    public static final String TAG = "WebViewHelper";
    private static ArrayList<String> loadHistoryUrls = new ArrayList<>();
    public static String mUrl;

    public static void addHistoryUrl(String str) {
        loadHistoryUrls.add(str);
    }

    public static boolean canGoBack() {
        return loadHistoryUrls.size() > 0;
    }

    public static void goBackAndLoad(WebView webView) {
        if (loadHistoryUrls.size() > 0) {
            loadHistoryUrls.remove(loadHistoryUrls.size() - 1);
            webView.loadUrl(loadHistoryUrls.get(loadHistoryUrls.size() - 1));
        }
    }

    public static void clearHistory() {
        loadHistoryUrls.clear();
    }

    public static void initWebSetting(WebView webView) {
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            settings.setJavaScriptEnabled(true);
            settings.setTextZoom(100);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setCacheMode(-1);
            String userAgentString = settings.getUserAgentString();
            settings.setUserAgentString(userAgentString + " XiaoMi/GlobalShop/" + Device.s);
            configureAppCache(webView);
            configureDOMStorage(webView);
            settings.setBuiltInZoomControls(false);
            webView.addJavascriptInterface(new WebEvent(webView), "WE");
        }
    }

    public static void setUrl(String str) {
        mUrl = str;
    }

    public static void clearHistory(WebView webView) {
        webView.clearHistory();
    }

    public static void clearCache(WebView webView) {
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
    }

    protected static void configureAppCache(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setAppCacheEnabled(true);
        String path = webView.getContext().getApplicationContext().getDir("cache", 0).getPath();
        LogUtil.b("app cacheDir:" + path);
        ensureExistence(path);
        settings.setAppCachePath(path);
    }

    protected static void configureDOMStorage(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        String path = webView.getContext().getApplicationContext().getDir("database", 0).getPath();
        LogUtil.b("dom storageDir:" + path);
        settings.setDatabasePath(path);
    }

    private static void ensureExistence(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
