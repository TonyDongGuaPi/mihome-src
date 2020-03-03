package com.xiaomi.smarthome.operation.js_sdk.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.intercept.UrlInterceptorChainHelper;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.MibiInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.lifecycle.IWebPageAction;
import com.xiaomi.smarthome.operation.js_sdk.network.NetworkHelper;
import com.xiaomi.smarthome.operation.js_sdk.share.ShareHelper;
import com.xiaomi.smarthome.operation.js_sdk.titlebar.ITitleBar;

public class CommonWebView extends BaseWebView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21067a = "CommonWebView";
    private ShareHelper b;
    private NetworkHelper c;
    /* access modifiers changed from: private */
    public UrlInterceptorChainHelper d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public String f = "";
    /* access modifiers changed from: private */
    public String g;
    private boolean h = true;
    private String i;
    private MibiInterceptor j;
    private IWebPageAction k;
    private ITitleBar l;
    private final WebChromeClient m = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            ITitleBar titleBarImpl = CommonWebView.this.getTitleBarImpl();
            if (titleBarImpl != null) {
                BaseWebView.runOnUiThread(new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ITitleBar.this.onReceivedTitle(this.f$1);
                    }
                });
            }
        }

        public void onProgressChanged(WebView webView, int i) {
            ITitleBar titleBarImpl = CommonWebView.this.getTitleBarImpl();
            if (titleBarImpl != null) {
                BaseWebView.runOnUiThread(new Runnable(i) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ITitleBar.this.onProgressChanged(this.f$1);
                    }
                });
            }
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CommonWebView.this.getContext());
            builder.b((CharSequence) str2);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(jsResult) {
                private final /* synthetic */ JsResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.confirm();
                }
            });
            builder.a(false);
            builder.b().show();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CommonWebView.this.getContext());
            builder.b((CharSequence) str2);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(jsResult) {
                private final /* synthetic */ JsResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.confirm();
                }
            });
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(jsResult) {
                private final /* synthetic */ JsResult f$0;

                {
                    this.f$0 = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.cancel();
                }
            });
            builder.a(false);
            builder.b().show();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            Toast.makeText(CommonWebView.this.getContext(), str2, 0).show();
            return true;
        }
    };
    private final WebViewClient n = new WebViewClient() {
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.d(CommonWebView.f21067a, "onPageStarted url: " + str);
            if (CommonWebView.this.e) {
                if (CommonWebView.this.f.equals(CommonWebView.this.g)) {
                    Log.d(CommonWebView.f21067a, "onPageStarted url finish:" + CommonWebView.this.g);
                    ((Activity) CommonWebView.this.getContext()).finish();
                    return;
                } else if (str.startsWith("https://account.xiaomi.com/pass/serviceLogin")) {
                    boolean unused = CommonWebView.this.e = false;
                    if (CommonWebView.this.canGoBack()) {
                        CommonWebView.this.goBack();
                        return;
                    }
                    return;
                }
            }
            CommonWebView.this.d.a().a(webView, str, bitmap);
            super.onPageStarted(webView, str, bitmap);
            boolean unused2 = CommonWebView.this.e = false;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.d(CommonWebView.f21067a, "shouldOverrideUrlLoading url: " + str);
            boolean unused = CommonWebView.this.e = false;
            if (CommonWebView.this.d.a().a(webView, str) || super.shouldOverrideUrlLoading(webView, str)) {
                return true;
            }
            return false;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            boolean unused = CommonWebView.this.e = false;
            if (CommonWebView.this.d.a().a(webView, webResourceRequest) || super.shouldOverrideUrlLoading(webView, webResourceRequest)) {
                return true;
            }
            return false;
        }

        public void onPageFinished(WebView webView, String str) {
            Log.d(CommonWebView.f21067a, "onPageFinished url: " + str);
            String unused = CommonWebView.this.f = BaseWebView.getPath(str);
            boolean unused2 = CommonWebView.this.e = false;
            CommonWebView.this.d.a().b(webView, str);
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            CommonWebView.this.d.a().a(webView, str, str2, str3);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            CommonWebView.this.d.a().a(webView, i, str, str2);
        }
    };

    public CommonWebView(Context context) {
        super(context);
        a(context);
    }

    public CommonWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CommonWebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }

    private void a(Context context) {
        this.c = new NetworkHelper(this);
        this.d = new UrlInterceptorChainHelper();
        this.b = new ShareHelper(this, context);
        this.d.a(context);
        this.d.a().a(ShareHelper.class.getSimpleName(), (IUrlInterceptor) this.b);
        this.j = (MibiInterceptor) this.d.a().a(MibiInterceptor.class);
        a();
    }

    @SuppressLint({"AddJavascriptInterface"})
    private void a() {
        setWebViewClient(this.n);
        setWebChromeClient(this.m);
        addJavascriptInterface(new CloudVideoJavaScriptInterface((Activity) getContext(), this), "_cloud_video_interface");
        addJavascriptInterface(new CommonJavaScriptInterface((Activity) getContext(), this), "_native_interface");
    }

    public void addUrlInterceptor(String str, IUrlInterceptor iUrlInterceptor) {
        this.d.a().a(str, iUrlInterceptor);
    }

    public void loadUrl(String str) {
        super.loadUrl(str);
        if (this.h) {
            this.h = false;
            this.g = getPath(str);
        }
    }

    public void onResume() {
        super.onResume();
        this.d.a().a();
    }

    public void destroy() {
        super.destroy();
        this.c.a();
        this.d.a().b();
    }

    public boolean onBackPressed() {
        if (!canGoBack()) {
            return false;
        }
        this.e = true;
        goBack();
        return true;
    }

    public void setWebPageActionImpl(IWebPageAction iWebPageAction) {
        this.k = iWebPageAction;
    }

    public IWebPageAction getWebPageActionImpl() {
        return this.k;
    }

    public void setTitleBarImpl(ITitleBar iTitleBar) {
        this.l = iTitleBar;
    }

    public ITitleBar getTitleBarImpl() {
        return this.l;
    }

    public NetworkHelper getNetworkHelper() {
        return this.c;
    }

    public String getDid() {
        return this.i;
    }

    public void setDid(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.i = str;
            if (this.j != null) {
                this.j.a(this.i);
            }
            CookieSyncManager.createInstance(getContext());
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            setCookie(instance, "did", str, ".mi.com");
        }
    }

    public void doShare() {
        this.b.c();
    }

    public void onShareOptionsReady(String str) {
        this.b.a(str);
    }
}
