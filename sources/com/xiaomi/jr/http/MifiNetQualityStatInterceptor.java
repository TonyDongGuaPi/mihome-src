package com.xiaomi.jr.http;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

public class MifiNetQualityStatInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private String f10816a;

    public MifiNetQualityStatInterceptor(String str) {
        this.f10816a = str;
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v43 */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0076, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0077, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x007b, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007c, code lost:
        r0 = r12;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0087, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0088, code lost:
        r0 = r12;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0096, code lost:
        r12 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0097, code lost:
        r5 = r7;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x009a, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x009b, code lost:
        r0 = r12;
        r12 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0076 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0014] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r12) {
        /*
            r11 = this;
            okhttp3.Request r0 = r12.request()
            okhttp3.HttpUrl r0 = r0.url()
            java.lang.String r1 = r0.toString()
            java.lang.String r8 = ""
            r0 = 0
            r2 = 0
            r3 = 0
            r5 = 1
            r6 = 0
            okhttp3.Request r7 = r12.request()     // Catch:{ SocketTimeoutException -> 0x009a, JSONException -> 0x0087, IOException -> 0x007b, all -> 0x0076 }
            okhttp3.Response r12 = r12.proceed(r7)     // Catch:{ SocketTimeoutException -> 0x009a, JSONException -> 0x0087, IOException -> 0x007b, all -> 0x0076 }
            long r9 = r12.sentRequestAtMillis()     // Catch:{ SocketTimeoutException -> 0x0073, JSONException -> 0x0070, IOException -> 0x006d, all -> 0x0076 }
            int r7 = r12.code()     // Catch:{ SocketTimeoutException -> 0x0069, JSONException -> 0x0065, IOException -> 0x0061, all -> 0x005e }
            boolean r2 = r12.isSuccessful()     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            if (r2 == 0) goto L_0x0047
            okhttp3.ResponseBody r2 = r12.body()     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            if (r2 == 0) goto L_0x0047
            java.lang.String r2 = r11.a(r12)     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            r3.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            java.lang.String r2 = r11.f10816a     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            int r2 = r3.optInt(r2)     // Catch:{ SocketTimeoutException -> 0x0059, JSONException -> 0x0056, IOException -> 0x0053, all -> 0x004e }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L_0x0046
            r6 = r2
            r4 = 0
            goto L_0x0048
        L_0x0046:
            r6 = r2
        L_0x0047:
            r4 = 1
        L_0x0048:
            r0 = 0
            r2 = r9
            r5 = r7
            r7 = r0
            goto L_0x00a7
        L_0x004e:
            r12 = move-exception
            r5 = r7
            r2 = r9
            goto L_0x0098
        L_0x0053:
            r0 = move-exception
            r2 = r9
            goto L_0x0080
        L_0x0056:
            r0 = move-exception
            r2 = r9
            goto L_0x008c
        L_0x0059:
            r0 = move-exception
            r5 = r7
            r2 = r9
            goto L_0x009f
        L_0x005e:
            r12 = move-exception
            r2 = r9
            goto L_0x0078
        L_0x0061:
            r2 = move-exception
            r0 = r2
            r2 = r9
            goto L_0x007f
        L_0x0065:
            r2 = move-exception
            r0 = r2
            r2 = r9
            goto L_0x008b
        L_0x0069:
            r2 = move-exception
            r0 = r2
            r2 = r9
            goto L_0x009e
        L_0x006d:
            r2 = move-exception
            r0 = r2
            goto L_0x007e
        L_0x0070:
            r2 = move-exception
            r0 = r2
            goto L_0x008a
        L_0x0073:
            r2 = move-exception
            r0 = r2
            goto L_0x009d
        L_0x0076:
            r12 = move-exception
            r2 = r3
        L_0x0078:
            r4 = 1
            r5 = 0
            goto L_0x00ac
        L_0x007b:
            r12 = move-exception
            r0 = r12
            r12 = r2
        L_0x007e:
            r2 = r3
        L_0x007f:
            r7 = 0
        L_0x0080:
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0096 }
            r8 = 0
            r4 = r5
            goto L_0x0093
        L_0x0087:
            r12 = move-exception
            r0 = r12
            r12 = r2
        L_0x008a:
            r2 = r3
        L_0x008b:
            r7 = 0
        L_0x008c:
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0096 }
            r4 = 1
            r6 = 0
            r8 = 0
        L_0x0093:
            r5 = r7
            r7 = r8
            goto L_0x00a6
        L_0x0096:
            r12 = move-exception
            r5 = r7
        L_0x0098:
            r4 = 1
            goto L_0x00ac
        L_0x009a:
            r12 = move-exception
            r0 = r12
            r12 = r2
        L_0x009d:
            r2 = r3
        L_0x009e:
            r5 = 0
        L_0x009f:
            r4 = 2
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00ab }
            r6 = 0
            r7 = 0
        L_0x00a6:
            r8 = r0
        L_0x00a7:
            com.xiaomi.jr.stats.StatUtils.a(r1, r2, r4, r5, r6, r7, r8)
            return r12
        L_0x00ab:
            r12 = move-exception
        L_0x00ac:
            r6 = 0
            r7 = 0
            com.xiaomi.jr.stats.StatUtils.a(r1, r2, r4, r5, r6, r7, r8)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.http.MifiNetQualityStatInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private String a(Response response) throws IOException {
        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        if (response.body().contentLength() != 0) {
            return buffer.clone().readUtf8();
        }
        return null;
    }
}
