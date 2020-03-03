package com.mi.global.shop.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.sys.a;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.cache.WebCache;
import com.mi.global.shop.imageselector.MultiImageSelector;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.SyncModel;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.DownloadUtils;
import com.mi.global.shop.util.LocationUtil;
import com.mi.global.shop.util.MediaUriUtils;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.UrlUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.webview.BaseWebChromeClient;
import com.mi.global.shop.webview.BaseWebViewClient;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.webview.WebViewHelper;
import com.mi.global.shop.widget.BaseWebView;
import com.mi.global.shop.widget.CommonButton;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EmptyLoadingViewPlus;
import com.mi.global.shop.widget.dialog.CustomCloseDialog;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.permission.PermissionUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.download.DownloadManager;
import com.ximalaya.ting.android.xmpayordersdk.PayOrderManager;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/globalShop/webActivity")
public class WebActivity extends BaseActivity implements View.OnClickListener, FacebookCallback<Sharer.Result>, LoginManager.AccountListener {
    public static final String ORIGINAL_URL = "originalUrl";
    public static final String TAG = "WebActivity";

    /* renamed from: a  reason: collision with root package name */
    private static final String f5471a = "/buy/checkout/";
    private static final String b = "/buy/confirm?id=";
    private static final int c = 1;
    public static String currentUrl = "";
    private static final String d = "needClose";
    private static final String e = "needLocation";
    public static boolean needReload = false;
    private static final int s = 10000;
    public CallbackManager callbackManager;
    /* access modifiers changed from: private */
    public BaseWebView f;
    /* access modifiers changed from: private */
    public View g;
    /* access modifiers changed from: private */
    public View h;
    /* access modifiers changed from: private */
    public EmptyLoadingViewPlus i;
    private boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public boolean l = Utils.Network.isNetWorkConnected(ShopApp.g());
    /* access modifiers changed from: private */
    public String m;
    public ImageView mImageView;
    protected ProgressBar mProgressBar;
    public CommonButton mRetryBtn;
    public LinearLayout mRetryContainer;
    public CustomTextView mTextView;
    public FrameLayout mWebViewContainer;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public Boolean o = false;
    private boolean p = false;
    /* access modifiers changed from: private */
    public ValueCallback<Uri> q;
    /* access modifiers changed from: private */
    public ValueCallback<Uri[]> r;
    public ShareDialog shareDialog;
    private DownloadManagerReceiver t;
    private String u;

    public boolean gotoRN(String str) {
        return false;
    }

    public void jumpToPayment(String str, String str2) {
    }

    public void onCancel() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            setCustomContentView(R.layout.shop_web_activity);
            Intent intent = getIntent();
            if (!(intent == null || intent.getExtras() == null)) {
                Bundle extras = intent.getExtras();
                String string = extras.getString("url");
                if (gotoRN(string)) {
                    this.p = true;
                    finish();
                }
                this.o = Boolean.valueOf(extras.getBoolean("cart_webview", false));
                LogUtil.b(TAG, "get url from bundle:" + string);
                try {
                    if (Uri.parse(string).getQueryParameter(e).equals("1") && !TextUtils.isEmpty(LocationUtil.a())) {
                        string = string + "&location=" + LocationUtil.a();
                    }
                } catch (Exception unused) {
                }
                final String a2 = a(string);
                a();
                if (TextUtils.isEmpty(a2) || ((!a2.startsWith(ConnectionHelper.o) && !a2.startsWith(ConnectionHelper.p)) || !LoginManager.u().x() || !LoginManager.u().g() || !TextUtils.isEmpty(LoginManager.u().L()))) {
                    b(a2);
                } else {
                    new AsyncTask<String, String, String>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public String doInBackground(String... strArr) {
                            return null;
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public void onPostExecute(String str) {
                            WebActivity.this.b(a2);
                        }
                    }.execute(new String[0]);
                }
            }
            try {
                FacebookSdk.sdkInitialize(getApplicationContext());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.callbackManager = CallbackManager.Factory.create();
            this.shareDialog = new ShareDialog((Activity) this);
            this.shareDialog.registerCallback(this.callbackManager, this);
            LogUtil.b(TAG, "on create end");
            if (getIntent().getStringExtra("debug_cookie") != null && TextUtils.isEmpty(getIntent().getStringExtra("debug_cookie"))) {
                setDebugCookies(getIntent().getStringExtra("debug_cookie"));
            }
            if (getIntent().getIntExtra("debug_model", 0) != 0 && Build.VERSION.SDK_INT >= 19 && this.f != null) {
                BaseWebView baseWebView = this.f;
                BaseWebView.setWebContentsDebuggingEnabled(true);
            }
        } catch (Exception e3) {
            if (e3.getMessage() == null || !e3.getMessage().contains("MissingWebViewPackageException")) {
                MiToast.a((Context) this, (CharSequence) getResources().getString(R.string.loading_error), 0);
            } else {
                MiToast.a((Context) this, (CharSequence) getResources().getString(R.string.webview_tips_uploaing), 0);
            }
            finish();
        }
    }

    private String a(String str) {
        String str2;
        String c2 = c(str);
        if (c2 != null) {
            str2 = ConnectionHelper.i(c2);
        } else {
            str2 = ConnectionHelper.i(str);
        }
        LogUtil.b(TAG, "processing url:" + str2);
        return str2;
    }

    private void a() {
        this.mCartView.setOnClickListener(this);
        this.mBackView.setOnClickListener(this);
        this.mBackView.setVisibility(0);
        this.g = findViewById(R.id.title_bar_close_btn);
        this.g.setOnClickListener(this);
        this.g.setVisibility(0);
        this.h = findViewById(R.id.title_bar_refresh_btn);
        this.h.setOnClickListener(this);
        this.h.setVisibility(0);
        this.mProgressBar = (ProgressBar) findViewById(R.id.browser_progress_bar);
        this.i = (EmptyLoadingViewPlus) findViewById(R.id.background_loading);
        this.i.setVisibility(0);
        this.i.startLoading(false);
        this.mWebViewContainer = (FrameLayout) findViewById(R.id.web_view_container);
        this.mRetryContainer = (LinearLayout) findViewById(R.id.retry_container);
        this.mTextView = (CustomTextView) findViewById(R.id.net_access_text);
        this.mImageView = (ImageView) findViewById(R.id.net_unaccess_img);
        this.mRetryBtn = (CommonButton) findViewById(R.id.retry_btn);
        this.mRetryBtn.setText(R.string.shop_retry);
        this.mRetryBtn.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        this.f = (BaseWebView) findViewById(R.id.browser);
        if (this.f == null) {
            finish();
            return;
        }
        this.f.setWebViewClient(new InnerWebViewClient());
        this.f.setWebChromeClient(new InnerWebChromeClient());
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!PermissionUtil.a(WebActivity.this.getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    MiToast.a(WebActivity.this.getApplicationContext(), R.string.storage_permission_error, 1);
                    return;
                }
                WebActivity.this.b();
                DownloadUtils.a(WebActivity.this.getApplicationContext(), str, str3, str4);
            }
        });
        if (Build.VERSION.SDK_INT >= 19 && ShopApp.i()) {
            BaseWebView baseWebView = this.f;
            BaseWebView.setWebContentsDebuggingEnabled(true);
        }
        enableHardAccelerateMode(str);
        WebViewHelper.a((WebView) this.f);
        WebViewHelper.a(str);
        if (str.contains(ConnectionHelper.u) || str.contains(ConnectionHelper.v)) {
            str = UrlUtil.d(str);
        }
        setCookies(this);
        this.u = str;
        this.f.loadUrl(ConnectionHelper.j(str));
        handleWebViewVisiable(true);
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.t == null) {
            this.t = new DownloadManagerReceiver();
            registerReceiver(this.t, new IntentFilter(DownloadManager.D));
        }
    }

    public void enableHardAccelerateMode(String str) {
        if (!ConnectionHelper.a(str)) {
            this.f.setLayerType(1, (Paint) null);
        }
        if (getIntent().getIntExtra("needPlayVideo", 0) == 1) {
            this.f.setLayerType(2, (Paint) null);
        }
        if (SyncModel.hardwareAccelerateModel) {
            this.f.setLayerType(2, (Paint) null);
            if (!TextUtils.isEmpty(str) && SyncModel.inSoftWareUrls != null) {
                for (String contains : SyncModel.inSoftWareUrls) {
                    if (str.contains(contains)) {
                        this.f.setLayerType(1, (Paint) null);
                        return;
                    }
                }
            }
        }
    }

    public void handleWebViewVisiable(boolean z) {
        if (z) {
            this.mWebViewContainer.setVisibility(0);
            this.mRetryContainer.setVisibility(8);
            return;
        }
        this.mWebViewContainer.setVisibility(8);
        this.mRetryContainer.setVisibility(0);
    }

    public void startCartActivity() {
        if (!LocaleHelper.g()) {
            this.i.setVisibility(0);
            this.i.startLoading(false);
            if (this.f != null) {
                this.f.loadUrl(ConnectionHelper.j(ConnectionHelper.ay()));
            }
        } else if (this.o.booleanValue()) {
            setResult(-1);
            finish();
        } else {
            startActivityForResult(new Intent(this, ShoppingCartActivity.class), 22);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.title_bar_cart_view) {
            MiShopStatInterface.a("title_cart", getPageId());
            startCartActivity();
        } else if (view.getId() == R.id.title_bar_back) {
            MiShopStatInterface.a("title_back", getPageId());
            onBackPressed();
        } else if (view.getId() == R.id.title_bar_close_btn) {
            MiShopStatInterface.a("title_close", getPageId());
            finish();
        } else if (view.getId() == R.id.title_bar_refresh_btn) {
            MiShopStatInterface.a("title_refresh", getPageId());
            handleWebViewVisiable(true);
            this.i.setVisibility(0);
            this.i.startLoading(false);
            this.f.reload();
        } else if (view.getId() == R.id.retry_btn) {
            handleWebViewVisiable(true);
            LogUtil.b(TAG, "webView reloading, lastUrl:" + this.f.getLastUrl() + ",currentUrl:" + this.f.getCurrentUrl());
            this.i.setVisibility(0);
            this.i.startLoading(false);
            this.f.reload();
        }
    }

    public String getPageId() {
        String url = this.f.getUrl();
        return TextUtils.isEmpty(url) ? currentUrl : url;
    }

    public void onLogin(String str, String str2, String str3) {
        LogUtil.b(TAG, "on login success");
        super.onLogin(str, str2, str3);
        WebViewCookieManager.a(this, str, str2, str3);
        if (this.f != null) {
            LogUtil.b(TAG, "on login success reload:");
            this.f.post(new Runnable() {
                public void run() {
                    WebActivity.this.f.reload();
                }
            });
        }
    }

    public void onUserInfoUpdate(String str, String str2, String str3, int i2, String str4) {
        LogUtil.b(TAG, String.format("BaseActivity-userinfoupdate:%s, %s, %s,%d", new Object[]{str, str2, str3, Integer.valueOf(i2)}));
        super.onUserInfoUpdate(str, str2, str3, i2, str4);
        WebViewCookieManager.b(this, str4);
    }

    public void onLogout() {
        super.onLogout();
    }

    public void onResume() {
        super.onResume();
        LogUtil.b(TAG, "on resume");
        if (this.f != null) {
            this.f.onResume();
        }
        new SetCookiesTask(this).execute(new Void[0]);
    }

    public static void setCookies(Context context) {
        WebViewCookieManager.a();
        WebViewCookieManager.e(context);
        WebViewCookieManager.a(context);
        WebViewCookieManager.d(context);
        WebViewCookieManager.a(context, BaseActivity.shoppingCartNum);
        WebViewCookieManager.a(ConnectionHelper.E);
    }

    public void setDebugCookies(String str) {
        try {
            for (String str2 : str.split("#")) {
                WebViewCookieManager.a(this, str2.split(":")[0], str2.split(":")[1]);
            }
        } catch (Exception unused) {
            MiToast.a((Context) this, (CharSequence) "invalid cookie", 3000);
        }
    }

    public void onBackPressed() {
        if (this.f.canGoBack()) {
            handleWebViewVisiable(true);
            this.f.goBack();
            String currentTitle = this.f.getCurrentTitle();
            if (currentTitle != null) {
                currentTitle = currentTitle.trim();
            }
            setTitle((CharSequence) currentTitle);
            updateCartBadgeViewVisble(currentTitle);
            return;
        }
        finish();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        LogUtil.b(TAG, "onKeyDown keycoder:" + i2 + " Key Event:" + keyEvent.toString());
        return super.onKeyDown(i2, keyEvent);
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(TAG, "uberAppLink is empty");
            return null;
        }
        LogUtil.b(TAG, "uberAppLink:" + str);
        String str2 = "https://m.uber.com/";
        if (str.trim().indexOf("uber://") == 0) {
            PackageManager packageManager = ShopApp.g().getPackageManager();
            try {
                if (str.contains("&noapp=")) {
                    String substring = str.substring(str.indexOf("&noapp=") + "&noapp=".length());
                    try {
                        str = str.replace("&noapp=" + substring, "");
                        str2 = URLDecoder.decode(substring, "utf-8");
                        LogUtil.b(TAG, "uberAppLink get noappUrl:" + str2);
                    } catch (Exception unused) {
                        return substring;
                    }
                }
                LogUtil.b(TAG, "uberAppLink get uberUrl:" + str);
                packageManager.getPackageInfo("com.ubercab", 1);
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                finish();
            } catch (Exception unused2) {
                return str2;
            }
        }
        return null;
    }

    @SuppressLint({"DefaultLocale"})
    private class InnerWebViewClient extends BaseWebViewClient {
        private static final String c = "/cart/add/?id=";
        private AppEventsLogger b;
        private boolean d;

        private InnerWebViewClient() {
            this.d = true;
        }

        private void b(String str) {
            LogUtil.b(WebActivity.TAG, "recordCartEvent : url" + str);
            if (str.contains(c) && str.contains(a.b)) {
                if (this.b == null) {
                    this.b = AppEventsLogger.newLogger(WebActivity.this);
                }
                try {
                    String substring = str.substring(str.indexOf(c) + c.length(), str.indexOf(38));
                    Bundle bundle = new Bundle();
                    bundle.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, substring);
                    this.b.logEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART, 1.0d, bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            LogUtil.b(WebActivity.TAG, "Resource Request URL:" + str);
            if (str.contains(c)) {
                b(str);
            }
            WebActivity.this.d(str);
            String[] a2 = WebCache.a(str, !WebActivity.this.l);
            if (a2 != null) {
                return a(WebActivity.TAG, webView, str, a2);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            boolean z;
            String i = ConnectionHelper.i(str);
            WebViewHelper.a(str);
            LogUtil.b(WebActivity.TAG, "shouldOverrideUrlLoading newUrl:" + i);
            if (i != null && i.startsWith("intent://view/pmobpay")) {
                return true;
            }
            if (LocaleHelper.n() && Build.VERSION.SDK_INT >= 26 && i != null && i.startsWith("https://payments353.paysecure.ru/")) {
                return false;
            }
            if (LocaleHelper.q() && i != null && i.startsWith("https://payments.worldpay.com/app/hpp/integration/wpg/corporate")) {
                return false;
            }
            try {
                z = Uri.parse(str).getQueryParameter(WebActivity.d).equals("1");
            } catch (Exception unused) {
                z = false;
            }
            if (a(WebActivity.this, str) || WebActivity.this.gotoRN(str)) {
                return true;
            }
            if (!LocaleHelper.g() || !ConnectionHelper.g(str).booleanValue()) {
                if (!TextUtils.isEmpty(str) && str.contains("/in/user/orderview?order_id=")) {
                    String substring = str.substring(str.indexOf("/in/user/orderview?order_id=") + "/in/user/orderview?order_id=".length());
                    if (substring.indexOf(38) >= 0) {
                        substring = substring.substring(0, substring.indexOf(38));
                    }
                    Intent intent = new Intent(WebActivity.this, OrderViewActivity.class);
                    intent.putExtra("orderview_orderid", substring);
                    WebActivity.this.startActivity(intent);
                    return true;
                } else if (c(WebActivity.this, str)) {
                    if (WebActivity.this.i != null && WebActivity.this.i.getVisibility() == 0) {
                        WebActivity.this.i.setVisibility(8);
                    }
                    return true;
                } else if (i.equalsIgnoreCase(ConnectionHelper.an())) {
                    WebActivity.this.finish();
                    return true;
                } else if (i.equalsIgnoreCase(ConnectionHelper.au())) {
                    Intent intent2 = new Intent(WebActivity.this, MainTabActivity.class);
                    intent2.putExtra("go_usercentral", 1);
                    intent2.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    WebActivity.this.startActivity(intent2);
                    WebActivity.this.finish();
                    return true;
                } else if (LocaleHelper.g() && str.indexOf(ConnectionHelper.ay) >= 0) {
                    Intent intent3 = new Intent(WebActivity.this, CheckoutActivity.class);
                    if (ConnectionHelper.h(str).booleanValue()) {
                        intent3.putExtra(CheckoutActivity.ONE_CLICK_EXTRA, 1);
                    }
                    intent3.putExtra(WebActivity.ORIGINAL_URL, str);
                    WebActivity.this.startActivity(intent3);
                    if (z) {
                        WebActivity.this.finish();
                    }
                    return true;
                } else if (LocaleHelper.g() && str.indexOf("/buy/checkout/") >= 0) {
                    WebActivity.this.startActivityForResult(new Intent(WebActivity.this, CheckoutActivity.class), 16);
                    return true;
                } else if (str.indexOf("/in/buy/confirm?id=") >= 0) {
                    String substring2 = str.substring(str.indexOf("/in/buy/confirm?id=") + "/in/buy/confirm?id=".length());
                    if (substring2.indexOf(38) >= 0) {
                        substring2 = substring2.substring(0, substring2.indexOf(38));
                    }
                    String unused2 = WebActivity.this.m = substring2;
                    String unused3 = WebActivity.this.n = str.substring(0, str.indexOf("/in/buy/confirm?id="));
                    Intent intent4 = new Intent(WebActivity.this, ConfirmActivity.class);
                    intent4.putExtra("com.mi.global.shop.extra_buy_confirm_orderid", WebActivity.this.m);
                    WebActivity.this.startActivityForResult(intent4, 100);
                    WebActivity.this.updateShoppingCart(0);
                    return true;
                } else if (LocaleHelper.g() && !TextUtils.isEmpty(str) && str.contains(ConnectionHelper.cg)) {
                    WebActivity.this.startActivity(new Intent(WebActivity.this, OTExActivity.class));
                    return true;
                } else if (!ConnectionHelper.b(str)) {
                    WebActivity.this.i.setVisibility(0);
                    WebActivity.this.i.startLoading(false);
                    if (ConnectionHelper.c(str)) {
                        i = ConnectionHelper.j(i);
                    }
                    webView.loadUrl(i);
                    return true;
                } else {
                    try {
                        WebActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        if (TextUtils.isEmpty(WebActivity.this.f.getCurrentUrl())) {
                            WebActivity.this.finish();
                        }
                    } catch (Exception unused4) {
                    }
                    return true;
                }
            } else if (WebActivity.this.o.booleanValue()) {
                WebActivity.this.setResult(-1);
                WebActivity.this.finish();
                return false;
            } else {
                WebActivity.this.startActivityForResult(new Intent(WebActivity.this, ShoppingCartActivity.class), 22);
                return true;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!webView.getSettings().getLoadsImagesAutomatically()) {
                webView.getSettings().setLoadsImagesAutomatically(true);
            }
            if (WebActivity.this.k) {
                boolean unused = WebActivity.this.k = false;
                WebActivity.this.f.clearHistory();
            }
            super.onPageFinished(webView, str);
            LogUtil.b("InnerWebViewClient", "onPageFinished");
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            WebActivity.this.mProgressBar.setVisibility(0);
            WebActivity.currentUrl = str;
            if (this.d) {
                this.d = false;
            } else {
                MiShopStatInterface.b();
            }
            MiShopStatInterface.a((Context) WebActivity.this, str);
            LogUtil.b("InnerWebViewClient", "onPageStarted" + str);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            LogUtil.b(WebActivity.TAG, "errorCode:" + i + ", description:" + str + ", failingUrl:" + str2);
            LogUtil.b("onReceivedError", "errorCode:" + i + ", description:" + str + ", failingUrl:" + str2);
            if (i != -1 || !TextUtils.equals(str, "net::ERR_CACHE_MISS")) {
                webView.loadUrl("javascript:document.body.innerHTML=\"\"");
                WebActivity.this.handleWebViewVisiable(false);
                WebActivity.this.i.setVisibility(8);
                if (i == -1 && str2.contains("/buy/paygo")) {
                    WebActivity.this.onBackPressed();
                }
            } else if (webView.canGoBackOrForward(-1)) {
                webView.goBackOrForward(-1);
            }
        }

        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            LogUtil.b(WebActivity.TAG, "onReceivedError Target 23");
            LogUtil.b("onReceivedError", webResourceError.getDescription().toString() + webResourceRequest.getUrl().toString());
            if (webResourceRequest.isForMainFrame()) {
                onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
            }
        }

        @TargetApi(23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            if (webResourceRequest.isForMainFrame()) {
                onReceivedError(webView, webResourceResponse.getStatusCode(), webResourceResponse.getReasonPhrase().toString(), webResourceRequest.getUrl().toString());
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (ShopApp.j()) {
                sslErrorHandler.proceed();
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }
    }

    private class InnerWebChromeClient extends BaseWebChromeClient {
        private InnerWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (WebActivity.this.mProgressBar != null) {
                WebActivity.this.mProgressBar.setProgress(i);
                if (i == 100) {
                    WebActivity.this.mProgressBar.setVisibility(4);
                    WebActivity.this.i.setVisibility(8);
                }
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            LogUtil.b(WebActivity.TAG, "onReceivedTitle : " + str);
            super.onReceivedTitle(webView, str);
            WebActivity.this.i.setVisibility(8);
            if (WebActivity.this.k) {
                boolean unused = WebActivity.this.k = false;
                WebActivity.this.f.clearHistory();
            }
            if (str != null) {
                str = str.trim();
            }
            ((WebActivity) webView.getContext()).setTitle((CharSequence) str);
            WebActivity.this.updateCartBadgeViewVisble(str);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            LogUtil.b("openFileChooser < 3.0");
            ValueCallback unused = WebActivity.this.q = valueCallback;
            WebActivity.this.c();
        }

        public void openFileChooser(ValueCallback valueCallback, String str) {
            LogUtil.b("openFileChooser 3.0");
            ValueCallback unused = WebActivity.this.q = valueCallback;
            WebActivity.this.c();
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            LogUtil.b("openFileChooser 4.1");
            ValueCallback unused = WebActivity.this.q = valueCallback;
            WebActivity.this.c();
        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            LogUtil.b("openFileChooser 5.0");
            ValueCallback unused = WebActivity.this.r = valueCallback;
            WebActivity.this.c();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        MultiImageSelector.a().a(true).c().a(1).a(this, 10000);
    }

    @TargetApi(21)
    private void a(int i2, int i3, Intent intent) {
        Uri[] uriArr;
        ArrayList<String> stringArrayListExtra;
        if (this.r != null) {
            if (i3 != -1 || intent == null || (stringArrayListExtra = intent.getStringArrayListExtra("select_result")) == null || stringArrayListExtra.size() <= 0) {
                uriArr = null;
            } else {
                uriArr = new Uri[stringArrayListExtra.size()];
                for (int i4 = 0; i4 < stringArrayListExtra.size(); i4++) {
                    File file = new File(stringArrayListExtra.get(i4));
                    LogUtil.b("get uri picker:" + stringArrayListExtra.get(i4));
                    if (file.exists()) {
                        uriArr[i4] = Uri.fromFile(file);
                    } else {
                        uriArr[i4] = MediaUriUtils.a(this, stringArrayListExtra.get(i4));
                    }
                }
            }
            LogUtil.b("Image picker:" + Arrays.toString(uriArr));
            this.r.onReceiveValue(uriArr);
            this.r = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        MiShopStatInterface.a((Context) this, this.f.getUrl());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.i != null && this.i.getVisibility() == 0) {
            this.i.setVisibility(8);
        }
        if (this.f != null) {
            this.f.onPause();
        }
        LogUtil.b(TAG, "onPause");
    }

    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.b(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        if (this.f != null) {
            ViewParent parent = this.f.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.f);
            }
            LogUtil.b(TAG, PayOrderManager.a.f);
            this.f.stopLoading();
            this.f.removeAllViews();
            this.f.destroy();
        }
        if (this.t != null) {
            unregisterReceiver(this.t);
            this.t = null;
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri uri;
        new SetCookiesTask(this).execute(new Void[0]);
        if (i2 == CallbackManagerImpl.RequestCodeOffset.Share.toRequestCode()) {
            super.onActivityResult(i2, i3, intent);
            this.callbackManager.onActivityResult(i2, i3, intent);
        } else if (i2 != 16) {
            if (i2 == 10000) {
                if (this.q == null && this.r == null) {
                    if (TextUtils.isEmpty(this.f.getUrl()) || !this.f.getUrl().contains(ConnectionHelper.cm)) {
                        MiToast.a((Context) this, R.string.webview_tips_upload_data_lost_custom, 1);
                    } else {
                        new CustomCloseDialog.Builder(this).b(getString(R.string.webview_tips_upload_data_lost)).a((Boolean) true).a().show();
                    }
                } else if (this.r != null) {
                    a(i2, i3, intent);
                } else if (intent != null) {
                    ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("select_result");
                    if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
                        String str = stringArrayListExtra.get(0);
                        if (MediaUriUtils.a(str)) {
                            uri = Uri.parse(str);
                        } else {
                            File file = new File(str);
                            if (file.exists()) {
                                uri = Uri.fromFile(file);
                            }
                        }
                        LogUtil.b("Image picker:" + uri);
                        this.q.onReceiveValue(uri);
                        this.q = null;
                    }
                    uri = null;
                    LogUtil.b("Image picker:" + uri);
                    this.q.onReceiveValue(uri);
                    this.q = null;
                }
            } else if (intent != null && intent.hasExtra("result")) {
                String stringExtra = intent.getStringExtra("result");
                LogUtil.b(TAG, "------ webActivity onActivityResult " + stringExtra);
                try {
                    String obj = new JSONObject(stringExtra).get("url").toString();
                    if (obj != null && !obj.equals("")) {
                        String j2 = ConnectionHelper.j(obj);
                        LogUtil.b("--------- url:" + j2);
                        this.f.loadUrl(j2);
                        this.f.clearHistory();
                        this.k = true;
                    }
                } catch (JSONException unused) {
                }
            } else if (intent == null && this.n != null) {
                this.f.loadUrl(ConnectionHelper.j(this.n + "/in/user/orderview?order_id=" + this.m));
            }
        }
    }

    public void onSuccess(Sharer.Result result) {
        MiToast.a((Context) this, R.string.facebook_sharing_success, 0);
    }

    public void onError(FacebookException facebookException) {
        MiToast.a((Context) this, R.string.facebook_sharing_fail, 0);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.i.setVisibility(8);
        this.mProgressBar.setVisibility(8);
    }

    public class DownloadManagerReceiver extends BroadcastReceiver {
        public DownloadManagerReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.D.equals(intent.getAction())) {
                WebActivity.this.d();
                int a2 = DownloadUtils.a(context, intent.getLongExtra(DownloadManager.G, -1));
                if (a2 == 16) {
                    LogUtil.a("download failed!!!");
                    MiToast.a(context, R.string.webview_tips_download_failed, 1);
                } else if (a2 == 8) {
                    LogUtil.b("download success");
                    MiToast.a(context, R.string.webview_tips_download_success, 1);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(final String str) {
        if (!TextUtils.isEmpty(str) && LocaleHelper.g()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (str.contains(ConnectionHelper.bZ)) {
                        WebActivity.this.mCartView.setVisibility(8);
                        WebActivity.this.g.setVisibility(8);
                        WebActivity.this.h.setVisibility(8);
                        WebActivity.this.mForgetPwd.setVisibility(0);
                        WebActivity.this.mForgetPwd.setTextColor(WebActivity.this.getResources().getColor(R.color.orange_red));
                        WebActivity.this.mForgetPwd.setText(WebActivity.this.getResources().getString(R.string.user_exchange_coupon_record));
                        WebActivity.this.mForgetPwd.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                WebActivity.this.f.loadUrl(ConnectionHelper.j(ConnectionHelper.bm()));
                            }
                        });
                    } else if (!str.contains(ConnectionHelper.ca)) {
                        if (str.contains(ConnectionHelper.v) || str.contains(ConnectionHelper.u)) {
                            WebActivity.this.mCartView.setVisibility(0);
                            WebActivity.this.g.setVisibility(0);
                            WebActivity.this.h.setVisibility(0);
                            WebActivity.this.mForgetPwd.setVisibility(8);
                        }
                    }
                }
            });
        }
    }

    private static class SetCookiesTask extends AsyncTask<Void, Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<Context> f5479a;

        public SetCookiesTask(Context context) {
            this.f5479a = new WeakReference<>(context);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            Context context = (Context) this.f5479a.get();
            if (context == null) {
                return null;
            }
            WebActivity.setCookies(context);
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
        }
    }
}
