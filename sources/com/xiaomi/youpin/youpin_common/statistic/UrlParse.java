package com.xiaomi.youpin.youpin_common.statistic;

import android.text.TextUtils;
import android.util.Pair;
import com.alipay.sdk.sys.a;
import java.net.URLDecoder;
import java.util.HashMap;

public class UrlParse {
    public static Pair<String, HashMap<String, String>> a(String str) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return new Pair<>("", hashMap);
        }
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            int i = indexOf + 1;
            if (i < str.length()) {
                a(str.substring(i), (HashMap<String, String>) hashMap);
            }
            str = str.substring(0, indexOf);
        }
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf >= 0) {
            str = str.substring(lastIndexOf + 1, str.length());
        }
        return new Pair<>(str, hashMap);
    }

    private static void a(String str, HashMap<String, String> hashMap) {
        int i;
        String str2;
        int i2;
        String str3;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(a.b);
            if (split.length > 0) {
                for (String str4 : split) {
                    int indexOf = str4.indexOf(61);
                    if (indexOf > 0 && (i2 = indexOf + 1) < str4.length()) {
                        try {
                            str3 = URLDecoder.decode(str4.substring(i2), "UTF-8");
                        } catch (Exception e) {
                            e.printStackTrace();
                            str3 = "";
                        }
                        if (!TextUtils.isEmpty(str3)) {
                            hashMap.put(str4.substring(0, indexOf), str3);
                        }
                    }
                }
                return;
            }
            int indexOf2 = str.indexOf(61);
            if (indexOf2 > 0 && (i = indexOf2 + 1) < str.length()) {
                try {
                    str2 = URLDecoder.decode(str.substring(i), "UTF-8");
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str2 = "";
                }
                if (!TextUtils.isEmpty(str2)) {
                    hashMap.put(str.substring(0, indexOf2), str2);
                }
            }
        }
    }

    public static String b(String str) {
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

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("?");
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String str3 = str2 + "=";
        int indexOf = str.indexOf(str3);
        if (indexOf < 0) {
            return "";
        }
        String substring = str.substring(indexOf + str3.length());
        int indexOf2 = substring.indexOf(a.b);
        if (indexOf2 < 0) {
            return substring;
        }
        return substring.substring(0, indexOf2);
    }
}
