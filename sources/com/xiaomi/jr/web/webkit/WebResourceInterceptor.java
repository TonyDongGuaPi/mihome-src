package com.xiaomi.jr.web.webkit;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.http.WebHttpManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@TargetApi(21)
class WebResourceInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11084a = "WebResourceInterceptor";

    WebResourceInterceptor() {
    }

    static WebResourceResponse a(WebResourceRequest webResourceRequest) {
        Response response;
        if (!TextUtils.equals(webResourceRequest.getMethod(), "GET")) {
            return null;
        }
        Uri url = webResourceRequest.getUrl();
        String scheme = url.getScheme();
        if (!scheme.equals("http") && !scheme.equals("https")) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(url.toString()).get();
        builder.headers(Headers.of(webResourceRequest.getRequestHeaders()));
        try {
            response = WebHttpManager.a().b().newCall(builder.build()).execute();
        } catch (IOException e) {
            MifiLog.e(f11084a, e.toString());
            response = null;
        }
        if (response != null) {
            if (!response.isSuccessful()) {
                return null;
            }
            ResponseBody body = response.body();
            if (body != null) {
                return new WebResourceResponse("", response.header("Content-Encoding", ""), response.code(), TextUtils.isEmpty(response.message()) ? Constants.ax : response.message(), a(response.headers()), body.byteStream());
            }
        }
        return new WebResourceResponse((String) null, (String) null, (InputStream) null);
    }

    private static Map<String, String> a(Headers headers) {
        TreeMap treeMap = new TreeMap();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            String value = headers.value(i);
            if (treeMap.containsKey(name)) {
                treeMap.put(name, ((String) treeMap.get(name)) + "," + value);
            } else {
                treeMap.put(name, value);
            }
        }
        return treeMap;
    }
}
