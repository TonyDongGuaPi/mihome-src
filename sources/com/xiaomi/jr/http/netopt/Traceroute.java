package com.xiaomi.jr.http.netopt;

import android.text.TextUtils;
import java.util.LinkedHashMap;
import java.util.Map;

public class Traceroute {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10839a = "trace_end";
    private static final String b = "*";

    public static Map<String, Float> a(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 1;
        while (true) {
            String a2 = a(str, i);
            if (TextUtils.equals(a2, f10839a)) {
                return linkedHashMap;
            }
            if (a2 != null) {
                if (TextUtils.equals(a2, "*")) {
                    linkedHashMap.put(a2, Float.valueOf(0.0f));
                } else {
                    linkedHashMap.put(a2, Float.valueOf(Ping.a(a2, 3, 60).c));
                }
            }
            i++;
        }
    }

    private static String a(String str, int i) {
        String a2 = Ping.a(str, 1, 1, i, (String) null, 1);
        String str2 = "bytes from " + str;
        if (a2 == null) {
            return null;
        }
        if (!a2.startsWith("From ")) {
            return a2.contains(str2) ? f10839a : "*";
        }
        String substring = a2.substring("From ".length(), a2.indexOf(32, "From ".length()));
        return substring.endsWith(":") ? substring.substring(0, substring.length() - 1) : substring;
    }
}
