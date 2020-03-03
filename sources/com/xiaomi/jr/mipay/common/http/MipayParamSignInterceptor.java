package com.xiaomi.jr.mipay.common.http;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.mipay.common.util.Coder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MipayParamSignInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10947a = "MipayParamSignInterceptor";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (!HttpUtils.a(request)) {
            MifiLog.e(f10947a, "shouldn't reach here, in request " + request.toString());
        } else if (request.body() instanceof FormBody) {
            if (!(request.tag() instanceof String)) {
                HttpUtils.a("can't sign params due to no security in request");
                return chain.proceed(request);
            }
            String str = (String) request.tag();
            FormBody formBody = (FormBody) request.body();
            TreeMap treeMap = new TreeMap();
            for (int i = 0; i < formBody.size(); i++) {
                treeMap.put(formBody.name(i), formBody.value(i));
            }
            HttpUrl build = request.url().newBuilder().addQueryParameter("signature", a(request.method(), request.url().url().getPath(), treeMap, str)).build();
            FormBody.Builder builder = new FormBody.Builder();
            for (String str2 : treeMap.keySet()) {
                String str3 = (String) treeMap.get(str2);
                if (!TextUtils.isEmpty(str3)) {
                    builder.add(str2, str3);
                }
            }
            request = request.newBuilder().url(build).post(builder.build()).build();
            HttpUtils.a("[url] " + build + ", security=" + str);
            StringBuilder sb = new StringBuilder();
            sb.append("[reflowParam] ");
            sb.append(HttpUtils.b(request));
            HttpUtils.a(sb.toString());
        }
        return chain.proceed(request);
    }

    private String a(String str, String str2, TreeMap<String, String> treeMap, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str.toUpperCase());
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (treeMap != null && !treeMap.isEmpty()) {
            for (String next : treeMap.keySet()) {
                String str4 = treeMap.get(next);
                if (!TextUtils.isEmpty(str4)) {
                    arrayList.add(String.format("%s=%s", new Object[]{next, str4}));
                }
            }
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3);
        }
        return a(TextUtils.join(a.b, arrayList));
    }

    private String a(String str) {
        byte[] b = Coder.b(str);
        if (b == null) {
            return null;
        }
        return Coder.b(b);
    }
}
