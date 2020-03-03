package com.alipay.zoloz.android.phone.mrpc.core;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import com.alipay.sdk.util.i;
import com.google.code.microlog4android.appender.DatagramAppender;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.cybergarage.http.HTTP;

public class HttpWorker implements Callable<Response> {

    /* renamed from: a  reason: collision with root package name */
    private static final HttpRequestRetryHandler f1189a = new ZHttpRequestRetryHandler();
    private HttpUriRequest b;
    private HttpContext c = new BasicHttpContext();
    private CookieStore d = new BasicCookieStore();
    private CookieManager e;
    private AbstractHttpEntity f;
    private HttpHost g;
    private URL h;
    private int i = 0;
    private boolean j = false;
    private boolean k = false;
    private String l = null;
    private String m;
    protected Context mContext;
    protected HttpManager mHttpManager;
    protected HttpUrlRequest mRequest;
    String mUrl;

    /* access modifiers changed from: protected */
    public boolean willHandleOtherCode(int i2, String str) {
        return i2 == 304;
    }

    public HttpWorker(HttpManager httpManager, HttpUrlRequest httpUrlRequest) {
        this.mHttpManager = httpManager;
        this.mContext = this.mHttpManager.mContext;
        this.mRequest = httpUrlRequest;
    }

    /* access modifiers changed from: protected */
    public URI getUri() {
        String url = this.mRequest.getUrl();
        if (this.mUrl != null) {
            url = this.mUrl;
        }
        if (url != null) {
            return new URI(url);
        }
        throw new RuntimeException("url should not be null");
    }

    /* access modifiers changed from: protected */
    public AbstractHttpEntity getPostData() {
        if (this.f != null) {
            return this.f;
        }
        byte[] reqData = this.mRequest.getReqData();
        String tag = this.mRequest.getTag("gzip");
        if (reqData != null) {
            if (TextUtils.equals(tag, "true")) {
                this.f = AndroidHttpClient.getCompressedEntity(reqData, (ContentResolver) null);
            } else {
                this.f = new ByteArrayEntity(reqData);
            }
            this.f.setContentType(this.mRequest.getContentType());
        }
        return this.f;
    }

    /* access modifiers changed from: protected */
    public ArrayList<Header> getHeaders() {
        return this.mRequest.getHeaders();
    }

    private HttpUriRequest a() {
        if (this.b != null) {
            return this.b;
        }
        AbstractHttpEntity postData = getPostData();
        if (postData != null) {
            HttpPost httpPost = new HttpPost(getUri());
            httpPost.setEntity(postData);
            this.b = httpPost;
        } else {
            this.b = new HttpGet(getUri());
        }
        return this.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x03b7, code lost:
        c().onFailed(r12.mRequest, 3, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x03d1, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0401, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(3, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0402, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0403, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x040a, code lost:
        if (c() != null) goto L_0x040c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x040c, code lost:
        c().onFailed(r12.mRequest, 6, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0426, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0456, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(6, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0457, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0458, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x045f, code lost:
        if (c() != null) goto L_0x0461;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0461, code lost:
        c().onFailed(r12.mRequest, 2, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x047b, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x04ab, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(2, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x04ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x04ad, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x04b4, code lost:
        if (c() != null) goto L_0x04b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x04b6, code lost:
        c().onFailed(r12.mRequest, 2, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x04d0, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0500, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(2, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0501, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x050d, code lost:
        throw new java.lang.RuntimeException("Url parser error!", r0.getCause());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x050e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x050f, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0516, code lost:
        if (c() != null) goto L_0x0518;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0518, code lost:
        c().onFailed(r12.mRequest, r0.getCode(), r0.getMsg());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0529, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x053f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0130, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, "", r1);
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x013e, code lost:
        if (c() != null) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0140, code lost:
        c().onFailed(r12.mRequest, 0, r1 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0174, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(0, r1 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0175, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0176, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x017b, code lost:
        if (r12.i < 1) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x017d, code lost:
        r12.i++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0186, code lost:
        return call();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0187, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r2 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01b7, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(0, r2 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01b8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01b9, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01c0, code lost:
        if (c() != null) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01c2, code lost:
        c().onFailed(r12.mRequest, 6, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01dc, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x020c, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(6, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x020d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x020e, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0217, code lost:
        if (c() != null) goto L_0x0219;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0219, code lost:
        c().onFailed(r12.mRequest, 9, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0233, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0263, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(9, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0264, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0265, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x026e, code lost:
        if (c() != null) goto L_0x0270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0270, code lost:
        c().onFailed(r12.mRequest, 8, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x028a, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, "", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02ab, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(8, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x02ad, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02b5, code lost:
        if (c() != null) goto L_0x02b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02b7, code lost:
        c().onFailed(r12.mRequest, 5, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02d1, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0301, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(5, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0302, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0303, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x030b, code lost:
        if (c() != null) goto L_0x030d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x030d, code lost:
        c().onFailed(r12.mRequest, 4, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0327, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0357, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(4, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0358, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0359, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0360, code lost:
        if (c() != null) goto L_0x0362;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0362, code lost:
        c().onFailed(r12.mRequest, 3, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x037c, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x03ac, code lost:
        throw new com.alipay.zoloz.android.phone.mrpc.core.HttpException(3, r0 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x03ad, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x03ae, code lost:
        b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x03b5, code lost:
        if (c() != null) goto L_0x03b7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0402 A[ExcHandler: SSLException (r0v10 'e' javax.net.ssl.SSLException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0457 A[ExcHandler: SSLPeerUnverifiedException (r0v7 'e' javax.net.ssl.SSLPeerUnverifiedException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x04ac A[ExcHandler: SSLHandshakeException (r0v4 'e' javax.net.ssl.SSLHandshakeException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0501 A[ExcHandler: URISyntaxException (r0v2 'e' java.net.URISyntaxException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x050e A[ExcHandler: HttpException (r0v1 'e' com.alipay.zoloz.android.phone.mrpc.core.HttpException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0175 A[ExcHandler: NullPointerException (r2v31 'e' java.lang.NullPointerException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01b8 A[ExcHandler: IOException (r0v31 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x020d A[ExcHandler: UnknownHostException (r0v28 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0264 A[ExcHandler: HttpHostConnectException (r0v25 'e' org.apache.http.conn.HttpHostConnectException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02ac A[ExcHandler: NoHttpResponseException (r0v22 'e' org.apache.http.NoHttpResponseException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0302 A[ExcHandler: SocketTimeoutException (r0v19 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0358 A[ExcHandler: ConnectTimeoutException (r0v16 'e' org.apache.http.conn.ConnectTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x03ad A[ExcHandler: ConnectionPoolTimeoutException (r0v13 'e' org.apache.http.conn.ConnectionPoolTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.zoloz.android.phone.mrpc.core.Response call() {
        /*
            r12 = this;
            r0 = 0
            r1 = 1
            r2 = 3
            r3 = 6
            r4 = 2
            android.content.Context r5 = r12.mContext     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            boolean r5 = com.alipay.zoloz.android.phone.mrpc.core.NetworkUtils.isNetworkAvailable(r5)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r5 == 0) goto L_0x0123
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r5 = r12.c()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r5 == 0) goto L_0x001c
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r5 = r12.c()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r6 = r12.mRequest     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r5.onPreExecute(r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
        L_0x001c:
            r12.h()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            org.apache.http.protocol.HttpContext r5 = r12.c     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r6 = "http.cookie-store"
            org.apache.http.client.CookieStore r7 = r12.d     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r5.setAttribute(r6, r7)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.AndroidHttpClient r5 = r12.g()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            org.apache.http.client.HttpRequestRetryHandler r6 = f1189a     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r5.setHttpRequestRetryHandler(r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            org.apache.http.HttpResponse r7 = r12.d()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.HttpManager r10 = r12.mHttpManager     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r11 = 0
            long r8 = r8 - r5
            r10.addConnectTime(r8)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            org.apache.http.client.CookieStore r5 = r12.d     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.util.List r5 = r5.getCookies()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r6 = r12.mRequest     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            boolean r6 = r6.isResetCookie()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r6 == 0) goto L_0x0059
            android.webkit.CookieManager r6 = r12.m()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r6.removeAllCookie()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
        L_0x0059:
            boolean r6 = r5.isEmpty()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r6 != 0) goto L_0x00c1
            java.util.Iterator r5 = r5.iterator()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
        L_0x0063:
            boolean r6 = r5.hasNext()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r6 == 0) goto L_0x00c1
            java.lang.Object r6 = r5.next()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            org.apache.http.cookie.Cookie r6 = (org.apache.http.cookie.Cookie) r6     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r8 = r6.getDomain()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r8 != 0) goto L_0x0076
            goto L_0x0063
        L_0x0076:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r8.<init>()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r9 = r6.getName()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r8.append(r9)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r9 = "="
            r8.append(r9)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r9 = r6.getValue()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r8.append(r9)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r9 = "; domain="
            r8.append(r9)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r9 = r6.getDomain()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r8.append(r9)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            boolean r6 = r6.isSecure()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r6 == 0) goto L_0x00a3
            java.lang.String r6 = "; Secure"
            goto L_0x00a5
        L_0x00a3:
            java.lang.String r6 = ""
        L_0x00a5:
            r8.append(r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r6 = r8.toString()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            android.webkit.CookieManager r8 = r12.m()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r9 = r12.mRequest     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r9 = r9.getUrl()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r8.setCookie(r9, r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            android.webkit.CookieSyncManager r6 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r6.sync()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            goto L_0x0063
        L_0x00c1:
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r5 = r12.mRequest     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.Response r5 = r12.processResponse(r7, r5)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r6 = -1
            if (r5 == 0) goto L_0x00d8
            byte[] r8 = r5.getResData()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r8 == 0) goto L_0x00d8
            byte[] r8 = r5.getResData()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            int r8 = r8.length     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            long r8 = (long) r8     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            goto L_0x00d9
        L_0x00d8:
            r8 = r6
        L_0x00d9:
            int r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x00f9
            boolean r6 = r5 instanceof com.alipay.zoloz.android.phone.mrpc.core.HttpUrlResponse     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r6 == 0) goto L_0x00f9
            r6 = r5
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlResponse r6 = (com.alipay.zoloz.android.phone.mrpc.core.HttpUrlResponse) r6     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlHeader r6 = r6.getHeader()     // Catch:{ Exception -> 0x00f2, HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175 }
            java.lang.String r7 = "Content-Length"
            java.lang.String r6 = r6.getHead(r7)     // Catch:{ Exception -> 0x00f2, HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175 }
            java.lang.Long.parseLong(r6)     // Catch:{ Exception -> 0x00f2, HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175 }
            goto L_0x00f9
        L_0x00f2:
            java.lang.String r6 = "HttpWorker"
            java.lang.String r7 = "parse Content-Length error"
            android.util.Log.e(r6, r7)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
        L_0x00f9:
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r6 = r12.mRequest     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r6 = r6.getUrl()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r6 == 0) goto L_0x0122
            java.lang.String r7 = r12.f()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            if (r7 != 0) goto L_0x0122
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r7.<init>()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r7.append(r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r6 = "#"
            r7.append(r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r6 = r12.f()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r7.append(r6)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            r7.toString()     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
        L_0x0122:
            return r5
        L_0x0123:
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r5 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            java.lang.String r7 = "The network is not available"
            r5.<init>(r6, r7)     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
            throw r5     // Catch:{ HttpException -> 0x050e, URISyntaxException -> 0x0501, SSLHandshakeException -> 0x04ac, SSLPeerUnverifiedException -> 0x0457, SSLException -> 0x0402, ConnectionPoolTimeoutException -> 0x03ad, ConnectTimeoutException -> 0x0358, SocketTimeoutException -> 0x0302, NoHttpResponseException -> 0x02ac, HttpHostConnectException -> 0x0264, UnknownHostException -> 0x020d, IOException -> 0x01b8, NullPointerException -> 0x0175, Exception -> 0x012f }
        L_0x012f:
            r1 = move-exception
            java.lang.String r2 = "HttpManager"
            java.lang.String r3 = ""
            android.util.Log.e(r2, r3, r1)
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r2 = r12.c()
            if (r2 == 0) goto L_0x015a
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r2 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r1)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.onFailed(r3, r0, r4)
        L_0x015a:
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r2 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = ""
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r0, r1)
            throw r2
        L_0x0175:
            r2 = move-exception
            r12.b()
            int r3 = r12.i
            if (r3 >= r1) goto L_0x0187
            int r0 = r12.i
            int r0 = r0 + r1
            r12.i = r0
            com.alipay.zoloz.android.phone.mrpc.core.Response r0 = r12.call()
            return r0
        L_0x0187:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            java.lang.String r3 = ""
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = ""
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r0, r2)
            throw r1
        L_0x01b8:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x01dc
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r2 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r2, r3, r4)
        L_0x01dc:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x020d:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            r2 = 9
            if (r1 == 0) goto L_0x0233
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r3, r2, r4)
        L_0x0233:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r3 = ""
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0264:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            r2 = 8
            if (r1 == 0) goto L_0x028a
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r3, r2, r4)
        L_0x028a:
            java.lang.String r1 = "HttpManager"
            java.lang.String r3 = ""
            android.util.Log.e(r1, r3, r0)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x02ac:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            r2 = 5
            if (r1 == 0) goto L_0x02d1
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r3, r2, r4)
        L_0x02d1:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r3 = ""
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0302:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            r2 = 4
            if (r1 == 0) goto L_0x0327
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r3, r2, r4)
        L_0x0327:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r3 = ""
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0358:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x037c
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r3, r2, r4)
        L_0x037c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r3 = ""
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x03ad:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x03d1
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r3 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r3, r2, r4)
        L_0x03d1:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r3 = ""
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0402:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x0426
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r2 = r12.mRequest
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r5 = ""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.onFailed(r2, r3, r4)
        L_0x0426:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0457:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x047b
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r2 = r12.mRequest
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r5 = ""
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r1.onFailed(r2, r4, r3)
        L_0x047b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x04ac:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x04d0
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r2 = r12.mRequest
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r5 = ""
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r1.onFailed(r2, r4, r3)
        L_0x04d0:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.zoloz.android.phone.mrpc.core.HttpException r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ""
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0501:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.Throwable r0 = r0.getCause()
            java.lang.String r2 = "Url parser error!"
            r1.<init>(r2, r0)
            throw r1
        L_0x050e:
            r0 = move-exception
            r12.b()
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            if (r1 == 0) goto L_0x0529
            com.alipay.zoloz.android.phone.mrpc.core.TransportCallback r1 = r12.c()
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlRequest r2 = r12.mRequest
            int r3 = r0.getCode()
            java.lang.String r4 = r0.getMsg()
            r1.onFailed(r2, r3, r4)
        L_0x0529:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.android.phone.mrpc.core.HttpWorker.call():com.alipay.zoloz.android.phone.mrpc.core.Response");
    }

    private void b() {
        if (this.b != null) {
            this.b.abort();
        }
    }

    private TransportCallback c() {
        return this.mRequest.getCallback();
    }

    private HttpResponse d() {
        return e();
    }

    private HttpResponse e() {
        Log.d("HttpWorker", "By Http/Https to request. operationType=" + f() + " url=" + this.b.getURI().toString());
        g().getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, l());
        HttpHost i2 = i();
        if (j() == 80) {
            i2 = new HttpHost(k().getHost());
        }
        return g().execute(i2, (HttpRequest) this.b, this.c);
    }

    private String f() {
        if (!TextUtils.isEmpty(this.m)) {
            return this.m;
        }
        this.m = this.mRequest.getTag("operationType");
        return this.m;
    }

    private AndroidHttpClient g() {
        return this.mHttpManager.getHttpClient();
    }

    private void h() {
        ArrayList<Header> headers = getHeaders();
        if (headers != null && !headers.isEmpty()) {
            Iterator<Header> it = headers.iterator();
            while (it.hasNext()) {
                a().addHeader(it.next());
            }
        }
        AndroidHttpClient.modifyRequestToAcceptGzipResponse(a());
        AndroidHttpClient.modifyRequestToKeepAlive(a());
        a().addHeader("cookie", m().getCookie(this.mRequest.getUrl()));
    }

    private HttpHost i() {
        if (this.g != null) {
            return this.g;
        }
        URL k2 = k();
        this.g = new HttpHost(k2.getHost(), j(), k2.getProtocol());
        return this.g;
    }

    private int j() {
        URL k2 = k();
        if (k2.getPort() == -1) {
            return k2.getDefaultPort();
        }
        return k2.getPort();
    }

    private URL k() {
        if (this.h != null) {
            return this.h;
        }
        this.h = new URL(this.mRequest.getUrl());
        return this.h;
    }

    private HttpHost l() {
        HttpHost proxy = NetworkUtils.getProxy(this.mContext);
        if (proxy == null || !TextUtils.equals(proxy.getHostName(), DatagramAppender.DEFAULT_HOST) || proxy.getPort() != 8087) {
            return proxy;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> getContentType(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(i.b)) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00b8 A[SYNTHETIC, Splitter:B:19:0x00b8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.zoloz.android.phone.mrpc.core.Response handleResponse(org.apache.http.HttpResponse r9, int r10, java.lang.String r11) {
        /*
            r8 = this;
            java.lang.String r0 = "HttpWorker"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "开始handle，handleResponse-1,"
            r1.append(r2)
            java.lang.Thread r2 = java.lang.Thread.currentThread()
            long r2 = r2.getId()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            org.apache.http.HttpEntity r0 = r9.getEntity()
            r1 = 0
            if (r0 == 0) goto L_0x00d1
            org.apache.http.StatusLine r2 = r9.getStatusLine()
            int r2 = r2.getStatusCode()
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L_0x00d1
            java.lang.String r2 = "HttpWorker"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "200，开始处理，handleResponse-2,threadid = "
            r3.append(r4)
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            long r4 = r4.getId()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00b4 }
            r2.<init>()     // Catch:{ all -> 0x00b4 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00b2 }
            r5 = 0
            r8.writeData(r0, r5, r2)     // Catch:{ all -> 0x00b2 }
            byte[] r0 = r2.toByteArray()     // Catch:{ all -> 0x00b2 }
            r1 = 0
            r8.k = r1     // Catch:{ all -> 0x00b2 }
            com.alipay.zoloz.android.phone.mrpc.core.HttpManager r1 = r8.mHttpManager     // Catch:{ all -> 0x00b2 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00b2 }
            r7 = 0
            long r5 = r5 - r3
            r1.addSocketTime(r5)     // Catch:{ all -> 0x00b2 }
            com.alipay.zoloz.android.phone.mrpc.core.HttpManager r1 = r8.mHttpManager     // Catch:{ all -> 0x00b2 }
            int r3 = r0.length     // Catch:{ all -> 0x00b2 }
            long r3 = (long) r3     // Catch:{ all -> 0x00b2 }
            r1.addDataSize(r3)     // Catch:{ all -> 0x00b2 }
            java.lang.String r1 = "HttpWorker"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r3.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r4 = "res:"
            r3.append(r4)     // Catch:{ all -> 0x00b2 }
            int r4 = r0.length     // Catch:{ all -> 0x00b2 }
            r3.append(r4)     // Catch:{ all -> 0x00b2 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00b2 }
            android.util.Log.i(r1, r3)     // Catch:{ all -> 0x00b2 }
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlResponse r1 = new com.alipay.zoloz.android.phone.mrpc.core.HttpUrlResponse     // Catch:{ all -> 0x00b2 }
            com.alipay.zoloz.android.phone.mrpc.core.HttpUrlHeader r3 = r8.handleResponseHeader(r9)     // Catch:{ all -> 0x00b2 }
            r1.<init>(r3, r10, r11, r0)     // Catch:{ all -> 0x00b2 }
            r8.fillResponse(r1, r9)     // Catch:{ all -> 0x00b2 }
            r2.close()     // Catch:{ IOException -> 0x00a5 }
            java.lang.String r9 = "HttpWorker"
            java.lang.String r10 = "finally,handleResponse"
            android.util.Log.d(r9, r10)
            goto L_0x00dd
        L_0x00a5:
            r9 = move-exception
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.Throwable r9 = r9.getCause()
            java.lang.String r11 = "ArrayOutputStream close error!"
            r10.<init>(r11, r9)
            throw r10
        L_0x00b2:
            r9 = move-exception
            goto L_0x00b6
        L_0x00b4:
            r9 = move-exception
            r2 = r1
        L_0x00b6:
            if (r2 == 0) goto L_0x00c9
            r2.close()     // Catch:{ IOException -> 0x00bc }
            goto L_0x00c9
        L_0x00bc:
            r9 = move-exception
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            java.lang.Throwable r9 = r9.getCause()
            java.lang.String r11 = "ArrayOutputStream close error!"
            r10.<init>(r11, r9)
            throw r10
        L_0x00c9:
            java.lang.String r10 = "HttpWorker"
            java.lang.String r11 = "finally,handleResponse"
            android.util.Log.d(r10, r11)
            throw r9
        L_0x00d1:
            if (r0 != 0) goto L_0x00dd
            org.apache.http.StatusLine r9 = r9.getStatusLine()
            int r9 = r9.getStatusCode()
            r10 = 304(0x130, float:4.26E-43)
        L_0x00dd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.android.phone.mrpc.core.HttpWorker.handleResponse(org.apache.http.HttpResponse, int, java.lang.String):com.alipay.zoloz.android.phone.mrpc.core.Response");
    }

    /* access modifiers changed from: protected */
    public HttpUrlHeader handleResponseHeader(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    /* access modifiers changed from: protected */
    public void fillResponse(HttpUrlResponse httpUrlResponse, HttpResponse httpResponse) {
        String str;
        long period = getPeriod(httpResponse);
        Header contentType = httpResponse.getEntity().getContentType();
        String str2 = null;
        if (contentType != null) {
            HashMap<String, String> contentType2 = getContentType(contentType.getValue());
            str2 = contentType2.get(HTTP.CHARSET);
            str = contentType2.get("Content-Type");
        } else {
            str = null;
        }
        httpUrlResponse.setContentType(str);
        httpUrlResponse.setCharset(str2);
        httpUrlResponse.setCreateTime(System.currentTimeMillis());
        httpUrlResponse.setPeriod(period);
    }

    /* access modifiers changed from: protected */
    public long getPeriod(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return parserMaxage(split);
                } catch (NumberFormatException e2) {
                    Log.w("HttpWorker", e2);
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Expires");
        if (firstHeader2 != null) {
            return AndroidHttpClient.parseDate(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public long parserMaxage(String[] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if ("max-age".equalsIgnoreCase(strArr[i2])) {
                int i3 = i2 + 1;
                if (strArr[i3] != null) {
                    try {
                        return Long.parseLong(strArr[i3]);
                    } catch (Exception unused) {
                    }
                } else {
                    continue;
                }
            }
        }
        return 0;
    }

    public HttpUrlRequest getRequest() {
        return this.mRequest;
    }

    /* access modifiers changed from: protected */
    public void writeData(HttpEntity httpEntity, long j2, OutputStream outputStream) {
        if (outputStream != null) {
            InputStream ungzippedContent = AndroidHttpClient.getUngzippedContent(httpEntity);
            long contentLength = httpEntity.getContentLength();
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = ungzippedContent.read(bArr);
                    if (read == -1 || this.mRequest.isCanceled()) {
                        outputStream.flush();
                    } else {
                        outputStream.write(bArr, 0, read);
                        j2 += (long) read;
                        if (c() != null && contentLength > 0) {
                            TransportCallback c2 = c();
                            HttpUrlRequest httpUrlRequest = this.mRequest;
                            double d2 = (double) j2;
                            double d3 = (double) contentLength;
                            Double.isNaN(d2);
                            Double.isNaN(d3);
                            c2.onProgressUpdate(httpUrlRequest, d2 / d3);
                        }
                    }
                }
                outputStream.flush();
                IOUtil.closeStream(ungzippedContent);
            } catch (Exception e2) {
                Log.w("HttpWorker", e2.getCause());
                throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
            } catch (Throwable th) {
                IOUtil.closeStream(ungzippedContent);
                throw th;
            }
        } else {
            httpEntity.consumeContent();
            throw new IllegalArgumentException("Output stream may not be null");
        }
    }

    private CookieManager m() {
        if (this.e != null) {
            return this.e;
        }
        this.e = CookieManager.getInstance();
        return this.e;
    }

    public Response processResponse(HttpResponse httpResponse, HttpUrlRequest httpUrlRequest) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        if (statusCode == 200 || willHandleOtherCode(statusCode, reasonPhrase)) {
            return handleResponse(httpResponse, statusCode, reasonPhrase);
        }
        throw new HttpException(Integer.valueOf(httpResponse.getStatusLine().getStatusCode()), httpResponse.getStatusLine().getReasonPhrase());
    }
}
