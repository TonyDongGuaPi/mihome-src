package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.q;
import com.tencent.smtt.utils.t;
import java.io.InputStream;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;

@SuppressLint({"NewApi", "Override"})
class ad extends WebViewClient {
    private static String c;

    /* renamed from: a  reason: collision with root package name */
    private WebViewClient f9127a;
    /* access modifiers changed from: private */
    public WebView b;

    private static class a extends ClientCertRequest {

        /* renamed from: a  reason: collision with root package name */
        private android.webkit.ClientCertRequest f9128a;

        public a(android.webkit.ClientCertRequest clientCertRequest) {
            this.f9128a = clientCertRequest;
        }

        public void cancel() {
            this.f9128a.cancel();
        }

        public String getHost() {
            return this.f9128a.getHost();
        }

        public String[] getKeyTypes() {
            return this.f9128a.getKeyTypes();
        }

        public int getPort() {
            return this.f9128a.getPort();
        }

        public Principal[] getPrincipals() {
            return this.f9128a.getPrincipals();
        }

        public void ignore() {
            this.f9128a.ignore();
        }

        public void proceed(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.f9128a.proceed(privateKey, x509CertificateArr);
        }
    }

    private static class b implements HttpAuthHandler {

        /* renamed from: a  reason: collision with root package name */
        private android.webkit.HttpAuthHandler f9129a;

        b(android.webkit.HttpAuthHandler httpAuthHandler) {
            this.f9129a = httpAuthHandler;
        }

        public void cancel() {
            this.f9129a.cancel();
        }

        public void proceed(String str, String str2) {
            this.f9129a.proceed(str, str2);
        }

        public boolean useHttpAuthUsernamePassword() {
            return this.f9129a.useHttpAuthUsernamePassword();
        }
    }

    private static class c implements SslErrorHandler {

        /* renamed from: a  reason: collision with root package name */
        android.webkit.SslErrorHandler f9130a;

        c(android.webkit.SslErrorHandler sslErrorHandler) {
            this.f9130a = sslErrorHandler;
        }

        public void cancel() {
            this.f9130a.cancel();
        }

        public void proceed() {
            this.f9130a.proceed();
        }
    }

    private static class d implements SslError {

        /* renamed from: a  reason: collision with root package name */
        android.net.http.SslError f9131a;

        d(android.net.http.SslError sslError) {
            this.f9131a = sslError;
        }

        public boolean addError(int i) {
            return this.f9131a.addError(i);
        }

        public SslCertificate getCertificate() {
            return this.f9131a.getCertificate();
        }

        public int getPrimaryError() {
            return this.f9131a.getPrimaryError();
        }

        public boolean hasError(int i) {
            return this.f9131a.hasError(i);
        }
    }

    private class e implements WebResourceRequest {
        private String b;
        private boolean c;
        private boolean d;
        private boolean e;
        private String f;
        private Map<String, String> g;

        public e(String str, boolean z, boolean z2, boolean z3, String str2, Map<String, String> map) {
            this.b = str;
            this.c = z;
            this.d = z2;
            this.e = z3;
            this.f = str2;
            this.g = map;
        }

        public String getMethod() {
            return this.f;
        }

        public Map<String, String> getRequestHeaders() {
            return this.g;
        }

        public Uri getUrl() {
            return Uri.parse(this.b);
        }

        public boolean hasGesture() {
            return this.e;
        }

        public boolean isForMainFrame() {
            return this.c;
        }

        public boolean isRedirect() {
            return this.d;
        }
    }

    private static class f implements WebResourceRequest {

        /* renamed from: a  reason: collision with root package name */
        android.webkit.WebResourceRequest f9133a;

        f(android.webkit.WebResourceRequest webResourceRequest) {
            this.f9133a = webResourceRequest;
        }

        public String getMethod() {
            return this.f9133a.getMethod();
        }

        public Map<String, String> getRequestHeaders() {
            return this.f9133a.getRequestHeaders();
        }

        public Uri getUrl() {
            return this.f9133a.getUrl();
        }

        public boolean hasGesture() {
            return this.f9133a.hasGesture();
        }

        public boolean isForMainFrame() {
            return this.f9133a.isForMainFrame();
        }

        public boolean isRedirect() {
            if (Build.VERSION.SDK_INT >= 24) {
                Object a2 = q.a((Object) this.f9133a, "isRedirect");
                if (a2 instanceof Boolean) {
                    return ((Boolean) a2).booleanValue();
                }
            }
            return false;
        }
    }

    private static class g extends WebResourceResponse {

        /* renamed from: a  reason: collision with root package name */
        android.webkit.WebResourceResponse f9134a;

        public g(android.webkit.WebResourceResponse webResourceResponse) {
            this.f9134a = webResourceResponse;
        }

        public InputStream getData() {
            return this.f9134a.getData();
        }

        public String getEncoding() {
            return this.f9134a.getEncoding();
        }

        public String getMimeType() {
            return this.f9134a.getMimeType();
        }

        public String getReasonPhrase() {
            return this.f9134a.getReasonPhrase();
        }

        public Map<String, String> getResponseHeaders() {
            return this.f9134a.getResponseHeaders();
        }

        public int getStatusCode() {
            return this.f9134a.getStatusCode();
        }

        public void setData(InputStream inputStream) {
            this.f9134a.setData(inputStream);
        }

        public void setEncoding(String str) {
            this.f9134a.setEncoding(str);
        }

        public void setMimeType(String str) {
            this.f9134a.setMimeType(str);
        }

        public void setResponseHeaders(Map<String, String> map) {
            this.f9134a.setResponseHeaders(map);
        }

        public void setStatusCodeAndReasonPhrase(int i, String str) {
            this.f9134a.setStatusCodeAndReasonPhrase(i, str);
        }
    }

    ad(WebView webView, WebViewClient webViewClient) {
        this.b = webView;
        this.f9127a = webViewClient;
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.b.a(webView);
        this.f9127a.doUpdateVisitedHistory(this.b, str, z);
    }

    public void onFormResubmission(WebView webView, Message message, Message message2) {
        this.b.a(webView);
        this.f9127a.onFormResubmission(this.b, message, message2);
    }

    public void onLoadResource(WebView webView, String str) {
        this.b.a(webView);
        this.f9127a.onLoadResource(this.b, str);
    }

    public void onPageFinished(WebView webView, String str) {
        t a2;
        if (c == null && (a2 = t.a()) != null) {
            a2.a(true);
            c = Boolean.toString(true);
        }
        this.b.a(webView);
        this.b.f9110a++;
        this.f9127a.onPageFinished(this.b, str);
        if (TbsConfig.APP_QZONE.equals(webView.getContext().getApplicationInfo().packageName)) {
            this.b.a(webView.getContext());
        }
        TbsLog.app_extra("SystemWebViewClient", webView.getContext());
        WebView.d();
        if (!TbsShareManager.mHasQueryed && this.b.getContext() != null && TbsShareManager.isThirdPartyApp(this.b.getContext())) {
            TbsShareManager.mHasQueryed = true;
            new Thread(new ae(this)).start();
        }
        if (this.b.getContext() != null && !TbsLogReport.a(this.b.getContext()).e()) {
            TbsLogReport.a(this.b.getContext()).a(true);
            TbsLogReport.a(this.b.getContext()).b();
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.b.a(webView);
        this.f9127a.onPageStarted(this.b, str, bitmap);
    }

    public void onReceivedClientCertRequest(WebView webView, android.webkit.ClientCertRequest clientCertRequest) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.b.a(webView);
            this.f9127a.onReceivedClientCertRequest(this.b, new a(clientCertRequest));
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.b.a(webView);
        this.f9127a.onReceivedError(this.b, i, str, str2);
    }

    public void onReceivedError(WebView webView, android.webkit.WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        this.b.a(webView);
        af afVar = null;
        f fVar = webResourceRequest != null ? new f(webResourceRequest) : null;
        if (webResourceError != null) {
            afVar = new af(this, webResourceError);
        }
        this.f9127a.onReceivedError(this.b, fVar, afVar);
    }

    public void onReceivedHttpAuthRequest(WebView webView, android.webkit.HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.b.a(webView);
        this.f9127a.onReceivedHttpAuthRequest(this.b, new b(httpAuthHandler), str, str2);
    }

    public void onReceivedHttpError(WebView webView, android.webkit.WebResourceRequest webResourceRequest, android.webkit.WebResourceResponse webResourceResponse) {
        this.b.a(webView);
        this.f9127a.onReceivedHttpError(this.b, new f(webResourceRequest), new g(webResourceResponse));
    }

    @TargetApi(12)
    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        if (Build.VERSION.SDK_INT >= 12) {
            this.b.a(webView);
            this.f9127a.onReceivedLoginRequest(this.b, str, str2, str3);
        }
    }

    @TargetApi(8)
    public void onReceivedSslError(WebView webView, android.webkit.SslErrorHandler sslErrorHandler, android.net.http.SslError sslError) {
        if (Build.VERSION.SDK_INT >= 8) {
            this.b.a(webView);
            this.f9127a.onReceivedSslError(this.b, new c(sslErrorHandler), new d(sslError));
        }
    }

    public void onScaleChanged(WebView webView, float f2, float f3) {
        this.b.a(webView);
        this.f9127a.onScaleChanged(this.b, f2, f3);
    }

    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        this.b.a(webView);
        this.f9127a.onTooManyRedirects(this.b, message, message2);
    }

    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.b.a(webView);
        this.f9127a.onUnhandledKeyEvent(this.b, keyEvent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r10, android.webkit.WebResourceRequest r11) {
        /*
            r9 = this;
            int r10 = android.os.Build.VERSION.SDK_INT
            r0 = 0
            r1 = 21
            if (r10 >= r1) goto L_0x0008
            return r0
        L_0x0008:
            if (r11 != 0) goto L_0x000b
            return r0
        L_0x000b:
            r10 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L_0x0024
            java.lang.String r1 = "isRedirect"
            java.lang.Object r1 = com.tencent.smtt.utils.q.a((java.lang.Object) r11, (java.lang.String) r1)
            boolean r2 = r1 instanceof java.lang.Boolean
            if (r2 == 0) goto L_0x0024
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r10 = r1.booleanValue()
            r5 = r10
            goto L_0x0025
        L_0x0024:
            r5 = 0
        L_0x0025:
            com.tencent.smtt.sdk.ad$e r10 = new com.tencent.smtt.sdk.ad$e
            android.net.Uri r1 = r11.getUrl()
            java.lang.String r3 = r1.toString()
            boolean r4 = r11.isForMainFrame()
            boolean r6 = r11.hasGesture()
            java.lang.String r7 = r11.getMethod()
            java.util.Map r8 = r11.getRequestHeaders()
            r1 = r10
            r2 = r9
            r1.<init>(r3, r4, r5, r6, r7, r8)
            com.tencent.smtt.sdk.WebViewClient r11 = r9.f9127a
            com.tencent.smtt.sdk.WebView r1 = r9.b
            com.tencent.smtt.export.external.interfaces.WebResourceResponse r10 = r11.shouldInterceptRequest((com.tencent.smtt.sdk.WebView) r1, (com.tencent.smtt.export.external.interfaces.WebResourceRequest) r10)
            if (r10 != 0) goto L_0x004f
            return r0
        L_0x004f:
            android.webkit.WebResourceResponse r11 = new android.webkit.WebResourceResponse
            java.lang.String r0 = r10.getMimeType()
            java.lang.String r1 = r10.getEncoding()
            java.io.InputStream r2 = r10.getData()
            r11.<init>(r0, r1, r2)
            java.util.Map r0 = r10.getResponseHeaders()
            r11.setResponseHeaders(r0)
            int r0 = r10.getStatusCode()
            java.lang.String r10 = r10.getReasonPhrase()
            int r1 = r11.getStatusCode()
            if (r0 != r1) goto L_0x0081
            if (r10 == 0) goto L_0x0084
            java.lang.String r1 = r11.getReasonPhrase()
            boolean r1 = r10.equals(r1)
            if (r1 != 0) goto L_0x0084
        L_0x0081:
            r11.setStatusCodeAndReasonPhrase(r0, r10)
        L_0x0084:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ad.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    @TargetApi(11)
    public android.webkit.WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        WebResourceResponse shouldInterceptRequest;
        if (Build.VERSION.SDK_INT >= 11 && (shouldInterceptRequest = this.f9127a.shouldInterceptRequest(this.b, str)) != null) {
            return new android.webkit.WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getData());
        }
        return null;
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.b.a(webView);
        return this.f9127a.shouldOverrideKeyEvent(this.b, keyEvent);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null || this.b.showDebugView(str)) {
            return true;
        }
        this.b.a(webView);
        return this.f9127a.shouldOverrideUrlLoading(this.b, str);
    }
}
