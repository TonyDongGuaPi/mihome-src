package com.xiaomi.smarthome.miio.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.UserAgentUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.lang.ref.WeakReference;
import java.util.Locale;
import org.json.JSONObject;

public class OperatingWebActivity extends BaseActivity {
    private static final int c = 1;
    private static String d;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public View f19625a;
    /* access modifiers changed from: private */
    public WebView b;
    boolean mIsGoBack = false;
    int mPreProgress;
    ProgressBar mProgressBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_operating_web_layout);
        a();
    }

    private void a() {
        this.f19625a = findViewById(R.id.loading_content);
        this.b = (WebView) findViewById(R.id.content_webview);
        this.mProgressBar = (ProgressBar) findViewById(R.id.loading_progress);
        this.mProgressBar.setIndeterminateDrawable((Drawable) null);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_product_categories);
        initWebView();
    }

    public void handleMessage(Message message) {
        if (message.what == 1 && this.mProgressBar.getProgress() < this.mPreProgress) {
            this.mProgressBar.setProgress(this.mProgressBar.getProgress() + 1);
            this.mProgressBar.postInvalidate();
            this.mHandler.sendEmptyMessageDelayed(1, (long) ((((int) (Math.random() * 5.0d)) + 2) * 50));
        }
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"AddJavascriptInterface"})
    public void initWebView() {
        WebSettings settings = this.b.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
        settings.setUserAgentString(a(settings.getUserAgentString()));
        if (GlobalSetting.E && Build.VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.b.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                if (!TextUtils.isEmpty(str)) {
                    OperatingWebActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            }
        });
        this.b.addJavascriptInterface(new JavaScriptInterface(this), "_native_interface");
        this.b.requestFocus();
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
        this.b.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                WebView access$000 = OperatingWebActivity.this.b;
                if (access$000 != null) {
                    if (access$000.getVisibility() == 8 || OperatingWebActivity.this.f19625a.getVisibility() == 0) {
                        access$000.setVisibility(0);
                        access$000.requestFocus(130);
                        access$000.setOnTouchListener(new View.OnTouchListener() {
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                switch (motionEvent.getAction()) {
                                    case 0:
                                    case 1:
                                        if (view.hasFocus()) {
                                            return false;
                                        }
                                        view.requestFocus();
                                        return false;
                                    default:
                                        return false;
                                }
                            }
                        });
                        OperatingWebActivity.this.f19625a.setVisibility(8);
                    }
                }
            }
        });
        this.b.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i >= OperatingWebActivity.this.mPreProgress) {
                    OperatingWebActivity.this.mProgressBar.setVisibility(0);
                    if (OperatingWebActivity.this.mHandler != null) {
                        OperatingWebActivity.this.mHandler.removeMessages(1);
                        if (i >= OperatingWebActivity.this.mProgressBar.getProgress()) {
                            double d = (double) i;
                            Double.isNaN(d);
                            int i2 = (int) (d * 1.1d);
                            if (i2 <= 99) {
                                OperatingWebActivity.this.mProgressBar.setProgress(i2);
                                OperatingWebActivity.this.mProgressBar.postInvalidate();
                            }
                        }
                    }
                }
                if (i >= 90 && OperatingWebActivity.this.mHandler != null) {
                    OperatingWebActivity.this.mHandler.removeMessages(1);
                    OperatingWebActivity.this.mProgressBar.setVisibility(8);
                }
            }
        });
        String str = "https://home.mi.com/app_webview/newoperation/index.html";
        if (GlobalSetting.E) {
            str = "http://st.iot.home.mi.com/views/operation.html#/";
        }
        this.b.loadUrl(str);
    }

    static class JavaScriptInterface {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<OperatingWebActivity> f19630a = null;
        String b = "{}";

        public JavaScriptInterface(OperatingWebActivity operatingWebActivity) {
            this.f19630a = new WeakReference<>(operatingWebActivity);
            SystemApi a2 = SystemApi.a();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("os_name", "android");
                jSONObject.put("app_version", a2.e(operatingWebActivity));
                jSONObject.put("os_version", a2.f());
                jSONObject.put(DTransferConstants.l, a2.e());
                this.b = jSONObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public boolean openYoupinPage(final String str) {
            OperatingWebActivity operatingWebActivity;
            if (TextUtils.isEmpty(str) || (operatingWebActivity = (OperatingWebActivity) this.f19630a.get()) == null || !operatingWebActivity.isValid()) {
                return false;
            }
            operatingWebActivity.runOnUiThread(new Runnable() {
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
            OperatingWebActivity operatingWebActivity = (OperatingWebActivity) this.f19630a.get();
            if (operatingWebActivity != null && operatingWebActivity.isValid()) {
                operatingWebActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        OperatingWebActivity operatingWebActivity = (OperatingWebActivity) JavaScriptInterface.this.f19630a.get();
                        if (operatingWebActivity != null && operatingWebActivity.isValid()) {
                            if (z) {
                                operatingWebActivity.forceBack();
                            } else {
                                operatingWebActivity.onBackPressed();
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
            OperatingWebActivity operatingWebActivity = (OperatingWebActivity) this.f19630a.get();
            if (operatingWebActivity == null || !operatingWebActivity.isValid() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return false;
            }
            CoreApi.a().s();
            Intent intent = new Intent(operatingWebActivity, CommonShareActivity.class);
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
            operatingWebActivity.startActivity(intent);
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

    private void a(CookieManager cookieManager, String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null) {
            cookieManager.setCookie(str3, str + "=" + str2 + "; domain=" + str3);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            Class.forName("android.webkit.WebView").getMethod("onPause", (Class[]) null).invoke(this.b, (Object[]) null);
            this.b.loadUrl("");
        } catch (Exception unused) {
        }
        ViewParent parent = this.b.getParent();
        this.b.removeAllViews();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.b);
        }
        this.b.destroy();
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public void forceBack() {
        try {
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        try {
            WebView webView = this.b;
            if (webView == null || !webView.canGoBack()) {
                super.onBackPressed();
                return;
            }
            this.mIsGoBack = true;
            webView.goBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String a(String str) {
        if (d == null) {
            d = str + UserAgentUtil.a((Context) this) + " XiaoMi/HybridView/";
        }
        return d;
    }
}
