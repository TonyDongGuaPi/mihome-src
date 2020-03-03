package com.xiaomi.smarthome.auth.bindaccount;

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
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
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
import com.google.android.exoplayer2.C;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class ThirdAccountAuthWebActivity extends BaseActivity {
    public static final String ARGS_KEY_TITLE = "title";
    static final String TAG = "ThirdAccountAuthActivit";

    /* renamed from: a  reason: collision with root package name */
    private static final int f13867a = 1;
    private static final boolean d = false;
    static String sUserAgent;
    /* access modifiers changed from: private */
    public XQProgressDialog b;
    /* access modifiers changed from: private */
    public boolean c = false;
    private String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public String g;
    private String h;
    private WebViewClient i;
    private WebChromeClient j;
    @BindView(2131430969)
    ImageView mActionBarBack;
    Context mContext;
    String mCurrentUrl;
    boolean mIsGoBack;
    String mLastBackPath;
    int mPreProgress;
    @BindView(2131430637)
    ProgressBar mProgressBar;
    String mStartPath;
    String mTitleText;
    @BindView(2131430975)
    TextView mTitleView;
    String mUrl;
    @BindView(2131433972)
    WebView mWebView;

    public ThirdAccountAuthWebActivity() {
        String str;
        String str2;
        String str3;
        if (TextUtils.equals("preview", CoreApi.a().H())) {
            str = "https://pv-oauth-redirect-home.io.mi.com/o/";
        } else {
            str = ServerCompact.l(CoreApi.a().F()) ? "https://st-oauth-redirect-home.io.mi.com/o/" : "https://oauth-redirect.api.home.mi.com/o/";
        }
        this.e = str;
        if (TextUtils.equals("preview", CoreApi.a().H())) {
            str2 = "https://pv-oauth-redirect-home.io.mi.com/o/";
        } else {
            str2 = ServerCompact.l(CoreApi.a().F()) ? "https://st-oauth-redirect-home.io.mi.com/o/" : "https://oauth-redirect.api.home.mi.com/o/";
        }
        this.mUrl = str2;
        if (TextUtils.equals("preview", CoreApi.a().H())) {
            str3 = "https://pv-oauth-redirect-home.io.mi.com/result/";
        } else {
            str3 = ServerCompact.l(CoreApi.a().F()) ? "https://st-oauth-redirect-home.io.mi.com/result/" : "https://oauth-redirect.api.home.mi.com/result/";
        }
        this.f = str3;
        this.mLastBackPath = "";
        this.mIsGoBack = false;
        this.i = new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                Miio.h(ThirdAccountAuthWebActivity.TAG, "onPageStarted url: " + str);
                if (ThirdAccountAuthWebActivity.this.mIsGoBack) {
                    if (ThirdAccountAuthWebActivity.this.mLastBackPath.equals(ThirdAccountAuthWebActivity.this.mStartPath)) {
                        Miio.h(ThirdAccountAuthWebActivity.TAG, "onPageStarted url finish:" + ThirdAccountAuthWebActivity.this.mStartPath);
                        ThirdAccountAuthWebActivity.this.finish();
                        return;
                    } else if (str.startsWith("https://account.xiaomi.com/pass/serviceLogin")) {
                        ThirdAccountAuthWebActivity.this.mIsGoBack = false;
                        if (ThirdAccountAuthWebActivity.this.mWebView != null && ThirdAccountAuthWebActivity.this.mWebView.canGoBack()) {
                            ThirdAccountAuthWebActivity.this.mWebView.goBack();
                            return;
                        }
                        return;
                    }
                }
                super.onPageStarted(webView, str, bitmap);
                ThirdAccountAuthWebActivity.this.mIsGoBack = false;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Miio.h(ThirdAccountAuthWebActivity.TAG, "shouldOverrideUrlLoading url: " + str);
                ThirdAccountAuthWebActivity.this.mIsGoBack = false;
                if (!a(str)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                return true;
            }

            private boolean a(String str) {
                if (TextUtils.isEmpty(str) || !str.startsWith(ThirdAccountAuthWebActivity.this.f)) {
                    return false;
                }
                try {
                    Uri parse = Uri.parse(str);
                    LogUtil.a(ThirdAccountAuthWebActivity.TAG, "url=" + str);
                    String queryParameter = parse.getQueryParameter("data");
                    if (TextUtils.isEmpty(queryParameter)) {
                        ToastUtil.a((int) R.string.failed);
                        ThirdAccountAuthWebActivity.this.finish();
                        return true;
                    }
                    JSONObject jSONObject = new JSONObject(queryParameter);
                    LogUtil.a(ThirdAccountAuthWebActivity.TAG, "url=" + jSONObject.toString());
                    if (jSONObject.isNull("code")) {
                        ToastUtil.a((int) R.string.failed);
                        ThirdAccountAuthWebActivity.this.finish();
                        return true;
                    } else if (jSONObject.optInt("code") != 0) {
                        ToastUtil.a((int) R.string.failed);
                        ThirdAccountAuthWebActivity.this.finish();
                        return true;
                    } else {
                        ThirdAccountAuthWebActivity.this.setResult(-1);
                        ThirdAccountAuthWebActivity.this.startUpdateThirdCloudData();
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                ThirdAccountAuthWebActivity.this.mIsGoBack = false;
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }

            public void onPageFinished(WebView webView, String str) {
                Miio.h(ThirdAccountAuthWebActivity.TAG, "onPageFinished url: " + str);
                ThirdAccountAuthWebActivity.this.mLastBackPath = ThirdAccountAuthWebActivity.this.getPath(str);
                ThirdAccountAuthWebActivity.this.mIsGoBack = false;
            }

            public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
                if ("com.xiaomi".equals(str)) {
                    try {
                        MiAccountManager miAccountManager = MiAccountManager.get(ThirdAccountAuthWebActivity.this.getApplicationContext());
                        Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                        if (accountsByType.length != 0) {
                            miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) ThirdAccountAuthWebActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
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
                                        Miio.h(ThirdAccountAuthWebActivity.TAG, "web sso failed.");
                                        return;
                                    }
                                    if (ThirdAccountAuthWebActivity.this.mWebView != null) {
                                        ThirdAccountAuthWebActivity.this.mWebView.loadUrl(str);
                                    }
                                    Miio.h(ThirdAccountAuthWebActivity.TAG, "web sso succeed.");
                                }
                            }, (Handler) null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.j = new WebChromeClient() {
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                if (!TextUtils.isEmpty(str)) {
                    ThirdAccountAuthWebActivity.this.mTitleView.setText(str);
                } else {
                    ThirdAccountAuthWebActivity.this.mTitleView.setText(ThirdAccountAuthWebActivity.this.mTitleText);
                }
            }

            public void onProgressChanged(WebView webView, int i) {
                if (i >= ThirdAccountAuthWebActivity.this.mPreProgress) {
                    ThirdAccountAuthWebActivity.this.mProgressBar.setVisibility(0);
                    if (ThirdAccountAuthWebActivity.this.mHandler != null) {
                        ThirdAccountAuthWebActivity.this.mHandler.removeMessages(1);
                        if (i >= ThirdAccountAuthWebActivity.this.mProgressBar.getProgress()) {
                            double d = (double) i;
                            Double.isNaN(d);
                            int i2 = (int) (d * 1.1d);
                            if (i2 <= 99) {
                                ThirdAccountAuthWebActivity.this.mProgressBar.setProgress(i2);
                                ThirdAccountAuthWebActivity.this.mProgressBar.postInvalidate();
                            }
                        }
                    }
                }
                if (i >= 90 && ThirdAccountAuthWebActivity.this.mHandler != null) {
                    ThirdAccountAuthWebActivity.this.mHandler.removeMessages(1);
                    ThirdAccountAuthWebActivity.this.mProgressBar.setVisibility(8);
                }
            }

            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ThirdAccountAuthWebActivity.this.mContext);
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
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(ThirdAccountAuthWebActivity.this.mContext);
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
                Toast.makeText(ThirdAccountAuthWebActivity.this.mContext, str2, 0).show();
                return true;
            }

            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
            }
        };
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
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.g = intent.getStringExtra("account_id");
        if (TextUtils.isEmpty(this.g)) {
            finish();
            return;
        }
        setContentView(R.layout.comm_web_activity);
        ButterKnife.bind((Activity) this);
        this.mContext = this;
        this.mProgressBar.setIndeterminateDrawable((Drawable) null);
        this.mActionBarBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThirdAccountAuthWebActivity.this.onBackPressed();
            }
        });
        this.mTitleText = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(this.mTitleText)) {
            this.mTitleView.setText(this.mTitleText);
        }
        initWebView();
        a();
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
        if (this.mWebView != null) {
            ViewParent parent = this.mWebView.getParent();
            this.mWebView.removeAllViews();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.mWebView);
            }
            this.mWebView.destroy();
        }
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
        this.mWebView.setWebViewClient(this.i);
        this.mWebView.setWebChromeClient(this.j);
        this.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    ThirdAccountAuthWebActivity.this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.mWebView.requestFocus();
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

    public void startUpdateThirdCloudData() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
        this.c = false;
        this.b = new XQProgressDialog(this);
        this.b.setCancelable(true);
        this.b.setMessage(getResources().getString(R.string.loading_share_info));
        this.b.show();
        this.b.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                boolean unused = ThirdAccountAuthWebActivity.this.c = true;
            }
        });
        ThirdAccountBindManager.a().b((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (ThirdAccountAuthWebActivity.this.isValid()) {
                    ThirdAccountAuthWebActivity.this.b.dismiss();
                    ThirdAccountAuthWebActivity.this.c();
                    Intent intent = new Intent(SHApplication.getAppContext(), ThirdAccountDeviceListActivity.class);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("account_id", ThirdAccountAuthWebActivity.this.g);
                    intent.putExtra(ThirdAccountDeviceListActivity.INTENT_KEY_SYNC_ON_START, true);
                    ThirdAccountAuthWebActivity.this.startActivity(intent);
                    ThirdAccountAuthWebActivity.this.finish();
                    STAT.i.a(CoreApi.a().s());
                }
            }

            public void onFailure(Error error) {
                if (ThirdAccountAuthWebActivity.this.isValid()) {
                    ThirdAccountAuthWebActivity.this.b.dismiss();
                    ThirdAccountAuthWebActivity.this.c();
                    Intent intent = new Intent(SHApplication.getAppContext(), ThirdAccountDeviceListActivity.class);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("account_id", ThirdAccountAuthWebActivity.this.g);
                    intent.putExtra(ThirdAccountDeviceListActivity.INTENT_KEY_SYNC_ON_START, true);
                    ThirdAccountAuthWebActivity.this.startActivity(intent);
                    ThirdAccountAuthWebActivity.this.finish();
                }
            }
        });
    }

    private void a() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
        this.c = false;
        this.b = new XQProgressDialog(this);
        this.b.setCancelable(true);
        this.b.setMessage(getResources().getString(R.string.loading_share_info));
        this.b.show();
        this.b.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                boolean unused = ThirdAccountAuthWebActivity.this.c = true;
            }
        });
        RemoteFamilyApi.a().c((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (ThirdAccountAuthWebActivity.this.isValid()) {
                    ThirdAccountAuthWebActivity.this.a(jSONObject);
                    if (ThirdAccountAuthWebActivity.this.b != null && ThirdAccountAuthWebActivity.this.b.isShowing()) {
                        ThirdAccountAuthWebActivity.this.b.dismiss();
                    }
                }
            }

            public void onFailure(Error error) {
                if (ThirdAccountAuthWebActivity.this.isValid()) {
                    ToastUtil.a((int) R.string.third_account_auth_failed);
                    if (ThirdAccountAuthWebActivity.this.b != null && ThirdAccountAuthWebActivity.this.b.isShowing()) {
                        ThirdAccountAuthWebActivity.this.b.dismiss();
                    }
                    ThirdAccountAuthWebActivity.this.finish();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        String str;
        this.h = jSONObject == null ? "" : jSONObject.optString("result", "");
        if (jSONObject == null) {
            str = "";
        } else {
            str = jSONObject.toString();
        }
        LogUtil.a(TAG, str);
        if (TextUtils.isEmpty(this.h)) {
            ToastUtil.a((int) R.string.third_account_auth_failed);
            finish();
            return;
        }
        this.mUrl = this.e + this.g + "?state=" + this.h;
        StringBuilder sb = new StringBuilder();
        sb.append("mUrl=");
        sb.append(this.mUrl);
        LogUtil.a(TAG, sb.toString());
        if (!TextUtils.isEmpty(this.mUrl)) {
            this.mWebView.loadUrl(this.mUrl);
            this.mStartPath = getPath(this.mUrl);
            this.mCurrentUrl = this.mUrl;
            Miio.h(TAG, "onPageStarted url oncreate: " + this.mStartPath);
        }
    }

    private void b() {
        c();
        Intent intent = new Intent(SHApplication.getAppContext(), ThirdAccountDeviceListActivity.class);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("account_id", this.g);
        intent.putExtra(ThirdAccountDeviceListActivity.INTENT_KEY_SYNC_ON_START, true);
        startActivity(intent);
        finish();
    }

    private void a(int i2) {
        ToastUtil.a((int) R.string.third_account_auth_failed);
        finish();
    }

    /* access modifiers changed from: private */
    public void c() {
        try {
            Object[] array = mActivityStack.values().toArray();
            if (array == null) {
                return;
            }
            if (array.length != 0) {
                for (int length = array.length - 1; length >= 0; length--) {
                    WeakReference weakReference = (WeakReference) array[length];
                    if (weakReference != null) {
                        Activity activity = (Activity) weakReference.get();
                        if (activity != null) {
                            if (TextUtils.equals(activity.getClass().getCanonicalName(), ThirdAccountDetailActivity.class.getCanonicalName())) {
                                activity.finish();
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
