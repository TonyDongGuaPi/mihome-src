package com.xiaomi.jr.http;

import com.alipay.sdk.sys.a;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.http.encoding.UrlEncoder;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

public class MifiParamsSignInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1428a = "MiFiParamsSignInterceptor";
    private static final String b = "sign";

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r9) throws java.io.IOException {
        /*
            r8 = this;
            okhttp3.Request r0 = r9.request()
            okhttp3.Request$Builder r1 = r0.newBuilder()
            java.util.TreeMap r2 = new java.util.TreeMap
            r2.<init>()
            java.lang.String r3 = r0.method()
            int r4 = r3.hashCode()
            r5 = 70454(0x11336, float:9.8727E-41)
            r6 = 0
            if (r4 == r5) goto L_0x002b
            r5 = 2461856(0x2590a0, float:3.449795E-39)
            if (r4 == r5) goto L_0x0021
            goto L_0x0035
        L_0x0021:
            java.lang.String r4 = "POST"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0035
            r3 = 1
            goto L_0x0036
        L_0x002b:
            java.lang.String r4 = "GET"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0035
            r3 = 0
            goto L_0x0036
        L_0x0035:
            r3 = -1
        L_0x0036:
            switch(r3) {
                case 0: goto L_0x008e;
                case 1: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            goto L_0x00c5
        L_0x003b:
            okhttp3.RequestBody r3 = r0.body()
            boolean r3 = r3 instanceof okhttp3.FormBody
            if (r3 != 0) goto L_0x0045
            goto L_0x00c5
        L_0x0045:
            okhttp3.FormBody$Builder r3 = new okhttp3.FormBody$Builder
            r3.<init>()
            okhttp3.RequestBody r4 = r0.body()
            okhttp3.FormBody r4 = (okhttp3.FormBody) r4
        L_0x0050:
            int r5 = r4.size()
            if (r6 >= r5) goto L_0x006f
            java.lang.String r5 = r4.name(r6)
            java.lang.String r7 = r4.value(r6)
            r2.put(r5, r7)
            java.lang.String r5 = r4.encodedName(r6)
            java.lang.String r7 = r4.encodedValue(r6)
            r3.addEncoded(r5, r7)
            int r6 = r6 + 1
            goto L_0x0050
        L_0x006f:
            java.lang.String r2 = a(r0, r2)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L_0x007e
            java.lang.String r4 = "sign"
            r3.add(r4, r2)
        L_0x007e:
            java.lang.String r0 = r0.method()
            okhttp3.FormBody r2 = r3.build()
            r1.method(r0, r2)
            okhttp3.Request r0 = r1.build()
            goto L_0x00c5
        L_0x008e:
            okhttp3.HttpUrl r3 = r0.url()
        L_0x0092:
            int r4 = r3.querySize()
            if (r6 >= r4) goto L_0x00a6
            java.lang.String r4 = r3.queryParameterName(r6)
            java.lang.String r5 = r3.queryParameterValue(r6)
            r2.put(r4, r5)
            int r6 = r6 + 1
            goto L_0x0092
        L_0x00a6:
            okhttp3.HttpUrl$Builder r3 = r3.newBuilder()
            java.lang.String r0 = a(r0, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x00b9
            java.lang.String r2 = "sign"
            r3.addQueryParameter(r2, r0)
        L_0x00b9:
            okhttp3.HttpUrl r0 = r3.build()
            okhttp3.Request$Builder r0 = r1.url((okhttp3.HttpUrl) r0)
            okhttp3.Request r0 = r0.build()
        L_0x00c5:
            okhttp3.Response r9 = r9.proceed(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.http.MifiParamsSignInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private static String a(Request request, TreeMap<String, String> treeMap) {
        HttpUrl url = request.url();
        String method = request.method();
        String host = url.host();
        String encodedPath = url.encodedPath();
        int port = url.port();
        if (!(port == -1 || port == 80 || port == 443)) {
            host = host + ":" + port;
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : treeMap.entrySet()) {
                if (!((String) next.getKey()).equals("sign")) {
                    String a2 = UrlEncoder.a((String) next.getKey());
                    String a3 = UrlEncoder.a((String) next.getValue());
                    sb.append(a2);
                    sb.append("=");
                    sb.append(a3);
                    sb.append(a.b);
                }
            }
            sb.append("secret=");
            sb.append("ruyW+hhS8TbCFk09GZBzwHB3Ezih27VUUEqMLqQjGmo=");
            return HashUtils.a(method + 10 + host + 10 + encodedPath + 10 + sb);
        } catch (Exception unused) {
            return null;
        }
    }
}
