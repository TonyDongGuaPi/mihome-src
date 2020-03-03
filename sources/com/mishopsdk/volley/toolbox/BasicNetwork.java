package com.mishopsdk.volley.toolbox;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.mishopsdk.volley.Cache;
import com.mishopsdk.volley.Network;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.RetryPolicy;
import com.mishopsdk.volley.ServerError;
import com.mishopsdk.volley.VolleyError;
import com.mishopsdk.volley.VolleyLog;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.io.http.RequestConstants;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008f, code lost:
        r14 = r4;
        r13 = null;
        r28 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f9, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00fb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00fc, code lost:
        r27 = r4;
        r28 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0100, code lost:
        r13 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0103, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0104, code lost:
        r27 = r4;
        r28 = 0;
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0109, code lost:
        r14 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010d, code lost:
        r28 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0110, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0111, code lost:
        r28 = 0;
        r24 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0115, code lost:
        r14 = r3;
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0123, code lost:
        r0 = r23.getStatusLine().getStatusCode();
        r2 = new java.lang.Object[2];
        r2[r28] = java.lang.Integer.valueOf(r0);
        r2[1] = r30.getUrl();
        com.mishopsdk.volley.VolleyLog.e("Unexpected response code %d for %s", r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0140, code lost:
        if (r13 != null) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0142, code lost:
        r11 = new com.mishopsdk.volley.NetworkResponse(r0, r13, r14, false, (int) (android.os.SystemClock.elapsedRealtime() - r9), (int) (r24 - r9), (int) (android.os.SystemClock.elapsedRealtime() - r24), r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0164, code lost:
        if (r0 == 401) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0170, code lost:
        throw new com.mishopsdk.volley.ServerError(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0171, code lost:
        attemptRetryOnException("auth", r8, new com.mishopsdk.volley.AuthFailureError(r11));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0181, code lost:
        throw new com.mishopsdk.volley.NetworkError(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0187, code lost:
        throw new com.mishopsdk.volley.NoConnectionError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0188, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a3, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r30.getUrl(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01a4, code lost:
        r24 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01a6, code lost:
        attemptRetryOnException("connection", r8, new com.mishopsdk.volley.TimeoutError());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01b1, code lost:
        r24 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01b3, code lost:
        attemptRetryOnException("socket", r8, new com.mishopsdk.volley.TimeoutError());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0188 A[ExcHandler: MalformedURLException (r0v3 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:78:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:6:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:82:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:6:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0182 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.mishopsdk.volley.NetworkResponse performRequest(com.mishopsdk.volley.Request<?> r30) throws com.mishopsdk.volley.VolleyError {
        /*
            r29 = this;
            r7 = r29
            r8 = r30
            long r9 = android.os.SystemClock.elapsedRealtime()
            long r21 = java.lang.System.currentTimeMillis()
            long r0 = android.os.SystemClock.elapsedRealtime()
            r1 = r0
        L_0x0011:
            java.util.Map r3 = java.util.Collections.emptyMap()
            r6 = 0
            r5 = 0
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0118 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0118 }
            com.mishopsdk.volley.Cache$Entry r4 = r30.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0118 }
            r7.addCacheHeaders(r0, r4)     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0118 }
            com.mishopsdk.volley.toolbox.HttpStack r4 = r7.mHttpStack     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0118 }
            org.apache.http.HttpResponse r23 = r4.performRequest(r8, r0)     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0118 }
            long r24 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b1, ConnectTimeoutException -> 0x01a4, MalformedURLException -> 0x0188, IOException -> 0x0110 }
            org.apache.http.StatusLine r0 = r23.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x010c }
            int r12 = r0.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x010c }
            java.lang.String r1 = r30.getUrl()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x010c }
            org.apache.http.Header[] r2 = r23.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x010c }
            java.util.Map r4 = r7.convertHeaders(r1, r2)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x010c }
            r1 = 304(0x130, float:4.26E-43)
            if (r12 != r1) goto L_0x0095
            com.mishopsdk.volley.Cache$Entry r0 = r30.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            if (r0 != 0) goto L_0x0069
            com.mishopsdk.volley.NetworkResponse r0 = new com.mishopsdk.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r12 = 304(0x130, float:4.26E-43)
            r13 = 0
            r15 = 1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r3 = 0
            long r1 = r1 - r9
            int r1 = (int) r1     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            long r2 = r24 - r9
            int r2 = (int) r2     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r18 = 0
            r11 = r0
            r14 = r4
            r16 = r1
            r17 = r2
            r19 = r21
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            return r0
        L_0x0069:
            java.util.Map<java.lang.String, java.lang.String> r1 = r0.responseHeaders     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r1.putAll(r4)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            com.mishopsdk.volley.NetworkResponse r1 = new com.mishopsdk.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r12 = 304(0x130, float:4.26E-43)
            byte[] r13 = r0.data     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            java.util.Map<java.lang.String, java.lang.String> r14 = r0.responseHeaders     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r15 = 1
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r0 = 0
            long r2 = r2 - r9
            int r0 = (int) r2     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            long r2 = r24 - r9
            int r2 = (int) r2     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            r18 = 0
            r11 = r1
            r16 = r0
            r17 = r2
            r19 = r21
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            return r1
        L_0x008e:
            r0 = move-exception
            r14 = r4
            r13 = r5
            r28 = 0
            goto L_0x0121
        L_0x0095:
            org.apache.http.HttpEntity r1 = r23.getEntity()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x0103 }
            if (r1 == 0) goto L_0x00a4
            org.apache.http.HttpEntity r1 = r23.getEntity()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            byte[] r1 = r7.entityToBytes(r1)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x008e }
            goto L_0x00a6
        L_0x00a4:
            byte[] r1 = new byte[r6]     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x0103 }
        L_0x00a6:
            r26 = r1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00fb }
            r3 = 0
            long r13 = r1 - r9
            r1 = r29
            r2 = r13
            r27 = r4
            r4 = r30
            r15 = r5
            r5 = r26
            r28 = 0
            r6 = r0
            r1.logSlowRequests(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f8 }
            r0 = 200(0xc8, float:2.8E-43)
            if (r12 < r0) goto L_0x00ef
            r0 = 299(0x12b, float:4.19E-43)
            if (r12 > r0) goto L_0x00ef
            com.mishopsdk.volley.NetworkResponse r0 = new com.mishopsdk.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f8 }
            r1 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f8 }
            r4 = 0
            long r2 = r2 - r9
            int r2 = (int) r2     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f8 }
            long r3 = r24 - r9
            int r3 = (int) r3     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f8 }
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f8 }
            r6 = 0
            long r4 = r4 - r24
            int r4 = (int) r4
            r11 = r0
            r13 = r26
            r14 = r27
            r5 = r15
            r15 = r1
            r16 = r2
            r17 = r3
            r18 = r4
            r19 = r21
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f6 }
            return r0
        L_0x00ef:
            r5 = r15
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f6 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f6 }
            throw r0     // Catch:{ SocketTimeoutException -> 0x01b3, ConnectTimeoutException -> 0x01a6, MalformedURLException -> 0x0188, IOException -> 0x00f6 }
        L_0x00f6:
            r0 = move-exception
            goto L_0x0100
        L_0x00f8:
            r0 = move-exception
            r5 = r15
            goto L_0x0100
        L_0x00fb:
            r0 = move-exception
            r27 = r4
            r28 = 0
        L_0x0100:
            r13 = r26
            goto L_0x0109
        L_0x0103:
            r0 = move-exception
            r27 = r4
            r28 = 0
            r13 = r5
        L_0x0109:
            r14 = r27
            goto L_0x0121
        L_0x010c:
            r0 = move-exception
            r28 = 0
            goto L_0x0115
        L_0x0110:
            r0 = move-exception
            r28 = 0
            r24 = r1
        L_0x0115:
            r14 = r3
            r13 = r5
            goto L_0x0121
        L_0x0118:
            r0 = move-exception
            r28 = 0
            r24 = r1
            r14 = r3
            r13 = r5
            r23 = r13
        L_0x0121:
            if (r23 == 0) goto L_0x0182
            org.apache.http.StatusLine r0 = r23.getStatusLine()
            int r0 = r0.getStatusCode()
            java.lang.String r1 = "Unexpected response code %d for %s"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r2[r28] = r3
            r3 = 1
            java.lang.String r4 = r30.getUrl()
            r2[r3] = r4
            com.mishopsdk.volley.VolleyLog.e(r1, r2)
            if (r13 == 0) goto L_0x017c
            com.mishopsdk.volley.NetworkResponse r1 = new com.mishopsdk.volley.NetworkResponse
            r15 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r2 = r2 - r9
            int r2 = (int) r2
            long r3 = r24 - r9
            int r3 = (int) r3
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r4 - r24
            int r4 = (int) r4
            r11 = r1
            r12 = r0
            r16 = r2
            r17 = r3
            r18 = r4
            r19 = r21
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)
            r2 = 401(0x191, float:5.62E-43)
            if (r0 == r2) goto L_0x0171
            r2 = 403(0x193, float:5.65E-43)
            if (r0 != r2) goto L_0x016b
            goto L_0x0171
        L_0x016b:
            com.mishopsdk.volley.ServerError r0 = new com.mishopsdk.volley.ServerError
            r0.<init>(r1)
            throw r0
        L_0x0171:
            java.lang.String r0 = "auth"
            com.mishopsdk.volley.AuthFailureError r2 = new com.mishopsdk.volley.AuthFailureError
            r2.<init>((com.mishopsdk.volley.NetworkResponse) r1)
            attemptRetryOnException(r0, r8, r2)
            goto L_0x01be
        L_0x017c:
            com.mishopsdk.volley.NetworkError r0 = new com.mishopsdk.volley.NetworkError
            r0.<init>((com.mishopsdk.volley.NetworkResponse) r5)
            throw r0
        L_0x0182:
            com.mishopsdk.volley.NoConnectionError r1 = new com.mishopsdk.volley.NoConnectionError
            r1.<init>(r0)
            throw r1
        L_0x0188:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Bad URL "
            r2.append(r3)
            java.lang.String r3 = r30.getUrl()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x01a4:
            r24 = r1
        L_0x01a6:
            java.lang.String r0 = "connection"
            com.mishopsdk.volley.TimeoutError r1 = new com.mishopsdk.volley.TimeoutError
            r1.<init>()
            attemptRetryOnException(r0, r8, r1)
            goto L_0x01be
        L_0x01b1:
            r24 = r1
        L_0x01b3:
            java.lang.String r0 = "socket"
            com.mishopsdk.volley.TimeoutError r1 = new com.mishopsdk.volley.TimeoutError
            r1.<init>()
            attemptRetryOnException(r0, r8, r1)
        L_0x01be:
            r1 = r24
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mishopsdk.volley.toolbox.BasicNetwork.performRequest(com.mishopsdk.volley.Request):com.mishopsdk.volley.NetworkResponse");
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put(HttpHeaders.IF_NONE_MATCH, entry.etag);
            }
            if (entry.serverDate > 0) {
                map.put(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.formatDate(new Date(entry.serverDate)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void logError(String str, String str2, long j) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j), str2);
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                byte[] buf = this.mPool.getBuf(1024);
                while (true) {
                    try {
                        int read = content.read(buf);
                        if (read == -1) {
                            break;
                        }
                        poolingByteArrayOutputStream.write(buf, 0, read);
                    } catch (Throwable th) {
                        th = th;
                        bArr = buf;
                        try {
                            httpEntity.consumeContent();
                        } catch (IOException unused) {
                            VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
                        }
                        this.mPool.returnBuf(bArr);
                        poolingByteArrayOutputStream.close();
                        throw th;
                    }
                }
                byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
                try {
                    httpEntity.consumeContent();
                } catch (IOException unused2) {
                    VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
                }
                this.mPool.returnBuf(buf);
                poolingByteArrayOutputStream.close();
                return byteArray;
            }
            throw new ServerError();
        } catch (Throwable th2) {
            th = th2;
            httpEntity.consumeContent();
            this.mPool.returnBuf(bArr);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, String> convertHeaders(String str, Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        String str2 = (String) treeMap.get(RequestConstants.Keys.uuid);
        if (!TextUtils.isEmpty(str2)) {
            PreferenceUtil.setStringPref(ShopApp.instance, Constants.Preference.PREF_KEY_HEADER_UUID, str2);
        }
        return treeMap;
    }
}
