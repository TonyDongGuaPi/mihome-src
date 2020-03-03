package com.xiaomi.smarthome.core.server.internal.util;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.RequestBody;

public class KeyValuePairUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14730a = "&";
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

    public static Headers a(List<KeyValuePair> list) {
        if (list == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        for (KeyValuePair next : list) {
            builder.add(next.a(), next.b());
        }
        return builder.build();
    }

    public static RequestBody b(List<KeyValuePair> list) {
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

    public static String c(List<KeyValuePair> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (KeyValuePair next : list) {
                String a2 = next.a();
                String b2 = next.b();
                if (!TextUtils.isEmpty(a2) && b2 != null) {
                    String encode = URLEncoder.encode(a2, "UTF-8");
                    String encode2 = URLEncoder.encode(b2, "UTF-8");
                    stringBuffer.append("&" + encode + "=" + encode2);
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            if (!TextUtils.isEmpty(stringBuffer2)) {
                stringBuffer2 = stringBuffer2.replaceFirst("&", "?");
            }
            return stringBuffer2;
        } catch (Exception unused) {
            return null;
        }
    }
}
