package com.xiaomi.smarthome.library.http.util;

import android.text.TextUtils;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.Request;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class KeyValuePairUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19116a = "&";
    private static final String b = "=";

    public static String a(String str, List<KeyValuePair> list) {
        if (list == null || list.isEmpty()) {
            return str;
        }
        String a2 = a((List<? extends KeyValuePair>) list, "UTF-8");
        if (!str.contains("?")) {
            return str + "?" + a2;
        }
        return str + "&" + a2;
    }

    private static String a(List<? extends KeyValuePair> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (KeyValuePair keyValuePair : list) {
            String a2 = a(keyValuePair.a(), str);
            String b2 = keyValuePair.b();
            String a3 = b2 != null ? a(b2, str) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(a2);
            sb.append("=");
            sb.append(a3);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static RequestBody a(List<KeyValuePair> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (KeyValuePair next : list) {
            String a2 = next.a();
            String b2 = next.b();
            if (!TextUtils.isEmpty(a2) && b2 != null) {
                builder.add(a2, b2);
            }
        }
        return builder.build();
    }

    public static RequestBody a(Request request) {
        if (request == null) {
            return null;
        }
        RequestBody e = request.e();
        if (e != null) {
            return e;
        }
        return a(request.d());
    }
}
