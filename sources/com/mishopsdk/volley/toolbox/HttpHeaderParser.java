package com.mishopsdk.volley.toolbox;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.google.common.net.HttpHeaders;
import com.mishopsdk.volley.Cache;
import com.mishopsdk.volley.NetworkResponse;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.cybergarage.http.HTTP;

public class HttpHeaderParser {
    public static final String CONTENT_TYPE_LOWER = "content-type";

    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.etag = map.get(HttpHeaders.ETAG);
        entry.softTtl = 0;
        entry.ttl = entry.softTtl;
        entry.serverDate = 0;
        entry.responseHeaders = map;
        return entry;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0;
        }
    }

    public static String parseCharset(Map<String, String> map) {
        String str = map.get("Content-Type");
        if (TextUtils.isEmpty(str)) {
            str = map.get("content-type");
        }
        if (str == null) {
            return "UTF-8";
        }
        String[] split = str.split(i.b);
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals(HTTP.CHARSET)) {
                return split2[1];
            }
        }
        return "UTF-8";
    }

    public static boolean isContentLengthEmpty(Map<String, String> map) {
        if (map == null || map.size() < 1) {
            return true;
        }
        return TextUtils.isEmpty(map.get("Content-Length"));
    }
}
