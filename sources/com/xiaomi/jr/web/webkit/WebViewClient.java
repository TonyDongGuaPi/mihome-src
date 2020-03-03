package com.xiaomi.jr.web.webkit;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.xiaomi.jr.QualityMonitor;
import com.xiaomi.jr.account.Constants;
import com.xiaomi.jr.account.IWebLoginProcessor;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.ReflectUtil;
import com.xiaomi.jr.http.WebHttpManager;
import com.xiaomi.jr.http.certificate.CertificatePinning;
import com.xiaomi.jr.web.R;
import com.xiaomi.stat.a.l;
import java.util.regex.Pattern;

public class WebViewClient extends android.webkit.WebViewClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11085a = "MiFiWebViewClient";
    protected boolean b;
    protected IWebLoginProcessor.WebLoginListener c;

    /* access modifiers changed from: protected */
    public void a(WebView webView, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void b(WebView webView, boolean z) {
    }

    /* access modifiers changed from: protected */
    public void a(IWebLoginProcessor.WebLoginListener webLoginListener) {
        this.c = webLoginListener;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        MifiLog.b(f11085a, "onPageStarted - url = " + str);
        a(webView, false);
        this.b = false;
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        MifiLog.b(f11085a, "onPageFinished - url = " + str + ", progress = " + webView.getProgress());
        b(webView, this.b ^ true);
        this.b = false;
        super.onPageFinished(webView, str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        MifiLog.b(f11085a, "shouldOverrideUrlLoading - url = " + str + ", progress = " + webView.getProgress());
        if (!XiaomiWebLoginProcessor.a(str)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        if (XiaomiAccountManager.a().d()) {
            b(webView, str);
            return true;
        }
        a(webView, str);
        return true;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.b = true;
        super.onReceivedError(webView, i, str, str2);
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        MifiLog.e(f11085a, "onReceivedSslError - error=" + sslError.getPrimaryError() + ", url=" + sslError.getUrl());
        if (CertificatePinning.f1434a) {
            MifiLog.c(f11085a, "Ignore SSL error due to cert-pinning disabled");
            try {
                ReflectUtil.a(ReflectUtil.a(Class.forName("android.webkit.SslErrorHandler"), "proceed", (Class<?>[]) new Class[0]), (Object) sslErrorHandler, new Object[0]);
            } catch (ClassNotFoundException unused) {
            }
        } else {
            sslErrorHandler.cancel();
        }
    }

    @TargetApi(21)
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        Pattern c2;
        if (Build.VERSION.SDK_INT < 24 && !CertificatePinning.f1434a && (c2 = WebHttpManager.a().c()) != null) {
            Uri url = webResourceRequest.getUrl();
            String host = url != null ? url.getHost() : null;
            if (host != null && c2.matcher(host).matches() && !TextUtils.equals(url.getLastPathSegment(), l.a.B)) {
                return WebResourceInterceptor.a(webResourceRequest);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void b(WebView webView, String str) {
        a(webView, true);
        XiaomiWebLoginProcessor.a(webView, str, this);
    }

    /* access modifiers changed from: protected */
    public void a(WebView webView, String str) {
        a(webView, false, "account not login", (String) null);
    }

    /* access modifiers changed from: protected */
    public void a(WebView webView, boolean z, String str, String str2) {
        if (this.c != null) {
            this.c.onWebLoginResult(z, str2);
        }
        if (!z) {
            QualityMonitor.a(Constants.j, "weblogin_failure", "reason", "get authorized sts url null", "systemAccount", String.valueOf(XiaomiAccountManager.a().c()), "url", str2, "error", str, "front", String.valueOf(webView.getTag(R.id.background_webview) != Boolean.TRUE));
        }
    }
}
