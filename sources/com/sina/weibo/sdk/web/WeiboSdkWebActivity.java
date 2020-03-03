package com.sina.weibo.sdk.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.BaseSsoHandler;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.UIUtils;
import com.sina.weibo.sdk.utils.WbUtils;
import com.sina.weibo.sdk.web.client.AuthWebViewClient;
import com.sina.weibo.sdk.web.client.BaseWebViewClient;
import com.sina.weibo.sdk.web.client.DefaultWebViewClient;
import com.sina.weibo.sdk.web.client.ShareWebViewClient;
import com.sina.weibo.sdk.web.param.AuthWebViewRequestParam;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;
import com.sina.weibo.sdk.web.param.DefaultWebViewRequestParam;
import com.sina.weibo.sdk.web.param.ShareWebViewRequestParam;
import com.sina.weibo.sdk.web.view.LoadingBar;

public class WeiboSdkWebActivity extends Activity implements WebViewRequestCallback {
    public static final String BROWSER_CLOSE_SCHEME = "sinaweibo://browser/close";

    /* renamed from: a  reason: collision with root package name */
    private static final String f8854a = "Close";
    private static final String b = "关闭";
    private static final String c = "关闭";
    private static final String d = "A network error occurs, please tap the button to reload";
    private static final String e = "网络出错啦，请点击按钮重新加载";
    private static final String f = "網路出錯啦，請點擊按鈕重新載入";
    private static final String g = "channel_data_error";
    private static final String h = "重新加载";
    private static final String i = "重新載入";
    private static final String j = "No Title";
    private static final String k = "无标题";
    private static final String l = "無標題";
    private static final String m = "Loading....";
    private static final String n = "加载中....";
    private static final String o = "載入中....";
    private TextView p;
    /* access modifiers changed from: private */
    public TextView q;
    /* access modifiers changed from: private */
    public WebView r;
    /* access modifiers changed from: private */
    public LoadingBar s;
    private Button t;
    private TextView u;
    private LinearLayout v;
    /* access modifiers changed from: private */
    public BaseWebViewRequestParam w;
    /* access modifiers changed from: private */
    public BaseWebViewClient x;
    /* access modifiers changed from: private */
    public int y = 0;

    public void onPageStartedCallBack(WebView webView, String str, Bitmap bitmap) {
    }

    public boolean shouldOverrideUrlLoadingCallBack(WebView webView, String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        LogUtil.b("Share", "startWebActivity");
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(b());
        a();
    }

    private void a() {
        LogUtil.b("Share", "WebActivity.initLoad().start");
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        int i2 = extras.getInt("type", -1);
        if (i2 == -1) {
            finish();
            return;
        }
        switch (i2) {
            case 0:
                this.w = new DefaultWebViewRequestParam();
                this.x = new DefaultWebViewClient(this, this.w);
                break;
            case 1:
                this.w = new ShareWebViewRequestParam(this);
                this.x = new ShareWebViewClient(this, this, this.w);
                break;
            case 2:
                this.w = new AuthWebViewRequestParam();
                this.x = new AuthWebViewClient(this, this, this.w);
                break;
        }
        this.r.setWebViewClient(this.x);
        this.w.d(extras);
        c();
        if (this.w.a()) {
            this.w.a((BaseWebViewRequestParam.ExtraTaskCallback) new BaseWebViewRequestParam.ExtraTaskCallback() {
                public void a(String str) {
                    LogUtil.b("Share", "WebActivity.sharePic.onComplete()");
                    if (WeiboSdkWebActivity.this.a(WeiboSdkWebActivity.this.w.b())) {
                        WeiboSdkWebActivity.this.r.loadUrl(WeiboSdkWebActivity.this.w.b());
                    }
                }

                public void b(String str) {
                    LogUtil.b("Share", "WebActivity.sharePic.onException()");
                    WeiboSdkWebActivity.this.x.a(WeiboSdkWebActivity.this, "pic upload error");
                    WeiboSdkWebActivity.this.finish();
                }
            });
        } else {
            String b2 = this.w.b();
            if (a(b2)) {
                this.r.loadUrl(b2);
            }
        }
        LogUtil.b("Share", "WebActivity.initLoad().end");
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.startsWith(ShareWebViewRequestParam.b) || str.startsWith(BaseSsoHandler.OAUTH2_BASE_URL);
        }
        return false;
    }

    private View b() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(-1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        this.p = new TextView(this);
        this.p.setTextSize(17.0f);
        this.p.setTextColor(ResourceManager.a(-32256, 1728020992));
        this.p.setText(ResourceManager.a(this, "Close", "关闭", "关闭"));
        this.p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WeiboSdkWebActivity.this.x.a();
                WeiboSdkWebActivity.this.d();
            }
        });
        this.q = new TextView(this);
        this.q.setTextSize(18.0f);
        this.q.setTextColor(-11382190);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        this.p.setPadding(UIUtils.a(10, this), 0, UIUtils.a(10, this), 0);
        layoutParams2.addRule(13);
        relativeLayout2.addView(this.p, layoutParams);
        relativeLayout2.addView(this.q, layoutParams2);
        relativeLayout.addView(relativeLayout2, new RelativeLayout.LayoutParams(-1, UIUtils.a(55, this)));
        this.r = new WebView(getApplicationContext());
        this.r.getSettings().setSavePassword(false);
        this.r.getSettings().setAllowFileAccess(false);
        this.r.getSettings().setAllowContentAccess(false);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.topMargin = UIUtils.a(55, this);
        relativeLayout.addView(this.r, layoutParams3);
        this.s = new LoadingBar(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, UIUtils.a(3, this));
        layoutParams4.topMargin = UIUtils.a(55, this);
        relativeLayout.addView(this.s, layoutParams4);
        View view = new View(this);
        view.setBackgroundResource(getResources().getIdentifier("weibosdk_common_shadow_top", "drawable", getPackageName()));
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, UIUtils.a(3, this));
        layoutParams5.topMargin = UIUtils.a(55, this);
        relativeLayout.addView(view, layoutParams5);
        this.v = new LinearLayout(this);
        this.v.setOrientation(1);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(getResources().getIdentifier("weibosdk_empty_failed", "drawable", getPackageName()));
        this.v.addView(imageView);
        this.u = new TextView(this);
        this.u.setTextSize(14.0f);
        this.u.setTextColor(-4342339);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams6.topMargin = UIUtils.a(18, this);
        layoutParams6.bottomMargin = UIUtils.a(20, this);
        this.v.addView(this.u, layoutParams6);
        this.t = new Button(this);
        this.t.setTextSize(16.0f);
        this.t.setTextColor(-8882056);
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(UIUtils.a(142, this), UIUtils.a(46, this));
        layoutParams7.gravity = 17;
        this.v.addView(this.t, layoutParams7);
        this.t.setBackgroundResource(getResources().getIdentifier("retry_btn_selector", "drawable", getPackageName()));
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(13);
        relativeLayout.addView(this.v, layoutParams8);
        this.v.setVisibility(8);
        this.r.setWebChromeClient(new MyChromeClient());
        this.t.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = WeiboSdkWebActivity.this.y = 0;
                WeiboSdkWebActivity.this.f();
                WeiboSdkWebActivity.this.r.reload();
            }
        });
        this.u.setText(ResourceManager.a(this, d, e, f));
        this.t.setText(ResourceManager.a(this, g, h, i));
        return relativeLayout;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void c() {
        if (!TextUtils.isEmpty(this.w.d().getSpecifyTitle())) {
            this.q.setText(this.w.d().getSpecifyTitle());
        }
        this.r.getSettings().setJavaScriptEnabled(true);
        this.r.getSettings().setSavePassword(false);
        this.r.getSettings().setUserAgentString(WbUtils.a(this, this.w.d().getAuthInfo().getAppKey()));
        this.r.requestFocus();
        this.r.setScrollBarStyle(0);
        removeJavascriptInterface(this.r, "searchBoxJavaBridge_");
        removeJavascriptInterface(this.r, "accessibility");
        removeJavascriptInterface(this.r, "accessibilityTraversal");
        if (Build.VERSION.SDK_INT >= 21) {
            this.r.getSettings().setMixedContentMode(2);
        }
    }

    public static void removeJavascriptInterface(WebView webView, String str) {
        try {
            WebView.class.getDeclaredMethod("removeJavascriptInterface", new Class[]{String.class}).invoke(webView, new Object[]{str});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        finish();
    }

    private class MyChromeClient extends WebChromeClient {
        private MyChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            WeiboSdkWebActivity.this.s.drawProgress(i);
            if (i == 100) {
                WeiboSdkWebActivity.this.s.setVisibility(4);
            } else {
                WeiboSdkWebActivity.this.s.setVisibility(0);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(WeiboSdkWebActivity.this.w.d().getSpecifyTitle())) {
                WeiboSdkWebActivity.this.q.setText(str);
            }
        }
    }

    private void e() {
        this.v.setVisibility(0);
        this.r.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void f() {
        this.v.setVisibility(8);
        this.r.setVisibility(0);
    }

    public void onPageFinishedCallBack(WebView webView, String str) {
        if (this.y == -1) {
            e();
        } else {
            f();
        }
    }

    public void onReceivedErrorCallBack(WebView webView, int i2, String str, String str2) {
        String url = webView.getUrl();
        try {
            if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(str2)) {
                Uri parse = Uri.parse(url);
                Uri parse2 = Uri.parse(str2);
                if (parse.getHost().equals(parse2.getHost()) && parse.getScheme().equals(parse2.getScheme())) {
                    this.y = -1;
                }
            }
        } catch (Exception unused) {
        }
    }

    public void onReceivedSslErrorCallBack(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("你访问的连接可能存在隐患，是否继续访问");
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                sslErrorHandler.proceed();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                sslErrorHandler.cancel();
            }
        });
        builder.create().show();
    }

    public void closePage() {
        finish();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (this.x.b()) {
                return true;
            }
            if (this.r.canGoBack()) {
                this.r.goBack();
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }
}
