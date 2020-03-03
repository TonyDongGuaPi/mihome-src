package com.xiaomi.jr.web.staticresource;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.FileUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.smarthome.download.Constants;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class StaticResourceUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1461a = "StaticResourceUtils";
    private static Map<String, String> b = new HashMap();

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public boolean f11079a;
        public InputStream b;
    }

    public static void a(Map<String, String> map) {
        b = map;
    }

    public static Result a(Context context, String str) {
        String str2;
        Result result = new Result();
        String c = c(str);
        if (c != null) {
            str2 = FileUtils.a(context, "static_resource" + File.separator + c);
        } else {
            str2 = null;
        }
        result.f11079a = str2 != null && str2.contains(Constants.m);
        if (str2 == null || !new File(str2).isFile()) {
            return result;
        }
        result.b = FileUtils.d(str2);
        return result;
    }

    private static String c(String str) {
        try {
            String decode = URLDecoder.decode(str);
            String d = d(decode);
            return d == null ? b(decode) : d;
        } catch (Exception e) {
            MifiLog.b(f1461a, "getResourceHostPath fail: " + e.getMessage());
            return null;
        }
    }

    static String a(String str) {
        try {
            String protocol = new URL(str).getProtocol();
            return protocol + "://" + c(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String d(String str) {
        return b.get(b(str));
    }

    static String b(String str) {
        try {
            URL url = new URL(str);
            String host = url.getHost();
            String path = url.getPath();
            if (host == null || path == null) {
                return null;
            }
            return host + path;
        } catch (MalformedURLException unused) {
            MifiLog.b(f1461a, "getHostPath fail: " + str);
            return null;
        }
    }

    private static boolean e(String str) {
        int i;
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0 || (i = lastIndexOf + 1) >= str.length() || !"js".equalsIgnoreCase(str.substring(i))) {
            return true;
        }
        int lastIndexOf2 = str.lastIndexOf(46, lastIndexOf - 1);
        if (lastIndexOf2 < 0) {
            return false;
        }
        String substring = str.substring(lastIndexOf2 + 1, lastIndexOf);
        long currentTimeMillis = System.currentTimeMillis();
        String f = FileUtils.f(str);
        MifiLog.b("TestTime", "get file md5 takes: " + (System.currentTimeMillis() - currentTimeMillis));
        return TextUtils.equals(substring, f);
    }
}
