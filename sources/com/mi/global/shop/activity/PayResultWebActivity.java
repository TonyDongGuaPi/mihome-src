package com.mi.global.shop.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.mi.global.shop.R;
import com.mi.global.shop.webview.WebViewHelper;
import com.mi.global.shop.widget.BaseWebView;
import com.mi.util.MiToast;
import com.mobikwik.sdk.lib.Constants;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;

public class PayResultWebActivity extends Activity {
    public static final int DEFAULT_RESULT = 0;
    public static final int MOBIKWIK_RESULT = 1;
    public static final int OTHER_RESULT = 2;

    /* renamed from: a  reason: collision with root package name */
    private BaseWebView f5410a;
    private int b = 0;
    private ProgressDialog c;
    protected ProgressBar mProgressBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            setContentView(R.layout.shop_mobikwik_web_activity);
            String stringExtra = getIntent().getStringExtra("htmlString");
            String stringExtra2 = getIntent().getStringExtra("url");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.b = 1;
            }
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.b = 2;
            }
            if (this.b == 0) {
                finish();
            }
            initView();
            initProgressDialog();
            if (this.b == 1) {
                this.f5410a.loadDataWithBaseURL("about:blank", stringExtra, NanoHTTPD.c, "utf-8", (String) null);
            }
            if (this.b == 2) {
                this.f5410a.loadUrl(stringExtra2);
            }
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains("MissingWebViewPackageException")) {
                MiToast.a((Context) this, (CharSequence) getResources().getString(R.string.loading_error), 0);
            } else {
                MiToast.a((Context) this, (CharSequence) getResources().getString(R.string.webview_tips_uploaing), 0);
            }
            finish();
        }
    }

    public void initView() {
        this.f5410a = (BaseWebView) findViewById(R.id.browser);
        this.mProgressBar = (ProgressBar) findViewById(R.id.browser_progress_bar);
        this.f5410a.setWebViewClient(new InnerWebViewClient());
        this.f5410a.setWebChromeClient(new InnerWebChromeClient());
        WebViewHelper.a((WebView) this.f5410a);
        if (this.b == 1) {
            this.f5410a.addJavascriptInterface(new MobWebEvent(this.f5410a), Constants.LABEL_MERCHANT_NAME);
        }
        if (this.b == 2) {
            this.f5410a.addJavascriptInterface(new MobWebEvent(this.f5410a), "PayU");
        }
    }

    private static class MobWebEvent {
        WebView mWebView;

        MobWebEvent(WebView webView) {
            this.mWebView = webView;
        }

        @JavascriptInterface
        public void onSuccess() {
            onSuccess("");
        }

        @JavascriptInterface
        public void onSuccess(final String str) {
            ((Activity) this.mWebView.getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent();
                    intent.putExtra("result", str);
                    ((Activity) MobWebEvent.this.mWebView.getContext()).setResult(-1, intent);
                    ((Activity) MobWebEvent.this.mWebView.getContext()).finish();
                }
            });
        }

        @JavascriptInterface
        public void onFailure() {
            onFailure("");
        }

        @JavascriptInterface
        public void onFailure(final String str) {
            ((Activity) this.mWebView.getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent();
                    intent.putExtra("result", str);
                    ((Activity) MobWebEvent.this.mWebView.getContext()).setResult(0, intent);
                    ((Activity) MobWebEvent.this.mWebView.getContext()).finish();
                }
            });
        }
    }

    private class InnerWebViewClient extends WebViewClient {
        private InnerWebViewClient() {
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            PayResultWebActivity.this.showLoading();
            PayResultWebActivity.this.mProgressBar.setVisibility(0);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    private class InnerWebChromeClient extends WebChromeClient {
        private InnerWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (PayResultWebActivity.this.mProgressBar != null) {
                PayResultWebActivity.this.mProgressBar.setProgress(i);
                if (i == 100) {
                    PayResultWebActivity.this.hideLoading();
                    PayResultWebActivity.this.mProgressBar.setVisibility(4);
                }
            }
        }
    }

    public void initProgressDialog() {
        this.c = new ProgressDialog(this);
        this.c.setMessage(getString(R.string.please_wait));
        this.c.setIndeterminate(true);
        this.c.setCancelable(false);
        this.c.setCanceledOnTouchOutside(false);
    }

    public void showLoading() {
        if (BaseActivity.isActivityAlive(this)) {
            if (this.c != null) {
                this.c.show();
                return;
            }
            initProgressDialog();
            this.c.show();
        }
    }

    public void hideLoading() {
        if (BaseActivity.isActivityAlive(this) && this.c != null && this.c.isShowing()) {
            this.c.dismiss();
        }
    }
}
