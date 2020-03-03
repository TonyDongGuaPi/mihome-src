package com.mi.global.shop.cache;

import android.text.TextUtils;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Utils;
import com.mi.log.LogUtil;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.io.File;
import java.util.regex.Pattern;

public class WebCache {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6895a = "webcache";
    public static final long b = 52428800;
    public static final String c = "webview";
    public static final String d = "webview";
    public static String e = a();
    public static String f = (e + IOUtils.f15883a + LocaleHelper.b + "_version.txt");

    public static String a() {
        File filesDir = ShopApp.g().getFilesDir();
        if (filesDir == null) {
            return "";
        }
        return filesDir.getAbsolutePath() + IOUtils.f15883a + "webview";
    }

    public static void b() {
        if (!TextUtils.isEmpty(e)) {
            long a2 = Utils.Files.a(new File(e));
            LogUtil.b(f6895a, "offline html5 cache size: " + a2 + " byte");
            if (a2 > 52428800) {
                Utils.Files.b(new File(e));
            }
        }
    }

    public static void c() {
        if (!TextUtils.isEmpty(e) && !new File(f).exists()) {
            Utils.Files.b(new File(e));
            Utils.Files.b(ShopApp.g().getAssets(), "webview", e);
        }
    }

    public static String[] a(String str, boolean z) {
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("/css");
        if (indexOf != -1) {
            return new String[]{"text/css", e + str.substring(indexOf, str.length()).replaceAll(Pattern.quote("?"), JSMethod.NOT_SET)};
        }
        int indexOf2 = str.indexOf("/js");
        if (indexOf2 != -1) {
            return new String[]{"text/javascript", e + str.substring(indexOf2, str.length()).replaceAll(Pattern.quote("?"), JSMethod.NOT_SET)};
        } else if (!z) {
            return null;
        } else {
            String[] split = str.split(ConnectionHelper.e());
            if (split.length != 2) {
                return null;
            }
            String str2 = split[1];
            int indexOf3 = str2.indexOf(63);
            if (indexOf3 != -1) {
                str2 = str2.substring(0, indexOf3);
            }
            if (str2.endsWith("/")) {
                str2 = str2.substring(0, str2.length() - 1) + Constants.m;
            }
            return new String[]{NanoHTTPD.c, e + "/" + str2};
        }
    }
}
