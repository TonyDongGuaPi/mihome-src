package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;

class SystemWebChromeClient extends WebChromeClient {

    /* renamed from: a  reason: collision with root package name */
    private WebView f9082a;
    private WebChromeClient b;

    private static class a implements ConsoleMessage {

        /* renamed from: a  reason: collision with root package name */
        private ConsoleMessage.MessageLevel f9083a;
        private String b;
        private String c;
        private int d;

        a(android.webkit.ConsoleMessage consoleMessage) {
            this.f9083a = ConsoleMessage.MessageLevel.valueOf(consoleMessage.messageLevel().name());
            this.b = consoleMessage.message();
            this.c = consoleMessage.sourceId();
            this.d = consoleMessage.lineNumber();
        }

        a(String str, String str2, int i) {
            this.f9083a = ConsoleMessage.MessageLevel.LOG;
            this.b = str;
            this.c = str2;
            this.d = i;
        }

        public int lineNumber() {
            return this.d;
        }

        public String message() {
            return this.b;
        }

        public ConsoleMessage.MessageLevel messageLevel() {
            return this.f9083a;
        }

        public String sourceId() {
            return this.c;
        }
    }

    class b implements IX5WebChromeClient.CustomViewCallback {

        /* renamed from: a  reason: collision with root package name */
        WebChromeClient.CustomViewCallback f9084a;

        b(WebChromeClient.CustomViewCallback customViewCallback) {
            this.f9084a = customViewCallback;
        }

        public void onCustomViewHidden() {
            this.f9084a.onCustomViewHidden();
        }
    }

    class c implements GeolocationPermissionsCallback {

        /* renamed from: a  reason: collision with root package name */
        GeolocationPermissions.Callback f9085a;

        c(GeolocationPermissions.Callback callback) {
            this.f9085a = callback;
        }

        public void invoke(String str, boolean z, boolean z2) {
            this.f9085a.invoke(str, z, z2);
        }
    }

    private class d implements JsPromptResult {

        /* renamed from: a  reason: collision with root package name */
        android.webkit.JsPromptResult f9086a;

        d(android.webkit.JsPromptResult jsPromptResult) {
            this.f9086a = jsPromptResult;
        }

        public void cancel() {
            this.f9086a.cancel();
        }

        public void confirm() {
            this.f9086a.confirm();
        }

        public void confirm(String str) {
            this.f9086a.confirm(str);
        }
    }

    private class e implements JsResult {

        /* renamed from: a  reason: collision with root package name */
        android.webkit.JsResult f9087a;

        e(android.webkit.JsResult jsResult) {
            this.f9087a = jsResult;
        }

        public void cancel() {
            this.f9087a.cancel();
        }

        public void confirm() {
            this.f9087a.confirm();
        }
    }

    class f implements WebStorage.QuotaUpdater {

        /* renamed from: a  reason: collision with root package name */
        WebStorage.QuotaUpdater f9088a;

        f(WebStorage.QuotaUpdater quotaUpdater) {
            this.f9088a = quotaUpdater;
        }

        public void updateQuota(long j) {
            this.f9088a.updateQuota(j);
        }
    }

    SystemWebChromeClient(WebView webView, WebChromeClient webChromeClient) {
        this.f9082a = webView;
        this.b = webChromeClient;
    }

    @TargetApi(7)
    public Bitmap getDefaultVideoPoster() {
        Bitmap defaultVideoPoster = this.b.getDefaultVideoPoster();
        if (defaultVideoPoster == null) {
            try {
                if (Build.VERSION.SDK_INT >= 24) {
                    return BitmapFactory.decodeResource(this.f9082a.getResources(), 17301540);
                }
            } catch (Exception unused) {
            }
        }
        return defaultVideoPoster;
    }

    @TargetApi(7)
    public View getVideoLoadingProgressView() {
        return this.b.getVideoLoadingProgressView();
    }

    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        this.b.getVisitedHistory(new y(this, valueCallback));
    }

    public void onCloseWindow(WebView webView) {
        this.f9082a.a(webView);
        this.b.onCloseWindow(this.f9082a);
    }

    public void onConsoleMessage(String str, int i, String str2) {
        this.b.onConsoleMessage(new a(str, str2, i));
    }

    public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
        return this.b.onConsoleMessage(new a(consoleMessage));
    }

    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView webView2 = this.f9082a;
        webView2.getClass();
        WebView.WebViewTransport webViewTransport = new WebView.WebViewTransport();
        Message obtain = Message.obtain(message.getTarget(), new z(this, webViewTransport, message));
        obtain.obj = webViewTransport;
        return this.b.onCreateWindow(this.f9082a, z, z2, obtain);
    }

    @TargetApi(5)
    @Deprecated
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        this.b.onExceededDatabaseQuota(str, str2, j, j2, j3, new f(quotaUpdater));
    }

    @TargetApi(5)
    public void onGeolocationPermissionsHidePrompt() {
        this.b.onGeolocationPermissionsHidePrompt();
    }

    @TargetApi(5)
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        this.b.onGeolocationPermissionsShowPrompt(str, new c(callback));
    }

    @TargetApi(7)
    public void onHideCustomView() {
        this.b.onHideCustomView();
    }

    public boolean onJsAlert(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.f9082a.a(webView);
        return this.b.onJsAlert(this.f9082a, str, str2, new e(jsResult));
    }

    public boolean onJsBeforeUnload(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.f9082a.a(webView);
        return this.b.onJsBeforeUnload(this.f9082a, str, str2, new e(jsResult));
    }

    public boolean onJsConfirm(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.f9082a.a(webView);
        return this.b.onJsConfirm(this.f9082a, str, str2, new e(jsResult));
    }

    public boolean onJsPrompt(android.webkit.WebView webView, String str, String str2, String str3, android.webkit.JsPromptResult jsPromptResult) {
        this.f9082a.a(webView);
        return this.b.onJsPrompt(this.f9082a, str, str2, str3, new d(jsPromptResult));
    }

    @TargetApi(7)
    public boolean onJsTimeout() {
        return this.b.onJsTimeout();
    }

    public void onProgressChanged(android.webkit.WebView webView, int i) {
        this.f9082a.a(webView);
        this.b.onProgressChanged(this.f9082a, i);
    }

    @TargetApi(7)
    @Deprecated
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        this.b.onReachedMaxAppCacheSize(j, j2, new f(quotaUpdater));
    }

    public void onReceivedIcon(android.webkit.WebView webView, Bitmap bitmap) {
        this.f9082a.a(webView);
        this.b.onReceivedIcon(this.f9082a, bitmap);
    }

    public void onReceivedTitle(android.webkit.WebView webView, String str) {
        this.f9082a.a(webView);
        this.b.onReceivedTitle(this.f9082a, str);
    }

    @TargetApi(7)
    public void onReceivedTouchIconUrl(android.webkit.WebView webView, String str, boolean z) {
        this.f9082a.a(webView);
        this.b.onReceivedTouchIconUrl(this.f9082a, str, z);
    }

    public void onRequestFocus(android.webkit.WebView webView) {
        this.f9082a.a(webView);
        this.b.onRequestFocus(this.f9082a);
    }

    @TargetApi(14)
    @Deprecated
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b.onShowCustomView(view, i, new b(customViewCallback));
    }

    @TargetApi(7)
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b.onShowCustomView(view, new b(customViewCallback));
    }

    public boolean onShowFileChooser(android.webkit.WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        ab abVar = new ab(this, valueCallback);
        ac acVar = new ac(this, fileChooserParams);
        this.f9082a.a(webView);
        return this.b.onShowFileChooser(this.f9082a, abVar, acVar);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, (String) null, (String) null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooser(valueCallback, str, (String) null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        this.b.openFileChooser(new aa(this, valueCallback), str, str2);
    }

    public void setupAutoFill(Message message) {
    }
}
