package com.xiaomi.smarthome.framework.webview;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ImageSaveHelper;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.screenshot.Screenshot;
import com.xiaomi.smarthome.screenshot.callback.ScreenshotListener;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;
import org.json.JSONObject;

public class ScreenshotWebViewActivity extends BaseActivity {
    public static final String ARGS_KEY_NAV_ENABLE = "nav_enable";
    public static final String ARGS_KEY_TITLE = "title";
    public static final String ARGS_KEY_URL = "url";
    static final String TAG = "ScreenshotWebViewActivity";

    /* renamed from: a  reason: collision with root package name */
    private static final int f17876a = 1;
    static String sUserAgent;
    private ImageView b;
    /* access modifiers changed from: private */
    public Runnable c = new Runnable() {
        public void run() {
            if (ScreenshotWebViewActivity.this.isValid() && !ScreenshotWebViewActivity.this.mScreenshotTaken) {
                ScreenshotWebViewActivity.this.a();
                ScreenshotWebViewActivity.this.mScreenshotTaken = true;
            }
        }
    };
    private WebViewClient d = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Miio.h(ScreenshotWebViewActivity.TAG, "onPageStarted url: " + str);
            if (ScreenshotWebViewActivity.this.mIsGoBack) {
                if (ScreenshotWebViewActivity.this.mLastBackPath.equals(ScreenshotWebViewActivity.this.mStartPath)) {
                    Miio.h(ScreenshotWebViewActivity.TAG, "onPageStarted url finish:" + ScreenshotWebViewActivity.this.mStartPath);
                    ScreenshotWebViewActivity.this.finish();
                    return;
                } else if (str.startsWith("https://account.xiaomi.com/pass/serviceLogin")) {
                    ScreenshotWebViewActivity.this.mIsGoBack = false;
                    if (ScreenshotWebViewActivity.this.mWebView != null && ScreenshotWebViewActivity.this.mWebView.canGoBack()) {
                        ScreenshotWebViewActivity.this.mWebView.goBack();
                        return;
                    }
                    return;
                }
            }
            super.onPageStarted(webView, str, bitmap);
            ScreenshotWebViewActivity.this.mIsGoBack = false;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Miio.h(ScreenshotWebViewActivity.TAG, "shouldOverrideUrlLoading url: " + str);
            ScreenshotWebViewActivity.this.mIsGoBack = false;
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            ScreenshotWebViewActivity.this.mIsGoBack = false;
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        public void onPageFinished(WebView webView, String str) {
            Miio.h(ScreenshotWebViewActivity.TAG, "onPageFinished url: " + str);
            ScreenshotWebViewActivity.this.mLastBackPath = ScreenshotWebViewActivity.this.getPath(str);
            ScreenshotWebViewActivity.this.mIsGoBack = false;
            ScreenshotWebViewActivity.this.mWebviewClientFlagFinished = true;
            if (ScreenshotWebViewActivity.this.mWebFlagFinished) {
                ScreenshotWebViewActivity.this.mHandler.removeCallbacks(ScreenshotWebViewActivity.this.c);
                ScreenshotWebViewActivity.this.mHandler.postDelayed(ScreenshotWebViewActivity.this.c, 0);
            }
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if ("com.xiaomi".equals(str)) {
                try {
                    MiAccountManager miAccountManager = MiAccountManager.get(ScreenshotWebViewActivity.this.getApplicationContext());
                    Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                    if (accountsByType.length != 0) {
                        miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) ScreenshotWebViewActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
                            public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                                String str = null;
                                if (accountManagerFuture != null) {
                                    try {
                                        Bundle result = accountManagerFuture.getResult();
                                        if (result != null) {
                                            str = result.getString("authtoken");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (str == null) {
                                    Miio.h(ScreenshotWebViewActivity.TAG, "web sso failed.");
                                    return;
                                }
                                if (ScreenshotWebViewActivity.this.mWebView != null) {
                                    ScreenshotWebViewActivity.this.mWebView.loadUrl(str);
                                }
                                Miio.h(ScreenshotWebViewActivity.TAG, "web sso succeed.");
                            }
                        }, (Handler) null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private WebChromeClient e = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (!TextUtils.isEmpty(str)) {
                ScreenshotWebViewActivity.this.mTitleView.setText(str);
            } else {
                ScreenshotWebViewActivity.this.mTitleView.setText(ScreenshotWebViewActivity.this.mTitleText);
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i >= ScreenshotWebViewActivity.this.mPreProgress) {
                ScreenshotWebViewActivity.this.mProgressBar.setVisibility(0);
                if (ScreenshotWebViewActivity.this.mHandler != null) {
                    ScreenshotWebViewActivity.this.mHandler.removeMessages(1);
                    if (i >= ScreenshotWebViewActivity.this.mProgressBar.getProgress()) {
                        double d = (double) i;
                        Double.isNaN(d);
                        int i2 = (int) (d * 1.1d);
                        if (i2 <= 99) {
                            ScreenshotWebViewActivity.this.mProgressBar.setProgress(i2);
                            ScreenshotWebViewActivity.this.mProgressBar.postInvalidate();
                        }
                    }
                }
            }
            if (i >= 90 && ScreenshotWebViewActivity.this.mHandler != null) {
                ScreenshotWebViewActivity.this.mHandler.removeMessages(1);
                ScreenshotWebViewActivity.this.mProgressBar.setVisibility(8);
            }
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ScreenshotWebViewActivity.this.mContext);
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
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ScreenshotWebViewActivity.this.mContext);
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
            Toast.makeText(ScreenshotWebViewActivity.this.mContext, str2, 0).show();
            return true;
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    };
    private Screenshot f;
    @BindView(2131430969)
    ImageView mActionBarBack;
    Context mContext;
    String mCurrentUrl;
    boolean mIsGoBack = false;
    String mLastBackPath = "";
    int mPreProgress;
    @BindView(2131430637)
    ProgressBar mProgressBar;
    volatile boolean mScreenshotTaken = false;
    String mStartPath;
    String mTitleText;
    @BindView(2131430975)
    TextView mTitleView;
    String mUrl;
    boolean mWebFlagFinished = false;
    @BindView(2131433972)
    WebView mWebView;
    boolean mWebviewClientFlagFinished = false;

    public void handleMessage(Message message) {
        if (message.what == 1 && this.mProgressBar.getProgress() < this.mPreProgress) {
            this.mProgressBar.setProgress(this.mProgressBar.getProgress() + 1);
            this.mProgressBar.postInvalidate();
            this.mHandler.sendEmptyMessageDelayed(1, (long) ((((int) (Math.random() * 5.0d)) + 2) * 50));
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.comm_web_activity);
        ButterKnife.bind((Activity) this);
        this.mContext = this;
        this.mProgressBar.setIndeterminateDrawable((Drawable) null);
        this.mActionBarBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScreenshotWebViewActivity.this.onBackPressed();
            }
        });
        this.mTitleText = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(this.mTitleText)) {
            this.mTitleView.setText(this.mTitleText);
        }
        this.mUrl = getIntent().getStringExtra("url");
        findViewById(R.id.title_bar).setVisibility(8);
        this.b = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
        this.b.setVisibility(4);
        initWebView();
        if (!TextUtils.isEmpty(this.mUrl)) {
            if (Build.VERSION.SDK_INT >= 21) {
                this.mWebView.getSettings().setMixedContentMode(0);
            }
            this.mWebView.loadUrl(this.mUrl);
            this.mStartPath = getPath(this.mUrl);
            this.mCurrentUrl = this.mUrl;
            Miio.h(TAG, "onPageStarted url oncreate: " + this.mStartPath);
            return;
        }
        finish();
    }

    /* access modifiers changed from: package-private */
    public String getPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(63);
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mActionBarBack.requestFocus();
        if (this.mWebView != null) {
            this.mWebView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.mWebView, (Object[]) null);
            this.mWebView.loadUrl("");
        } catch (Exception unused) {
        }
        ViewParent parent = this.mWebView.getParent();
        this.mWebView.removeAllViews();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mWebView);
        }
        this.mWebView.destroy();
        this.mWebView = null;
    }

    /* access modifiers changed from: private */
    public void a() {
        this.f = new Screenshot.Builder(this).a((View) this.mWebView).a(true).a(new File(ImageSaveHelper.b, "screenshot.jpg").getAbsolutePath()).a((ScreenshotListener) new ScreenshotListener() {
            public void a(Bitmap bitmap, boolean z) {
                LogUtil.b("MainActivity", "onSuccess");
                ToastUtil.a((CharSequence) "screenshot success", 1);
                ScreenshotWebViewActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (ScreenshotWebViewActivity.this.isValid()) {
                            ScreenshotWebViewActivity.this.finish();
                        }
                    }
                }, 200);
            }

            public void a(int i, String str) {
                LogUtil.b("MainActivity", "onFail = " + str);
                ToastUtil.a((CharSequence) "screenshot fail");
                ScreenshotWebViewActivity.this.finish();
            }

            public void a() {
                LogUtil.b("MainActivity", "onPreStart");
            }
        }).a();
        this.f.a();
    }

    public void onBackPressed() {
        try {
            if (this.mWebView == null || !this.mWebView.canGoBack()) {
                super.onBackPressed();
                return;
            }
            this.mIsGoBack = true;
            this.mWebView.goBack();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void doGoBack() {
        onBackPressed();
    }

    /* access modifiers changed from: package-private */
    public void doFinishPage() {
        finish();
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"AddJavascriptInterface"})
    public void initWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setUserAgentString(a(settings.getUserAgentString()));
        settings.setAllowFileAccess(false);
        this.mWebView.setWebViewClient(this.d);
        this.mWebView.setWebChromeClient(this.e);
        this.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    ScreenshotWebViewActivity.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.mWebView.requestFocus();
        this.mWebView.addJavascriptInterface(new JavaScriptInterface(this), "_native_interface");
        this.mWebView.setDrawingCacheEnabled(true);
        this.mWebView.buildDrawingCache();
        CookieSyncManager.createInstance(getContext());
        CookieManager instance = CookieManager.getInstance();
        if (CoreApi.a().q() && !CoreApi.a().v()) {
            String w = CoreApi.a().w();
            String s = CoreApi.a().s();
            if (!TextUtils.isEmpty(w)) {
                a(instance, "passToken", w, ".account.xiaomi.com");
            }
            if (!TextUtils.isEmpty(s)) {
                a(instance, "userId", s, ".account.xiaomi.com");
            }
        }
    }

    static class JavaScriptInterface {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<ScreenshotWebViewActivity> f17888a = null;
        String b = "{}";

        public JavaScriptInterface(ScreenshotWebViewActivity screenshotWebViewActivity) {
            this.f17888a = new WeakReference<>(screenshotWebViewActivity);
            SystemApi a2 = SystemApi.a();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("os_name", "android");
                jSONObject.put("app_version", a2.e(screenshotWebViewActivity));
                jSONObject.put("os_version", a2.f());
                jSONObject.put(DTransferConstants.l, a2.e());
                this.b = jSONObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void onPageLoadFinished() {
            ScreenshotWebViewActivity screenshotWebViewActivity = (ScreenshotWebViewActivity) this.f17888a.get();
            if (screenshotWebViewActivity != null && screenshotWebViewActivity.isValid()) {
                screenshotWebViewActivity.mWebFlagFinished = true;
                if (screenshotWebViewActivity.mWebviewClientFlagFinished) {
                    screenshotWebViewActivity.mHandler.removeCallbacks(screenshotWebViewActivity.c);
                    screenshotWebViewActivity.mHandler.postDelayed(screenshotWebViewActivity.c, 0);
                }
            }
        }

        @JavascriptInterface
        public void onBackPressed(final boolean z) {
            ScreenshotWebViewActivity screenshotWebViewActivity = (ScreenshotWebViewActivity) this.f17888a.get();
            if (screenshotWebViewActivity != null && screenshotWebViewActivity.isValid()) {
                screenshotWebViewActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        ScreenshotWebViewActivity screenshotWebViewActivity = (ScreenshotWebViewActivity) JavaScriptInterface.this.f17888a.get();
                        if (screenshotWebViewActivity != null && screenshotWebViewActivity.isValid()) {
                            if (z) {
                                screenshotWebViewActivity.forceBack();
                            } else {
                                screenshotWebViewActivity.onBackPressed();
                            }
                        }
                    }
                });
            }
        }

        @JavascriptInterface
        public String getSettings() {
            return this.b;
        }

        @JavascriptInterface
        public String getLocale() {
            Locale g = GlobalDynamicSettingManager.a().g();
            if (g == null) {
                g = Locale.getDefault();
            }
            return g.toString();
        }
    }

    /* access modifiers changed from: protected */
    public void forceBack() {
        try {
            super.onBackPressed();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(CookieManager cookieManager, String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null) {
            cookieManager.setCookie(str3, str + "=" + str2 + "; domain=" + str3);
        }
    }

    private String a(String str) {
        if (sUserAgent == null) {
            sUserAgent = str + UserAgentUtil.a((Context) this) + " XiaoMi/HybridView/";
        }
        return sUserAgent;
    }
}
