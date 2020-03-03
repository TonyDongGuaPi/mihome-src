package com.xiaomi.youpin.login.ui.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.other.common.TitleBarUtil;
import com.xiaomi.youpin.login.ui.baseui.BaseActivity;
import com.xiaomi.youpin.login.view.LoginCommonTitleBar;

public class LoginWebActivity extends BaseActivity {
    public static final String COMMON_WEB_TITLE = "common_web_title";
    public static final String COMMON_WEB_URL = "common_web_url";
    protected LoginCustomWebChromeClient mLoginCustomWebChromeClient;
    protected String mTitle;
    protected String mUrl;
    protected ProgressBar vProgressBar;
    protected LoginCommonTitleBar vTitleBar;
    protected WebView vWebView;

    /* access modifiers changed from: protected */
    public void onWebViewFinish() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_common_web_activity);
        if (!parseIntent(getIntent())) {
            onWebViewFinish();
            finish();
            return;
        }
        initView();
        initWebView();
        this.vWebView.loadUrl(this.mUrl);
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.vTitleBar = (LoginCommonTitleBar) findViewById(R.id.title_bar);
        TitleBarUtil.a((View) this.vTitleBar);
        this.vTitleBar.setOnBackClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginWebActivity.this.onWebViewFinish();
                LoginWebActivity.this.finish();
            }
        });
        this.vTitleBar.setCenterText(this.mTitle);
        this.vWebView = (WebView) findViewById(R.id.common_web_view);
        this.vProgressBar = (ProgressBar) findViewById(R.id.common_web_loading);
    }

    /* access modifiers changed from: protected */
    public void initWebView() {
        initWebSettings(this.vWebView.getSettings());
        this.vWebView.setWebViewClient(getWebViewClient());
        this.mLoginCustomWebChromeClient = new LoginCustomWebChromeClient(this) {
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                if (!TextUtils.isEmpty(str)) {
                    LoginWebActivity.this.vTitleBar.setCenterText(str);
                }
            }

            public void onProgressChanged(WebView webView, int i) {
                LoginWebActivity.this.vProgressBar.setProgress(i);
            }

            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        };
        this.vWebView.setWebChromeClient(this.mLoginCustomWebChromeClient);
        this.vWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    LoginWebActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
    }

    public void onBackPressed() {
        if (this.vWebView == null || !this.vWebView.canGoBack()) {
            onWebViewFinish();
            finish();
            return;
        }
        this.vWebView.goBack();
    }

    /* access modifiers changed from: protected */
    public boolean parseIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        this.mUrl = intent.getStringExtra("common_web_url");
        if (TextUtils.isEmpty(this.mUrl)) {
            return false;
        }
        this.mTitle = intent.getStringExtra("common_web_title");
        return true;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void initWebSettings(WebSettings webSettings) {
        webSettings.setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT < 19) {
            webSettings.setDatabasePath(getFilesDir().getPath() + this.vWebView.getContext().getPackageName() + "/databases/");
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setBlockNetworkImage(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mLoginCustomWebChromeClient != null && 1001 == i) {
            this.mLoginCustomWebChromeClient.a(i, i2, intent);
        }
    }
}
