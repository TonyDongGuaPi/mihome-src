package com.xiaomi.smarthome.library.http.util;

import java.util.Map;
import okhttp3.Headers;

public class HeaderUtil {
    public static Headers a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        return Headers.of(map);
    }

    public static void a(String str) throws Exception {
        if (str == null) {
            throw new IllegalArgumentException("name == null");
        } else if (!str.isEmpty()) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt <= 31 || charAt >= 127) {
                    throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header name: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str}));
                }
            }
        } else {
            throw new IllegalArgumentException("name is empty");
        }
    }

    public static void b(String str) throws Exception {
        if (str != null) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt <= 31 || charAt >= 127) {
                    throw new IllegalArgumentException(String.format("Unexpected char %#04x at %d in header value: %s", new Object[]{Integer.valueOf(charAt), Integer.valueOf(i), str}));
                }
            }
            return;
        }
        throw new IllegalArgumentException("value == null");
    }
}
