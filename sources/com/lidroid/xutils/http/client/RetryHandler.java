package com.lidroid.xutils.http.client;

import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;

public class RetryHandler implements HttpRequestRetryHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6341a = 500;
    private static HashSet<Class<?>> b = new HashSet<>();
    private static HashSet<Class<?>> c = new HashSet<>();
    private final int d;

    static {
        b.add(NoHttpResponseException.class);
        b.add(UnknownHostException.class);
        b.add(SocketException.class);
        c.add(InterruptedIOException.class);
        c.add(SSLHandshakeException.class);
    }

    public RetryHandler(int i) {
        this.d = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean retryRequest(java.io.IOException r5, int r6, org.apache.http.protocol.HttpContext r7) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L_0x0078
            if (r7 != 0) goto L_0x0007
            goto L_0x0078
        L_0x0007:
            java.lang.String r1 = "http.request_sent"
            java.lang.Object r1 = r7.getAttribute(r1)
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0017
        L_0x0011:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
        L_0x0017:
            int r2 = r4.d
            r3 = 1
            if (r6 <= r2) goto L_0x001e
        L_0x001c:
            r3 = 0
            goto L_0x0035
        L_0x001e:
            java.util.HashSet<java.lang.Class<?>> r6 = c
            java.lang.Class r2 = r5.getClass()
            boolean r6 = r6.contains(r2)
            if (r6 == 0) goto L_0x002b
            goto L_0x001c
        L_0x002b:
            java.util.HashSet<java.lang.Class<?>> r6 = b
            java.lang.Class r5 = r5.getClass()
            boolean r5 = r6.contains(r5)
        L_0x0035:
            if (r3 == 0) goto L_0x006f
            java.lang.String r5 = "http.request"
            java.lang.Object r5 = r7.getAttribute(r5)     // Catch:{ Throwable -> 0x0067 }
            if (r5 == 0) goto L_0x0061
            boolean r6 = r5 instanceof org.apache.http.client.methods.HttpRequestBase     // Catch:{ Throwable -> 0x0067 }
            if (r6 == 0) goto L_0x0050
            org.apache.http.client.methods.HttpRequestBase r5 = (org.apache.http.client.methods.HttpRequestBase) r5     // Catch:{ Throwable -> 0x0067 }
            java.lang.String r6 = "GET"
            java.lang.String r5 = r5.getMethod()     // Catch:{ Throwable -> 0x0067 }
            boolean r5 = r6.equals(r5)     // Catch:{ Throwable -> 0x0067 }
            goto L_0x0070
        L_0x0050:
            boolean r6 = r5 instanceof org.apache.http.impl.client.RequestWrapper     // Catch:{ Throwable -> 0x0067 }
            if (r6 == 0) goto L_0x006f
            org.apache.http.impl.client.RequestWrapper r5 = (org.apache.http.impl.client.RequestWrapper) r5     // Catch:{ Throwable -> 0x0067 }
            java.lang.String r6 = "GET"
            java.lang.String r5 = r5.getMethod()     // Catch:{ Throwable -> 0x0067 }
            boolean r5 = r6.equals(r5)     // Catch:{ Throwable -> 0x0067 }
            goto L_0x0070
        L_0x0061:
            java.lang.String r5 = "retry error, curr request is null"
            com.lidroid.xutils.util.LogUtils.b((java.lang.String) r5)     // Catch:{ Throwable -> 0x0067 }
            goto L_0x006d
        L_0x0067:
            r5 = move-exception
            java.lang.String r6 = "retry error"
            com.lidroid.xutils.util.LogUtils.b(r6, r5)
        L_0x006d:
            r5 = 0
            goto L_0x0070
        L_0x006f:
            r5 = r3
        L_0x0070:
            if (r5 == 0) goto L_0x0077
            r6 = 500(0x1f4, double:2.47E-321)
            android.os.SystemClock.sleep(r6)
        L_0x0077:
            return r5
        L_0x0078:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.http.client.RetryHandler.retryRequest(java.io.IOException, int, org.apache.http.protocol.HttpContext):boolean");
    }
}
