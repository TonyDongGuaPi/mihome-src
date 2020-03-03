package com.lidroid.xutils.util;

import com.xiaomi.youpin.network.annotation.Encoding;
import java.util.ArrayList;
import java.util.List;

public class CharsetUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6366a = "ISO-8859-1";
    public static final List<String> b = new ArrayList();

    private CharsetUtils() {
    }

    public static String a(String str, String str2, int i) {
        try {
            return new String(str.getBytes(a(str, i)), str2);
        } catch (Throwable th) {
            LogUtils.a(th);
            return str;
        }
    }

    public static String a(String str, int i) {
        for (String next : b) {
            if (b(str, next, i)) {
                return next;
            }
        }
        return "ISO-8859-1";
    }

    public static boolean b(String str, String str2, int i) {
        try {
            if (str.length() > i) {
                str = str.substring(0, i);
            }
            return str.equals(new String(str.getBytes(str2), str2));
        } catch (Throwable unused) {
            return false;
        }
    }

    static {
        b.add("ISO-8859-1");
        b.add("GB2312");
        b.add(Encoding.GBK);
        b.add("GB18030");
        b.add("US-ASCII");
        b.add("ASCII");
        b.add("ISO-2022-KR");
        b.add("ISO-8859-2");
        b.add("ISO-2022-JP");
        b.add("ISO-2022-JP-2");
        b.add("UTF-8");
    }
}
