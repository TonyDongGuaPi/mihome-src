package com.xiaomi.jr.web.webkit;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.Constants;
import com.xiaomi.jr.account.IWebLoginProcessor;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.account.XiaomiAccountUtils;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.web.R;
import com.xiaomi.jr.web.utils.MifiWebUtils;
import com.xiaomi.jr.web.utils.UserAgentUtils;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.util.concurrent.TimeUnit;

public class XiaomiWebLoginProcessor implements IWebLoginProcessor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1464a = "XiaomiWebLoginProcessor";
    private static final int b = 30;

    private static class WebLoginWebViewClient extends WebViewClient {

        /* renamed from: a  reason: collision with root package name */
        private String f11086a;

        WebLoginWebViewClient(IWebLoginProcessor.WebLoginListener webLoginListener, String str) {
            a(webLoginListener);
            this.f11086a = str;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!MifiWebUtils.a(str, this.f11086a)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            a(webView, true, (String) null, str);
            return true;
        }

        /* access modifiers changed from: protected */
        public void b(WebView webView, boolean z) {
            if (!z) {
                a(webView, false, "page loading error", this.f11086a);
            }
        }

        /* access modifiers changed from: protected */
        public void a(WebView webView, boolean z, String str, String str2) {
            QualityMonitor.a(Constants.j, "weblogin", "End weblogin. success=" + z + ", requestLoginUrl=" + str2);
            a(webView);
            super.a(webView, z, str, str2);
            if (webView != null) {
                webView.removeAllViews();
                webView.destroy();
            }
        }

        private void a(WebView webView) {
            CookieSyncManager.createInstance(webView.getContext());
            CookieManager instance = CookieManager.getInstance();
            if (Build.VERSION.SDK_INT >= 21) {
                instance.flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
        }
    }

    public void a(Context context, String str, IWebLoginProcessor.WebLoginListener webLoginListener) {
        QualityMonitor.a(Constants.j, "weblogin", "do webLogin, url: " + str);
        b(context, str, webLoginListener);
    }

    private static void b(Context context, String str, IWebLoginProcessor.WebLoginListener webLoginListener) {
        new Handler(Looper.getMainLooper()).post(new Runnable(context, webLoginListener, str) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ IWebLoginProcessor.WebLoginListener f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                XiaomiWebLoginProcessor.a(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, IWebLoginProcessor.WebLoginListener webLoginListener, String str) {
        WebView a2 = a(context);
        a2.setTag(R.id.background_webview, true);
        a2.setWebViewClient(new WebLoginWebViewClient(webLoginListener, str));
        a2.loadUrl(MifiWebUtils.b(str));
    }

    static void a(WebView webView, String str, WebViewClient webViewClient) {
        XiaomiPassportExecutor.getSingleton().execute(new Runnable(str, webView, webViewClient) {
            private final /* synthetic */ String f$0;
            private final /* synthetic */ WebView f$1;
            private final /* synthetic */ WebViewClient f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                XiaomiWebLoginProcessor.a(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(String str, WebView webView, WebViewClient webViewClient) {
        String str2;
        Exception e;
        String b2 = b(str);
        QualityMonitor.a(Constants.j, "weblogin", "Start weblogin... requestLoginUrl=" + b2 + ", args=" + str);
        String str3 = null;
        try {
            Bundle result = XiaomiAccountManager.j().a("weblogin:" + str).getResult(30, TimeUnit.SECONDS);
            String string = result.getString("serviceToken");
            try {
                if (!TextUtils.isEmpty(string)) {
                    str2 = UrlUtils.a(string, "_userIdNeedEncrypt", String.valueOf(true));
                    try {
                        if (TextUtils.equals(Uri.parse(str2).getPath(), "/auth/service")) {
                            new Handler(Looper.getMainLooper()).post(new Runnable(webView, str2) {
                                private final /* synthetic */ WebView f$0;
                                private final /* synthetic */ String f$1;

                                {
                                    this.f$0 = r1;
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    this.f$0.loadUrl(this.f$1);
                                }
                            });
                            if (!a(webView)) {
                                QualityMonitor.a(Constants.j, "weblogin", "weblogin encounters risk issue and weview is in background. requestLoginUrl=" + b2 + ", riskHandlingUrl=" + str2);
                                return;
                            }
                            return;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        QualityMonitor.a(Constants.j, "weblogin", "Weblogin get auth token throw exception - " + e);
                        str3 = e.getMessage();
                        new Handler(Looper.getMainLooper()).post(new Runnable(str2, webViewClient, webView, str3, b2) {
                            private final /* synthetic */ String f$0;
                            private final /* synthetic */ WebViewClient f$1;
                            private final /* synthetic */ WebView f$2;
                            private final /* synthetic */ String f$3;
                            private final /* synthetic */ String f$4;

                            {
                                this.f$0 = r1;
                                this.f$1 = r2;
                                this.f$2 = r3;
                                this.f$3 = r4;
                                this.f$4 = r5;
                            }

                            public final void run() {
                                XiaomiWebLoginProcessor.a(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
                            }
                        });
                    }
                } else {
                    str3 = XiaomiAccountUtils.a(result);
                    str2 = string;
                }
            } catch (Exception e3) {
                e = e3;
                str2 = string;
                QualityMonitor.a(Constants.j, "weblogin", "Weblogin get auth token throw exception - " + e);
                str3 = e.getMessage();
                new Handler(Looper.getMainLooper()).post(new Runnable(str2, webViewClient, webView, str3, b2) {
                    private final /* synthetic */ String f$0;
                    private final /* synthetic */ WebViewClient f$1;
                    private final /* synthetic */ WebView f$2;
                    private final /* synthetic */ String f$3;
                    private final /* synthetic */ String f$4;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        XiaomiWebLoginProcessor.a(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            }
        } catch (Exception e4) {
            e = e4;
            str2 = null;
            QualityMonitor.a(Constants.j, "weblogin", "Weblogin get auth token throw exception - " + e);
            str3 = e.getMessage();
            new Handler(Looper.getMainLooper()).post(new Runnable(str2, webViewClient, webView, str3, b2) {
                private final /* synthetic */ String f$0;
                private final /* synthetic */ WebViewClient f$1;
                private final /* synthetic */ WebView f$2;
                private final /* synthetic */ String f$3;
                private final /* synthetic */ String f$4;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void run() {
                    XiaomiWebLoginProcessor.a(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
                }
            });
        }
        new Handler(Looper.getMainLooper()).post(new Runnable(str2, webViewClient, webView, str3, b2) {
            private final /* synthetic */ String f$0;
            private final /* synthetic */ WebViewClient f$1;
            private final /* synthetic */ WebView f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ String f$4;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                XiaomiWebLoginProcessor.a(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(String str, WebViewClient webViewClient, WebView webView, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            webViewClient.a(webView, false, "get sts null: " + str2, str3);
            return;
        }
        if (a(webView)) {
            webView = a(webView.getContext());
            webView.setWebViewClient(new WebLoginWebViewClient(webViewClient.c, str3));
        }
        webView.loadUrl(str);
    }

    private static WebView a(Context context) {
        WebView webView = new WebView(context);
        webView.setTag(R.id.background_webview, true);
        webView.getSettings().setCacheMode(2);
        webView.getSettings().setUserAgentString(UserAgentUtils.f11081a);
        return webView;
    }

    private static boolean a(WebView webView) {
        Object tag = webView.getTag(R.id.background_webview);
        return tag == null || !((Boolean) tag).booleanValue();
    }

    private static String b(String str) {
        try {
            return Uri.parse(Uri.parse(str).getQueryParameter("callback")).getQueryParameter("followup");
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean a(String str) {
        return str.startsWith(URLs.URL_LOGIN) && b(str) != null;
    }
}
