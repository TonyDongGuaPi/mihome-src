package wendu.webviewjavascriptbridge;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Keep;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.sina.weibo.sdk.constant.WBConstants;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class WVJBWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4256a = "WVJBInterface";
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private String b;
    /* access modifiers changed from: private */
    public JavascriptCloseWindowListener g = null;
    /* access modifiers changed from: private */
    public ArrayList<WVJBMessage> h = null;
    private Map<String, WVJBResponseCallback> i = null;
    /* access modifiers changed from: private */
    public Map<String, WVJBHandler> j = null;
    private long k = 0;
    /* access modifiers changed from: private */
    public boolean l = true;
    private WebChromeClient m = new WebChromeClient() {
        public void onProgressChanged(WebView webView, int i) {
            if (i > 80) {
                try {
                    InputStream open = webView.getContext().getAssets().open("WebViewJavascriptBridge.js");
                    byte[] bArr = new byte[open.available()];
                    open.read(bArr);
                    open.close();
                    WVJBWebView.this.evaluateJavascript(new String(bArr));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (WVJBWebView.this) {
                    if (WVJBWebView.this.h != null) {
                        for (int i2 = 0; i2 < WVJBWebView.this.h.size(); i2++) {
                            WVJBWebView.this.b((WVJBMessage) WVJBWebView.this.h.get(i2));
                        }
                        ArrayList unused = WVJBWebView.this.h = null;
                    }
                }
            }
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onProgressChanged(webView, i);
            } else {
                super.onProgressChanged(webView, i);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onReceivedTitle(webView, str);
            } else {
                super.onReceivedTitle(webView, str);
            }
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onReceivedIcon(webView, bitmap);
            } else {
                super.onReceivedIcon(webView, bitmap);
            }
        }

        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onReceivedTouchIconUrl(webView, str, z);
            } else {
                super.onReceivedTouchIconUrl(webView, str, z);
            }
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onShowCustomView(view, customViewCallback);
            } else {
                super.onShowCustomView(view, customViewCallback);
            }
        }

        @TargetApi(14)
        public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onShowCustomView(view, i, customViewCallback);
            } else {
                super.onShowCustomView(view, i, customViewCallback);
            }
        }

        public void onHideCustomView() {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onHideCustomView();
            } else {
                super.onHideCustomView();
            }
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.onCreateWindow(webView, z, z2, message);
            }
            return super.onCreateWindow(webView, z, z2, message);
        }

        public void onRequestFocus(WebView webView) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onRequestFocus(webView);
            } else {
                super.onRequestFocus(webView);
            }
        }

        public void onCloseWindow(WebView webView) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onCloseWindow(webView);
            } else {
                super.onCloseWindow(webView);
            }
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            if (!WVJBWebView.this.l) {
                jsResult.confirm();
            }
            if (WVJBWebView.this.webChromeClient != null && WVJBWebView.this.webChromeClient.onJsAlert(webView, str, str2, jsResult)) {
                return true;
            }
            new AlertDialog.Builder(WVJBWebView.this.getContext()).setMessage((CharSequence) str2).setCancelable(false).setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    if (WVJBWebView.this.l) {
                        jsResult.confirm();
                    }
                }
            }).create().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, final JsResult jsResult) {
            if (!WVJBWebView.this.l) {
                jsResult.confirm();
            }
            if (WVJBWebView.this.webChromeClient != null && WVJBWebView.this.webChromeClient.onJsConfirm(webView, str, str2, jsResult)) {
                return true;
            }
            AnonymousClass2 r3 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!WVJBWebView.this.l) {
                        return;
                    }
                    if (i == -1) {
                        jsResult.confirm();
                    } else {
                        jsResult.cancel();
                    }
                }
            };
            new AlertDialog.Builder(WVJBWebView.this.getContext()).setMessage((CharSequence) str2).setCancelable(false).setPositiveButton(17039370, (DialogInterface.OnClickListener) r3).setNegativeButton(17039360, (DialogInterface.OnClickListener) r3).show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, final JsPromptResult jsPromptResult) {
            if (Build.VERSION.SDK_INT <= 16) {
                if (str2.equals("_wvjbxx")) {
                    WVJBWebView.this.mainThreadHandler.sendMessage(WVJBWebView.this.mainThreadHandler.obtainMessage(4, str3));
                }
                return true;
            }
            if (!WVJBWebView.this.l) {
                jsPromptResult.confirm();
            }
            if (WVJBWebView.this.webChromeClient != null && WVJBWebView.this.webChromeClient.onJsPrompt(webView, str, str2, str3, jsPromptResult)) {
                return true;
            }
            final EditText editText = new EditText(WVJBWebView.this.getContext());
            editText.setText(str3);
            if (str3 != null) {
                editText.setSelection(str3.length());
            }
            float f = WVJBWebView.this.getContext().getResources().getDisplayMetrics().density;
            AnonymousClass3 r12 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!WVJBWebView.this.l) {
                        return;
                    }
                    if (i == -1) {
                        jsPromptResult.confirm(editText.getText().toString());
                    } else {
                        jsPromptResult.cancel();
                    }
                }
            };
            new AlertDialog.Builder(WVJBWebView.this.getContext()).setTitle((CharSequence) str2).setView((View) editText).setCancelable(false).setPositiveButton(17039370, (DialogInterface.OnClickListener) r12).setNegativeButton(17039360, (DialogInterface.OnClickListener) r12).show();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            int i = (int) (16.0f * f);
            layoutParams.setMargins(i, 0, i, 0);
            layoutParams.gravity = 1;
            editText.setLayoutParams(layoutParams);
            int i2 = (int) (15.0f * f);
            editText.setPadding(i2 - ((int) (f * 5.0f)), i2, i2, i2);
            return true;
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.onJsBeforeUnload(webView, str, str2, jsResult);
            }
            return super.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            } else {
                super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }
            super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onGeolocationPermissionsShowPrompt(str, callback);
            } else {
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }
        }

        public void onGeolocationPermissionsHidePrompt() {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onGeolocationPermissionsHidePrompt();
            } else {
                super.onGeolocationPermissionsHidePrompt();
            }
        }

        @TargetApi(21)
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onPermissionRequest(permissionRequest);
            } else {
                super.onPermissionRequest(permissionRequest);
            }
        }

        @TargetApi(21)
        public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onPermissionRequestCanceled(permissionRequest);
            } else {
                super.onPermissionRequestCanceled(permissionRequest);
            }
        }

        public boolean onJsTimeout() {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.onJsTimeout();
            }
            return super.onJsTimeout();
        }

        public void onConsoleMessage(String str, int i, String str2) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.onConsoleMessage(str, i, str2);
            } else {
                super.onConsoleMessage(str, i, str2);
            }
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        public Bitmap getDefaultVideoPoster() {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.getDefaultVideoPoster();
            }
            return super.getDefaultVideoPoster();
        }

        public View getVideoLoadingProgressView() {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.getVideoLoadingProgressView();
            }
            return super.getVideoLoadingProgressView();
        }

        public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
            if (WVJBWebView.this.webChromeClient != null) {
                WVJBWebView.this.webChromeClient.getVisitedHistory(valueCallback);
            } else {
                super.getVisitedHistory(valueCallback);
            }
        }

        @TargetApi(21)
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (WVJBWebView.this.webChromeClient != null) {
                return WVJBWebView.this.webChromeClient.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }
            return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }
    };
    MyHandler mainThreadHandler = null;
    private WebViewClient n = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (WVJBWebView.this.webViewClient != null) {
                return WVJBWebView.this.webViewClient.shouldOverrideUrlLoading(webView, str);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onPageStarted(webView, str, bitmap);
            } else {
                super.onPageStarted(webView, str, bitmap);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onPageFinished(webView, str);
            } else {
                super.onPageFinished(webView, str);
            }
        }

        public void onLoadResource(WebView webView, String str) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onLoadResource(webView, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @TargetApi(23)
        public void onPageCommitVisible(WebView webView, String str) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onPageCommitVisible(webView, str);
            } else {
                super.onPageCommitVisible(webView, str);
            }
        }

        @Deprecated
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (WVJBWebView.this.webViewClient != null) {
                return WVJBWebView.this.webViewClient.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @TargetApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (WVJBWebView.this.webViewClient != null) {
                return WVJBWebView.this.webViewClient.shouldInterceptRequest(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Deprecated
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onTooManyRedirects(webView, message, message2);
            } else {
                super.onTooManyRedirects(webView, message, message2);
            }
        }

        @Deprecated
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedError(webView, i, str, str2);
            } else {
                super.onReceivedError(webView, i, str, str2);
            }
        }

        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedError(webView, webResourceRequest, webResourceError);
            } else {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        }

        @TargetApi(23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            } else {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onFormResubmission(webView, message, message2);
            } else {
                super.onFormResubmission(webView, message, message2);
            }
        }

        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.doUpdateVisitedHistory(webView, str, z);
            } else {
                super.doUpdateVisitedHistory(webView, str, z);
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @TargetApi(21)
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            } else {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            } else {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (WVJBWebView.this.webViewClient != null) {
                return WVJBWebView.this.webViewClient.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        @Deprecated
        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onUnhandledKeyEvent(webView, keyEvent);
            } else {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        public void onScaleChanged(WebView webView, float f, float f2) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onScaleChanged(webView, f, f2);
            } else {
                super.onScaleChanged(webView, f, f2);
            }
        }

        @TargetApi(12)
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (WVJBWebView.this.webViewClient != null) {
                WVJBWebView.this.webViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            } else {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }
    };
    WebChromeClient webChromeClient;
    WebViewClient webViewClient;

    public interface JavascriptCloseWindowListener {
        boolean a();
    }

    public interface WVJBHandler<T, R> {
        void a(T t, WVJBResponseCallback<R> wVJBResponseCallback);
    }

    public interface WVJBMethodExistCallback {
        void a(boolean z);
    }

    public interface WVJBResponseCallback<T> {
        void a(T t);
    }

    class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<Context> f4268a;

        MyHandler(Context context) {
            super(Looper.getMainLooper());
            this.f4268a = new WeakReference<>(context);
        }

        public void handleMessage(Message message) {
            if (((Context) this.f4268a.get()) != null) {
                switch (message.what) {
                    case 1:
                        WVJBWebView.this.b((String) message.obj);
                        return;
                    case 2:
                        WVJBWebView.super.loadUrl((String) message.obj);
                        return;
                    case 3:
                        RequestInfo requestInfo = (RequestInfo) message.obj;
                        WVJBWebView.super.loadUrl(requestInfo.f4269a, requestInfo.b);
                        return;
                    case 4:
                        WVJBWebView.this.a((String) message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private class RequestInfo {

        /* renamed from: a  reason: collision with root package name */
        String f4269a;
        Map<String, String> b;

        RequestInfo(String str, Map<String, String> map) {
            this.f4269a = str;
            this.b = map;
        }
    }

    private class WVJBMessage {

        /* renamed from: a  reason: collision with root package name */
        Object f4270a;
        String b;
        String c;
        String d;
        Object e;

        private WVJBMessage() {
            this.f4270a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }
    }

    public WVJBWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public WVJBWebView(Context context) {
        super(context);
        init();
    }

    public void disableJavascriptAlertBoxSafetyTimeout(boolean z) {
        this.l = !z;
    }

    public void callHandler(String str) {
        callHandler(str, (Object) null, (WVJBResponseCallback) null);
    }

    public void callHandler(String str, Object obj) {
        callHandler(str, obj, (WVJBResponseCallback) null);
    }

    public <T> void callHandler(String str, Object obj, WVJBResponseCallback<T> wVJBResponseCallback) {
        a(obj, wVJBResponseCallback, str);
    }

    public void hasJavascriptMethod(String str, final WVJBMethodExistCallback wVJBMethodExistCallback) {
        callHandler("_hasJavascriptMethod", str, new WVJBResponseCallback() {
            public void a(Object obj) {
                wVJBMethodExistCallback.a(((Boolean) obj).booleanValue());
            }
        });
    }

    public void setJavascriptCloseWindowListener(JavascriptCloseWindowListener javascriptCloseWindowListener) {
        this.g = javascriptCloseWindowListener;
    }

    public <T, R> void registerHandler(String str, WVJBHandler<T, R> wVJBHandler) {
        if (str != null && str.length() != 0 && wVJBHandler != null) {
            this.j.put(str, wVJBHandler);
        }
    }

    private void a(Object obj, WVJBResponseCallback wVJBResponseCallback, String str) {
        if (obj != null || (str != null && str.length() != 0)) {
            WVJBMessage wVJBMessage = new WVJBMessage();
            if (obj != null) {
                wVJBMessage.f4270a = obj;
            }
            if (wVJBResponseCallback != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("java_cb_");
                long j2 = this.k + 1;
                this.k = j2;
                sb.append(j2);
                String sb2 = sb.toString();
                this.i.put(sb2, wVJBResponseCallback);
                wVJBMessage.b = sb2;
            }
            if (str != null) {
                wVJBMessage.c = str;
            }
            a(wVJBMessage);
        }
    }

    private synchronized void a(WVJBMessage wVJBMessage) {
        if (this.h != null) {
            this.h.add(wVJBMessage);
        } else {
            b(wVJBMessage);
        }
    }

    /* access modifiers changed from: private */
    public void b(WVJBMessage wVJBMessage) {
        evaluateJavascript(String.format("WebViewJavascriptBridge._handleMessageFromJava(%s)", new Object[]{c(wVJBMessage).toString()}));
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        try {
            WVJBMessage a2 = a(new JSONObject(str));
            if (a2.d != null) {
                WVJBResponseCallback remove = this.i.remove(a2.d);
                if (remove != null) {
                    remove.a(a2.e);
                    return;
                }
                return;
            }
            AnonymousClass2 r0 = null;
            if (a2.b != null) {
                final String str2 = a2.b;
                r0 = new WVJBResponseCallback() {
                    public void a(Object obj) {
                        WVJBMessage wVJBMessage = new WVJBMessage();
                        wVJBMessage.d = str2;
                        wVJBMessage.e = obj;
                        WVJBWebView.this.b(wVJBMessage);
                    }
                };
            }
            WVJBHandler wVJBHandler = this.j.get(a2.c);
            if (wVJBHandler != null) {
                wVJBHandler.a(a2.f4270a, r0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private JSONObject c(WVJBMessage wVJBMessage) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (wVJBMessage.b != null) {
                jSONObject.put(WBConstants.F, wVJBMessage.b);
            }
            if (wVJBMessage.f4270a != null) {
                jSONObject.put("data", wVJBMessage.f4270a);
            }
            if (wVJBMessage.c != null) {
                jSONObject.put("handlerName", wVJBMessage.c);
            }
            if (wVJBMessage.d != null) {
                jSONObject.put("responseId", wVJBMessage.d);
            }
            if (wVJBMessage.e != null) {
                jSONObject.put("responseData", wVJBMessage.e);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private WVJBMessage a(JSONObject jSONObject) {
        WVJBMessage wVJBMessage = new WVJBMessage();
        try {
            if (jSONObject.has(WBConstants.F)) {
                wVJBMessage.b = jSONObject.getString(WBConstants.F);
            }
            if (jSONObject.has("data")) {
                wVJBMessage.f4270a = jSONObject.get("data");
            }
            if (jSONObject.has("handlerName")) {
                wVJBMessage.c = jSONObject.getString("handlerName");
            }
            if (jSONObject.has("responseId")) {
                wVJBMessage.d = jSONObject.getString("responseId");
            }
            if (jSONObject.has("responseData")) {
                wVJBMessage.e = jSONObject.get("responseData");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return wVJBMessage;
    }

    /* access modifiers changed from: package-private */
    @Keep
    public void init() {
        this.mainThreadHandler = new MyHandler(getContext());
        this.b = getContext().getFilesDir().getAbsolutePath() + "/webcache";
        this.i = new HashMap();
        this.j = new HashMap();
        this.h = new ArrayList<>();
        WebSettings settings = getSettings();
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true);
            settings.setMixedContentMode(0);
        }
        settings.setAllowFileAccess(false);
        settings.setAppCacheEnabled(false);
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCachePath(this.b);
        settings.setUseWideViewPort(true);
        super.setWebChromeClient(this.m);
        super.setWebViewClient(this.n);
        registerHandler("_hasNativeMethod", new WVJBHandler() {
            public void a(Object obj, WVJBResponseCallback wVJBResponseCallback) {
                wVJBResponseCallback.a(Boolean.valueOf(WVJBWebView.this.j.get(obj) != null));
            }
        });
        registerHandler("_closePage", new WVJBHandler() {
            public void a(Object obj, WVJBResponseCallback wVJBResponseCallback) {
                if (WVJBWebView.this.g == null || WVJBWebView.this.g.a()) {
                    ((Activity) WVJBWebView.this.getContext()).onBackPressed();
                }
            }
        });
        registerHandler("_disableJavascriptAlertBoxSafetyTimeout", new WVJBHandler() {
            public void a(Object obj, WVJBResponseCallback wVJBResponseCallback) {
                WVJBWebView.this.disableJavascriptAlertBoxSafetyTimeout(((Boolean) obj).booleanValue());
            }
        });
        if (Build.VERSION.SDK_INT > 16) {
            super.addJavascriptInterface(new Object() {
                @Keep
                @JavascriptInterface
                public void notice(String str) {
                    WVJBWebView.this.mainThreadHandler.sendMessage(WVJBWebView.this.mainThreadHandler.obtainMessage(4, str));
                }
            }, f4256a);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            super.evaluateJavascript(str, (ValueCallback) null);
            return;
        }
        super.loadUrl("javascript:" + str);
    }

    public void evaluateJavascript(String str) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            b(str);
            return;
        }
        this.mainThreadHandler.sendMessage(this.mainThreadHandler.obtainMessage(1, str));
    }

    public void loadUrl(String str) {
        this.mainThreadHandler.sendMessage(this.mainThreadHandler.obtainMessage(2, str));
    }

    public void loadUrl(String str, Map<String, String> map) {
        this.mainThreadHandler.sendMessage(this.mainThreadHandler.obtainMessage(3, new RequestInfo(str, map)));
    }

    public void setWebChromeClient(WebChromeClient webChromeClient2) {
        this.webChromeClient = webChromeClient2;
    }

    public void setWebViewClient(WebViewClient webViewClient2) {
        this.webViewClient = webViewClient2;
    }
}
