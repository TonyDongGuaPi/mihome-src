package com.xiaomi.smarthome.framework.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.shop.utils.ShopLauncher;

public class WebShellActivity extends BaseActivity {
    public static final String ARGS_KEY_URL = "url";
    public static final String TITLE_ID = "title_id";

    /* renamed from: a  reason: collision with root package name */
    private static final String f17898a = "http://";
    private static final String b = "https://";
    private static final String c = "mihome://";
    /* access modifiers changed from: private */
    public String d;
    private WebViewClient e = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            WebShellActivity.this.mTitle.setText(R.string.web_shell_loading);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!str.startsWith("http://") && !str.startsWith("https://")) {
                Uri parse = Uri.parse(str);
                if (PageUrl.a(parse) && ShopLauncher.a(WebShellActivity.this.mContext, str, true)) {
                    return true;
                }
                if (str.startsWith("tel") || str.startsWith("mailto")) {
                    WebShellActivity.this.mContext.startActivity(new Intent("android.intent.action.VIEW", parse));
                    return true;
                }
                if (str.startsWith("xmbbsapp://bbs.xiaomi.cn")) {
                    OpenExternalBrowserCompat.a(WebShellActivity.this.mContext, str);
                }
                return super.shouldOverrideUrlLoading(webView, str);
            } else if (WebShellActivity.this.d == null || str == null || !str.equals(WebShellActivity.this.d)) {
                webView.loadUrl(str);
                String unused = WebShellActivity.this.d = str;
                return true;
            } else {
                WebShellActivity.this.mWebView.goBack();
                return true;
            }
        }
    };
    private WebChromeClient f = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(WebShellActivity.this.mTitleStr)) {
                WebShellActivity.this.mTitle.setText(str);
            } else {
                WebShellActivity.this.mTitle.setText(WebShellActivity.this.mTitleStr);
            }
        }
    };
    Context mContext;
    TextView mTitle;
    String mTitleStr;
    String mUrl;
    WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setContentView(R.layout.web_shell_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebShellActivity.this.onBackPressed();
            }
        });
        this.mTitle = (TextView) findViewById(R.id.module_a_3_return_title);
        this.mWebView = (WebView) findViewById(R.id.webview);
        this.mUrl = getIntent().getStringExtra("url");
        int intExtra = getIntent().getIntExtra(TITLE_ID, -1);
        if (intExtra != -1) {
            this.mTitleStr = getString(intExtra);
        }
        initWebView();
        if (!TextUtils.isEmpty(this.mUrl)) {
            this.mWebView.loadUrl(this.mUrl);
        } else {
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public void initWebView() {
        if (Build.VERSION.SDK_INT >= 11) {
            this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        }
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setFocusable(true);
        this.mWebView.setFocusableInTouchMode(true);
        this.mWebView.requestFocusFromTouch();
        this.mWebView.requestFocus();
        this.mWebView.setVisibility(0);
        this.mWebView.setWebViewClient(this.e);
        this.mWebView.setWebChromeClient(this.f);
        this.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                try {
                    intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                    WebShellActivity.this.mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public String getActivityName() {
        return getClass().getName() + Operators.BRACKET_START_STR + this.mUrl + Operators.BRACKET_END_STR;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mWebView.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mWebView.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mWebView.destroy();
    }

    public void onBackPressed() {
        if (this.mWebView == null) {
            super.onBackPressed();
        } else if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: package-private */
    public void doGoBack() {
        if (this.mWebView == null || !this.mWebView.canGoBack()) {
            super.onBackPressed();
        } else {
            this.mWebView.goBack();
        }
    }

    /* access modifiers changed from: package-private */
    public void doFinishPage() {
        finish();
    }

    /* access modifiers changed from: package-private */
    public void injectJS(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, (ValueCallback) null);
            return;
        }
        webView.loadUrl("javascript:" + str);
    }
}
