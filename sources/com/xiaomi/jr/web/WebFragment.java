package com.xiaomi.jr.web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.common.net.HttpHeaders;
import com.miui.supportlite.app.AlertDialog;
import com.qti.location.sdk.IZatTestService;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.IWebLoginProcessor;
import com.xiaomi.jr.account.SimpleAccountLoginCallback;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.base.BaseFragment;
import com.xiaomi.jr.base.pulltorefresh.PullToRefreshBase;
import com.xiaomi.jr.base.view.LoadingErrorView;
import com.xiaomi.jr.base.view.LoadingView;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.app.PhotoManager;
import com.xiaomi.jr.common.utils.BitmapUtils;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.dialog.DialogManager;
import com.xiaomi.jr.http.certificate.CertificatePinning;
import com.xiaomi.jr.http.certificate.CertificateUtils;
import com.xiaomi.jr.http.netopt.NetworkDiagnosis;
import com.xiaomi.jr.hybrid.HybridCallbackManager;
import com.xiaomi.jr.hybrid.HybridContext;
import com.xiaomi.jr.hybrid.JsBridge;
import com.xiaomi.jr.hybrid.NativeInterface;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardManager;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardView;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.jr.ui.ActionBarCustomizer;
import com.xiaomi.jr.web.ObservableWebView;
import com.xiaomi.jr.web.WebFragment;
import com.xiaomi.jr.web.pulltorefresh.PullToRefreshWebView;
import com.xiaomi.jr.web.sms.SMSMonitor;
import com.xiaomi.jr.web.staticresource.ResourceChecker;
import com.xiaomi.jr.web.staticresource.ResourceUpdateManager;
import com.xiaomi.jr.web.staticresource.StaticResourceUtils;
import com.xiaomi.jr.web.utils.MifiWebUtils;
import com.xiaomi.jr.web.utils.UserAgentUtils;
import com.xiaomi.jr.web.utils.WebConstants;
import com.xiaomi.jr.web.webkit.WebChromeClient;
import com.xiaomi.jr.web.webkit.WebViewClient;
import com.xiaomi.jr.web.webpbackport.WebpFetcher;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WebFragment extends BaseFragment implements NativeInterface {
    protected static final String k = "MiFiWebFragment";
    private static final String n = "if (window.onPause instanceof Function) {onPause()}";
    private static final String o = "if (window.onResume instanceof Function) {onResume()}";
    private static final String p = "window.onReload instanceof Function ? window.onReload() : window.MiFiJsBridge && (window.MiFiJsBridge.lookup('Reload', 'reload') == 0) && window.MiFiJsBridge.invoke('Reload', 'reload', null, null)";
    private static final String q = "onBackPressed()";
    private static final String r = "onSafeKeyboardKey(%d)";
    private static final String s = "setMessageCode";
    private static final int t = 100;
    private static final int u = 100;
    private static final String v = "view_created_before_last_destroy";
    private static final String w = "webview_database";
    private static final String x = "https://wx.tenpay.com/";
    private static boolean y;
    private PullToRefreshBase.OnRefreshListener<ObservableWebView> A;
    /* access modifiers changed from: private */
    public LoadingView B;
    private LoadingErrorView C;
    private boolean D;
    private boolean E;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;
    private boolean I = false;
    private JsBridge J;
    private HybridContext K;
    /* access modifiers changed from: private */
    public SMSMonitor.SmsVerificationCodeListener L;
    private boolean M;
    /* access modifiers changed from: private */
    public boolean N;
    private View O;
    /* access modifiers changed from: private */
    public SafeKeyboardView P;
    private SafeKeyboardView.OnKeyEventListener Q = new SafeKeyboardView.OnKeyEventListener() {
        public final void onKeyEvent(int i) {
            WebFragment.this.c(i);
        }
    };
    /* access modifiers changed from: private */
    public String R;
    /* access modifiers changed from: private */
    public Map<String, String> S = new HashMap();
    /* access modifiers changed from: private */
    public boolean T = false;
    private boolean U;
    private Handler V = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (ActivityChecker.a((Activity) WebFragment.this.getActivity()) || message.what == 11) {
                switch (message.what) {
                    case 0:
                        WebFragment.this.b((String) message.obj);
                        return;
                    case 1:
                        WebFragment.this.c(((Boolean) message.obj).booleanValue());
                        return;
                    case 2:
                        WebFragment.this.g(((Boolean) message.obj).booleanValue());
                        return;
                    case 3:
                        Map map = (Map) message.obj;
                        WebFragment.this.c((String) map.get("url"), (String) map.get("title"));
                        return;
                    case 4:
                        if (!WebFragment.this.j.a()) {
                            WebFragment.this.f();
                            WebFragment.this.getActivity().finish();
                            return;
                        }
                        MifiLog.d(WebFragment.k, "Can not close page, since it's home page");
                        return;
                    case 5:
                        Bundle bundle = (Bundle) message.obj;
                        WebFragment.this.d(bundle.getString(WebEvent.A), bundle.getString("value"));
                        return;
                    case 6:
                        WebFragment.this.e(((Boolean) message.obj).booleanValue());
                        return;
                    case 7:
                        WebFragment.this.d(((Boolean) message.obj).booleanValue());
                        return;
                    case 8:
                        boolean unused = WebFragment.this.N = true;
                        MifiLog.b("TestLoading", "h5 start loading, hideContent=" + message.obj);
                        WebFragment.this.a(false, false, ((Boolean) message.obj).booleanValue());
                        return;
                    case 9:
                        boolean unused2 = WebFragment.this.N = false;
                        MifiLog.b("TestLoading", "h5 stop loading");
                        WebFragment.this.k(false);
                        return;
                    case 10:
                        WebFragment.this.c((String) message.obj);
                        return;
                    case 11:
                        Bundle bundle2 = (Bundle) message.obj;
                        boolean z = bundle2.getBoolean("reload");
                        String string = bundle2.getString(WebEvent.D);
                        int i = bundle2.getInt(WebEvent.E, -1);
                        if (WebFragment.this.j != null) {
                            WebFragment.this.j.getPageReloader().a(z, string, i);
                            return;
                        }
                        return;
                    case 12:
                        WebFragment.this.i(true);
                        return;
                    case 13:
                        if (WebFragment.this.j == null) {
                            return;
                        }
                        if (WebFragment.this.j.a()) {
                            boolean unused3 = WebFragment.this.T = true;
                            WebFragment.this.j.reload(WebFragment.this);
                            return;
                        }
                        WebFragment.this.j.getPageReloader().a(true);
                        return;
                    case 15:
                        Bundle bundle3 = (Bundle) message.obj;
                        String unused4 = WebFragment.this.c = bundle3.getString("title");
                        WebFragment.this.a(WebFragment.this.c, bundle3.getString("subtitle"));
                        return;
                    case 16:
                        WebFragment.this.f(((Boolean) message.obj).booleanValue());
                        return;
                    case 17:
                        Bundle bundle4 = (Bundle) message.obj;
                        WebFragment.this.a(bundle4.getString(WebEvent.F), bundle4.getLong("timeout"), message.arg1);
                        return;
                    case 18:
                        if (WebFragment.this.L != null) {
                            SMSMonitor.a(WebFragment.this.getContext());
                            WebFragment.this.a(message.arg1, (String) null, "timeout");
                            SMSMonitor.SmsVerificationCodeListener unused5 = WebFragment.this.L = null;
                            return;
                        }
                        return;
                    case 20:
                        if (WebFragment.this.P != null) {
                            SafeKeyboardManager.a(WebFragment.this.P, (String) message.obj);
                            return;
                        }
                        return;
                    case 21:
                        if (WebFragment.this.P != null) {
                            SafeKeyboardManager.a(WebFragment.this.P);
                            return;
                        }
                        return;
                    case 22:
                        if (WebFragment.this.j != null) {
                            WebFragment.this.j.getAppDelegate().a(WebFragment.this.getActivity());
                            return;
                        }
                        return;
                    case 23:
                        if (WebFragment.this.j != null) {
                            WebFragment.this.j.getAppDelegate().b();
                            return;
                        }
                        return;
                    case 24:
                        WebFragment.this.startActivityForResult((Intent) message.obj, message.arg1);
                        return;
                    case 25:
                        WebFragment.this.startActivity((Intent) message.obj);
                        return;
                    case 26:
                        if (WebFragment.this.j != null) {
                            WebFragment.this.j.getAppDelegate().d();
                            return;
                        }
                        return;
                    case 27:
                        Bundle bundle5 = (Bundle) message.obj;
                        String string2 = bundle5.getString("title");
                        String string3 = bundle5.getString("message");
                        if (WebFragment.this.j != null) {
                            WebFragment.this.j.getAppDelegate().e().a(WebFragment.this.getActivity(), string2, string3);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private boolean W;
    protected ObservableWebView l;
    protected ViewGroup m;
    private PullToRefreshWebView z;

    public void a(int i) {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 19 && MifiWebUtils.a()) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(int i) {
        b(String.format(Locale.getDefault(), r, new Object[]{Integer.valueOf(i)}));
    }

    public void a(PullToRefreshBase.OnRefreshListener onRefreshListener) {
        this.A = onRefreshListener;
    }

    private class MiFiWebViewClient extends WebViewClient {
        private static final String d = "%s_load_v2";
        private static final String e = "%s_load_with_login_v2";
        private boolean f;
        private long g;
        private ResourceChecker.Listener h;
        private SimpleAccountLoginCallback i;

        MiFiWebViewClient() {
            a(new IWebLoginProcessor.WebLoginListener() {
                public final void onWebLoginResult(boolean z, String str) {
                    WebFragment.MiFiWebViewClient.this.a(z, str);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(boolean z, String str) {
            if (WebFragment.this.l != null) {
                if (z) {
                    WebFragment.this.a(str);
                    return;
                }
                b((WebView) WebFragment.this.l, false);
                if (ActivityChecker.a((Activity) WebFragment.this.getActivity())) {
                    Utils.a((DialogFragment) new AlertDialog.Builder(WebFragment.this.getActivity()).a(false).a(R.string.web_login_failure_title).b(XiaomiAccountManager.a().c() ? R.string.system_web_login_failure_message : R.string.local_web_login_failure_message).a(R.string.web_login_failure_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            WebFragment.MiFiWebViewClient.this.a(dialogInterface, i);
                        }
                    }).a(), WebFragment.this.getFragmentManager(), "weblogin_failure");
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
            WebFragment.this.getActivity().finish();
        }

        @TargetApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            String uri = webResourceRequest.getUrl().toString();
            MifiLog.c(WebFragment.k, "intercept " + uri + ", headers=" + webResourceRequest.getRequestHeaders());
            CookieManager instance = CookieManager.getInstance();
            MifiLog.c("TestCookie", "intercept getCookie: " + instance.getCookie(uri) + ", url=" + uri);
            WebResourceResponse a2 = a(uri);
            if (a2 != null) {
                return a2;
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            WebResourceResponse a2 = a(str);
            if (a2 != null) {
                return a2;
            }
            return super.shouldInterceptRequest(webView, str);
        }

        private WebResourceResponse a(String str) {
            byte[] bArr;
            if (str.endsWith("/favicon.ico")) {
                return new WebResourceResponse("text/plain", "UTF-8", new ByteArrayInputStream(new byte[0]));
            }
            ByteArrayInputStream byteArrayInputStream = null;
            if (str.contains(".webp")) {
                MifiLog.b(WebFragment.k, "intercept webp url: " + str);
                if (Build.VERSION.SDK_INT < 17) {
                    return new WebResourceResponse("", "", WebpFetcher.a().a(WebFragment.this.getActivity().getApplicationContext(), str));
                }
            } else {
                Uri parse = Uri.parse(str);
                if (Constants.g.equals(parse.getScheme())) {
                    String path = parse.getPath();
                    MifiLog.b(WebFragment.k, "file path: " + path);
                    if ("photo".equals(parse.getHost())) {
                        String path2 = parse.getPath();
                        String substring = path2.substring(path2.lastIndexOf(47) + 1);
                        Bitmap a2 = PhotoManager.a(substring);
                        Bitmap b = PhotoManager.b(substring);
                        if (a2 == null || a2.isRecycled()) {
                            bArr = (b == null || b.isRecycled()) ? null : BitmapUtils.a(b);
                        } else {
                            bArr = BitmapUtils.a(a2);
                        }
                        if (bArr != null) {
                            byteArrayInputStream = new ByteArrayInputStream(bArr);
                        }
                    }
                    if (byteArrayInputStream == null) {
                        byteArrayInputStream = FileUtils.d(path);
                    }
                    return new WebResourceResponse("", "", byteArrayInputStream);
                } else if (!ResourceUpdateManager.f1457a) {
                    MifiLog.a(WebFragment.k, "try load local resource " + str);
                    StaticResourceUtils.Result a3 = StaticResourceUtils.a(WebFragment.this.getContext(), str);
                    if (a3.b != null) {
                        if (a3.f11079a) {
                            this.h = new ResourceChecker.Listener(str) {
                                private final /* synthetic */ String f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void onResult(boolean z) {
                                    WebFragment.MiFiWebViewClient.this.b(this.f$1, z);
                                }
                            };
                            ResourceChecker.a(WebFragment.this.getActivity().getApplicationContext(), str, this.h);
                        }
                        MifiLog.a(WebFragment.k, "return local resource " + str);
                        return new WebResourceResponse("", "", a3.b);
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(String str, boolean z) {
            if (z && ActivityChecker.a((Activity) WebFragment.this.getActivity())) {
                if (TextUtils.equals(UrlUtils.b(str), UrlUtils.b(WebFragment.this.d))) {
                    MifiLog.b("TestModified", "modified, reload url: " + str);
                    WebFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public final void run() {
                            WebFragment.MiFiWebViewClient.this.a();
                        }
                    });
                }
                ResourceUpdateManager.a(WebFragment.this.getActivity().getApplicationContext());
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            WebFragment.this.i(true);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (TextUtils.equals(Uri.parse(str).getPath(), "/loan/loanh5/index.html")) {
                CookieManager instance = CookieManager.getInstance();
                MifiLog.c("TestCookie", "onPageStart getCookie: " + instance.getCookie(str) + ", url=" + str);
            }
            String unused = WebFragment.this.R = str;
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (MifiWebUtils.c(str) || !str.startsWith("http")) {
                DeeplinkUtils.openExternalUrl(WebFragment.this, str);
                return true;
            }
            WebFragment.this.S.put(str, WebFragment.this.R);
            String unused = WebFragment.this.R = str;
            if (str.startsWith(WebFragment.x)) {
                String url = webView.getUrl();
                if (!TextUtils.isEmpty(url)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(HttpHeaders.REFERER, url);
                    webView.loadUrl(str, hashMap);
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        /* access modifiers changed from: protected */
        public void a(final WebView webView, final String str) {
            this.i = new SimpleAccountLoginCallback() {
                public void a(boolean z) {
                    super.a(z);
                    FragmentActivity activity = WebFragment.this.getActivity();
                    if (ActivityChecker.a((Activity) activity)) {
                        if (z) {
                            if (WebFragment.this.j != null) {
                                WebFragment.this.j.getPageReloader().a(true);
                                boolean unused = WebFragment.this.T = true;
                                WebFragment.this.j.reload(WebFragment.this);
                            }
                            MifiLog.b(WebFragment.k, "account login finished, continue service login: url = " + str);
                            MiFiWebViewClient.this.b(webView, str);
                        } else if (WebFragment.this.j == null || !WebFragment.this.j.a()) {
                            activity.finish();
                        } else {
                            WebFragment.this.j.getAppDelegate().d();
                        }
                    }
                }
            };
            XiaomiAccountManager.a().a((Activity) WebFragment.this.getActivity(), (AccountNotifier.AccountLoginCallback) this.i);
        }

        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            MifiLog.e(WebFragment.k, "onReceivedError - err = " + i2 + ", description: " + str + ", failingUrl: " + str2);
            HashMap hashMap = new HashMap();
            hashMap.put("errorCode", String.valueOf(i2));
            hashMap.put("description", str);
            hashMap.put("failingUrl", str2);
            StatUtils.a("WebView", "WebViewReceivedError", (Map<String, String>) hashMap);
            if (i2 == -4 && XiaomiAccountManager.a().b()) {
                MifiLog.e(WebFragment.k, "onReceivedError - invalid account, will clear and exit");
                if (WebFragment.this.j != null) {
                    WebFragment.this.j.getAppDelegate().d();
                    return;
                }
                return;
            }
            WebFragment.this.h(false);
            super.onReceivedError(webView, i2, str, str2);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            if (!CertificatePinning.f1434a) {
                SslCertificate certificate = sslError.getCertificate();
                MifiLog.e(WebFragment.k, "onReceivedSslError - SslCertificate: " + certificate.toString());
                if (CertificateUtils.a(certificate) == null) {
                    sslErrorHandler.cancel();
                }
                FragmentActivity activity = WebFragment.this.getActivity();
                if (ActivityChecker.a((Activity) activity)) {
                    DialogManager.a((DialogFragment) new AlertDialog.Builder(activity).a(R.string.title_ssl_error).b((CharSequence) activity.getResources().getString(R.string.message_ssl_error, new Object[]{Integer.valueOf(sslError.getPrimaryError()), certificate.toString()})).a(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(sslErrorHandler, activity) {
                        private final /* synthetic */ SslErrorHandler f$0;
                        private final /* synthetic */ Activity f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final void onClick(DialogInterface dialogInterface, int i) {
                            WebFragment.MiFiWebViewClient.a(this.f$0, this.f$1, dialogInterface, i);
                        }
                    }).a(false).a(), (Context) activity, "ssl_error");
                }
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void a(SslErrorHandler sslErrorHandler, Activity activity, DialogInterface dialogInterface, int i2) {
            sslErrorHandler.cancel();
            activity.finish();
        }

        /* access modifiers changed from: protected */
        public void a(WebView webView, boolean z) {
            WebFragment.this.q();
            if (WebFragment.this.B != null) {
                WebFragment.this.B.setLoadingText(z ? WebFragment.this.getString(R.string.mifi_web_login_description) : "");
            }
            String b = UrlUtils.b(WebFragment.this.d);
            if (!this.f) {
                StatUtils.b(b);
                StatUtils.d(a(b, false));
            }
            if (NetworkDiagnosis.a().a(WebFragment.this.d)) {
                this.g = System.currentTimeMillis();
                NetworkDiagnosis a2 = NetworkDiagnosis.a();
                long j = this.g;
                a2.a(j, "page slow: " + b);
            }
        }

        /* access modifiers changed from: protected */
        public void b(WebView webView, boolean z) {
            WebFragment.this.j(!z);
            String b = UrlUtils.b(WebFragment.this.d);
            if (!this.f) {
                this.f = true;
                StatUtils.c(b);
                StatUtils.a(WebFragment.this.getContext(), a(b, false));
            }
            if (NetworkDiagnosis.a().a(WebFragment.this.d)) {
                NetworkDiagnosis.a().a(this.g);
            }
        }

        private String a(String str, boolean z) {
            return String.format(z ? e : d, new Object[]{str});
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (webView.getProgress() == 100) {
                MifiLog.b(WebFragment.k, "injectSensorsDataJSSDK");
                SensorsDataManager.a().b(webView);
            }
        }
    }

    public void a(int i, Object obj, NativeInterface.Callback callback) {
        Message obtain = Message.obtain();
        obtain.what = i;
        if (callback != null) {
            obtain.arg1 = callback.a();
        }
        obtain.obj = obj;
        this.V.sendMessage(obtain);
    }

    public Object b(int i) {
        boolean z2 = true;
        switch (i) {
            case 0:
                return UserAgentUtils.a(getContext(), this.l.getSettings().getUserAgentString());
            case 1:
                return new int[]{this.l.getWidth(), this.l.getHeight()};
            case 2:
                if (this.j == null || !this.j.a()) {
                    z2 = false;
                }
                return Boolean.valueOf(z2);
            default:
                return null;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(this.c, (String) null);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.j != null) {
            this.I = this.j.a();
        }
    }

    public void onDestroy() {
        if (this.l != null) {
            n();
            this.l.removeAllViews();
            this.l.destroy();
            this.l = null;
        }
        if (this.K != null) {
            this.K.g();
        }
        this.V.removeCallbacksAndMessages((Object) null);
        if (this.L != null) {
            SMSMonitor.a(getContext());
            this.L = null;
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.a(bundle);
        if (bundle != null) {
            this.F = bundle.getBoolean(WebConstants.i, false);
            this.D = bundle.getBoolean(WebConstants.j, false);
        }
    }

    public void startActivity(Intent intent) {
        a(intent);
        super.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        a(intent);
        super.startActivityForResult(intent, i);
    }

    private Intent a(Intent intent) {
        intent.putExtras(StatUtils.a(new Bundle(), this.d, (Activity) getActivity()));
        return intent;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        NativeInterface.Callback b = HybridCallbackManager.b(i);
        if (b != null) {
            b.a(Integer.valueOf(i2), intent);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        i();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z2 = false;
        View inflate = layoutInflater.inflate(R.layout.web_fragment, viewGroup, false);
        if (bundle != null) {
            z2 = bundle.getBoolean(v);
        }
        if (!this.D || z2) {
            a(inflate, this.d);
        }
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(v, this.E);
        super.onSaveInstanceState(bundle);
    }

    public void c() {
        super.c();
        if (this.l != null) {
            b(n);
            this.l.onPause();
        }
    }

    public void d() {
        super.d();
        if (this.l != null) {
            this.l.onResume();
            b(o);
        }
    }

    private boolean a(View view, String str) {
        if (this.E) {
            MifiLog.e(k, "doCreateView is called already!");
            return false;
        } else if (!ActivityChecker.a((Activity) getActivity())) {
            return false;
        } else {
            if (TextUtils.isEmpty(str)) {
                MifiLog.e(k, "doCreateView: url is empty");
            }
            ((ViewStub) view.findViewById(R.id.content_stub)).inflate();
            this.m = (ViewGroup) view.findViewById(R.id.web_container);
            this.B = (LoadingView) view.findViewById(R.id.loading_view);
            if (!y) {
                this.B.setVisibility(0);
                y = true;
            }
            this.C = (LoadingErrorView) view.findViewById(R.id.web_view_error_page);
            this.C.setRetryButton(R.string.retry, new View.OnClickListener() {
                public final void onClick(View view) {
                    WebFragment.this.a(view);
                }
            });
            this.z = (PullToRefreshWebView) view.findViewById(R.id.pull_web_view);
            this.z.setPullRefreshEnabled(false);
            this.z.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener() {
                public final void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
                    WebFragment.this.a(pullToRefreshBase);
                }
            });
            this.l = (ObservableWebView) this.z.getRefreshableView();
            if (Build.VERSION.SDK_INT < 19) {
                this.l.setLayerType(1, (Paint) null);
            }
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.U = arguments.getBoolean(WebConstants.g, false);
                if (this.U && (getActivity() instanceof com.miui.supportlite.app.Activity)) {
                    this.l.setOnScrollChangedListener(new ObservableWebView.OnScrollChangedListener(getResources().getDimension(R.dimen.scroll_range_to_fade_actionbar)) {
                        private final /* synthetic */ float f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void onScrollChanged(int i, int i2, int i3, int i4) {
                            WebFragment.this.a(this.f$1, i, i2, i3, i4);
                        }
                    });
                }
            }
            MifiWebUtils.a((WebView) this.l);
            String a2 = UserAgentUtils.a(getContext(), this.l.getSettings().getUserAgentString());
            WebSettings settings = this.l.getSettings();
            settings.setUseWideViewPort(true);
            settings.setUserAgentString(a2);
            settings.setJavaScriptEnabled(true);
            settings.setDatabaseEnabled(true);
            SensorsDataManager.a().a((WebView) this.l);
            if (Build.VERSION.SDK_INT < 19) {
                settings.setDatabasePath(getActivity().getDir(w, 0).getPath());
            }
            settings.setDomStorageEnabled(true);
            File cacheDir = getActivity().getApplicationContext().getCacheDir();
            if (cacheDir != null) {
                settings.setAppCachePath(cacheDir.getAbsolutePath());
            }
            settings.setAppCacheEnabled(true);
            settings.setAppCacheMaxSize(IZatTestService.y);
            settings.setCacheMode(-1);
            if (MifiWebUtils.d(str)) {
                settings.setBuiltInZoomControls(true);
                settings.setSupportZoom(true);
            }
            settings.setTextZoom(100);
            if (MifiWebUtils.e(str)) {
                this.O = ((ViewStub) view.findViewById(R.id.shield_stub)).inflate();
            }
            this.K = new WebHybridContext(getContext(), this, this, this.l);
            this.J = new JsBridge(this.K);
            m();
            this.l.setWebViewClient(new MiFiWebViewClient());
            this.l.setWebChromeClient(new WebChromeClient(this));
            this.l.setOnKeyListener(new View.OnKeyListener() {
                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return WebFragment.this.a(view, i, keyEvent);
                }
            });
            if (Build.VERSION.SDK_INT > 27) {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                ObservableWebView observableWebView = this.l;
                observableWebView.getClass();
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public final void onGlobalLayout() {
                        ObservableWebView.this.requestFocus();
                    }
                });
            }
            this.l.setDownloadListener(new DownloadListener() {
                public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                    WebFragment.this.a(str, str2, str3, str4, j);
                }
            });
            WebManager.a(this, this.d);
            this.E = true;
            a(str);
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        i(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(PullToRefreshBase pullToRefreshBase) {
        i(true);
        if (this.A != null) {
            this.A.onPullDownToRefresh(pullToRefreshBase);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(float f, int i, int i2, int i3, int i4) {
        ActionBarCustomizer.a((com.miui.supportlite.app.Activity) getActivity(), ((float) i2) / f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return false;
        }
        if (this.H || this.G) {
            b(q);
            return true;
        }
        o();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, String str3, String str4, long j) {
        DownloadListener b = WebManager.b();
        if (b != null) {
            b.onDownloadStart(str, str2, str3, str4, j);
        }
        String str5 = this.S.get(str);
        if (str5 != null) {
            this.R = str5;
            MifiLog.b(k, "current url is restored: " + this.R);
            this.S.remove(str);
        }
    }

    private void m() {
        if (!this.W && this.l != null) {
            this.l.addJavascriptInterface(this.J, JsBridge.f10848a);
            this.W = true;
        }
    }

    private void n() {
        if (this.W && this.l != null) {
            this.l.removeJavascriptInterface(JsBridge.f10848a);
            this.W = false;
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        Utils.a();
        if (this.l != null && !TextUtils.isEmpty(str)) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.l.evaluateJavascript(str, new ValueCallback(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onReceiveValue(Object obj) {
                        WebFragment.this.d(this.f$1, (String) obj);
                    }
                });
            } else {
                this.l.loadUrl(str);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void d(String str, String str2) {
        if (!TextUtils.isEmpty(str) && q.contains(str) && !Boolean.parseBoolean(str2)) {
            o();
        }
    }

    private void o() {
        if (this.I || this.l == null || !this.l.canGoBack()) {
            FragmentActivity activity = getActivity();
            if (ActivityChecker.a((Activity) activity)) {
                activity.onBackPressed();
                return;
            }
            return;
        }
        this.l.goBack();
    }

    public boolean g() {
        if (this.P != null && this.P.getVisibility() == 0) {
            SafeKeyboardManager.a(this.P);
            return true;
        } else if (this.H) {
            b(q);
            return true;
        } else {
            o();
            return true;
        }
    }

    public void a(String str, String str2) {
        if (this.F) {
            MifiLog.d(k, "SetTitle is disabled for this page.");
        } else if (getActivity() instanceof com.miui.supportlite.app.Activity) {
            boolean isEmpty = TextUtils.isEmpty(str);
            CharSequence charSequence = str;
            if (!isEmpty) {
                boolean a2 = MifiWebUtils.a(str);
                charSequence = str;
                if (a2) {
                    charSequence = MifiWebUtils.a((Context) getActivity(), str);
                }
            }
            boolean isEmpty2 = TextUtils.isEmpty(charSequence);
            CharSequence charSequence2 = charSequence;
            if (isEmpty2) {
                charSequence2 = " ";
            }
            ActionBarCustomizer.a((com.miui.supportlite.app.Activity) getActivity(), charSequence2, (CharSequence) str2);
        }
    }

    /* access modifiers changed from: private */
    public void c(boolean z2) {
        this.I = z2;
    }

    /* access modifiers changed from: private */
    public void d(boolean z2) {
        if (ActivityChecker.a((Activity) getActivity())) {
            this.z.requestDisallowInterceptTouchEvent(z2);
            if (this.j != null) {
                this.j.a(z2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e(boolean z2) {
        this.G = z2;
    }

    /* access modifiers changed from: private */
    public void f(boolean z2) {
        this.H = z2;
    }

    /* access modifiers changed from: private */
    public void g(boolean z2) {
        this.z.setPullRefreshEnabled(z2);
    }

    /* access modifiers changed from: private */
    public void h(boolean z2) {
        if (!z2) {
            if (this.C.getVisibility() != 0) {
                this.C.startEnterAnimation();
            }
            this.z.setVisibility(8);
            this.B.setVisibility(8);
            if (this.O != null) {
                this.O.setVisibility(8);
                return;
            }
            return;
        }
        if (this.C.getVisibility() == 0) {
            this.C.startExitAnimation();
        }
        if (this.B.getVisibility() != 0) {
            this.z.setVisibility(0);
        }
        if (this.O != null) {
            this.O.setVisibility(0);
        }
    }

    private boolean p() {
        return NetworkUtils.b(getContext());
    }

    /* access modifiers changed from: private */
    public void i(boolean z2) {
        if (this.l != null) {
            if (this.T) {
                this.T = false;
            } else if (!p()) {
                h(false);
            } else if (TextUtils.isEmpty(this.l.getUrl()) && !TextUtils.isEmpty(this.d)) {
                a(this.d);
            } else if (z2) {
                this.l.reload();
            } else {
                b(p);
            }
        }
    }

    public void e() {
        i(false);
    }

    public void a(String str) {
        if (!this.E) {
            if (this.D) {
                getActivity().runOnUiThread(new Runnable(str) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        WebFragment.this.d(this.f$1);
                    }
                });
            } else {
                MifiLog.d(k, "web fragment has not been created yet! loadUrl should always be called after create.");
            }
        } else if (ActivityChecker.a((Activity) getActivity()) && !TextUtils.isEmpty(str)) {
            this.d = str;
            if (!p()) {
                h(false);
            } else if (this.l != null) {
                String b = MifiWebUtils.b(str);
                if (this.U) {
                    b = UrlUtils.a(b, "_statusBarHeight", String.valueOf(Utils.b(getActivity())));
                }
                this.l.loadUrl(b);
                q();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(String str) {
        a(getView(), str);
    }

    public String h() {
        return this.R;
    }

    /* access modifiers changed from: private */
    public void c(String str, String str2) {
        DeeplinkUtils.openDeeplink(this, str2, str);
    }

    public void a(NetworkInfo networkInfo) {
        if (this.E) {
            boolean z2 = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (z2 && this.C.getVisibility() == 0) {
                i(true);
            }
            h(z2);
            if (!z2) {
                j(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        this.M = true;
        this.N = false;
        MifiLog.b("TestLoading", "start page loading");
        a(true, true, true);
    }

    /* access modifiers changed from: private */
    public void j(boolean z2) {
        this.M = false;
        MifiLog.b("TestLoading", "stop page loading");
        k(z2);
    }

    /* access modifiers changed from: private */
    public void a(boolean z2, boolean z3, boolean z4) {
        if (!this.z.isPullRefreshing()) {
            this.i.a(z2, z3, z4);
        }
    }

    /* access modifiers changed from: private */
    public void k(boolean z2) {
        if (!this.M && !this.N) {
            this.i.a(z2);
        }
    }

    public void a(boolean z2) {
        if (ActivityChecker.a((Activity) getActivity())) {
            MifiLog.b("MiFiTimeLine", "Webview " + this.d + ", startTime = " + System.currentTimeMillis());
            this.B.setVisibility(0);
            if (z2 && this.z != null) {
                this.z.setVisibility(8);
                this.C.setVisibility(8);
            }
        }
    }

    public void b(boolean z2) {
        if (ActivityChecker.a((Activity) getActivity())) {
            MifiLog.b("MiFiTimeLine", "Webview " + this.d + ", endTime = " + System.currentTimeMillis());
            this.B.setVisibility(8);
            h(!z2 && p());
        }
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        if (this.j != null) {
            this.j.getPageReloader().a(str);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, long j, int i) {
        if (this.L == null) {
            this.L = new SMSMonitor.SmsVerificationCodeListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onReceive(String str) {
                    WebFragment.this.a(this.f$1, str);
                }
            };
        }
        SMSMonitor.a(getContext(), str, this.L);
        this.V.removeMessages(18);
        Message obtain = Message.obtain();
        obtain.what = 18;
        obtain.arg1 = i;
        this.V.sendMessageDelayed(obtain, j);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(int i, String str) {
        a(i, str, (String) null);
        this.V.removeMessages(18);
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, String str2) {
        NativeInterface.Callback b = HybridCallbackManager.b(i);
        if (b != null) {
            b.a(str, str2);
            return;
        }
        if (str2 != null) {
            str = "ERROR:" + str2;
        }
        b("setMessageCode('" + str + "');");
    }

    public void i() {
        this.P = SafeKeyboardManager.a((Activity) getActivity(), "none");
        this.P.setOnKeyEventListener(this.Q);
        SafeKeyboardManager.a(this.P);
    }

    public PullToRefreshWebView j() {
        return this.z;
    }

    public String k() {
        return this.c;
    }

    public ObservableWebView l() {
        return this.l;
    }
}
