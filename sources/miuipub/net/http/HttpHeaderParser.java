package miuipub.net.http;

import java.util.Map;
import miuipub.net.http.Cache;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.cybergarage.http.HTTP;

class HttpHeaderParser {
    HttpHeaderParser() {
    }

    public static Cache.Entry a(HttpResponse httpResponse) {
        long j;
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> f = httpResponse.f();
        String str = f.get("date");
        long a2 = str != null ? a(str) : 0;
        String str2 = f.get("cache-control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",");
            int length = split.length;
            j = 0;
            while (i < length) {
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
        String str3 = f.get("expires");
        long a3 = str3 != null ? a(str3) : 0;
        String str4 = f.get("etag");
        if (str4 == null) {
            str4 = null;
        }
        if (i != 0) {
            j2 = currentTimeMillis + (j * 1000);
        } else if (a2 <= 0 || a3 < a2) {
            return null;
        } else {
            j2 = currentTimeMillis + (a3 - a2);
        }
        if (httpResponse.b() == null) {
            return null;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.f2973a = httpResponse.b();
        entry.b = httpResponse.c();
        entry.c = str4;
        entry.h = j2;
        entry.g = j2;
        entry.f = a2;
        entry.i = httpResponse.f();
        return entry;
    }

    public static long a(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0;
        }
    }
}
