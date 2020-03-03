package com.xiaomi.smarthome;

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
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.wificonfig.Base64;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;

public class ReportWebViewAct extends BaseActivity {
    public static final String ARGS_KEY_SHARE_TEXT = "report_text";
    public static final String ARGS_KEY_TITLE = "title";
    public static final String ARGS_KEY_URL = "report_url";
    static final String TAG = "ReportWebViewAct";
    public static final String TEXT_MEDAL_MARK = "*&*";

    /* renamed from: a  reason: collision with root package name */
    private static final int f13400a = 1;
    private static final int b = 12;
    static String sUserAgent;
    private WebViewClient c = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Miio.h(ReportWebViewAct.TAG, "onPageStarted url: " + str);
            if (ReportWebViewAct.this.mIsGoBack) {
                if (ReportWebViewAct.this.mLastBackPath.equals(ReportWebViewAct.this.mStartPath)) {
                    Miio.h(ReportWebViewAct.TAG, "onPageStarted url finish:" + ReportWebViewAct.this.mStartPath);
                    ReportWebViewAct.this.finish();
                    return;
                } else if (str.startsWith("https://account.xiaomi.com/pass/serviceLogin")) {
                    ReportWebViewAct.this.mIsGoBack = false;
                    if (ReportWebViewAct.this.mWebView != null && ReportWebViewAct.this.mWebView.canGoBack()) {
                        ReportWebViewAct.this.mWebView.goBack();
                        return;
                    }
                    return;
                }
            }
            super.onPageStarted(webView, str, bitmap);
            ReportWebViewAct.this.mIsGoBack = false;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Miio.h(ReportWebViewAct.TAG, "shouldOverrideUrlLoading url: " + str);
            ReportWebViewAct.this.mIsGoBack = false;
            return true;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            ReportWebViewAct.this.mIsGoBack = false;
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            Miio.h(ReportWebViewAct.TAG, "onPageFinished url: " + str);
            ReportWebViewAct.this.mLastBackPath = ReportWebViewAct.this.getPath(str);
            ReportWebViewAct.this.mIsGoBack = false;
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if ("com.xiaomi".equals(str)) {
                try {
                    MiAccountManager miAccountManager = MiAccountManager.get(ReportWebViewAct.this.getApplicationContext());
                    Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                    if (accountsByType.length != 0) {
                        miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) ReportWebViewAct.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
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
                                    Miio.h(ReportWebViewAct.TAG, "web sso failed.");
                                    return;
                                }
                                if (ReportWebViewAct.this.mWebView != null) {
                                    ReportWebViewAct.this.mWebView.loadUrl(str);
                                }
                                Miio.h(ReportWebViewAct.TAG, "web sso succeed.");
                            }
                        }, (Handler) null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private WebChromeClient d = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            Miio.b(ReportWebViewAct.TAG, "onReceivedTitle:" + str);
            super.onReceivedTitle(webView, str);
            if (!TextUtils.isEmpty(str)) {
                ReportWebViewAct.this.mTitleView.setText(str);
            } else {
                ReportWebViewAct.this.mTitleView.setText(ReportWebViewAct.this.mTitleText);
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i >= ReportWebViewAct.this.mPreProgress) {
                ReportWebViewAct.this.mProgressBar.setVisibility(0);
                if (ReportWebViewAct.this.mHandler != null) {
                    ReportWebViewAct.this.mHandler.removeMessages(1);
                    if (i >= ReportWebViewAct.this.mProgressBar.getProgress()) {
                        double d = (double) i;
                        Double.isNaN(d);
                        int i2 = (int) (d * 1.1d);
                        if (i2 <= 99) {
                            ReportWebViewAct.this.mProgressBar.setProgress(i2);
                            ReportWebViewAct.this.mProgressBar.postInvalidate();
                        }
                    }
                }
            }
            if (i >= 90 && ReportWebViewAct.this.mHandler != null) {
                ReportWebViewAct.this.mHandler.removeMessages(1);
                ReportWebViewAct.this.mProgressBar.setVisibility(8);
            }
        }

        public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ReportWebViewAct.this.mContext);
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
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ReportWebViewAct.this.mContext);
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
            Toast.makeText(ReportWebViewAct.this.mContext, str2, 0).show();
            return true;
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    };
    byte[] imageData;
    @BindView(2131430969)
    ImageView mActionBarBack;
    String mContentId = "";
    Context mContext;
    String mCurrentUrl;
    boolean mIsGoBack = false;
    String mLastBackPath = "";
    int mPreProgress;
    XQProgressDialog mProcessDialog;
    @BindView(2131430637)
    ProgressBar mProgressBar;
    String mReportConent = "";
    String mReportMedal = "";
    String mShareText;
    @BindView(2131430982)
    ImageView mShareView;
    String mStartPath;
    String mTitleText;
    @BindView(2131430975)
    TextView mTitleView;
    String mUrl;
    String mUrlFromServer;
    @BindView(2131433972)
    WebView mWebView;

    private void a() {
    }

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
        this.mShareView.setImageResource(R.drawable.std_tittlebar_main_shop_icon_share);
        this.mShareView.setVisibility(0);
        this.mShareView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!TextUtils.isEmpty(ReportWebViewAct.this.mContentId)) {
                    if (ReportWebViewAct.this.imageData == null) {
                        ReportWebViewAct.this.b();
                    } else {
                        ReportWebViewAct.this.a(ReportWebViewAct.this.imageData);
                    }
                }
            }
        });
        this.mProgressBar.setIndeterminateDrawable((Drawable) null);
        this.mActionBarBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ReportWebViewAct.this.onBackPressed();
            }
        });
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.mUrlFromServer = intent.getStringExtra(ARGS_KEY_URL);
        if (!TextUtils.isEmpty(this.mUrlFromServer)) {
            this.mShareText = intent.getStringExtra(ARGS_KEY_SHARE_TEXT);
            if (this.mShareText == null) {
                this.mShareText = "";
            }
            this.mUrl = this.mUrlFromServer + "?from=android";
            initWebView();
            if (!TextUtils.isEmpty(this.mUrl)) {
                this.mWebView.loadUrl(this.mUrl);
                this.mStartPath = getPath(this.mUrl);
                this.mCurrentUrl = this.mUrl;
                Miio.h(TAG, "onPageStarted url oncreate: " + this.mStartPath);
            } else {
                finish();
            }
            a();
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

    public void onBackPressed() {
        if (this.mWebView == null || !this.mWebView.canGoBack()) {
            super.onBackPressed();
        } else if (a(this.mWebView.getUrl())) {
            super.onBackPressed();
        } else {
            this.mIsGoBack = true;
            this.mWebView.goBack();
        }
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || TextUtils.equals(str, this.mUrl) || TextUtils.equals(str, this.mUrl);
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
        settings.setUserAgentString(b(settings.getUserAgentString()));
        this.mWebView.setWebViewClient(this.c);
        this.mWebView.setWebChromeClient(this.d);
        this.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    ReportWebViewAct.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.mWebView.requestFocus();
        this.mWebView.addJavascriptInterface(this, "android");
        CookieSyncManager.createInstance(getContext());
        CookieManager instance = CookieManager.getInstance();
        if (CoreApi.a().q()) {
            String s = CoreApi.a().s();
            MiServiceTokenInfo a2 = CoreApi.a().a("xiaomihome");
            if (a2 != null && !TextUtils.isEmpty(a2.c)) {
                a(instance, "serviceToken", a2.c, ".home.mi.com");
            }
            if (!TextUtils.isEmpty(s)) {
                a(instance, "userId", s, ".home.mi.com");
            }
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

    private String b(String str) {
        if (sUserAgent == null) {
            sUserAgent = str + UserAgentUtil.a((Context) this) + " XiaoMi/HybridView/";
        }
        return sUserAgent;
    }

    /* access modifiers changed from: private */
    public void b() {
        String s = CoreApi.a().s();
        Intent intent = new Intent(this.mContext, CommonShareActivity.class);
        intent.putExtra("ShareTitle", this.mReportConent);
        intent.putExtra(CommonShareActivity.SHARE_URL, this.mUrlFromServer + "?contentId=" + this.mContentId + "?userId=" + s);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void a(byte[] bArr) {
        Intent intent = new Intent(this.mContext, CommonShareActivity.class);
        intent.putExtra("ShareTitle", this.mReportConent);
        intent.putExtra(CommonShareActivity.SHARE_IMAGE_BYTE_ARRAY, bArr);
        startActivity(intent);
    }

    @JavascriptInterface
    public void onMedalInfo(String str, String str2) {
        this.mReportMedal = str;
        this.mContentId = str2;
        this.mReportConent = this.mShareText;
        if (this.mShareText.length() >= 3) {
            this.mReportConent = this.mShareText.replace(TEXT_MEDAL_MARK, this.mReportMedal);
        }
    }

    @JavascriptInterface
    public void onImageJS(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith("data:image/")) {
            this.imageData = Base64.a(str.substring(str.indexOf(",") + 1));
            a(this.imageData);
        }
    }
}
