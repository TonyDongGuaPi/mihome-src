package com.xiaomi.jr.mipay.codepay.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.mi.global.shop.util.ConnectionHelper;
import com.miui.supportlite.app.Activity;
import com.qti.location.sdk.IZatTestService;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.AccountEnvironment;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.ui.CodePayWebActivity;
import com.xiaomi.jr.mipay.common.util.AsyncTaskExecutor;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class CodePayWebActivity extends Activity {
    public static final String KEY_START_LOADING = "start_loading";
    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    protected static final String MIPAY_JS_INTERFACE = "Mipay";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Handler f10929a = new Handler(Looper.getMainLooper());
    protected ProgressBar mProgressBar;
    protected String mUrl;
    protected WebView mWebView;

    protected static class MipayJsInterface {

        /* renamed from: a  reason: collision with root package name */
        private static final String f10931a = "MipayJsInterface";
        private WeakReference<CodePayWebActivity> b;

        public MipayJsInterface(CodePayWebActivity codePayWebActivity) {
            this.b = new WeakReference<>(codePayWebActivity);
        }

        @JavascriptInterface
        public void finish() {
            if (this.b != null && this.b.get() != null) {
                ((CodePayWebActivity) this.b.get()).finish();
            }
        }

        @JavascriptInterface
        public void setResult(String str) {
            if (this.b != null && this.b.get() != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("code");
                    String optString = jSONObject.optString("message");
                    String optString2 = jSONObject.optString("result");
                    Intent intent = new Intent();
                    intent.putExtra("code", i);
                    intent.putExtra("message", optString);
                    intent.putExtras(a(new JSONObject(optString2)));
                } catch (JSONException e) {
                    MifiLog.e(f10931a, "setResult failed", e);
                }
            }
        }

        public static Bundle a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            Bundle bundle = new Bundle();
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    Object obj = jSONObject.get(next);
                    if (obj instanceof Integer) {
                        bundle.putString(next, obj.toString());
                    } else if (obj instanceof Boolean) {
                        bundle.putBoolean(next, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof String) {
                        bundle.putString(next, (String) obj);
                    } else {
                        bundle.putString(next, obj.toString());
                    }
                }
                return bundle;
            } catch (JSONException e) {
                MifiLog.e(f10931a, "convertJson2Bundle failed", e);
                return null;
            }
        }
    }

    protected class CodePayWebViewClient extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        private final String f10930a;

        /* access modifiers changed from: protected */
        public boolean a(String str) {
            return false;
        }

        protected CodePayWebViewClient() {
            StringBuilder sb = new StringBuilder();
            sb.append(AccountEnvironment.f1407a ? "http://account.preview.n.xiaomi.net/" : ConnectionHelper.bu);
            sb.append("pass/serviceLogin");
            this.f10930a = sb.toString();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (CodePayWebActivity.this.b(webView) || a(str)) {
                return true;
            }
            webView.loadUrl(str);
            return true;
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (!CodePayWebActivity.this.b(webView)) {
                AsyncTaskExecutor.a(new Runnable(str3, webView) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ WebView f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        CodePayWebActivity.CodePayWebViewClient.this.a(this.f$1, this.f$2);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(String str, WebView webView) {
            CodePayWebActivity.this.f10929a.post(new Runnable(webView, b(str)) {
                private final /* synthetic */ WebView f$0;
                private final /* synthetic */ String f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void run() {
                    this.f$0.loadUrl(this.f$1);
                }
            });
        }

        private String b(String str) {
            try {
                return XiaomiAccountManager.j().a("weblogin:" + str).getResult(30, TimeUnit.SECONDS).getString("serviceToken");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            CodePayWebActivity.this.showLoading(true);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!c(str) && webView.getProgress() == 100) {
                CodePayWebActivity.this.showLoading(false);
            }
        }

        private boolean c(String str) {
            return str.startsWith(this.f10930a);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jr_mipay_web_activity);
        this.mWebView = (WebView) findViewById(R.id.web_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress);
        a();
        setTitle(getIntent().getStringExtra("title"));
        this.mUrl = getIntent().getStringExtra("url");
        if (getIntent().getBooleanExtra(KEY_START_LOADING, true)) {
            this.mWebView.loadUrl(this.mUrl);
        }
    }

    private void a() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setUserAgentString("XiaoMi/MiuiBrowser/4.3 " + settings.getUserAgentString());
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(IZatTestService.y);
        this.mWebView.setWebViewClient(new CodePayWebViewClient());
        this.mWebView.addJavascriptInterface(new MipayJsInterface(this), MIPAY_JS_INTERFACE);
        a(this.mWebView);
    }

    private void a(WebView webView) {
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setSavePassword(false);
    }

    /* access modifiers changed from: private */
    public boolean b(WebView webView) {
        if (!webView.canGoForward()) {
            return false;
        }
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        finish();
        return true;
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void showLoading(boolean z) {
        int i = 8;
        this.mProgressBar.setVisibility(z ? 0 : 8);
        WebView webView = this.mWebView;
        if (!z) {
            i = 0;
        }
        webView.setVisibility(i);
    }
}
