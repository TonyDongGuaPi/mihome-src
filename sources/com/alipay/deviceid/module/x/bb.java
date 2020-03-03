package com.alipay.deviceid.module.x;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.alipay.deviceid.module.rpc.mrpc.core.HttpUrlHeader;
import com.alipay.sdk.util.i;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class bb implements Callable<bf> {
    private static final HttpRequestRetryHandler e = new bo();

    /* renamed from: a  reason: collision with root package name */
    protected ay f889a;
    protected Context b;
    protected az c;
    String d;
    private HttpUriRequest f;
    private HttpContext g = new BasicHttpContext();
    private CookieStore h = new BasicCookieStore();
    private CookieManager i;
    private AbstractHttpEntity j;
    private HttpHost k;
    private URL l;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private String p = null;
    private String q;

    public bb(ay ayVar, az azVar) {
        this.f889a = ayVar;
        this.b = this.f889a.f883a;
        this.c = azVar;
    }

    private URI b() {
        String str = this.c.f886a;
        if (this.d != null) {
            str = this.d;
        }
        if (str != null) {
            return new URI(str);
        }
        throw new RuntimeException("url should not be null");
    }

    private HttpUriRequest c() {
        if (this.f != null) {
            return this.f;
        }
        if (this.j == null) {
            byte[] bArr = this.c.b;
            String a2 = this.c.a("gzip");
            if (bArr != null) {
                if (TextUtils.equals(a2, "true")) {
                    this.j = at.a(bArr);
                } else {
                    this.j = new ByteArrayEntity(bArr);
                }
                this.j.setContentType(this.c.c);
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(b());
            httpPost.setEntity(abstractHttpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0294, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0295, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x029a, code lost:
        if (r13.m <= 0) goto L_0x029c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x029c, code lost:
        r13.m++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02a3, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02b9, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(0, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02ba, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02bb, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02c4, code lost:
        if (r13.c.a() != null) goto L_0x02c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02c6, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02ce, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02e4, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(6, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02e5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02e6, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02ef, code lost:
        if (r13.c.a() != null) goto L_0x02f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02f1, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02f9, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0311, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(9, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0312, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0313, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x031c, code lost:
        if (r13.c.a() != null) goto L_0x031e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x031e, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0326, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, "", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x033c, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(8, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x033d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x033e, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0347, code lost:
        if (r13.c.a() != null) goto L_0x0349;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0349, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0351, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0368, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(5, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0369, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x036a, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0373, code lost:
        if (r13.c.a() != null) goto L_0x0375;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0375, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x037d, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0394, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(4, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0395, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0396, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x039f, code lost:
        if (r13.c.a() != null) goto L_0x03a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03a1, code lost:
        new java.lang.StringBuilder().append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x03a9, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03bf, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(3, java.lang.String.valueOf(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03c0, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03c1, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x03ca, code lost:
        if (r13.c.a() != null) goto L_0x03cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x03cc, code lost:
        new java.lang.StringBuilder().append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x03d4, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03ea, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(3, java.lang.String.valueOf(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x03eb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x03ec, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x03f5, code lost:
        if (r13.c.a() != null) goto L_0x03f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03f7, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03ff, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0415, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(6, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0416, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0417, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0420, code lost:
        if (r13.c.a() != null) goto L_0x0422;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0422, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x042a, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0440, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(2, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0441, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0442, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x044b, code lost:
        if (r13.c.a() != null) goto L_0x044d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x044d, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0455, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x046b, code lost:
        throw new com.alipay.deviceid.module.rpc.mrpc.core.HttpException(2, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x046c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0478, code lost:
        throw new java.lang.RuntimeException("Url parser error!", r0.getCause());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0479, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x047a, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0483, code lost:
        if (r13.c.a() != null) goto L_0x0485;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0485, code lost:
        r0.getCode();
        r0.getMsg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x048b, code lost:
        android.util.Log.e(com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0494, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        android.util.Log.e("HttpWorker", "parse Content-Length error");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0294 A[ExcHandler: NullPointerException (r0v22 'e' java.lang.NullPointerException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02ba A[ExcHandler: IOException (r0v20 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02e5 A[ExcHandler: UnknownHostException (r0v18 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0312 A[ExcHandler: HttpHostConnectException (r0v16 'e' org.apache.http.conn.HttpHostConnectException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x033d A[ExcHandler: NoHttpResponseException (r0v14 'e' org.apache.http.NoHttpResponseException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0369 A[ExcHandler: SocketTimeoutException (r0v12 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0395 A[ExcHandler: ConnectTimeoutException (r1v18 'e' org.apache.http.conn.ConnectTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x03c0 A[ExcHandler: ConnectionPoolTimeoutException (r1v16 'e' org.apache.http.conn.ConnectionPoolTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x03eb A[ExcHandler: SSLException (r0v8 'e' javax.net.ssl.SSLException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0416 A[ExcHandler: SSLPeerUnverifiedException (r0v6 'e' javax.net.ssl.SSLPeerUnverifiedException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0441 A[ExcHandler: SSLHandshakeException (r0v4 'e' javax.net.ssl.SSLHandshakeException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x046c A[ExcHandler: URISyntaxException (r0v2 'e' java.net.URISyntaxException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034 A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0479 A[ExcHandler: HttpException (r0v1 'e' com.alipay.deviceid.module.rpc.mrpc.core.HttpException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x025f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fd A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fe A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0106 A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0109 A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x012a A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0162 A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x016f A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01e2 A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0214 A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x021b A[Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.deviceid.module.x.bf call() {
        /*
            r13 = this;
        L_0x0000:
            r0 = 3
            r1 = 6
            r2 = 2
            r3 = 1
            r4 = 0
            android.content.Context r5 = r13.b     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = "connectivity"
            java.lang.Object r5 = r5.getSystemService(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.net.NetworkInfo[] r5 = r5.getAllNetworkInfo()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r5 != 0) goto L_0x0019
        L_0x0017:
            r5 = 0
            goto L_0x0032
        L_0x0019:
            int r6 = r5.length     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7 = 0
        L_0x001b:
            if (r7 >= r6) goto L_0x0017
            r8 = r5[r7]     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r8 == 0) goto L_0x002f
            boolean r9 = r8.isAvailable()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r9 == 0) goto L_0x002f
            boolean r8 = r8.isConnectedOrConnecting()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r8 == 0) goto L_0x002f
            r5 = 1
            goto L_0x0032
        L_0x002f:
            int r7 = r7 + 1
            goto L_0x001b
        L_0x0032:
            if (r5 == 0) goto L_0x025f
            com.alipay.deviceid.module.x.az r5 = r13.c     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.util.ArrayList<org.apache.http.Header> r5 = r5.d     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r5 == 0) goto L_0x0058
            boolean r6 = r5.isEmpty()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 != 0) goto L_0x0058
            java.util.Iterator r5 = r5.iterator()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x0044:
            boolean r6 = r5.hasNext()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 == 0) goto L_0x0058
            java.lang.Object r6 = r5.next()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.Header r6 = (org.apache.http.Header) r6     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.methods.HttpUriRequest r7 = r13.c()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.addHeader(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            goto L_0x0044
        L_0x0058:
            org.apache.http.client.methods.HttpUriRequest r5 = r13.c()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.at.a((org.apache.http.HttpRequest) r5)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.methods.HttpUriRequest r5 = r13.c()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.at.b((org.apache.http.HttpRequest) r5)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.methods.HttpUriRequest r5 = r13.c()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = "cookie"
            android.webkit.CookieManager r7 = r13.i()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.az r8 = r13.c     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = r8.f886a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r7 = r7.getCookie(r8)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r5.addHeader(r6, r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.protocol.HttpContext r5 = r13.g     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = "http.cookie-store"
            org.apache.http.client.CookieStore r7 = r13.h     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r5.setAttribute(r6, r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.ay r5 = r13.f889a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.at r5 = r5.b     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.HttpRequestRetryHandler r6 = e     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.HttpClient r5 = r5.b     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.impl.client.DefaultHttpClient r5 = (org.apache.http.impl.client.DefaultHttpClient) r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r5.setHttpRequestRetryHandler(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = "By Http/Https to request. operationType="
            r7.<init>(r8)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = r13.f()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.append(r8)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = " url="
            r7.append(r8)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.methods.HttpUriRequest r8 = r13.f     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.net.URI r8 = r8.getURI()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = r8.toString()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.append(r8)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.ay r7 = r13.f889a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.at r7 = r7.b     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.params.HttpParams r7 = r7.getParams()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = "http.route.default-proxy"
            android.content.Context r9 = r13.b     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r10 = "connectivity"
            java.lang.Object r9 = r9.getSystemService(r10)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.net.ConnectivityManager r9 = (android.net.ConnectivityManager) r9     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.net.NetworkInfo r9 = r9.getActiveNetworkInfo()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r10 = 0
            if (r9 == 0) goto L_0x00e6
            boolean r9 = r9.isAvailable()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r9 == 0) goto L_0x00e6
            java.lang.String r9 = android.net.Proxy.getDefaultHost()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r11 = android.net.Proxy.getDefaultPort()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r9 == 0) goto L_0x00e6
            org.apache.http.HttpHost r12 = new org.apache.http.HttpHost     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r12.<init>(r9, r11)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            goto L_0x00e7
        L_0x00e6:
            r12 = r10
        L_0x00e7:
            if (r12 == 0) goto L_0x00fe
            java.lang.String r9 = r12.getHostName()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r11 = "127.0.0.1"
            boolean r9 = android.text.TextUtils.equals(r9, r11)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r9 == 0) goto L_0x00fe
            int r9 = r12.getPort()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r11 = 8087(0x1f97, float:1.1332E-41)
            if (r9 != r11) goto L_0x00fe
            goto L_0x00ff
        L_0x00fe:
            r10 = r12
        L_0x00ff:
            r7.setParameter(r8, r10)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.HttpHost r7 = r13.k     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r7 == 0) goto L_0x0109
            org.apache.http.HttpHost r7 = r13.k     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            goto L_0x0122
        L_0x0109:
            java.net.URL r7 = r13.h()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.HttpHost r8 = new org.apache.http.HttpHost     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = r7.getHost()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r10 = r13.g()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r7 = r7.getProtocol()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8.<init>(r9, r10, r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r13.k = r8     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.HttpHost r7 = r13.k     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x0122:
            int r8 = r13.g()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r9 = 80
            if (r8 != r9) goto L_0x0137
            org.apache.http.HttpHost r7 = new org.apache.http.HttpHost     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.net.URL r8 = r13.h()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = r8.getHost()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.<init>((java.lang.String) r8)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x0137:
            com.alipay.deviceid.module.x.ay r8 = r13.f889a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.at r8 = r8.b     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.methods.HttpUriRequest r9 = r13.f     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.protocol.HttpContext r10 = r13.g     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.HttpResponse r7 = r8.execute((org.apache.http.HttpHost) r7, (org.apache.http.HttpRequest) r9, (org.apache.http.protocol.HttpContext) r10)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.ay r10 = r13.f889a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r11 = 0
            long r8 = r8 - r5
            long r5 = r10.d     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r11 = 0
            long r5 = r5 + r8
            r10.d = r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r5 = r10.f     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r5 = r5 + r3
            r10.f = r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.client.CookieStore r5 = r13.h     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.util.List r5 = r5.getCookies()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.az r6 = r13.c     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            boolean r6 = r6.e     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 == 0) goto L_0x0169
            android.webkit.CookieManager r6 = r13.i()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r6.removeAllCookie()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x0169:
            boolean r6 = r5.isEmpty()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 != 0) goto L_0x01ce
            java.util.Iterator r5 = r5.iterator()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x0173:
            boolean r6 = r5.hasNext()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 == 0) goto L_0x01ce
            java.lang.Object r6 = r5.next()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.cookie.Cookie r6 = (org.apache.http.cookie.Cookie) r6     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r8 = r6.getDomain()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r8 == 0) goto L_0x0173
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8.<init>()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = r6.getName()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8.append(r9)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = "="
            r8.append(r9)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = r6.getValue()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8.append(r9)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = "; domain="
            r8.append(r9)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = r6.getDomain()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8.append(r9)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            boolean r6 = r6.isSecure()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 == 0) goto L_0x01b2
            java.lang.String r6 = "; Secure"
            goto L_0x01b4
        L_0x01b2:
            java.lang.String r6 = ""
        L_0x01b4:
            r8.append(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = r8.toString()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.webkit.CookieManager r8 = r13.i()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.x.az r9 = r13.c     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r9 = r9.f886a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8.setCookie(r9, r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            android.webkit.CookieSyncManager r6 = android.webkit.CookieSyncManager.getInstance()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r6.sync()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            goto L_0x0173
        L_0x01ce:
            org.apache.http.StatusLine r5 = r7.getStatusLine()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r5 = r5.getStatusCode()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.StatusLine r6 = r7.getStatusLine()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = r6.getReasonPhrase()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r8 = 200(0xc8, float:2.8E-43)
            if (r5 == r8) goto L_0x0206
            r8 = 304(0x130, float:4.26E-43)
            if (r5 != r8) goto L_0x01e8
            r8 = 1
            goto L_0x01e9
        L_0x01e8:
            r8 = 0
        L_0x01e9:
            if (r8 == 0) goto L_0x01ec
            goto L_0x0206
        L_0x01ec:
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r5 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.StatusLine r6 = r7.getStatusLine()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r6 = r6.getStatusCode()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            org.apache.http.StatusLine r7 = r7.getStatusLine()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r7 = r7.getReasonPhrase()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r5.<init>(r6, r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            throw r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x0206:
            com.alipay.deviceid.module.x.bf r5 = r13.a(r7, r5, r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r6 = -1
            if (r5 == 0) goto L_0x021b
            byte[] r8 = r5.a()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r8 == 0) goto L_0x021b
            byte[] r8 = r5.a()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            int r8 = r8.length     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            long r8 = (long) r8     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            goto L_0x021c
        L_0x021b:
            r8 = r6
        L_0x021c:
            int r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x023a
            boolean r6 = r5 instanceof com.alipay.deviceid.module.x.ba     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 == 0) goto L_0x023a
            r6 = r5
            com.alipay.deviceid.module.x.ba r6 = (com.alipay.deviceid.module.x.ba) r6     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            com.alipay.deviceid.module.rpc.mrpc.core.HttpUrlHeader r6 = r6.d     // Catch:{ Exception -> 0x0233, HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294 }
            java.lang.String r7 = "Content-Length"
            java.lang.String r6 = r6.getHead(r7)     // Catch:{ Exception -> 0x0233, HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294 }
            java.lang.Long.parseLong(r6)     // Catch:{ Exception -> 0x0233, HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294 }
            goto L_0x023a
        L_0x0233:
            java.lang.String r6 = "HttpWorker"
            java.lang.String r7 = "parse Content-Length error"
            android.util.Log.e(r6, r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x023a:
            com.alipay.deviceid.module.x.az r6 = r13.c     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = r6.f886a     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r6 == 0) goto L_0x025e
            java.lang.String r7 = r13.f()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            if (r7 != 0) goto L_0x025e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.<init>()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.append(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = "#"
            r7.append(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r6 = r13.f()     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            r7.append(r6)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x025e:
            return r5
        L_0x025f:
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r5 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            java.lang.String r7 = "The network is not available"
            r5.<init>(r6, r7)     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
            throw r5     // Catch:{ HttpException -> 0x0479, URISyntaxException -> 0x046c, SSLHandshakeException -> 0x0441, SSLPeerUnverifiedException -> 0x0416, SSLException -> 0x03eb, ConnectionPoolTimeoutException -> 0x03c0, ConnectTimeoutException -> 0x0395, SocketTimeoutException -> 0x0369, NoHttpResponseException -> 0x033d, HttpHostConnectException -> 0x0312, UnknownHostException -> 0x02e5, IOException -> 0x02ba, NullPointerException -> 0x0294, Exception -> 0x026b }
        L_0x026b:
            r0 = move-exception
            java.lang.String r1 = "HttpManager"
            java.lang.String r2 = ""
            android.util.Log.e(r1, r2, r0)
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x0286
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x0286:
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x0294:
            r0 = move-exception
            r13.e()
            int r1 = r13.m
            if (r1 > 0) goto L_0x02a3
            int r0 = r13.m
            int r0 = r0 + r3
            r13.m = r0
            goto L_0x0000
        L_0x02a3:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x02ba:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r2 = r13.c
            com.alipay.deviceid.module.x.bn r2 = r2.a()
            if (r2 == 0) goto L_0x02ce
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
        L_0x02ce:
            java.lang.String r2 = java.lang.String.valueOf(r0)
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r2)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r2 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.<init>(r1, r0)
            throw r2
        L_0x02e5:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x02f9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x02f9:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            r2 = 9
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x0312:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x0326
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x0326:
            java.lang.String r1 = "HttpManager"
            java.lang.String r2 = ""
            android.util.Log.e(r1, r2, r0)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            r2 = 8
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x033d:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x0351
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x0351:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x0369:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x037d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x037d:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            r2 = 4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x0395:
            r1 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r2 = r13.c
            com.alipay.deviceid.module.x.bn r2 = r2.a()
            if (r2 == 0) goto L_0x03a9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
        L_0x03a9:
            java.lang.String r2 = java.lang.String.valueOf(r1)
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r2)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r2 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r2.<init>(r0, r1)
            throw r2
        L_0x03c0:
            r1 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r2 = r13.c
            com.alipay.deviceid.module.x.bn r2 = r2.a()
            if (r2 == 0) goto L_0x03d4
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
        L_0x03d4:
            java.lang.String r2 = java.lang.String.valueOf(r1)
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r2)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r2 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r2.<init>(r0, r1)
            throw r2
        L_0x03eb:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r2 = r13.c
            com.alipay.deviceid.module.x.bn r2 = r2.a()
            if (r2 == 0) goto L_0x03ff
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
        L_0x03ff:
            java.lang.String r2 = java.lang.String.valueOf(r0)
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r2)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r2 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.<init>(r1, r0)
            throw r2
        L_0x0416:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x042a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x042a:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x0441:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x0455
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
        L_0x0455:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r3 = "HttpManager"
            android.util.Log.e(r3, r1)
            com.alipay.deviceid.module.rpc.mrpc.core.HttpException r1 = new com.alipay.deviceid.module.rpc.mrpc.core.HttpException
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r2, r0)
            throw r1
        L_0x046c:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.Throwable r0 = r0.getCause()
            java.lang.String r2 = "Url parser error!"
            r1.<init>(r2, r0)
            throw r1
        L_0x0479:
            r0 = move-exception
            r13.e()
            com.alipay.deviceid.module.x.az r1 = r13.c
            com.alipay.deviceid.module.x.bn r1 = r1.a()
            if (r1 == 0) goto L_0x048b
            r0.getCode()
            r0.getMsg()
        L_0x048b:
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "HttpManager"
            android.util.Log.e(r2, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.bb.call():com.alipay.deviceid.module.x.bf");
    }

    private void e() {
        if (this.f != null) {
            this.f.abort();
        }
    }

    private String f() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        this.q = this.c.a("operationType");
        return this.q;
    }

    private int g() {
        URL h2 = h();
        return h2.getPort() == -1 ? h2.getDefaultPort() : h2.getPort();
    }

    private URL h() {
        if (this.l != null) {
            return this.l;
        }
        this.l = new URL(this.c.f886a);
        return this.l;
    }

    private static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(i.b)) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split("=");
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c5 A[SYNTHETIC, Splitter:B:23:0x00c5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.alipay.deviceid.module.x.bf a(org.apache.http.HttpResponse r10, int r11, java.lang.String r12) {
        /*
            r9 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "开始handle，handleResponse-1,"
            r0.<init>(r1)
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            long r1 = r1.getId()
            r0.append(r1)
            org.apache.http.HttpEntity r0 = r10.getEntity()
            r1 = 0
            if (r0 == 0) goto L_0x00d7
            org.apache.http.StatusLine r2 = r10.getStatusLine()
            int r2 = r2.getStatusCode()
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L_0x00d7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "200，开始处理，handleResponse-2,threadid = "
            r2.<init>(r3)
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            long r3 = r3.getId()
            r2.append(r3)
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00c1 }
            r2.<init>()     // Catch:{ all -> 0x00c1 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00bf }
            r9.a(r0, r2)     // Catch:{ all -> 0x00bf }
            byte[] r0 = r2.toByteArray()     // Catch:{ all -> 0x00bf }
            r5 = 0
            r9.o = r5     // Catch:{ all -> 0x00bf }
            com.alipay.deviceid.module.x.ay r5 = r9.f889a     // Catch:{ all -> 0x00bf }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00bf }
            r8 = 0
            long r6 = r6 - r3
            long r3 = r5.e     // Catch:{ all -> 0x00bf }
            r8 = 0
            long r3 = r3 + r6
            r5.e = r3     // Catch:{ all -> 0x00bf }
            com.alipay.deviceid.module.x.ay r3 = r9.f889a     // Catch:{ all -> 0x00bf }
            int r4 = r0.length     // Catch:{ all -> 0x00bf }
            long r4 = (long) r4     // Catch:{ all -> 0x00bf }
            long r6 = r3.c     // Catch:{ all -> 0x00bf }
            r8 = 0
            long r6 = r6 + r4
            r3.c = r6     // Catch:{ all -> 0x00bf }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = "res:"
            r3.<init>(r4)     // Catch:{ all -> 0x00bf }
            int r4 = r0.length     // Catch:{ all -> 0x00bf }
            r3.append(r4)     // Catch:{ all -> 0x00bf }
            com.alipay.deviceid.module.x.ba r3 = new com.alipay.deviceid.module.x.ba     // Catch:{ all -> 0x00bf }
            com.alipay.deviceid.module.rpc.mrpc.core.HttpUrlHeader r4 = a((org.apache.http.HttpResponse) r10)     // Catch:{ all -> 0x00bf }
            r3.<init>(r4, r11, r12, r0)     // Catch:{ all -> 0x00bf }
            long r11 = b(r10)     // Catch:{ all -> 0x00bf }
            org.apache.http.HttpEntity r10 = r10.getEntity()     // Catch:{ all -> 0x00bf }
            org.apache.http.Header r10 = r10.getContentType()     // Catch:{ all -> 0x00bf }
            if (r10 == 0) goto L_0x009f
            java.lang.String r10 = r10.getValue()     // Catch:{ all -> 0x00bf }
            java.util.HashMap r10 = a((java.lang.String) r10)     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "charset"
            java.lang.Object r0 = r10.get(r0)     // Catch:{ all -> 0x00bf }
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = "Content-Type"
            java.lang.Object r10 = r10.get(r0)     // Catch:{ all -> 0x00bf }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x00bf }
            goto L_0x00a0
        L_0x009f:
            r10 = r1
        L_0x00a0:
            r3.a(r10)     // Catch:{ all -> 0x00bf }
            r3.c = r1     // Catch:{ all -> 0x00bf }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00bf }
            r3.f888a = r0     // Catch:{ all -> 0x00bf }
            r3.b = r11     // Catch:{ all -> 0x00bf }
            r2.close()     // Catch:{ IOException -> 0x00b2 }
            r1 = r3
            goto L_0x00e0
        L_0x00b2:
            r10 = move-exception
            java.lang.RuntimeException r11 = new java.lang.RuntimeException
            java.lang.Throwable r10 = r10.getCause()
            java.lang.String r12 = "ArrayOutputStream close error!"
            r11.<init>(r12, r10)
            throw r11
        L_0x00bf:
            r10 = move-exception
            goto L_0x00c3
        L_0x00c1:
            r10 = move-exception
            r2 = r1
        L_0x00c3:
            if (r2 == 0) goto L_0x00d6
            r2.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00d6
        L_0x00c9:
            r10 = move-exception
            java.lang.RuntimeException r11 = new java.lang.RuntimeException
            java.lang.Throwable r10 = r10.getCause()
            java.lang.String r12 = "ArrayOutputStream close error!"
            r11.<init>(r12, r10)
            throw r11
        L_0x00d6:
            throw r10
        L_0x00d7:
            if (r0 != 0) goto L_0x00e0
            org.apache.http.StatusLine r10 = r10.getStatusLine()
            r10.getStatusCode()
        L_0x00e0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.bb.a(org.apache.http.HttpResponse, int, java.lang.String):com.alipay.deviceid.module.x.bf");
    }

    private static HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    private static long b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split("=");
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Expires");
        if (firstHeader2 != null) {
            return at.b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0;
    }

    private static long a(String[] strArr) {
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

    public final az a() {
        return this.c;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        InputStream a2 = at.a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = a2.read(bArr);
                if (read == -1 || this.c.f) {
                    outputStream.flush();
                } else {
                    outputStream.write(bArr, 0, read);
                    if (this.c.a() != null) {
                        int i2 = (contentLength > 0 ? 1 : (contentLength == 0 ? 0 : -1));
                    }
                }
            }
            outputStream.flush();
            bc.a(a2);
        } catch (Exception e2) {
            e2.getCause();
            throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
        } catch (Throwable th) {
            bc.a(a2);
            throw th;
        }
    }

    private CookieManager i() {
        if (this.i != null) {
            return this.i;
        }
        this.i = CookieManager.getInstance();
        return this.i;
    }
}
