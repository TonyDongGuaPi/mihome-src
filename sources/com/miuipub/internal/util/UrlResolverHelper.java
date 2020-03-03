package com.miuipub.internal.util;

import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.shop2.util.AppUtil;
import java.util.HashSet;
import java.util.Set;

public class UrlResolverHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8286a = "http";
    private static final String b = "https";
    private static final String c = "mi";
    private static final String d = "mihttp";
    private static final String e = "mihttps";
    private static final String f = "mifb";
    private static final String[] g = {".xiaomi.com", ".mi.com", ".miui.com", ".mipay.com"};
    private static final String[] h = {".duokan.com", ".duokanbox.com"};
    private static final String[] i = {"com.xiaomi.channel", "com.duokan.reader", "com.duokan.hdreader", "com.duokan.fiction", "com.xiaomi.router", "com.xiaomi.smarthome", "com.xiaomi.o2o", AppUtil.MISHOP_PACKAGE_NAME, AppConstants.I, "com.xiaomi.jr.security", "com.xiaomi.loan", "com.xiaomi.loanx", "com.miui.miuibbs", "com.wali.live", "com.mi.live", "com.xiaomi.ab", "com.mfashiongallery.emag"};
    private static Set<String> j = new HashSet();
    private static Set<String> k = new HashSet();

    static {
        j.add(d);
        j.add(e);
        k.add("http");
        k.add("https");
        k.addAll(j);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if ("http".equals(parse.getScheme()) || "https".equals(parse.getScheme())) {
            return b(parse.getHost());
        }
        return false;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String contains : g) {
            if (str.contains(contains)) {
                return true;
            }
        }
        for (String contains2 : h) {
            if (str.contains(contains2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(String str) {
        for (String equals : i) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(String str) {
        return j.contains(str);
    }

    public static Uri e(String str) {
        return Uri.parse(str.substring(c.length()));
    }

    public static String a(Uri uri) {
        String a2 = a(uri, 0, (String) null);
        if (a2 != null) {
            if (k.contains(Uri.parse(a2).getScheme())) {
                return a2;
            }
        }
        return null;
    }

    private static String a(Uri uri, int i2, String str) {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append(f);
        if (i2 == 0) {
            obj = "";
        } else {
            obj = Integer.valueOf(i2);
        }
        sb.append(obj);
        String queryParameter = uri.getQueryParameter(sb.toString());
        return queryParameter != null ? a(uri, i2 + 1, queryParameter) : str;
    }
}
