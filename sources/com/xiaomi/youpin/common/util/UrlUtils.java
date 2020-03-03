package com.xiaomi.youpin.common.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Pair;
import com.alipay.sdk.sys.a;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf >= 0) {
            str = str.substring(lastIndexOf + 1, str.length());
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return (str.endsWith("youpin.mi.com") || str.endsWith("youpin.mi.com/")) ? "main" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r2) {
        /*
            java.lang.String r0 = "?"
            int r0 = r2.indexOf(r0)
            if (r0 < 0) goto L_0x0015
            int r0 = r0 + 1
            int r1 = r2.length()
            if (r0 >= r1) goto L_0x0015
            java.lang.String r2 = r2.substring(r0)
            return r2
        L_0x0015:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.UrlUtils.b(java.lang.String):java.lang.String");
    }

    public static Map<String, String> c(String str) {
        String[] split;
        HashMap hashMap = new HashMap();
        String b = b(str);
        if (!TextUtils.isEmpty(b) && (split = b.split(a.b)) != null && split.length > 0) {
            for (String str2 : split) {
                int indexOf = str2.indexOf("=");
                if (indexOf > 0 && indexOf < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                }
            }
        }
        return hashMap;
    }

    public static Map<String, String> d(String str) {
        HashMap hashMap = new HashMap();
        String b = b(str);
        if (TextUtils.isEmpty(b)) {
            return hashMap;
        }
        String[] split = b.split(a.b);
        if (split.length > 0) {
            for (String str2 : split) {
                int indexOf = str2.indexOf("=");
                if (indexOf > 0 && indexOf < str2.length() - 1) {
                    hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                }
            }
        }
        return hashMap;
    }

    public static Pair<String, HashMap<String, String>> e(@NonNull String str) {
        Matcher matcher = Pattern.compile("(\\w+)=([\\-_.%\\w+]+)").matcher(str);
        HashMap hashMap = new HashMap();
        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                hashMap.put(matcher.group(1), URLDecoder.decode(matcher.group(2)));
            }
        }
        return new Pair<>(f(str), hashMap);
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1, str.length()) : str;
    }
}
