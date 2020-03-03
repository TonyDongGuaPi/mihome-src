package com.xiaomi.account.http;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Map;

public class UrlConnHttpFactory extends HttpFactory {

    private class HttpUrlConnClient implements HttpClient {
        private static final String BODY_STRING_SPLIT = "&";
        private static final String TAG = "HttpUrlConnClient";
        HttpClientConfig config;

        /* JADX WARNING: Removed duplicated region for block: B:35:0x00f2 A[SYNTHETIC, Splitter:B:35:0x00f2] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.xiaomi.account.http.Response excute(com.xiaomi.account.http.Request r6) throws java.io.IOException {
            /*
                r5 = this;
                java.net.URL r0 = new java.net.URL
                java.lang.String r1 = r6.url
                r0.<init>(r1)
                java.net.URLConnection r0 = r0.openConnection()
                java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0
                com.xiaomi.account.http.HttpClientConfig r1 = r5.config     // Catch:{ all -> 0x00fe }
                long r1 = r1.connectTimeoutMs     // Catch:{ all -> 0x00fe }
                int r1 = (int) r1     // Catch:{ all -> 0x00fe }
                r0.setConnectTimeout(r1)     // Catch:{ all -> 0x00fe }
                com.xiaomi.account.http.HttpClientConfig r1 = r5.config     // Catch:{ all -> 0x00fe }
                long r1 = r1.readTimeoutMs     // Catch:{ all -> 0x00fe }
                int r1 = (int) r1     // Catch:{ all -> 0x00fe }
                r0.setReadTimeout(r1)     // Catch:{ all -> 0x00fe }
                java.util.Map<java.lang.String, java.lang.String> r1 = r6.formBody     // Catch:{ all -> 0x00fe }
                if (r1 == 0) goto L_0x002e
                r1 = 1
                r0.setDoInput(r1)     // Catch:{ all -> 0x00fe }
                r0.setDoOutput(r1)     // Catch:{ all -> 0x00fe }
                java.lang.String r1 = "POST"
                r0.setRequestMethod(r1)     // Catch:{ all -> 0x00fe }
                goto L_0x0033
            L_0x002e:
                java.lang.String r1 = "GET"
                r0.setRequestMethod(r1)     // Catch:{ all -> 0x00fe }
            L_0x0033:
                boolean r1 = r6.followRedirects     // Catch:{ all -> 0x00fe }
                r0.setInstanceFollowRedirects(r1)     // Catch:{ all -> 0x00fe }
                java.util.Map<java.lang.String, java.lang.String> r1 = r6.headers     // Catch:{ all -> 0x00fe }
                if (r1 == 0) goto L_0x0062
                java.util.Map<java.lang.String, java.lang.String> r1 = r6.headers     // Catch:{ all -> 0x00fe }
                java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x00fe }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00fe }
            L_0x0046:
                boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00fe }
                if (r2 == 0) goto L_0x0062
                java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00fe }
                java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x00fe }
                java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x00fe }
                java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00fe }
                java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x00fe }
                java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x00fe }
                r0.setRequestProperty(r3, r2)     // Catch:{ all -> 0x00fe }
                goto L_0x0046
            L_0x0062:
                r0.connect()     // Catch:{ all -> 0x00fe }
                java.util.Map<java.lang.String, java.lang.String> r1 = r6.formBody     // Catch:{ all -> 0x00fe }
                if (r1 == 0) goto L_0x008b
                java.io.OutputStream r1 = r0.getOutputStream()     // Catch:{ all -> 0x00fe }
                java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ all -> 0x00fe }
                java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00fe }
                java.lang.String r4 = "UTF-8"
                r3.<init>(r1, r4)     // Catch:{ all -> 0x00fe }
                r2.<init>(r3)     // Catch:{ all -> 0x00fe }
                java.util.Map<java.lang.String, java.lang.String> r6 = r6.formBody     // Catch:{ all -> 0x00fe }
                java.lang.String r6 = com.xiaomi.account.http.UrlConnHttpFactory.joinToQuery(r6)     // Catch:{ all -> 0x00fe }
                r2.write(r6)     // Catch:{ all -> 0x00fe }
                r2.flush()     // Catch:{ all -> 0x00fe }
                r2.close()     // Catch:{ all -> 0x00fe }
                r1.close()     // Catch:{ all -> 0x00fe }
            L_0x008b:
                int r6 = r0.getResponseCode()     // Catch:{ all -> 0x00fe }
                com.xiaomi.account.http.Response$Builder r1 = new com.xiaomi.account.http.Response$Builder     // Catch:{ all -> 0x00fe }
                r1.<init>()     // Catch:{ all -> 0x00fe }
                com.xiaomi.account.http.Response$Builder r1 = r1.code(r6)     // Catch:{ all -> 0x00fe }
                java.lang.String r2 = "Location"
                java.lang.String r2 = r0.getHeaderField(r2)     // Catch:{ all -> 0x00fe }
                com.xiaomi.account.http.Response$Builder r1 = r1.location(r2)     // Catch:{ all -> 0x00fe }
                java.lang.String r2 = "Set-Cookie"
                java.lang.String r2 = r0.getHeaderField(r2)     // Catch:{ all -> 0x00fe }
                com.xiaomi.account.http.Response$Builder r1 = r1.setCookie(r2)     // Catch:{ all -> 0x00fe }
                java.util.Map r2 = r0.getHeaderFields()     // Catch:{ all -> 0x00fe }
                com.xiaomi.account.http.Response$Builder r1 = r1.headers(r2)     // Catch:{ all -> 0x00fe }
                r2 = 200(0xc8, float:2.8E-43)
                if (r6 != r2) goto L_0x00f6
                r6 = 0
                java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x00ed }
                java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x00ed }
                java.io.InputStream r4 = r0.getInputStream()     // Catch:{ all -> 0x00ed }
                r3.<init>(r4)     // Catch:{ all -> 0x00ed }
                r4 = 1024(0x400, float:1.435E-42)
                r2.<init>(r3, r4)     // Catch:{ all -> 0x00ed }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00eb }
                r6.<init>()     // Catch:{ all -> 0x00eb }
            L_0x00ce:
                java.lang.String r3 = r2.readLine()     // Catch:{ all -> 0x00eb }
                if (r3 == 0) goto L_0x00d8
                r6.append(r3)     // Catch:{ all -> 0x00eb }
                goto L_0x00ce
            L_0x00d8:
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00eb }
                com.xiaomi.account.http.Response$Builder r6 = r1.body(r6)     // Catch:{ all -> 0x00eb }
                com.xiaomi.account.http.Response r6 = r6.build()     // Catch:{ all -> 0x00eb }
                r2.close()     // Catch:{ all -> 0x00fe }
                r0.disconnect()
                return r6
            L_0x00eb:
                r6 = move-exception
                goto L_0x00f0
            L_0x00ed:
                r1 = move-exception
                r2 = r6
                r6 = r1
            L_0x00f0:
                if (r2 == 0) goto L_0x00f5
                r2.close()     // Catch:{ all -> 0x00fe }
            L_0x00f5:
                throw r6     // Catch:{ all -> 0x00fe }
            L_0x00f6:
                com.xiaomi.account.http.Response r6 = r1.build()     // Catch:{ all -> 0x00fe }
                r0.disconnect()
                return r6
            L_0x00fe:
                r6 = move-exception
                r0.disconnect()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.http.UrlConnHttpFactory.HttpUrlConnClient.excute(com.xiaomi.account.http.Request):com.xiaomi.account.http.Response");
        }

        private HttpUrlConnClient(HttpClientConfig httpClientConfig) {
            this.config = httpClientConfig;
            CookieHandler.setDefault(new CookieManager());
            ((CookieManager) CookieHandler.getDefault()).setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        }
    }

    public HttpClient createHttpClient(HttpClientConfig httpClientConfig) {
        return new HttpUrlConnClient(httpClientConfig);
    }

    public static String joinToQuery(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(a.b);
                }
                sb.append(str);
                sb.append("=");
                sb.append(str2);
            }
        }
        return sb.toString();
    }
}
