package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        r1 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0090, code lost:
        r2 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0093, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0094, code lost:
        r2 = r4;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0096, code lost:
        r1 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a0, code lost:
        r2 = null;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a4, code lost:
        r0 = r14.getStatusLine().getStatusCode();
        com.android.volley.VolleyLog.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r0), r20.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c0, code lost:
        if (r2 != null) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c2, code lost:
        r3 = new com.android.volley.NetworkResponse(r0, r2, r1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c9, code lost:
        if (r0 == 401) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d5, code lost:
        throw new com.android.volley.ServerError(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d6, code lost:
        attemptRetryOnException("auth", r8, new com.android.volley.AuthFailureError(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e8, code lost:
        throw new com.android.volley.NetworkError((com.android.volley.NetworkResponse) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ee, code lost:
        throw new com.android.volley.NoConnectionError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00ef, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010a, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r20.getUrl(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x010b, code lost:
        attemptRetryOnException("connection", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0117, code lost:
        attemptRetryOnException("socket", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ef A[ExcHandler: MalformedURLException (r0v2 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:70:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00e9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r20) throws com.android.volley.VolleyError {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            long r9 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r11 = 1
            r12 = 0
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009f }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009f }
            com.android.volley.Cache$Entry r2 = r20.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009f }
            r7.addCacheHeaders(r0, r2)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009f }
            com.android.volley.toolbox.HttpStack r2 = r7.mHttpStack     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009f }
            org.apache.http.HttpResponse r14 = r2.performRequest(r8, r0)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009f }
            org.apache.http.StatusLine r6 = r14.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009c }
            int r0 = r6.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009c }
            org.apache.http.Header[] r15 = r14.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009c }
            java.util.Map r5 = convertHeaders(r15)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x009c }
            r1 = 304(0x130, float:4.26E-43)
            if (r0 != r1) goto L_0x004c
            com.android.volley.NetworkResponse r0 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
            com.android.volley.Cache$Entry r2 = r20.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
            if (r2 != 0) goto L_0x003f
            r2 = 0
            goto L_0x0045
        L_0x003f:
            com.android.volley.Cache$Entry r2 = r20.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
            byte[] r2 = r2.data     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
        L_0x0045:
            r0.<init>(r1, r2, r5, r11)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
            return r0
        L_0x0049:
            r0 = move-exception
            r1 = r5
            goto L_0x009d
        L_0x004c:
            org.apache.http.HttpEntity r1 = r14.getEntity()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0098 }
            if (r1 == 0) goto L_0x005b
            org.apache.http.HttpEntity r1 = r14.getEntity()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
            byte[] r1 = r7.entityToBytes(r1)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0049 }
            goto L_0x005d
        L_0x005b:
            byte[] r1 = new byte[r12]     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0098 }
        L_0x005d:
            r4 = r1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x0093 }
            r3 = 0
            long r16 = r1 - r9
            r1 = r19
            r2 = r16
            r18 = r4
            r4 = r20
            r13 = r5
            r5 = r18
            r1.logSlowRequests(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008f }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 < r1) goto L_0x0085
            r1 = 299(0x12b, float:4.19E-43)
            if (r0 > r1) goto L_0x0085
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008f }
            r2 = r18
            r1.<init>(r0, r2, r13, r12)     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008d }
            r1.allHeaders = r15     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008d }
            return r1
        L_0x0085:
            r2 = r18
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008d }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008d }
            throw r0     // Catch:{ SocketTimeoutException -> 0x0117, ConnectTimeoutException -> 0x010b, MalformedURLException -> 0x00ef, IOException -> 0x008d }
        L_0x008d:
            r0 = move-exception
            goto L_0x0096
        L_0x008f:
            r0 = move-exception
            r2 = r18
            goto L_0x0096
        L_0x0093:
            r0 = move-exception
            r2 = r4
            r13 = r5
        L_0x0096:
            r1 = r13
            goto L_0x00a2
        L_0x0098:
            r0 = move-exception
            r13 = r5
            r1 = r13
            goto L_0x009d
        L_0x009c:
            r0 = move-exception
        L_0x009d:
            r2 = 0
            goto L_0x00a2
        L_0x009f:
            r0 = move-exception
            r2 = 0
            r14 = 0
        L_0x00a2:
            if (r14 == 0) goto L_0x00e9
            org.apache.http.StatusLine r0 = r14.getStatusLine()
            int r0 = r0.getStatusCode()
            java.lang.String r3 = "Unexpected response code %d for %s"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            r4[r12] = r5
            java.lang.String r5 = r20.getUrl()
            r4[r11] = r5
            com.android.volley.VolleyLog.e(r3, r4)
            if (r2 == 0) goto L_0x00e2
            com.android.volley.NetworkResponse r3 = new com.android.volley.NetworkResponse
            r3.<init>(r0, r2, r1, r12)
            r1 = 401(0x191, float:5.62E-43)
            if (r0 == r1) goto L_0x00d6
            r1 = 403(0x193, float:5.65E-43)
            if (r0 != r1) goto L_0x00d0
            goto L_0x00d6
        L_0x00d0:
            com.android.volley.ServerError r0 = new com.android.volley.ServerError
            r0.<init>(r3)
            throw r0
        L_0x00d6:
            java.lang.String r0 = "auth"
            com.android.volley.AuthFailureError r1 = new com.android.volley.AuthFailureError
            r1.<init>((com.android.volley.NetworkResponse) r3)
            attemptRetryOnException(r0, r8, r1)
            goto L_0x0008
        L_0x00e2:
            com.android.volley.NetworkError r0 = new com.android.volley.NetworkError
            r1 = 0
            r0.<init>((com.android.volley.NetworkResponse) r1)
            throw r0
        L_0x00e9:
            com.android.volley.NoConnectionError r1 = new com.android.volley.NoConnectionError
            r1.<init>(r0)
            throw r1
        L_0x00ef:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Bad URL "
            r2.append(r3)
            java.lang.String r3 = r20.getUrl()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x010b:
            java.lang.String r0 = "connection"
            com.android.volley.TimeoutError r1 = new com.android.volley.TimeoutError
            r1.<init>()
            attemptRetryOnException(r0, r8, r1)
            goto L_0x0008
        L_0x0117:
            java.lang.String r0 = "socket"
            com.android.volley.TimeoutError r1 = new com.android.volley.TimeoutError
            r1.<init>()
            attemptRetryOnException(r0, r8, r1)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
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

    private static Map<String, String> convertHeaders(Header[] headerArr) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < headerArr.length; i++) {
            hashMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return hashMap;
    }
}
