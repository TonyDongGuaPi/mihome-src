package com.youpin.weex.app.extend;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import com.xiaomi.youpin.cookie.YouPinCookieManager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.client.methods.HttpPut;

public class HttpAdapter implements IWXHttpAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final IEventReporterDelegate f2522a = new NOPEventReportDelegate();
    private ExecutorService b;

    public interface IEventReporterDelegate {
        InputStream a(@Nullable InputStream inputStream);

        void a();

        void a(IOException iOException);

        void a(HttpURLConnection httpURLConnection, @Nullable String str);
    }

    private void a(Runnable runnable) {
        if (this.b == null) {
            this.b = Executors.newFixedThreadPool(3);
        }
        this.b.execute(runnable);
    }

    public void sendRequest(final WXRequest wXRequest, final IWXHttpAdapter.OnHttpListener onHttpListener) {
        String b2;
        if (onHttpListener != null) {
            onHttpListener.onHttpStart();
        }
        Uri parse = Uri.parse(wXRequest.url);
        if (!(parse.getHost() == null || (b2 = YouPinCookieManager.a().b(parse.getHost())) == null)) {
            wXRequest.paramMap.put("Cookie", b2);
        }
        a((Runnable) new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:40:0x00a2 A[Catch:{ Exception -> 0x00af }] */
            /* JADX WARNING: Removed duplicated region for block: B:42:0x00a9 A[SYNTHETIC, Splitter:B:42:0x00a9] */
            /* JADX WARNING: Removed duplicated region for block: B:49:0x00c5 A[Catch:{ Throwable -> 0x00d4, all -> 0x00ad }] */
            /* JADX WARNING: Removed duplicated region for block: B:52:0x00ce A[SYNTHETIC, Splitter:B:52:0x00ce] */
            /* JADX WARNING: Removed duplicated region for block: B:58:0x00da A[SYNTHETIC, Splitter:B:58:0x00da] */
            /* JADX WARNING: Removed duplicated region for block: B:63:0x00e5 A[SYNTHETIC, Splitter:B:63:0x00e5] */
            /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
            /* JADX WARNING: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r9 = this;
                    com.taobao.weex.common.WXResponse r0 = new com.taobao.weex.common.WXResponse
                    r0.<init>()
                    com.youpin.weex.app.extend.HttpAdapter r1 = com.youpin.weex.app.extend.HttpAdapter.this
                    com.youpin.weex.app.extend.HttpAdapter$IEventReporterDelegate r1 = r1.a()
                    r2 = 0
                    com.youpin.weex.app.extend.HttpAdapter r3 = com.youpin.weex.app.extend.HttpAdapter.this     // Catch:{ Exception -> 0x00af }
                    com.taobao.weex.common.WXRequest r4 = r4     // Catch:{ Exception -> 0x00af }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r5 = r5     // Catch:{ Exception -> 0x00af }
                    java.net.HttpURLConnection r3 = r3.a((com.taobao.weex.common.WXRequest) r4, (com.taobao.weex.adapter.IWXHttpAdapter.OnHttpListener) r5)     // Catch:{ Exception -> 0x00af }
                    com.taobao.weex.common.WXRequest r4 = r4     // Catch:{ Exception -> 0x00af }
                    java.lang.String r4 = r4.body     // Catch:{ Exception -> 0x00af }
                    r1.a(r3, r4)     // Catch:{ Exception -> 0x00af }
                    java.util.Map r4 = r3.getHeaderFields()     // Catch:{ Exception -> 0x0022 }
                    goto L_0x002b
                L_0x0022:
                    r4 = move-exception
                    r4.printStackTrace()     // Catch:{ Exception -> 0x00af }
                    java.util.HashMap r4 = new java.util.HashMap     // Catch:{ Exception -> 0x00af }
                    r4.<init>()     // Catch:{ Exception -> 0x00af }
                L_0x002b:
                    int r5 = r3.getResponseCode()     // Catch:{ Exception -> 0x00af }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r6 = r5     // Catch:{ Exception -> 0x00af }
                    if (r6 == 0) goto L_0x0038
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r6 = r5     // Catch:{ Exception -> 0x00af }
                    r6.onHeadersReceived(r5, r4)     // Catch:{ Exception -> 0x00af }
                L_0x0038:
                    r1.a()     // Catch:{ Exception -> 0x00af }
                    java.lang.String r4 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x00af }
                    r0.statusCode = r4     // Catch:{ Exception -> 0x00af }
                    r4 = 299(0x12b, float:4.19E-43)
                    r6 = 200(0xc8, float:2.8E-43)
                    if (r5 < r6) goto L_0x004e
                    if (r5 > r4) goto L_0x004e
                    java.io.InputStream r7 = r3.getInputStream()     // Catch:{ Exception -> 0x00af }
                    goto L_0x0052
                L_0x004e:
                    java.io.InputStream r7 = r3.getErrorStream()     // Catch:{ Exception -> 0x00af }
                L_0x0052:
                    r2 = r7
                    java.lang.String r3 = r3.getContentEncoding()     // Catch:{ Exception -> 0x00af }
                    if (r3 == 0) goto L_0x0068
                    java.lang.String r7 = "gzip"
                    boolean r7 = r3.contains(r7)     // Catch:{ Exception -> 0x00af }
                    if (r7 == 0) goto L_0x0068
                    java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x00af }
                    r3.<init>(r2)     // Catch:{ Exception -> 0x00af }
                L_0x0066:
                    r2 = r3
                    goto L_0x0078
                L_0x0068:
                    if (r3 == 0) goto L_0x0078
                    java.lang.String r7 = "deflate"
                    boolean r3 = r3.contains(r7)     // Catch:{ Exception -> 0x00af }
                    if (r3 == 0) goto L_0x0078
                    java.util.zip.InflaterInputStream r3 = new java.util.zip.InflaterInputStream     // Catch:{ Exception -> 0x00af }
                    r3.<init>(r2)     // Catch:{ Exception -> 0x00af }
                    goto L_0x0066
                L_0x0078:
                    if (r5 < r6) goto L_0x0094
                    if (r5 > r4) goto L_0x0094
                    java.io.InputStream r3 = r1.a((java.io.InputStream) r2)     // Catch:{ Exception -> 0x00af }
                    com.youpin.weex.app.extend.HttpAdapter r2 = com.youpin.weex.app.extend.HttpAdapter.this     // Catch:{ Exception -> 0x008f, all -> 0x008c }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r4 = r5     // Catch:{ Exception -> 0x008f, all -> 0x008c }
                    byte[] r2 = r2.a((java.io.InputStream) r3, (com.taobao.weex.adapter.IWXHttpAdapter.OnHttpListener) r4)     // Catch:{ Exception -> 0x008f, all -> 0x008c }
                    r0.originalData = r2     // Catch:{ Exception -> 0x008f, all -> 0x008c }
                    r2 = r3
                    goto L_0x009e
                L_0x008c:
                    r0 = move-exception
                    r2 = r3
                    goto L_0x00e3
                L_0x008f:
                    r2 = move-exception
                    r8 = r3
                    r3 = r2
                    r2 = r8
                    goto L_0x00b0
                L_0x0094:
                    com.youpin.weex.app.extend.HttpAdapter r3 = com.youpin.weex.app.extend.HttpAdapter.this     // Catch:{ Exception -> 0x00af }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r4 = r5     // Catch:{ Exception -> 0x00af }
                    java.lang.String r3 = r3.b(r2, r4)     // Catch:{ Exception -> 0x00af }
                    r0.errorMsg = r3     // Catch:{ Exception -> 0x00af }
                L_0x009e:
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r3 = r5     // Catch:{ Exception -> 0x00af }
                    if (r3 == 0) goto L_0x00a7
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r3 = r5     // Catch:{ Exception -> 0x00af }
                    r3.onHttpFinish(r0)     // Catch:{ Exception -> 0x00af }
                L_0x00a7:
                    if (r2 == 0) goto L_0x00e2
                    r2.close()     // Catch:{ IOException -> 0x00de }
                    goto L_0x00e2
                L_0x00ad:
                    r0 = move-exception
                    goto L_0x00e3
                L_0x00af:
                    r3 = move-exception
                L_0x00b0:
                    r3.printStackTrace()     // Catch:{ all -> 0x00ad }
                    java.lang.String r4 = "-1"
                    r0.statusCode = r4     // Catch:{ all -> 0x00ad }
                    java.lang.String r4 = "-1"
                    r0.errorCode = r4     // Catch:{ all -> 0x00ad }
                    java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x00ad }
                    r0.errorMsg = r4     // Catch:{ all -> 0x00ad }
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r4 = r5     // Catch:{ all -> 0x00ad }
                    if (r4 == 0) goto L_0x00ca
                    com.taobao.weex.adapter.IWXHttpAdapter$OnHttpListener r4 = r5     // Catch:{ all -> 0x00ad }
                    r4.onHttpFinish(r0)     // Catch:{ all -> 0x00ad }
                L_0x00ca:
                    boolean r0 = r3 instanceof java.io.IOException     // Catch:{ all -> 0x00ad }
                    if (r0 == 0) goto L_0x00d8
                    java.io.IOException r3 = (java.io.IOException) r3     // Catch:{ Throwable -> 0x00d4 }
                    r1.a((java.io.IOException) r3)     // Catch:{ Throwable -> 0x00d4 }
                    goto L_0x00d8
                L_0x00d4:
                    r0 = move-exception
                    r0.printStackTrace()     // Catch:{ all -> 0x00ad }
                L_0x00d8:
                    if (r2 == 0) goto L_0x00e2
                    r2.close()     // Catch:{ IOException -> 0x00de }
                    goto L_0x00e2
                L_0x00de:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x00e2:
                    return
                L_0x00e3:
                    if (r2 == 0) goto L_0x00ed
                    r2.close()     // Catch:{ IOException -> 0x00e9 }
                    goto L_0x00ed
                L_0x00e9:
                    r1 = move-exception
                    r1.printStackTrace()
                L_0x00ed:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.youpin.weex.app.extend.HttpAdapter.AnonymousClass1.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    public HttpURLConnection a(WXRequest wXRequest, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        HttpURLConnection a2 = a(new URL(wXRequest.url));
        a2.setConnectTimeout(wXRequest.timeoutMs);
        a2.setReadTimeout(wXRequest.timeoutMs);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        if (wXRequest.paramMap != null) {
            for (String next : wXRequest.paramMap.keySet()) {
                a2.addRequestProperty(next, wXRequest.paramMap.get(next));
            }
        }
        if ("POST".equals(wXRequest.method) || HttpPut.METHOD_NAME.equals(wXRequest.method) || "PATCH".equals(wXRequest.method)) {
            a2.setRequestMethod(wXRequest.method);
            if (wXRequest.body != null) {
                if (onHttpListener != null) {
                    onHttpListener.onHttpUploadProgress(0);
                }
                a2.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(a2.getOutputStream());
                dataOutputStream.write(wXRequest.body.getBytes());
                dataOutputStream.close();
                if (onHttpListener != null) {
                    onHttpListener.onHttpUploadProgress(100);
                }
            }
        } else if (!TextUtils.isEmpty(wXRequest.method)) {
            a2.setRequestMethod(wXRequest.method);
        } else {
            a2.setRequestMethod("GET");
        }
        return a2;
    }

    /* access modifiers changed from: private */
    public byte[] a(InputStream inputStream, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        int i = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
                i += read;
                if (onHttpListener != null) {
                    onHttpListener.onHttpResponseProgress(i);
                }
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* access modifiers changed from: private */
    public String b(InputStream inputStream, IWXHttpAdapter.OnHttpListener onHttpListener) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] cArr = new char[2048];
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                sb.append(cArr, 0, read);
                if (onHttpListener != null) {
                    onHttpListener.onHttpResponseProgress(sb.length());
                }
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    @NonNull
    public IEventReporterDelegate a() {
        return f2522a;
    }

    private static class NOPEventReportDelegate implements IEventReporterDelegate {
        public InputStream a(@Nullable InputStream inputStream) {
            return inputStream;
        }

        public void a() {
        }

        public void a(IOException iOException) {
        }

        public void a(HttpURLConnection httpURLConnection, @Nullable String str) {
        }

        private NOPEventReportDelegate() {
        }
    }
}
