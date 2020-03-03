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
import android.util.Log;
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
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ImageSaveHelper;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.screenshot.Screenshot;
import com.xiaomi.smarthome.screenshot.callback.ScreenshotListener;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Locale;
import org.json.JSONObject;

public class CommonWebViewActivity extends BaseActivity {
    public static final String ARGS_KEY_FROM_OPERATION = "from_operation";
    public static final String ARGS_KEY_NAV_CLOSE = "nav_close";
    public static final String ARGS_KEY_NAV_ENABLE = "nav_enable";
    public static final String ARGS_KEY_SHARE_DESC = "share_desc";
    public static final String ARGS_KEY_SHARE_IMG = "share_img";
    public static final String ARGS_KEY_SHARE_TITLE = "share_title";
    public static final String ARGS_KEY_SHARE_URL = "share_url";
    public static final String ARGS_KEY_TITLE = "title";
    public static final String ARGS_KEY_TITLE_ENABLE = "title_enable";
    public static final String ARGS_KEY_URL = "url";
    static final String TAG = "CommonWebViewActivity";

    /* renamed from: a  reason: collision with root package name */
    private static final String f17856a = "http://";
    private static final String b = "https://";
    private static final int c = 1;
    static String sUserAgent;
    private boolean d = true;
    private boolean e = false;
    private String f = "";
    private String g = "";
    private String h = "";
    /* access modifiers changed from: private */
    public ImageView i;
    private String j;
    /* access modifiers changed from: private */
    public boolean k;
    private WebViewClient l = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Miio.h(CommonWebViewActivity.TAG, "onPageStarted url: " + str);
            if (CommonWebViewActivity.this.mIsGoBack) {
                if (CommonWebViewActivity.this.mLastBackPath.equals(CommonWebViewActivity.this.mStartPath)) {
                    Miio.h(CommonWebViewActivity.TAG, "onPageStarted url finish:" + CommonWebViewActivity.this.mStartPath);
                    CommonWebViewActivity.this.finish();
                    return;
                } else if (str.startsWith("https://account.xiaomi.com/pass/serviceLogin")) {
                    CommonWebViewActivity.this.mIsGoBack = false;
                    if (CommonWebViewActivity.this.mWebView != null && CommonWebViewActivity.this.mWebView.canGoBack()) {
                        CommonWebViewActivity.this.mWebView.goBack();
                        return;
                    }
                    return;
                }
            }
            super.onPageStarted(webView, str, bitmap);
            CommonWebViewActivity.this.mIsGoBack = false;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Miio.h(CommonWebViewActivity.TAG, "shouldOverrideUrlLoading url: " + str);
            CommonWebViewActivity.this.mIsGoBack = false;
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            CommonWebViewActivity.this.mIsGoBack = false;
            return super.shouldOverrideUrlLoading(webView, webResourceRequest);
        }

        public void onPageFinished(WebView webView, String str) {
            Miio.h(CommonWebViewActivity.TAG, "onPageFinished url: " + str);
            CommonWebViewActivity.this.mLastBackPath = CommonWebViewActivity.this.getPath(str);
            CommonWebViewActivity.this.mIsGoBack = false;
            if (CommonWebViewActivity.this.i != null) {
                CommonWebViewActivity.this.i.setEnabled(true);
            }
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if ("com.xiaomi".equals(str)) {
                try {
                    MiAccountManager miAccountManager = MiAccountManager.get(CommonWebViewActivity.this.getApplicationContext());
                    Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                    if (accountsByType.length != 0) {
                        miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) CommonWebViewActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
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
                                    Miio.h(CommonWebViewActivity.TAG, "web sso failed.");
                                    return;
                                }
                                if (CommonWebViewActivity.this.mWebView != null) {
                                    CommonWebViewActivity.this.mWebView.loadUrl(str);
                                }
                                Miio.h(CommonWebViewActivity.TAG, "web sso succeed.");
                            }
                        }, (Handler) null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private WebChromeClient m = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (CommonWebViewActivity.this.k) {
                if (!TextUtils.isEmpty(CommonWebViewActivity.this.mTitleText)) {
                    CommonWebViewActivity.this.mTitleView.setText(CommonWebViewActivity.this.mTitleText);
                } else {
                    CommonWebViewActivity.this.mTitleView.setText(str);
                }
            } else if (!TextUtils.isEmpty(str)) {
                CommonWebViewActivity.this.mTitleView.setText(str);
            } else {
                CommonWebViewActivity.this.mTitleView.setText(CommonWebViewActivity.this.mTitleText);
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i >= CommonWebViewActivity.this.mPreProgress) {
                CommonWebViewActivity.this.mProgressBar.setVisibility(0);
                if (CommonWebViewActivity.this.mHandler != null) {
                    CommonWebViewActivity.this.mHandler.removeMessages(1);
                    if (i >= CommonWebViewActivity.this.mProgressBar.getProgress()) {
                        double d = (double) i;
                        Double.isNaN(d);
                        int i2 = (int) (d * 1.1d);
                        if (i2 <= 99) {
                            CommonWebViewActivity.this.mProgressBar.setProgress(i2);
                            CommonWebViewActivity.this.mProgressBar.postInvalidate();
                        }
                    }
                }
            }
            if (i >= 90 && CommonWebViewActivity.this.mHandler != null) {
                CommonWebViewActivity.this.mHandler.removeMessages(1);
                CommonWebViewActivity.this.mProgressBar.setVisibility(8);
            }
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CommonWebViewActivity.this.mContext);
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
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CommonWebViewActivity.this.mContext);
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
            Toast.makeText(CommonWebViewActivity.this.mContext, str2, 0).show();
            return true;
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    };
    @BindView(2131430969)
    ImageView mActionBarBack;
    Context mContext;
    String mCurrentUrl;
    boolean mIsGoBack = false;
    String mLastBackPath = "";
    int mPreProgress;
    @BindView(2131430637)
    ProgressBar mProgressBar;
    String mStartPath;
    @BindView(2131430965)
    ImageView mTitleClose;
    String mTitleText;
    @BindView(2131430975)
    TextView mTitleView;
    String mUrl;
    @BindView(2131433972)
    SmartHomeWebView mWebView;
    /* access modifiers changed from: private */
    public Runnable n = new Runnable() {
        public void run() {
            if (CommonWebViewActivity.this.isValid()) {
                CommonWebViewActivity.this.c();
            }
        }
    };
    private Screenshot o;

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
                CommonWebViewActivity.this.onBackPressed();
            }
        });
        this.mTitleText = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(this.mTitleText)) {
            this.mTitleView.setText(this.mTitleText);
        }
        boolean booleanExtra = getIntent().getBooleanExtra(ARGS_KEY_NAV_CLOSE, false);
        if (this.mTitleClose != null && booleanExtra) {
            this.mTitleClose.setVisibility(0);
            this.mTitleClose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CommonWebViewActivity.this.finish();
                }
            });
        }
        this.k = getIntent().getBooleanExtra(ARGS_KEY_FROM_OPERATION, false);
        this.d = true;
        Intent intent = getIntent();
        if (intent.hasExtra(ARGS_KEY_TITLE_ENABLE)) {
            Object obj = intent.getExtras().get(ARGS_KEY_TITLE_ENABLE);
            if (obj instanceof String) {
                this.d = TextUtils.equals("true", ((String) obj).toLowerCase());
            } else if (obj instanceof Boolean) {
                this.d = ((Boolean) obj).booleanValue();
            }
        }
        this.mUrl = getIntent().getStringExtra("url");
        try {
            if (TextUtils.isEmpty(Uri.parse(this.mUrl).getHost())) {
                finish();
            } else if (TextUtils.isEmpty(this.mUrl)) {
                finish();
            } else {
                if (this.d) {
                    findViewById(R.id.title_bar).setVisibility(0);
                } else {
                    findViewById(R.id.title_bar).setVisibility(8);
                }
                this.e = getIntent().getBooleanExtra("nav_enable", false);
                this.i = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
                if (this.e) {
                    this.i.setVisibility(0);
                    this.i.setImageResource(R.drawable.smartgroup_share_icon);
                    this.i.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            CommonWebViewActivity.this.a();
                        }
                    });
                } else {
                    this.i.setVisibility(4);
                }
                this.i.setEnabled(false);
                if (this.e) {
                    this.f = getIntent().getStringExtra("share_title");
                    this.g = getIntent().getStringExtra(ARGS_KEY_SHARE_IMG);
                    this.h = getIntent().getStringExtra(ARGS_KEY_SHARE_DESC);
                    this.j = getIntent().getStringExtra("share_url");
                }
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
        } catch (Exception e2) {
            e2.printStackTrace();
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.e) {
            Intent intent = new Intent(this, CommonShareActivity.class);
            intent.putExtra("ShareTitle", this.f);
            if (!TextUtils.isEmpty(this.h)) {
                intent.putExtra("ShareContent", this.h);
            }
            if (!TextUtils.isEmpty(this.g)) {
                if (this.g.endsWith(".zip")) {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, this.g);
                } else {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_URL_NO_ZIP, this.g);
                }
            }
            intent.putExtra(CommonShareActivity.SHARE_URL, this.j);
            startActivity(intent);
        }
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
        } catch (Throwable th) {
            th.printStackTrace();
        }
        ViewParent parent = this.mWebView.getParent();
        this.mWebView.removeAllViews();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mWebView);
        }
        this.mWebView.destroy();
        this.mWebView = null;
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
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setUserAgentString(a(settings.getUserAgentString()));
        settings.setAllowFileAccess(false);
        this.mWebView.setWebViewClient(this.l);
        this.mWebView.setWebChromeClient(this.m);
        this.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    CommonWebViewActivity.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.mWebView.requestFocus();
        this.mWebView.addJavascriptInterface(new JavaScriptInterface(this), "_native_interface");
        b();
    }

    /* access modifiers changed from: private */
    public void b() {
        CookieSyncManager.createInstance(getContext());
        CookieManager instance = CookieManager.getInstance();
        if (SHApplication.getStateNotifier().a() == 4) {
            String s = CoreApi.a().s();
            if (!TextUtils.isEmpty(s) && ((!TextUtils.isEmpty(this.mUrl) && this.mUrl.startsWith("https://")) || GlobalSetting.q)) {
                a(instance, "userId", s, ".home.mi.com");
            }
        }
        if (CoreApi.a().q() && !CoreApi.a().v()) {
            String w = CoreApi.a().w();
            if (!TextUtils.isEmpty(w)) {
                a(instance, "passToken", w, ".account.xiaomi.com");
            }
            String s2 = CoreApi.a().s();
            if (!TextUtils.isEmpty(s2)) {
                a(instance, "userId", s2, ".account.xiaomi.com");
            }
        }
    }

    static class JavaScriptInterface {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<CommonWebViewActivity> f17869a = null;
        String b = "{}";

        public JavaScriptInterface(CommonWebViewActivity commonWebViewActivity) {
            this.f17869a = new WeakReference<>(commonWebViewActivity);
            SystemApi a2 = SystemApi.a();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("os_name", "android");
                jSONObject.put("app_version", a2.e(commonWebViewActivity));
                jSONObject.put("os_version", a2.f());
                jSONObject.put(DTransferConstants.l, a2.e());
                this.b = jSONObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public boolean isLogin() {
            return SHApplication.getStateNotifier().a() == 4;
        }

        @JavascriptInterface
        public void startLogin() {
            final CommonWebViewActivity commonWebViewActivity = (CommonWebViewActivity) this.f17869a.get();
            if (commonWebViewActivity != null && commonWebViewActivity.isValid()) {
                if (SHApplication.getStateNotifier().a() == 4) {
                    commonWebViewActivity.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (commonWebViewActivity.isValid()) {
                                commonWebViewActivity.b();
                                SmartHomeWebView smartHomeWebView = commonWebViewActivity.mWebView;
                                if (smartHomeWebView != null) {
                                    smartHomeWebView.initCookie();
                                    smartHomeWebView.reload();
                                }
                            }
                        }
                    }, 200);
                } else {
                    LoginApi.a().a((Context) commonWebViewActivity, 1, (LoginApi.LoginCallback) new LoginApi.LoginCallback() {
                        public void a() {
                            commonWebViewActivity.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    if (commonWebViewActivity.isValid()) {
                                        commonWebViewActivity.b();
                                        SmartHomeWebView smartHomeWebView = commonWebViewActivity.mWebView;
                                        if (smartHomeWebView != null) {
                                            smartHomeWebView.initCookie();
                                            smartHomeWebView.reload();
                                        }
                                    }
                                }
                            }, 200);
                        }
                    });
                }
            }
        }

        @JavascriptInterface
        public void screenshot() {
            CommonWebViewActivity commonWebViewActivity = (CommonWebViewActivity) this.f17869a.get();
            if (commonWebViewActivity != null && commonWebViewActivity.isValid()) {
                commonWebViewActivity.mHandler.removeCallbacks(commonWebViewActivity.n);
                commonWebViewActivity.mHandler.postDelayed(commonWebViewActivity.n, 500);
            }
        }

        @JavascriptInterface
        public boolean openYoupinPage(final String str) {
            CommonWebViewActivity commonWebViewActivity;
            if (TextUtils.isEmpty(str) || (commonWebViewActivity = (CommonWebViewActivity) this.f17869a.get()) == null || !commonWebViewActivity.isValid()) {
                return false;
            }
            commonWebViewActivity.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        UrlDispatchManger.a().c(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return true;
        }

        @JavascriptInterface
        public void onBackPressed(final boolean z) {
            CommonWebViewActivity commonWebViewActivity = (CommonWebViewActivity) this.f17869a.get();
            if (commonWebViewActivity != null && commonWebViewActivity.isValid()) {
                commonWebViewActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        CommonWebViewActivity commonWebViewActivity = (CommonWebViewActivity) JavaScriptInterface.this.f17869a.get();
                        if (commonWebViewActivity != null && commonWebViewActivity.isValid()) {
                            if (z) {
                                commonWebViewActivity.forceBack();
                            } else {
                                commonWebViewActivity.onBackPressed();
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
        public boolean onShare(String str, String str2, String str3, String str4) {
            CommonWebViewActivity commonWebViewActivity = (CommonWebViewActivity) this.f17869a.get();
            if (commonWebViewActivity == null || !commonWebViewActivity.isValid() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return false;
            }
            CoreApi.a().s();
            Intent intent = new Intent(commonWebViewActivity, CommonShareActivity.class);
            intent.putExtra("ShareTitle", str);
            if (!TextUtils.isEmpty(str3)) {
                intent.putExtra("ShareContent", str3);
            }
            if (!TextUtils.isEmpty(str4)) {
                if (str4.endsWith(".zip")) {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, str4);
                } else {
                    intent.putExtra(CommonShareActivity.SHARE_IMAGE_URL_NO_ZIP, str4);
                }
            }
            intent.putExtra(CommonShareActivity.SHARE_URL, str2);
            commonWebViewActivity.startActivity(intent);
            return true;
        }

        @JavascriptInterface
        public int getTitleBarPadding() {
            return TitleBarUtil.a();
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

    public String getAppVersion() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception unused) {
            Log.w(TAG, "Get Version Name failed. return 1.0.0");
            return "1.0.0";
        }
    }

    private String a(String str) {
        if (sUserAgent == null) {
            sUserAgent = str + UserAgentUtil.a((Context) this) + " XiaoMi/HybridView/";
        }
        return sUserAgent;
    }

    public static class ExternalUrlValidator implements PageUrl.UrlConfigInfo.UrlValidator {
        public boolean a(Uri uri) {
            if (uri == null) {
                return false;
            }
            try {
                UrlResolver.Parameter a2 = UrlResolver.Parameter.a(uri);
                if (!(a2 == null || a2.b == null)) {
                    if (!a2.b.isEmpty()) {
                        String str = a2.b.get("url");
                        if (TextUtils.isEmpty(str)) {
                            return false;
                        }
                        String host = Uri.parse(str).getHost();
                        if (!TextUtils.isEmpty(host) && host.toLowerCase().endsWith(".mi.com")) {
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        final File file = new File(ImageSaveHelper.b, "screenshot.jpg");
        this.o = new Screenshot.Builder(this).a((View) this.mWebView).a(true).a(file.getAbsolutePath()).a((ScreenshotListener) new ScreenshotListener() {
            public void a(Bitmap bitmap, boolean z) {
                LogUtil.b(CommonWebViewActivity.TAG, "onSuccess");
                ToastUtil.a((CharSequence) CommonWebViewActivity.this.getContext().getString(R.string.image_saved) + ImageSaveHelper.f18682a + CommonWebViewActivity.this.getContext().getString(R.string.file_directory), 1);
                try {
                    ImageSaveHelper.a(file);
                    CommonWebViewActivity.this.mWebView.loadUrl("javascript:_hideImgDownloader()");
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void a(int i, String str) {
                LogUtil.b(CommonWebViewActivity.TAG, "onFail = " + str);
                ToastUtil.a((int) R.string.save_fail);
            }

            public void a() {
                LogUtil.b(CommonWebViewActivity.TAG, "onPreStart");
            }
        }).a();
        this.o.a();
    }
}
