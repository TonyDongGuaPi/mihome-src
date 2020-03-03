package com.xiaomi.youpin.cookie;

import android.text.TextUtils;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class YouPinCookieHandler extends CookieHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23310a = "Set-cookie";
    private static final String b = "Set-cookie2";
    private static final String c = "Cookie";

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > 31 && charAt < 127) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private static boolean b(String str) {
        return "Set-cookie".equalsIgnoreCase(str) || "Set-cookie2".equalsIgnoreCase(str);
    }

    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        String b2 = YouPinCookieManager.a().b(uri.toString());
        if (TextUtils.isEmpty(b2)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(a(b2)));
    }

    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        String uri2 = uri.toString();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (str != null && b(str)) {
                YouPinCookieManager.a().a(uri2, (List<String>) (List) next.getValue());
            }
        }
    }
}
