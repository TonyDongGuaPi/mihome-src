package com.android.volley.toolbox;

import com.alipay.sdk.util.i;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.google.common.net.HttpHeaders;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.cybergarage.http.HTTP;

public class HttpHeaderParser {
    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        String str = map.get(map.get("Date") == null ? "date" : "Date");
        long j2 = 0;
        long parseDateAsEpoch = str != null ? parseDateAsEpoch(str) : 0;
        String str2 = map.get(map.get("Cache-Control") == null ? "cache-control" : "Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",");
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals(HTTP.NO_CACHE) || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    j = 0;
                }
                i++;
            }
            i = 1;
        } else {
            j = 0;
        }
        String str3 = map.get(map.get("Expires") == null ? "expires" : "Expires");
        long parseDateAsEpoch2 = str3 != null ? parseDateAsEpoch(str3) : 0;
        String str4 = map.get(map.get(HttpHeaders.ETAG) == null ? "etag" : HttpHeaders.ETAG);
        if (str4 == null) {
            str4 = map.get(map.get("ETag2") == null ? "etag2" : "ETag2");
        }
        if (i != 0) {
            j2 = currentTimeMillis + (j * 1000);
        } else if (parseDateAsEpoch > 0 && parseDateAsEpoch2 >= parseDateAsEpoch) {
            j2 = currentTimeMillis + (parseDateAsEpoch2 - parseDateAsEpoch);
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.etag = str4;
        entry.softTtl = j2;
        entry.ttl = entry.softTtl;
        entry.serverDate = parseDateAsEpoch;
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
        String str = map.get(map.get("Content-Type") == null ? "content-type" : "Content-Type");
        if (str == null) {
            return "ISO-8859-1";
        }
        String[] split = str.split(i.b);
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals(HTTP.CHARSET)) {
                return split2[1];
            }
        }
        return "ISO-8859-1";
    }
}
